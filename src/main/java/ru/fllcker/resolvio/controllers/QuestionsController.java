package ru.fllcker.resolvio.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.resolvio.dto.NewQuestionDto;
import ru.fllcker.resolvio.dto.SearchQuestionsDto;
import ru.fllcker.resolvio.models.Question;
import ru.fllcker.resolvio.services.AuthService;
import ru.fllcker.resolvio.services.QuestionsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/questions")
public class QuestionsController {
    private final AuthService authService;
    private final QuestionsService questionsService;

    @PostMapping
    public ResponseEntity<Question> create(@RequestBody @Valid NewQuestionDto newQuestion, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());

        Question question = questionsService.create(authService.getAuthInfo().getEmail(), newQuestion);
        return ResponseEntity.ok(question);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Question> findById(@PathVariable Long id) {
        Question question = questionsService.findById(id);
        return ResponseEntity.ok(question);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        questionsService.deleteById(authService.getAuthInfo().getEmail(), id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("search")
    public ResponseEntity<List<Question>> searchQuestions(SearchQuestionsDto searchQuestionsDto) {
        var result = questionsService.searchQuestions(searchQuestionsDto.getKeywords());
        return ResponseEntity.ok(result);
    }
}
