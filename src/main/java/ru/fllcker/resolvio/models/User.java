package ru.fllcker.resolvio.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    private LocalDateTime createdTime = LocalDateTime.now();

    private String email;

    private String password;

    private String name;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Token> tokens;

    @OneToMany(mappedBy = "creator")
    @JsonBackReference
    private List<Question> questions;

    @OneToMany(mappedBy = "creator")
    @JsonBackReference
    private List<Answer> answers;

    @OneToMany(mappedBy = "creator")
    @JsonBackReference
    private List<Score> scores;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Notification> notifications;
}
