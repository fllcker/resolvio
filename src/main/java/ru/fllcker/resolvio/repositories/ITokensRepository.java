package ru.fllcker.resolvio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.resolvio.models.Token;

import java.util.Optional;

@Repository
public interface ITokensRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByValue(String value);
}
