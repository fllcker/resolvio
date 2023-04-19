package ru.fllcker.resolvio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.resolvio.models.Notification;

@Repository
public interface INotificationsRepository extends JpaRepository<Notification, Long> {
}
