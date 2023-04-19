package ru.fllcker.resolvio.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "scores")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdTime = LocalDateTime.now();

    @Enumerated(value = EnumType.STRING)
    private ScoreType type;

    @Enumerated(value = EnumType.STRING)
    private ScoreTarget target;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    @JsonManagedReference
    private Question question;

    @ManyToOne
    @JoinColumn(name = "answer_id", referencedColumnName = "id")
    @JsonManagedReference
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    @JsonManagedReference
    private User creator;
}
