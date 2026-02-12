package com.italo.copiavideo.service;
import com.italo.copiavideo.DTO.youtube.VideoDTO;
import com.italo.copiavideo.DTO.youtube.VideoSearchDTO;
import com.italo.copiavideo.infra.external.YoutubeAPI;
import com.italo.copiavideo.service.report.metrics.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class YoutubeService {

    @Autowired
    private SearchService searchService;

    private YoutubeAPI youtubeAPI;

    public YoutubeService() {
        this.youtubeAPI = new YoutubeAPI();
    }

    public List<VideoSearchDTO> getVideosBySearch(String search){
        this.searchService.saveSearch(search);
        return this.youtubeAPI.getVideos(search);
    }

    public VideoDTO getVideoById(String id){
        return this.youtubeAPI.getVideoById(id);
    }
}
