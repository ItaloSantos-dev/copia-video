package com.italo.copiavideo.repository;

import com.italo.copiavideo.DTO.internal.UserWithAmountIdeasDTO;
import com.italo.copiavideo.DTO.response.UserDTO;
import com.italo.copiavideo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    UserDetails findByEmail(String email);
    boolean existsByEmail(String email);

    List<User> findAllByOrderByCreatedAtAsc();

    @Query("SELECT new com.italo.copiavideo.DTO.internal.UserWithAmountIdeasDTO(u,COUNT(i)) FROM User u  JOIN u.ideas i GROUP BY u ORDER BY COUNT(i) ASC")
    List<UserWithAmountIdeasDTO> findUsersWithMostIdeas();

}
