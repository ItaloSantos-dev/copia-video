package com.italo.copiavideo.infra.external;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchResult;
import com.italo.copiavideo.DTO.youtube.VideoDTO;
import org.springframework.beans.factory.annotation.Value;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class YoutubeAPI {
    @Value("${youtube.api.key}")
    private String apiKey;

    private final YouTube youTube = new YouTube.Builder(
            new NetHttpTransport(), JacksonFactory.getDefaultInstance(), request ->{}
    ).setApplicationName("copia-video-app").build();


    public List<VideoDTO> getVideos(String theme){
        try{
            YouTube.Search.List request = youTube.search().list(List.of("snippet"));

            request.setQ(theme);
            request.setType(List.of("video"));
            request.setMaxResults(10L);
            request.setOrder("viewCount");
            request.setKey(apiKey);

            List<SearchResult> result =   request.execute().getItems();

            ObjectMapper mapper = new ObjectMapper();

            List<VideoDTO> response = mapper.readValue(result.toString(), new TypeReference<List<VideoDTO>>() {});

            return response;

        }catch (IOException exception){
            throw  new RuntimeException(exception.getMessage());
        }
    }
}
