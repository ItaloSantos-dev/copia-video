package com.italo.copiavideo.infra.external;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.VideoListResponse;
import com.italo.copiavideo.DTO.youtube.VideoDTO;
import com.italo.copiavideo.DTO.youtube.VideoSearchDTO;
import com.italo.copiavideo.exceptions.ResourceNotFoundException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class YoutubeAPI {

    private String apiKey = System.getenv("YOUTUBE_API_KEY");
    ObjectMapper mapper = new ObjectMapper();

    private final YouTube youTube = new YouTube.Builder(
            new NetHttpTransport(), JacksonFactory.getDefaultInstance(), request ->{}
    ).setApplicationName("copia-video-app").build();


    public List<VideoSearchDTO> getVideos(String search){
        try{
            YouTube.Search.List request = youTube.search().list(List.of("snippet"));

            request.setQ(search);
            request.setType(List.of("video"));
            request.setMaxResults(10L);
            request.setVideoDuration("medium");
            request.setKey(apiKey);

            List<SearchResult> result =   request.execute().getItems();



            List<VideoSearchDTO> response = mapper.readValue(result.toString(), new TypeReference<List<VideoSearchDTO>>() {});

            return response;

        }catch (IOException exception){
            throw  new RuntimeException(exception.getMessage());
        }
    }

    public VideoDTO getVideoById(String id){
        try{
            YouTube.Videos.List request = youTube.videos().list(List.of("snippet", "statistics"));

            request.setId(List.of(id));
            request.setKey(apiKey);

            VideoListResponse result = request.execute();
            if(result.getItems().isEmpty()) throw new ResourceNotFoundException("video", id);
            VideoDTO response = mapper.readValue(result.getItems().get(0).toString(), VideoDTO.class);
            return  response;

        }catch (IOException exception){
            throw  new RuntimeException(exception.getMessage());
        }
    }
}
