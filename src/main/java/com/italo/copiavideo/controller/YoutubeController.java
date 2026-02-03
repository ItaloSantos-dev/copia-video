package com.italo.copiavideo.controller;

import com.italo.copiavideo.DTO.youtube.VideoDTO;
import com.italo.copiavideo.DTO.youtube.VideoSearchDTO;
import com.italo.copiavideo.service.YoutubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/yt")
public class YoutubeController {

    @Autowired
    private YoutubeService youtubeService;

    @GetMapping
    public ResponseEntity<List<VideoSearchDTO>> getVideos(@RequestParam String search){
        return ResponseEntity.ok(this.youtubeService.getVideosBySearch(search));
    }

    @GetMapping("/video/{id}")
    public ResponseEntity<VideoDTO> getVideo(@PathVariable String id){
        return ResponseEntity.ok(this.youtubeService.getVideoById(id));
    }



}
