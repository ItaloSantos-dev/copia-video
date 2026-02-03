package com.italo.copiavideo.service;

import com.italo.copiavideo.DTO.request.CreateIdeaDTO;
import com.italo.copiavideo.model.Idea;
import com.italo.copiavideo.model.User;
import com.italo.copiavideo.repository.IdeaRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class IdeaService {

    @Autowired
    private IdeaRepository ideaRepository;

    @Autowired
    private EntityManager entityManager;

    public Idea createIdea(CreateIdeaDTO request){
        User user = this.entityManager.getReference(User.class, request.user_id());

        Idea newIdea = new Idea(user, request.link_video(), request.annotations(), request.title());

        return this.ideaRepository.save(newIdea);
    }
}
