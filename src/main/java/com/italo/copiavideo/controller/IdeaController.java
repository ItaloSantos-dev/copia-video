package com.italo.copiavideo.controller;

import com.auth0.jwt.JWT;
import com.italo.copiavideo.DTO.request.CreateIdeaDTO;
import com.italo.copiavideo.DTO.request.UpdateIdeaDTO;
import com.italo.copiavideo.DTO.response.IdeaDTO;
import com.italo.copiavideo.model.Idea;
import com.italo.copiavideo.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ideas")
@CrossOrigin(origins = "*")

public class IdeaController {

    @Autowired
    private IdeaService ideaService;

    @GetMapping("/all")
    public ResponseEntity<List<IdeaDTO>> getAllIdeas(){
        List<Idea> ideas = this.ideaService.getAllIdeas();

        List<IdeaDTO> response = ideas.stream().map( idea -> new IdeaDTO(idea.getId(), idea.getTitle(), idea.getLink_video(), idea.getAnnotations(), idea.getUser().getName())).toList();

        return ResponseEntity.ok(response);
    }

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
