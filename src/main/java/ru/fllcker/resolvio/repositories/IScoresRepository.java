package ru.fllcker.resolvio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.resolvio.models.Score;

@Repository
public interface IScoresRepository extends JpaRepository<Score, Long> {
}
