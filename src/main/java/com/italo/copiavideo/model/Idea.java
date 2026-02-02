package com.italo.copiavideo.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "ideas")
public class Idea {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column()
    private String annotations;

    @Column(nullable = false)
    private String link_video;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
