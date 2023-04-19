package ru.fllcker.resolvio.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.resolvio.models.User;
import ru.fllcker.resolvio.repositories.IUsersRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UsersService {
    private final IUsersRepository usersRepository;

    public User save(User user) {
        return usersRepository.save(user);
    }

    public User findById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
    }

    public User findByEmail(String email) {
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
    }

    public boolean existsByEmail(String email) {
        return usersRepository.existsByEmail(email);
    }
}
