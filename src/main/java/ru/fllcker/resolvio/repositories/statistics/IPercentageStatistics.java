package ru.fllcker.resolvio.repositories.statistics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.resolvio.models.PercentageStatistics;

@Repository
public interface IPercentageStatistics extends JpaRepository<PercentageStatistics, Long> {
}
