package com.myAgeEducation.cbseClass6New.maths.subtractions;

import com.myAgeEducation.cbseClass6New.maths.utils.NumberFormatUtil;
import com.myAgeEducation.cbseClass6New.utils.OptionUtils;
import com.myAgeEducation.cbseClass6New.OptionUtil;
import com.myAgeEducation.cbsecommon.Question;

import java.util.LinkedHashSet;
import java.util.Set;

public class SubtractionStoryQuestionGenerator
{
    private SubtractionStoryQuestionGenerator()
    {
    }

    public static Question generateQuestion()
    {
        SubtractionStoryQuestionData data = SubtractionStoryDataGenerator.generate();
        String correctAnswer = NumberFormatUtil.formatIndianNumber(data.answer);
        String[] options = generateOptions(data.answer);
        Question question = new Question();
        question.setQuestion(data.question);
        OptionUtils.setQuestionOptions(question, options);
        question.setAnswer(correctAnswer);
        return question;
    }

    private static String[] generateOptions(int answer)
    {
        Set<String> distractors = new LinkedHashSet<>();
        distractors.add(NumberFormatUtil.formatIndianNumber(answer + 1));
        distractors.add(NumberFormatUtil.formatIndianNumber(answer - 1));
        distractors.add(NumberFormatUtil.formatIndianNumber(answer + 10));
        distractors.add(NumberFormatUtil.formatIndianNumber(answer - 10));
        distractors.add(NumberFormatUtil.formatIndianNumber(answer + 2));
        distractors.add(NumberFormatUtil.formatIndianNumber(answer - 2));

        return OptionUtil.createOptions(NumberFormatUtil.formatIndianNumber(answer), distractors, 4);
    }
}
