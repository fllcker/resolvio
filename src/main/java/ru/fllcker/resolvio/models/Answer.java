package ru.fllcker.resolvio.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    private LocalDateTime createdTime = LocalDateTime.now();

    private String body;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    @JsonManagedReference
    private User creator;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    @JsonManagedReference
    private Question question;

    @OneToMany(mappedBy = "answer")
    @JsonBackReference
    private List<Score> scores;
}
