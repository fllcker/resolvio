package ru.fllcker.resolvio.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.resolvio.dto.NewAnswerDto;
import ru.fllcker.resolvio.models.Answer;
import ru.fllcker.resolvio.services.AnswersService;
import ru.fllcker.resolvio.services.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/answers")
public class AnswersController {
    private final AuthService authService;
    private final AnswersService answersService;

    @PostMapping("{questionId}")
    public ResponseEntity<Answer> create(@PathVariable Long questionId, @RequestBody @Valid NewAnswerDto newAnswer, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());

        Answer answer = answersService.create(authService.getAuthInfo().getEmail(), questionId, newAnswer);
        return ResponseEntity.ok(answer);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Answer> findById(@PathVariable Long id) {
        Answer answer = answersService.findById(id);
        return ResponseEntity.ok(answer);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        answersService.deleteById(authService.getAuthInfo().getEmail(), id);
        return ResponseEntity.ok().build();
    }
}
