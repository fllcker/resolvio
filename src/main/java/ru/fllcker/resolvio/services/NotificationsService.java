package ru.fllcker.resolvio.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.fllcker.resolvio.models.Notification;
import ru.fllcker.resolvio.models.User;
import ru.fllcker.resolvio.repositories.INotificationsRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationsService {
    private final INotificationsRepository notificationsRepository;
    private final UsersService usersService;

    public Notification save(Notification notification) {
        return notificationsRepository.save(notification);
    }

    public Page<Notification> findByUser(String email, Integer offset, Integer limit) {
        User user = usersService.findByEmail(email);
        return notificationsRepository.findNotificationsByUser(user, PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, "id")));
    }
}
