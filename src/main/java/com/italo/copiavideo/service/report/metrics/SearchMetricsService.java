package com.italo.copiavideo.service.report.metrics;

import com.italo.copiavideo.model.report.SearchMetric;
import com.italo.copiavideo.repository.SearchMetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class SearchMetricsService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private SearchMetricRepository searchMetricRepository;


    private final Set<String> stopWords = Set.of("a", "e", "o", "as", "os", "de", "do", "da", "em", "no", "na", "um", "se", "me", "te", "eu", "tu", "há", "ir", "já", "só", "oi", "ai", "que", "com", "por", "até", "mas", "nem", "seu", "teu", "meu", "nos", "vos", "ele", "foi", "era", "sou", "tem", "vai", "ver", "dar", "não", "sim", "bem", "mal", "uns", "das", "dos", "nas", "aos", "pro", "pra", "via");

    public void saveSearch(String search){
        String searchClean = search.toLowerCase().trim().replaceAll("[^a-zA-ZÀ-ÿ ]", "");
        String[] tokens = searchClean.split("\\s+");

        for (String token:tokens){
            if (!stopWords.contains(token) && token.length()>=3 ){
                String key ="searchs::"+token;
                var value = this.redisTemplate.opsForValue().get(key);
                boolean valueIsNull = value==null;

                if(!valueIsNull && Integer.parseInt(value)==5){
                    SearchMetric newSearchMetris = new SearchMetric(token, 5, LocalDate.now());
                    this.searchMetricRepository.save(newSearchMetris);
                    System.out.println("salvou");
                }
                else if(!valueIsNull && Integer.parseInt(value)>5){
                    SearchMetric searchMetrics = this.searchMetricRepository.findBySearchAndDate(token ,LocalDate.now());
                    searchMetrics.setQuantity(Integer.parseInt(value));
                    this.searchMetricRepository.save(searchMetrics);
                    System.out.println("editou");
                }

                this.redisTemplate.opsForValue().increment(key);
                Long ttl = redisTemplate.getExpire(key);
                if (ttl== -1){
                    redisTemplate.expire(key, Duration.ofHours(24));
                }
            }
        }
    }

    public List<SearchMetric> getAllSearchMetrics(){
        return this.searchMetricRepository.findAll();
    }

    public List<SearchMetric> getAllSearchMetricsBetweenDay(LocalDate initialDate, LocalDate finalDate){
        return this.searchMetricRepository.findAllSearchsByDateBetween(initialDate,finalDate);
    }
}
