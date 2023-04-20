package ru.fllcker.resolvio.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.resolvio.comparators.QuestionsComparator;
import ru.fllcker.resolvio.dto.NewQuestionDto;
import ru.fllcker.resolvio.models.Question;
import ru.fllcker.resolvio.models.User;
import ru.fllcker.resolvio.repositories.IQuestionsRepository;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionsService {
    private final IQuestionsRepository questionsRepository;
    private final UsersService usersService;

    public Question create(String email, NewQuestionDto newQuestion) {
        User creator = usersService.findByEmail(email);

        Question question = Question.builder()
                .title(newQuestion.getTitle())
                .description(newQuestion.getDescription())
                .creator(creator)
                .build();

        questionsRepository.save(question);
        return question;
    }

    public Question findById(Long id) {
        return questionsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found!"));
    }

    public void deleteById(String email, Long id) {
        Question question = findById(id);

        if (!question.getCreator().getEmail().equals(email))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No access!");

        questionsRepository.deleteById(id);
    }

    public List<Question> searchQuestions(List<String> keywords) {
        if (keywords.size() < 1)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong keywords!");

        String keyword = keywords.get(0);
        List<Question> questions = questionsRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
        questions.sort(new QuestionsComparator(keywords));

        return questions;
    }
}
