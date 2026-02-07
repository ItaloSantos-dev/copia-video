package com.italo.copiavideo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "ideas",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "title"})
    }
)
public class Idea {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column()
    private String annotations;

    @Column(nullable = false)
    private String video_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Idea() {
    }

    public Idea(User user, String video_id, String annotations, String title) {
        this.annotations = annotations;
        this.title = title;
        this.user = user;
        this.video_id = video_id;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnnotations() {
        return annotations;
    }

    public void setAnnotations(String annotations) {
        this.annotations = annotations;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setLink_video(String link_video) {
        this.video_id = link_video;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
