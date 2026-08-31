package com.myAgeEducation.cbseClass6New.maths.additions;

import com.myAgeEducation.cbseClass6New.OptionUtil;
import com.myAgeEducation.cbseClass6New.maths.utils.NumberFormatUtil;
import com.myAgeEducation.cbseClass6New.utils.OptionUtils;
import com.myAgeEducation.cbsecommon.Question;

import java.util.LinkedHashSet;
import java.util.Set;

public class AdditionStoryQuestionGenerator
{
    private AdditionStoryQuestionGenerator()
    {
    }

    public static Question generateQuestion()
    {
        AdditionStoryQuestionData data = AdditionStoryDataGenerator.generate();
        String correctAnswer = NumberFormatUtil.formatIndianNumber(data.answer);
        String[] options = generateOptions(data.answer);
        Question question = new Question();
        question.setQuestion(data.question);
        OptionUtils.setQuestionOptions(question, options);
        question.setAnswer(correctAnswer);
        return question;
    }

    private static String[] generateOptions(int correctAnswer)
    {
        Set<String> distractors = new LinkedHashSet<>();
        distractors.add(NumberFormatUtil.formatIndianNumber(correctAnswer + 1));
        distractors.add(NumberFormatUtil.formatIndianNumber(correctAnswer - 1));
        distractors.add(NumberFormatUtil.formatIndianNumber(correctAnswer + 10));
        distractors.add(NumberFormatUtil.formatIndianNumber(correctAnswer - 10));
        distractors.add(NumberFormatUtil.formatIndianNumber(correctAnswer + 2));
        distractors.add(NumberFormatUtil.formatIndianNumber(correctAnswer - 2));
        return OptionUtil.createOptions(NumberFormatUtil.formatIndianNumber(correctAnswer), distractors, 4);
    }
}