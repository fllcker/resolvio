package ru.fllcker.resolvio.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.fllcker.resolvio.models.Notification;
import ru.fllcker.resolvio.services.AuthService;
import ru.fllcker.resolvio.services.NotificationsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/notifications")
public class NotificationsController {
    private final AuthService authService;
    private final NotificationsService notificationsService;

    @GetMapping
    public Page<Notification> findByUser(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                         @RequestParam(value = "limit", defaultValue = "5") Integer limit) {
        return notificationsService.findByUser(authService.getAuthInfo().getEmail(), offset, limit);
    }
}
