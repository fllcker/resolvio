package ru.fllcker.resolvio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.resolvio.models.Question;

@Repository
public interface IQuestionsRepository extends JpaRepository<Question, Long> {
}
