package com.italo.copiavideo.service;

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

    @Mock
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("The user is trying to find an idea of \u200B\u200Byours.")
    void getIdeaByIdCase1() {
        UUID idUser = UUID.randomUUID();
        User user1 = new User(idUser, "italo", "italo@.com", "1131", RoleUser.USER);

        UUID idIdea = UUID.randomUUID();
        Idea idea = new Idea(idIdea, "aa", "aa", "aa", user1);


        Mockito.when(this.ideaRepository.findById(idea.getId())).thenReturn(Optional.of(idea));

        Idea result = this.ideaService.getIdeaById(idea.getId().toString(), user1);

        Assertions.assertEquals(result, idea);

    }

    @Test
    @DisplayName("The user is trying to find an idea from another user")
    void getIdeaByIdCase2(){
        UUID idUser = UUID.randomUUID();
        User user1 = new User(idUser, "italo", "italo@.com", "1131", RoleUser.USER);

        UUID idIdea = UUID.randomUUID();
        Idea idea = new Idea(idIdea, "aa", "aa", "aa", user1);


        UUID idUser2 = UUID.randomUUID();
        User user2 = new User(idUser2, "italo", "italo2@.com", "1131", RoleUser.USER);

        Mockito.when(this.ideaRepository.findById(idea.getId())).thenReturn(Optional.of(idea));

        Assertions.assertThrows(RuntimeException.class, ()->{this.ideaService.getIdeaById(idea.getId().toString(), user2);});
    }


}