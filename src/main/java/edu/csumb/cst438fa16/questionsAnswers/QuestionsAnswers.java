package edu.csumb.cst438fa16.questionsAnswers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class QuestionsAnswers {
    private final Map<String, String> questionsToAnswers = new HashMap<>();

    static String getRandomElement(Set<String> set) {
        if (set.isEmpty()) {
            throw new IllegalStateException("set cannot be empty");
        }
        int n = 0;
        String r = null;
        for (String e : set) {
            n += 1;
            if (Math.random() * n <= 1.0) {
                r = e;
            }
        }
        return r;
    }

    public void put(String question, String answer) {
        questionsToAnswers.put(question, answer);
    }

    public boolean testAnswer(String question, String answer) {
        String correct = questionsToAnswers.get(question);
        if (correct == null) {
            if (!questionsToAnswers.containsKey(question)) {
                throw new IllegalArgumentException("unknown question");
            }
            return answer == null;
        } else {
            return correct.equals(answer);
        }
    }

    public String getRandomQuestion() {
        return getRandomElement(questionsToAnswers.keySet());
    }
}
