package ru.fllcker.resolvio.events;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.fllcker.resolvio.models.Notification;
import ru.fllcker.resolvio.models.Question;
import ru.fllcker.resolvio.models.User;
import ru.fllcker.resolvio.services.NotificationsService;

@Component
@RequiredArgsConstructor
public class AnswerCreatedEventHandler {
    private final NotificationsService notificationsService;

    @EventListener
    public void handleAnswerCreatedEvent(AnswerCreatedEvent event) {
        User answerCreator = event.getAnswer().getCreator();
        Question question = event.getAnswer().getQuestion();
        User questionCreator = question.getCreator();

        Notification notification = Notification.builder()
                .user(questionCreator)
                .text(String.format("User %s answered your question \"%s\"", answerCreator.getName(), question.getTitle()))
                .build();

        notificationsService.save(notification);

        // ws send
    }
}
