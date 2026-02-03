package com.italo.copiavideo.service;
import com.italo.copiavideo.DTO.youtube.VideoDTO;
import com.italo.copiavideo.DTO.youtube.VideoSearchDTO;
import com.italo.copiavideo.infra.external.YoutubeAPI;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class YoutubeService {

    private YoutubeAPI youtubeAPI;

    public YoutubeService() {
        this.youtubeAPI = new YoutubeAPI();
    }

    public List<VideoSearchDTO> getVideos(String theme){
        return this.youtubeAPI.getVideos(theme);
    }


}
