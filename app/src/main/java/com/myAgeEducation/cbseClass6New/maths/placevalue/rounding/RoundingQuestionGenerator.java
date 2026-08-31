package com.myAgeEducation.cbseClass6New.maths.placevalue.rounding;

import com.myAgeEducation.cbseClass6New.maths.utils.PersonNameUtil;
import com.myAgeEducation.cbseClass6New.utils.OptionUtils;
import com.myAgeEducation.cbsecommon.Question;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RoundingQuestionGenerator
{
    private RoundingQuestionGenerator()
    {
        // Prevent object creation
    }

    public static Question generateQuestion()
    {
        RoundingQuestionData questionData = generate(new Random(), 4);

        Question question = new Question();
        question.setQuestion(questionData.getQuestionText());
        question.setAnswer(questionData.getAnswer());
        List<String> options = questionData.getOptions();
        List<String> optionsList = new ArrayList<>();

        optionsList.addAll(options);

        OptionUtils.setQuestionOptions(question, optionsList);
        return  question;
    }

    public static RoundingQuestionData generate(Random random, int optionCount)
    {
        if (optionCount < 2 || optionCount > 4)
        {
            throw new IllegalArgumentException("Option count must be between 2 and 4");
        }

        /*
         * Generate the ONE correct pair.
         */
        int validNumber = RoundingUtils.generateValidNumber(random);

        /*
         * Create the options.
         */
        List<String> options = new ArrayList<>();
        options.add(validNumber + "");

        /*
         * Generate all remaining options as invalid pairs.
         */
        while (options.size() < optionCount)
        {
            RoundingNumberPair invalidPair = RoundingUtils.generateInvalidPair(random);
            options.add(invalidPair.getFirstNumber() + "");
        }

        Collections.shuffle(options, random);

        String questionText =
                PersonNameUtil.getMaleName() + " rounded off a number to the nearest "
                        + "hundred. " + PersonNameUtil.getFemaleName() + " rounded off the same "
                        + "number to the nearest thousand. "
                        + "Both got the same result. "
                        + "Choose the number they "
                        + "might have used.";

        return new RoundingQuestionData(questionText, options, validNumber + "");
    }
}
