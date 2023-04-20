package ru.fllcker.resolvio.comparators;

import lombok.RequiredArgsConstructor;
import ru.fllcker.resolvio.models.Question;

import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
public class QuestionsComparator implements Comparator<Question> {
    private final List<String> keywords;

    @Override
    public int compare(Question q1, Question q2) {
        int matches1 = countMatches(q1.getDescription());
        int matches2 = countMatches(q2.getDescription());
        return Integer.compare(matches2, matches1);
    }

    private int countMatches(String text) {
        int matches = 0;
        for (String keyword : keywords) {
            if (text.toLowerCase().contains(keyword.toLowerCase())) {
                matches++;
            }
        }
        return matches;
    }
}
