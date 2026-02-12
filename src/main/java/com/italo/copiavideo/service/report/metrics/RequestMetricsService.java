package com.italo.copiavideo.service.report.metrics;

import com.italo.copiavideo.model.report.RequestMetric;
import com.italo.copiavideo.repository.RequestMetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Service
public class RequestMetricsService {
    //ver se existe valor nessa key

    // se existir, salvar ou incrementar no bd

    //criar ou incrementar cache

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RequestMetricRepository requestMetricRepository;

    public void saveTheRequest(String route, String method){
        String key = "requests::"+route + "-" + method;
        var value = this.redisTemplate.opsForValue().get(key);
        Long ttl = this.redisTemplate.getExpire(key);
        boolean valueIsNull =  value == null;

        if(!valueIsNull && Integer.parseInt(value)==5){
            RequestMetric newRequestMetric = new RequestMetric(route, method, 5, LocalDate.now());
            this.requestMetricRepository.save(newRequestMetric);
        }
        else if(!valueIsNull && Integer.parseInt(value)>5){
            RequestMetric requestMetric = this.requestMetricRepository.findByRouteAndMethodAndDate(route, method, LocalDate.now());
            requestMetric.setQuantity(Integer.parseInt(value));
        }
        this.redisTemplate.opsForValue().increment(key);
        if (ttl==-1){
            this.redisTemplate.expire(key, Duration.ofHours(24));
        }


    }

    public List<RequestMetric> getAllRequestMetrics(){
        return this.requestMetricRepository.findAll();
    }

    public List<RequestMetric> getAllRequestMetricsBetweenDay(LocalDate initialDate, LocalDate finalDate){
        return this.requestMetricRepository.findByDateBetween(initialDate, finalDate);
    }
}
