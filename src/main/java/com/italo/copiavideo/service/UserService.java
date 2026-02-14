package com.italo.copiavideo.service;

import com.italo.copiavideo.DTO.internal.UserWithAmountIdeasDTO;
import com.italo.copiavideo.DTO.response.UserDTO;
import com.italo.copiavideo.model.User;
import com.italo.copiavideo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(username);
    }

    public List<User> getAall(){
        return  this.userRepository.findAll();
    }

    public User getUserByEmail(String email){
        return (User)this.userRepository.findByEmail(email);
    }

    public User verifyIfUserIsBannid(User user){
        if(user.getName().equals("italo")) throw new RuntimeException("toraram o canenco de quem ta lendo");
        else {
            return user;
        }
    }

    public Long getUsersCount(){
        return this.userRepository.count();
    }

    public List<User> getLastedCreateusers(){
        return this.userRepository.findAllByOrderByCreatedAtAsc();
    }

    public List<UserWithAmountIdeasDTO> getUsersWithMostAmountIdeas(){
        return this.userRepository.findUsersWithMostIdeas();
    }

}
