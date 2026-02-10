package com.italo.copiavideo.service;

import com.italo.copiavideo.DTO.request.CreateIdeaDTO;
import com.italo.copiavideo.DTO.request.UpdateIdeaDTO;
import com.italo.copiavideo.exceptions.ResourceAlreadyExitsException;
import com.italo.copiavideo.exceptions.ResourceNotFoundException;
import com.italo.copiavideo.model.Idea;
import com.italo.copiavideo.model.User;
import com.italo.copiavideo.model.enums.RoleUser;
import com.italo.copiavideo.repository.IdeaRepository;
import com.italo.copiavideo.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class IdeaServiceTest {

    @InjectMocks
    IdeaService ideaService;

    @Mock
    IdeaRepository ideaRepository;


    private User createUser(){
        UUID idUser = UUID.randomUUID();
        return new User(idUser, "italo", idUser +"@.com", "1131", RoleUser.USER);
    }

    private Idea createIdea(User user){
        UUID idIdea = UUID.randomUUID();
        return  new Idea(idIdea, "aa", "aa", "aa", user);
    }

    @Test
    @DisplayName("The user is trying to find an idea of yours.")
    void getIdeaByIdCase1() {
        User user = createUser();

        Idea idea = createIdea(user);

        Mockito.when(this.ideaRepository.findById(idea.getId())).thenReturn(Optional.of(idea));

        Idea result = this.ideaService.getIdeaById(idea.getId().toString(), user);

        Assertions.assertEquals(result, idea);

    }

    @Test
    @DisplayName("The user is trying to find an idea from another user")
    void getIdeaByIdCase2(){
        User user1 = createUser();
        Idea idea = createIdea(user1);

        User user2 = createUser();
        Mockito.when(this.ideaRepository.findById(idea.getId())).thenReturn(Optional.of(idea));

        Assertions.assertThrows(ResourceNotFoundException.class, ()->{this.ideaService.getIdeaById(idea.getId().toString(), user2);});
    }


    @Test
    @DisplayName("Creating a idea with title already exists")
    void createIdea() {
        User user = createUser();
        Idea idea = createIdea(user);
        CreateIdeaDTO createIdeaDTO = new CreateIdeaDTO(idea.getTitle(), idea.getAnnotations(), idea.getVideo_id());

        Mockito.when(this.ideaRepository.existsByTitleAndUserId(idea.getTitle(), user.getId())).thenReturn(true);

        assertThrows(ResourceAlreadyExitsException.class, () -> {this.ideaService.createIdea(createIdeaDTO, user);});

    }

    @Test
    void updateIdea() {
        User user1 = createUser();
        User user2 = createUser();
        Idea idea = createIdea(user1);

        UpdateIdeaDTO updateIdeaDTO = new UpdateIdeaDTO("aapo", "aak");

        Mockito.when(this.ideaRepository.findById(idea.getId())).thenReturn(Optional.of(idea));

        assertThrows(ResourceNotFoundException.class, ()-> {this.ideaService.updateIdea(idea.getId(), updateIdeaDTO, user2);});
    }

    @Test
    @DisplayName("The idea is not of the user")
    void deleteIdea() {
        User user1 = createUser();
        User user2 = createUser();
        Idea idea = createIdea(user1);

        Mockito.when(this.ideaRepository.findById(idea.getId())).thenReturn(Optional.of(idea));

        assertThrows(ResourceNotFoundException.class, ()-> {
            this.ideaService.deleteIdea(idea.getId().toString(), user2);
        });

    }

}