package com.italo.copiavideo.infra.internal;


import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import com.italo.copiavideo.infra.internal.ports.IaAPI;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;


@Component
public class GeminiApi implements IaAPI {

    private final String API_KEY = System.getenv("GEMINI_API_KEY");


    private final Client client;

    public GeminiApi (){
        this.client = Client.builder().apiKey(API_KEY).build();
    }

    @Override
    public String getResponse(String prompt) {
        GenerateContentResponse response = client.models.generateContent("gemini-2.0-flash", prompt, null);
        return response.text();
    }
}
