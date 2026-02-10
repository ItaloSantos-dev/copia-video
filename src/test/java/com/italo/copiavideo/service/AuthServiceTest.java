package com.italo.copiavideo.service;

import com.italo.copiavideo.DTO.request.RegisterUserDTO;
import com.italo.copiavideo.exceptions.UserAlreadyRegisterException;
import com.italo.copiavideo.model.User;
import com.italo.copiavideo.model.enums.RoleUser;
import com.italo.copiavideo.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    AuthService authService;

    @Mock
    PasswordEncoder encoder;

    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("Email already cadaster in application")
    void registerCase1() {

        RegisterUserDTO registerUserDTO = new RegisterUserDTO("italo", "italo", "1131");

        Mockito.when(this.userRepository.existsByEmail(registerUserDTO.email())).thenReturn(true);


        assertThrows(UserAlreadyRegisterException.class, () -> {
            this.authService.register(registerUserDTO, encoder);
        });
    }

    @Test
    @DisplayName("Verify if password of user is encoding")
    void registerCase2(){


        RegisterUserDTO registerUserDTO = new RegisterUserDTO("italo", "italo", "1131");


        Mockito.when(this.userRepository.existsByEmail(registerUserDTO.email())).thenReturn(false);

        Mockito.when(this.encoder.encode(registerUserDTO.password())).thenReturn("senha-criptografada");

        Mockito.when(this.userRepository.save(Mockito.any(User.class))).thenAnswer(
                invocation -> invocation.getArgument(0));


        User response = this.authService.register(registerUserDTO, encoder);

        assertEquals("senha-criptografada", response.getPassword());

    }
}