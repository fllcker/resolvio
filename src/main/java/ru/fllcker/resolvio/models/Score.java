package ru.fllcker.resolvio.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import ru.fllcker.resolvio.models.enums.ScoreTarget;
import ru.fllcker.resolvio.models.enums.ScoreType;

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

    @Builder.Default
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
