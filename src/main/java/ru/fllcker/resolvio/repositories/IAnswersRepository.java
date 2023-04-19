package ru.fllcker.resolvio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.resolvio.models.Answer;

@Repository
public interface IAnswersRepository extends JpaRepository<Answer, Long> {
}
