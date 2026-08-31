package com.myAgeEducation.cbseClass6New.maths.additions;

public class AdditionStoryTemplate
{
    public final AdditionStoryType type;

    public final String questionTemplate;

    public AdditionStoryTemplate(
            AdditionStoryType type,
            String questionTemplate)
    {
        this.type = type;
        this.questionTemplate = questionTemplate;
    }
}