package com.italo.copiavideo.service;

import com.italo.copiavideo.DTO.request.CreateIdeaDTO;
import com.italo.copiavideo.DTO.request.UpdateIdeaDTO;
import com.italo.copiavideo.exceptions.ResourceAlreadyExitsException;
import com.italo.copiavideo.exceptions.ResourceNotFoundException;
import com.italo.copiavideo.model.Idea;
import com.italo.copiavideo.model.User;
import com.italo.copiavideo.repository.IdeaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

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

    public List<Idea> getMyIdeas(User user){
        return this.ideaRepository.findByUser_id(user.getId());
    }

    public Idea createIdea(CreateIdeaDTO request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Idea ideaModel = new Idea(user, request.video_id(), request.annotations(), request.title());

        try {
            Idea newIdea = this.ideaRepository.save(ideaModel);
            return newIdea;
        }catch (DataIntegrityViolationException e){
            throw new ResourceAlreadyExitsException("ideia", "título");
        }
    }

    public Idea updateIdea(UUID id, UpdateIdeaDTO request){
        if( !this.ideaRepository.existsById(id)) ;
        Idea idea = this.entityManager.getReference(Idea.class, id);

        idea.setTitle(request.title());
        idea.setAnnotations(request.annotations());

        return this.ideaRepository.save(idea);
    }

    public void deleteIdea(String id){
        if (!this.ideaRepository.existsById(UUID.fromString(id))) throw new ResourceNotFoundException("ideia", id.toString());
        this.ideaRepository.deleteById(UUID.fromString(id));
    }

    public Idea getIdeaById(String id, User user){

        Idea idea = this.ideaRepository.findById(UUID.fromString(id)).get();

        if(!idea.getUser().getId().equals(user.getId())){
            throw  new RuntimeException("nao é o memo user");
        }
        return idea;
    }
}
