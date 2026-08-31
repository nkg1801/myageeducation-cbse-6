package com.myAgeEducation.cbseClass6New.maths.integers;

import com.myAgeEducation.cbseClass6New.utils.OptionUtils;
import com.myAgeEducation.cbsecommon.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class IntegerQuestionGenerator {
    private static final Random RANDOM = new Random();

    public static Question generateQuestion() {
        IntegerQuestionType type = IntegerQuestionType.values()[RANDOM.nextInt(IntegerQuestionType.values().length)];
        switch (type) {
            case STEPS_ON_NUMBER_LINE:
                return generateStepsOnNumberLine();
            case CONCEPTUAL_PROPERTIES:
                return generateConceptualProperties();
            case OPPOSITE_INTEGER:
                return generateOppositeInteger();
            case COMPARE_INTEGERS:
                return generateCompareIntegers();
            case ABSOLUTE_VALUE:
                return generateAbsoluteValue();
            default:
                return generateConceptualProperties();
        }
    }

    private static Question generateStepsOnNumberLine() {
        int steps = 1 + RANDOM.nextInt(20);
        boolean left = RANDOM.nextBoolean();
        String direction = left ? "left" : "right";
        String questionText = steps + " steps to the " + direction + " of zero is ______";
        
        int val = left ? -steps : steps;
        String answer = (val > 0 ? "+" : "") + val;

        List<String> options = new ArrayList<>();
        options.add(answer);
        options.add((val > 0 ? "-" : "+") + steps);
        options.add("0");
        options.add(String.valueOf(RANDOM.nextInt(50) - 25));
        
        // Ensure options are unique and well-formatted
        List<String> finalOptions = new ArrayList<>();
        for(String opt : options) {
            if(!finalOptions.contains(opt)) finalOptions.add(opt);
        }
        while(finalOptions.size() < 4) finalOptions.add(String.valueOf(RANDOM.nextInt(100) - 50));
        
        Collections.shuffle(finalOptions);

        Question question = new Question();
        question.setQuestion(questionText);
        question.setAnswer(answer);
        OptionUtils.setQuestionOptions(question, finalOptions.toArray(new String[0]));
        return question;
    }

    private static Question generateConceptualProperties() {
        String[][] bank = {
                {"Every positive integer is larger than every negative integer.", "TRUE", "FALSE"},
                {"Every positive integer is smaller than every negative integer.", "FALSE", "TRUE"},
                {"Zero is less than every positive integer.", "TRUE", "FALSE"},
                {"Zero is larger than every positive integer.", "FALSE", "TRUE"},
                {"Zero is neither a negative integer nor a positive integer.", "TRUE", "FALSE"},
                {"Zero is larger than every negative integer.", "TRUE", "FALSE"},
                {"-1 is the largest negative integer.", "TRUE", "FALSE"},
                {"1 is the smallest positive integer.", "TRUE", "FALSE"},
                {"The integer -10 is greater than -5.", "FALSE", "TRUE"},
                {"The integer 0 is greater than -100.", "TRUE", "FALSE"}
        };
        int idx = RANDOM.nextInt(bank.length);
        String[] item = bank[idx];

        Question question = new Question();
        question.setQuestion(item[0]);
        question.setAnswer(item[1]);
        OptionUtils.setQuestionOptions(question, new String[]{"TRUE", "FALSE"});
        return question;
    }

    private static Question generateOppositeInteger() {
        int val = RANDOM.nextInt(100) + 1;
        boolean negative = RANDOM.nextBoolean();
        if (negative) val = -val;

        String questionText = "The opposite of the integer " + val + " is ______";
        String answer = String.valueOf(-val);

        List<String> options = new ArrayList<>();
        options.add(answer);
        options.add(String.valueOf(val));
        options.add("0");
        options.add(String.valueOf(val + 1));
        
        Collections.shuffle(options);
        Question question = new Question();
        question.setQuestion(questionText);
        question.setAnswer(answer);
        OptionUtils.setQuestionOptions(question, options.toArray(new String[0]));
        return question;
    }

    private static Question generateCompareIntegers() {
        int a = RANDOM.nextInt(41) - 20; // -20 to 20
        int b = RANDOM.nextInt(41) - 20;
        while (a == b) b = RANDOM.nextInt(41) - 20;

        boolean findGreater = RANDOM.nextBoolean();
        String questionText = "Which integer is " + (findGreater ? "greater" : "smaller") + "?";
        String answer = String.valueOf(findGreater ? Math.max(a, b) : Math.min(a, b));

        List<String> options = new ArrayList<>();
        options.add(String.valueOf(a));
        options.add(String.valueOf(b));
        options.add(String.valueOf(RANDOM.nextInt(50) - 25));
        options.add(String.valueOf(RANDOM.nextInt(50) - 25));
        
        // Ensure answer is in options and they are unique
        if (!options.contains(answer)) options.set(0, answer);
        
        Collections.shuffle(options);
        Question question = new Question();
        question.setQuestion(questionText);
        question.setAnswer(answer);
        OptionUtils.setQuestionOptions(question, options.toArray(new String[0]));
        return question;
    }

    private static Question generateAbsoluteValue() {
        int val = RANDOM.nextInt(50) + 1;
        boolean negative = RANDOM.nextBoolean();
        int signedVal = negative ? -val : val;

        String questionText = "What is the absolute value (numerical value) of " + signedVal + "?";
        String answer = String.valueOf(val);

        List<String> options = new ArrayList<>();
        options.add(answer);
        options.add(String.valueOf(-val));
        options.add("0");
        options.add(String.valueOf(val + 10));

        Collections.shuffle(options);
        Question question = new Question();
        question.setQuestion(questionText);
        question.setAnswer(answer);
        OptionUtils.setQuestionOptions(question, options.toArray(new String[0]));
        return question;
    }
}
