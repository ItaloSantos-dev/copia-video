package com.italo.copiavideo.controller;

import com.italo.copiavideo.DTO.youtube.VideoDTO;
import com.italo.copiavideo.DTO.youtube.VideoSearchDTO;
import com.italo.copiavideo.service.YoutubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/yt")
public class YoutubeController {

    @Autowired
    private YoutubeService youtubeService;

    @GetMapping("/{search}")
    public ResponseEntity<List<VideoSearchDTO>> getVideos(@PathVariable String search){
        return ResponseEntity.ok(this.youtubeService.getVideosBySearch(search));
    }

    @GetMapping("/video/{id}")
    public ResponseEntity<VideoDTO> getVideo(@PathVariable String id){
        return ResponseEntity.ok(this.youtubeService.getVideoById(id));
    }



}
