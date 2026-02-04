package com.italo.copiavideo.service;

import com.italo.copiavideo.DTO.request.CreateIdeaDTO;
import com.italo.copiavideo.DTO.request.UpdateIdeaDTO;
import com.italo.copiavideo.model.Idea;
import com.italo.copiavideo.model.User;
import com.italo.copiavideo.repository.IdeaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class IdeaService {

    @Autowired
    private IdeaRepository ideaRepository;

    @Autowired
    private EntityManager entityManager;

    public List<Idea> getAllIdeas(){
        return this.ideaRepository.findAll();
    }

    public List<Idea> getMyIdeas(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.ideaRepository.findByUser_id(user.getId());
    }

    public Idea createIdea(CreateIdeaDTO request){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Idea newIdea = new Idea(user, request.link_video(), request.annotations(), request.title());

        return this.ideaRepository.save(newIdea);
    }

    public Idea updateIdea(UUID id, UpdateIdeaDTO request){
        if( !this.ideaRepository.existsById(id)) throw new RuntimeException("Deu ruin");
        Idea idea = this.entityManager.getReference(Idea.class, id);

        idea.setTitle(request.title());
        idea.setAnnotations(request.annotations());
        idea.setLink_video(request.link_video());

        return this.ideaRepository.save(idea);
    }

    public void deleteIdea(UUID id){
        if (!this.ideaRepository.existsById(id)) throw new RuntimeException("deuruin");
        this.ideaRepository.deleteById(id);
    }
}
