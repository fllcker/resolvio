package ru.fllcker.resolvio.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fllcker.resolvio.models.Notification;
import ru.fllcker.resolvio.repositories.INotificationsRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationsService {
    private final INotificationsRepository notificationsRepository;

    public Notification save(Notification notification) {
        return notificationsRepository.save(notification);
    }
}
