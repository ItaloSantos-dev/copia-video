package com.italo.copiavideo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import tools.jackson.databind.node.ObjectNode;

import java.util.Map;
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

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> drawn;

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public Map<String, Object>  getDrawn() {
        return drawn;
    }

    public void setDrawn(Map<String, Object>  drawn) {
        this.drawn = drawn;
    }

    public Idea() {
    }

    public Idea(UUID id, String title, String annotations, String video_id, User user) {
        this.id = id;
        this.title = title;
        this.annotations = annotations;
        this.video_id = video_id;
        this.user = user;
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


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
