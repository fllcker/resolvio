package ru.fllcker.resolvio.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SearchQuestionsDto {
    private List<String> keywords;
}
