package ru.fllcker.resolvio.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.resolvio.models.Notification;
import ru.fllcker.resolvio.models.User;

@Repository
public interface INotificationsRepository extends JpaRepository<Notification, Long> {
    Page<Notification> findNotificationsByUser(User user, Pageable pageable);
}
