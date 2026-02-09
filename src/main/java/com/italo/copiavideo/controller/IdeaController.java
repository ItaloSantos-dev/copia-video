package com.italo.copiavideo.controller;

import com.auth0.jwt.JWT;
import com.italo.copiavideo.DTO.request.CreateIdeaDTO;
import com.italo.copiavideo.DTO.request.UpdateIdeaDTO;
import com.italo.copiavideo.DTO.response.IdeaDTO;
import com.italo.copiavideo.DTO.response.IdeaSimplifiedDTO;
import com.italo.copiavideo.model.Idea;
import com.italo.copiavideo.model.User;
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

        List<IdeaDTO> response = ideas.stream().map( idea -> new IdeaDTO(idea.getId(), idea.getTitle(), idea.getVideo_id(), idea.getAnnotations(), idea.getUser().getName())).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<IdeaSimplifiedDTO>> getMyIdeas(@AuthenticationPrincipal User user){
        List<Idea> ideas = this.ideaService.getMyIdeas(user);

        List<IdeaSimplifiedDTO> response = ideas.stream().map(idea ->
                new IdeaSimplifiedDTO(idea.getId(), idea.getTitle(), idea.getVideo_id(), idea.getAnnotations())
        ).toList();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<IdeaDTO> createIdea(@RequestBody CreateIdeaDTO createIdeaDTO){
        System.out.println(createIdeaDTO);
        Idea newIdea = this.ideaService.createIdea(createIdeaDTO);
        return ResponseEntity.ok(new IdeaDTO(newIdea.getId(), newIdea.getTitle(), newIdea.getVideo_id(), newIdea.getAnnotations(), newIdea.getUser().getName()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IdeaDTO> updateIdea(
            @PathVariable UUID id,
            @RequestBody UpdateIdeaDTO updateIdeaDTO){
        Idea idea = this.ideaService.updateIdea(id, updateIdeaDTO);
        return ResponseEntity.ok(new IdeaDTO(idea.getId(), idea.getTitle(), idea.getVideo_id(), idea.getAnnotations(), idea.getUser().getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Idea> deleteIdea(@PathVariable String id){
        System.out.println("id:" + id);
        this.ideaService.deleteIdea(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IdeaDTO> getIdeaById(@PathVariable String id, @AuthenticationPrincipal User user){
        Idea idea = this.ideaService.getIdeaById(id, user);
        return ResponseEntity.ok(new IdeaDTO(idea.getId(), idea.getTitle(), idea.getVideo_id(), idea.getAnnotations(), idea.getUser().getName()));
    }
}
