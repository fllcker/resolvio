package ru.fllcker.resolvio.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.fllcker.resolvio.models.Answer;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerCreatedEvent {
    private Answer answer;
}
