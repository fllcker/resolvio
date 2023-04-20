package ru.fllcker.resolvio.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewQuestionDto {
    @Size(min = 5, max = 32, message = "Title of question should be greater than 5 letters and less than 32 letters!")
    private String title;

    @Size(min = 5, max = 1024, message = "Title of question should be greater than 5 letters and less than 1024 letters!")
    private String description;
}
