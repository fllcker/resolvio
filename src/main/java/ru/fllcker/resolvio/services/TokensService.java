package ru.fllcker.resolvio.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.resolvio.models.Token;
import ru.fllcker.resolvio.repositories.ITokensRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class TokensService {
    private final ITokensRepository tokensRepository;

    public Token save(Token token) {
        return tokensRepository.save(token);
    }

    public Token findByValue(String value) {
        return tokensRepository.findByValue(value)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Token not found!"));
    }
}
