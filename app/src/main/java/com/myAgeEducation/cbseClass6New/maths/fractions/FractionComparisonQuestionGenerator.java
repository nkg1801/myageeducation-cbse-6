package com.myAgeEducation.cbseClass6New.maths.fractions;

import com.myAgeEducation.cbseClass6New.utils.OptionUtils;
import com.myAgeEducation.cbsecommon.Question;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FractionComparisonQuestionGenerator {
    private static final Random RANDOM = new Random();

    public static Question generateQuestion() {
        int type = RANDOM.nextInt(3);
        if (type == 0) {
            return generateLikeDenominatorComparison();
        } else if (type == 1) {
            return generateLikeNumeratorComparison();
        } else {
            return generateGeneralComparison();
        }
    }

    private static Question generateLikeDenominatorComparison() {
        int denominator = 2 + RANDOM.nextInt(18); // 2 to 19
        int n1 = 1 + RANDOM.nextInt(denominator - 1);
        int n2;
        do {
            n2 = 1 + RANDOM.nextInt(denominator - 1);
        } while (n1 == n2);

        String questionText = "Compare the fractions given below using < or > signs:\n\n"
                + n1 + "/" + denominator + " ______ " + n2 + "/" + denominator;

        String correctAnswer = n1 < n2 ? "<" : ">";

        return createQuestion(questionText, correctAnswer);
    }

    private static Question generateLikeNumeratorComparison() {
        int numerator = 1 + RANDOM.nextInt(9);
        int d1 = numerator + 1 + RANDOM.nextInt(10);
        int d2;
        do {
            d2 = numerator + 1 + RANDOM.nextInt(10);
        } while (d1 == d2);

        String questionText = "Compare the fractions given below using < or > signs:\n\n"
                + numerator + "/" + d1 + " ______ " + numerator + "/" + d2;

        // Larger denominator means smaller fraction
        String correctAnswer = d1 > d2 ? "<" : ">";

        return createQuestion(questionText, correctAnswer);
    }

    private static Question generateGeneralComparison() {
        // Simple unlike fractions for Class 5
        int n1 = 1 + RANDOM.nextInt(4);
        int d1 = n1 + 1 + RANDOM.nextInt(3);
        
        int n2 = 1 + RANDOM.nextInt(4);
        int d2 = n2 + 1 + RANDOM.nextInt(3);
        
        // Avoid equal fractions for now to keep it to < and > as requested
        if (n1 * d2 == n2 * d1) {
            return generateGeneralComparison();
        }

        String questionText = "Compare the fractions given below using < or > signs:\n\n"
                + n1 + "/" + d1 + " ______ " + n2 + "/" + d2;

        String correctAnswer = (n1 * d2 < n2 * d1) ? "<" : ">";

        return createQuestion(questionText, correctAnswer);
    }

    private static Question createQuestion(String questionText, String correctAnswer) {
        Question question = new Question();
        question.setQuestion(questionText);
        question.setAnswer(correctAnswer);
        
        List<String> options = new ArrayList<>();
        options.add("<");
        options.add(">");
        options.add("=");
        options.add("None of these");
        
        // Don't shuffle these as they have a natural order
        OptionUtils.setQuestionOptions(question, options.toArray(new String[0]));
        return question;
    }
}
