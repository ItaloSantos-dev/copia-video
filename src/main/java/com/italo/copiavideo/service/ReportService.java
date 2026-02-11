package com.italo.copiavideo.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

@Service
public class ReportService {

    private final Set<String> stopWords = Set.of("a", "e", "o", "as", "os", "de", "do", "da", "em", "no", "na", "um", "se", "me", "te", "eu", "tu", "há", "ir", "já", "só", "oi", "ai", "que", "com", "por", "até", "mas", "nem", "seu", "teu", "meu", "nos", "vos", "ele", "foi", "era", "sou", "tem", "vai", "ver", "dar", "não", "sim", "bem", "mal", "uns", "das", "dos", "nas", "aos", "pro", "pra", "via");



    public ArrayList<String> saveSearch(String search){
        String searchClean = search.toLowerCase().trim().replaceAll("[^a-zA-ZÀ-ÿ ]", "");
        String[] tokens = searchClean.split("\\s+");

        ArrayList<String> result = new ArrayList<>();

        for (String token:tokens){
            if (!stopWords.contains(token) && token.length()>=3 ){
                result.add(token);
            }
        }

        return result;

    }
}
