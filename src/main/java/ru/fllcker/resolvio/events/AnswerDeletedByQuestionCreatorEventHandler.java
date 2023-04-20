package ru.fllcker.resolvio.events;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.fllcker.resolvio.models.Notification;
import ru.fllcker.resolvio.models.User;
import ru.fllcker.resolvio.services.NotificationsService;

@Component
@RequiredArgsConstructor
public class AnswerDeletedByQuestionCreatorEventHandler {
    private final NotificationsService notificationsService;

    @EventListener
    public void handleAnswerDeletedByQuestionCreatorEvent(AnswerDeletedByQuestionCreatorEvent event) {
        User answerCreator = event.getAnswer().getCreator();
        User questionCreator = event.getAnswer().getQuestion().getCreator();

        Notification notification = Notification.builder()
                .user(answerCreator)
                .text(String.format("User %s deleted your answer to his question.", questionCreator.getName()))
                .build();

        notificationsService.save(notification);

        // ws send
    }
}
