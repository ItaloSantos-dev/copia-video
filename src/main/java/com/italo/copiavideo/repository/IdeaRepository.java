package com.italo.copiavideo.repository;

import com.italo.copiavideo.model.Idea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IdeaRepository extends JpaRepository<Idea, UUID> {
    List<Idea> findByUser_id(UUID user_id);
}
