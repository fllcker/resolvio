package ru.fllcker.resolvio.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "percentage_statistics")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PercentageStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    private LocalDate date = LocalDate.now();

    private String description;

    private Double percentage;
}
