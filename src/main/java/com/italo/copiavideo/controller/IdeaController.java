package com.italo.copiavideo.controller;

import com.auth0.jwt.JWT;
import com.italo.copiavideo.DTO.request.CreateIdeaDTO;
import com.italo.copiavideo.DTO.request.UpdateIdeaDTO;
import com.italo.copiavideo.model.Idea;
import com.italo.copiavideo.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ideas")

public class IdeaController {

    @Autowired
    private IdeaService ideaService;

    @GetMapping
    public ResponseEntity<List<Idea>> getMyIdeas(){
        return ResponseEntity.ok(this.ideaService.getMyIdeas());
    }

    @PostMapping
    public ResponseEntity<Idea> createIdea(@RequestBody CreateIdeaDTO createIdeaDTO){
        return ResponseEntity.ok(this.ideaService.createIdea(createIdeaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Idea> updateIdea(
            @PathVariable UUID id,
            @RequestBody UpdateIdeaDTO updateIdeaDTO){
        return ResponseEntity.ok(this.ideaService.updateIdea(id, updateIdeaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Idea> deleteIdea(@PathVariable UUID id){
        this.ideaService.deleteIdea(id);
        return ResponseEntity.ok().build();
    }
}
