package ru.fllcker.resolvio.tasks;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.fllcker.resolvio.models.PercentageStatistics;
import ru.fllcker.resolvio.repositories.statistics.IPercentageStatistics;
import ru.fllcker.resolvio.repositories.IQuestionsRepository;
import ru.fllcker.resolvio.repositories.IUsersRepository;

@Component
@RequiredArgsConstructor
public class StatisticsTask {
    private final IQuestionsRepository questionsRepository;
    private final IUsersRepository usersRepository;
    private final IPercentageStatistics percentageStatistics;

    @Scheduled(cron = "0 0 0 * * ?") // every day at 00:00
    public void getPercentageOfUsersWithQuestions() {
        double percentage = (double) questionsRepository.countDistinctByCreatorIsNotNull() / usersRepository.count() * 100;

        PercentageStatistics statistics = PercentageStatistics
                .builder()
                .percentage(percentage)
                .description("Users with questions")
                .build();

        percentageStatistics.save(statistics);
    }
}
