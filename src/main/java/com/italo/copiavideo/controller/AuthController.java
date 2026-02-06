package com.italo.copiavideo.controller;

import com.italo.copiavideo.DTO.request.LoginUserDTO;
import com.italo.copiavideo.DTO.request.RegisterUserDTO;
import com.italo.copiavideo.DTO.response.UserDTO;
import com.italo.copiavideo.model.User;
import com.italo.copiavideo.service.AuthService;
import com.italo.copiavideo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody RegisterUserDTO registerUserDTO){
        User newUser = this.authService.register(registerUserDTO);
        return ResponseEntity.ok(new UserDTO(newUser.getId(), newUser.getName(), newUser.getEmail(), newUser.getRole()));
    }

    @PostMapping("login")
    public ResponseEntity register(@RequestBody LoginUserDTO loginUserDTO){
        Authentication auth = this.authService.login(loginUserDTO);
        String token = this.tokenService.createToken( (User) auth.getPrincipal());

        return ResponseEntity.ok(token);
    }
}
