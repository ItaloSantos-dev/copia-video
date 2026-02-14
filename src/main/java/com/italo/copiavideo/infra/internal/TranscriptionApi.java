package com.italo.copiavideo.infra.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class TranscriptionApi {
    @Autowired
    private WebClient webClient;

    private final String urlBase = "http://localhost:8000";

    public String getTranscriptVideoById(String videoId){
        return this.webClient.get().uri(urlBase+"/transcript/"+videoId).retrieve().bodyToMono(String.class).block();
    }
}
