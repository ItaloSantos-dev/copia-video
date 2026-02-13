package com.italo.copiavideo.controller;

import com.italo.copiavideo.DTO.response.UserDTO;
import com.italo.copiavideo.model.User;
import com.italo.copiavideo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<User> users = this.userService.getAall();
        List<UserDTO> response = users.stream().map( user -> new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getRole(), user.getIdeas().size(),user.getCreated_at())).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getUsersCount(){
        return ResponseEntity.ok(this.userService.getUsersCount());
    }
}
