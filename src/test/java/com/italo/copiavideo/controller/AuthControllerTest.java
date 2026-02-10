package com.italo.copiavideo.controller;

import com.italo.copiavideo.DTO.request.LoginUserDTO;
import com.italo.copiavideo.DTO.request.RegisterUserDTO;
import com.italo.copiavideo.DTO.response.UserDTO;
import com.italo.copiavideo.model.User;
import com.italo.copiavideo.model.enums.RoleUser;
import com.italo.copiavideo.service.AuthService;
import com.italo.copiavideo.service.TokenService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @InjectMocks
    AuthController authController;

    @Mock
    AuthService authService;

    @Mock
    PasswordEncoder encoder;

    @Mock
    TokenService tokenService;

    @Test
    @DisplayName("Retur ok for the register")
    void register() {
        UUID userId = UUID.randomUUID();

        User user = new User(userId, "italo", "italo", "1131", RoleUser.USER);

        RegisterUserDTO registerUserDTO = new RegisterUserDTO(user.getName(), user.getEmail(), user.getPassword());

        Mockito.when(this.authService.register(registerUserDTO, this.encoder)).thenReturn(user);

        ResponseEntity response = this.authController.register(registerUserDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assert(response.getBody() instanceof UserDTO);

    }

    @Test
    @DisplayName("Return token of the user authenticated")
    void login() {
        UUID userId = UUID.randomUUID();

        User user = new User(userId, "italo", "italo", "1131", RoleUser.USER);

        LoginUserDTO loginDTO = new LoginUserDTO(user.getEmail(), user.getPassword());

        Mockito.when(this.authService.login(loginDTO))
                .thenReturn(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
        Mockito.when(this.tokenService.createToken(user)).thenReturn("token");

        ResponseEntity response = this.authController.login(loginDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assert(response.getBody() instanceof String);


    }



}