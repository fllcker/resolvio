package ru.fllcker.resolvio.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.resolvio.dto.NewAnswerDto;
import ru.fllcker.resolvio.events.AnswerCreatedEvent;
import ru.fllcker.resolvio.events.AnswerDeletedByQuestionCreatorEvent;
import ru.fllcker.resolvio.models.Answer;
import ru.fllcker.resolvio.models.Question;
import ru.fllcker.resolvio.models.User;
import ru.fllcker.resolvio.repositories.IAnswersRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class AnswersService {
    private final ApplicationEventPublisher eventPublisher;
    private final IAnswersRepository answersRepository;
    private final UsersService usersService;
    private final QuestionsService questionsService;

    public Answer create(String email, Long questionId, NewAnswerDto newAnswer) {
        User creator = usersService.findByEmail(email);
        Question question = questionsService.findById(questionId);

        if (question.getActive().equals(false))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Question is not active!");

        Answer answer = Answer.builder()
                .body(newAnswer.getBody())
                .creator(creator)
                .question(question)
                .build();

        answersRepository.save(answer);
        eventPublisher.publishEvent(new AnswerCreatedEvent(answer));
        return answer;
    }

    public Answer findById(Long id) {
        return answersRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Answer not found!"));
    }

    public void deleteById(String email, Long id) {
        User user = usersService.findByEmail(email);
        Answer answer = findById(id);

        if (!answer.getCreator().equals(user) && !answer.getQuestion().getCreator().equals(user))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No access");

        answersRepository.deleteById(id);

        if (answer.getQuestion().getCreator().equals(user) && !answer.getCreator().equals(user)) {
            // when answer deleted by question creator (not answer creator)
            eventPublisher.publishEvent(new AnswerDeletedByQuestionCreatorEvent(answer));
        }
    }
}
