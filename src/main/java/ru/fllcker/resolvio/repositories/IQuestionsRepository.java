package ru.fllcker.resolvio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.resolvio.models.Question;

import java.util.List;


@Repository
public interface IQuestionsRepository extends JpaRepository<Question, Long> {
    List<Question> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);

    // for statistics
    Long countDistinctByCreatorIsNotNull();
}
