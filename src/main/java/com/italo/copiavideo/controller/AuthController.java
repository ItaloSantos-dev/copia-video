package com.italo.copiavideo.controller;

import com.italo.copiavideo.DTO.request.LoginUserDTO;
import com.italo.copiavideo.DTO.request.RegisterUserDTO;
import com.italo.copiavideo.model.User;
import com.italo.copiavideo.service.AuthService;
import com.italo.copiavideo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterUserDTO registerUserDTO){

        return ResponseEntity.ok(this.authService.register(registerUserDTO));
    }

    @PostMapping("login")
    public ResponseEntity register(@RequestBody LoginUserDTO loginUserDTO){
        Authentication auth = this.authService.login(loginUserDTO);
        String token = this.tokenService.createToken( (User) auth.getPrincipal());

        return ResponseEntity.ok(token);
    }
}
