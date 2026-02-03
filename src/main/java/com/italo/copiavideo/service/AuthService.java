package com.italo.copiavideo.service;

import com.italo.copiavideo.DTO.request.RegisterUserDTO;
import com.italo.copiavideo.model.User;
import com.italo.copiavideo.model.enums.RoleUser;
import com.italo.copiavideo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;


    public User register(RegisterUserDTO request){
        if(userRepository.existsByEmail(request.email())) throw new RuntimeException("Deu ruin");

        User newUser = new User(request.name(), request.email(), request.password(), RoleUser.USER);

        return this.userRepository.save(newUser);
    }
}
