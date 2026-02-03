package com.italo.copiavideo.controller;

import com.italo.copiavideo.DTO.request.CreateIdeaDTO;
import com.italo.copiavideo.model.Idea;
import com.italo.copiavideo.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ideas")

public class IdeaController {

    @Autowired
    private IdeaService ideaService;

    @PostMapping("/idea")
    public ResponseEntity<Idea> createIdea(@RequestBody CreateIdeaDTO createIdeaDTO){
        return ResponseEntity.ok(this.ideaService.createIdea(createIdeaDTO));
    }
}
