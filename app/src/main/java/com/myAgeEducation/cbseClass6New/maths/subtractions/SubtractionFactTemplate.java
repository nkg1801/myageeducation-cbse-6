package com.myAgeEducation.cbseClass6New.maths.subtractions;

public class SubtractionFactTemplate
{
    public final SubtractionFactType type;

    public final String questionTemplate;

    public SubtractionFactTemplate(
            SubtractionFactType type,
            String questionTemplate)
    {
        this.type = type;
        this.questionTemplate = questionTemplate;
    }
}