package com.myAgeEducation.cbseClass6New.maths.divisions.facts;


public class DivisionPictureTemplate
{
    public final DivisionPictureType type;

    public final String questionTemplate;

    public DivisionPictureTemplate(
            DivisionPictureType type,
            String questionTemplate)
    {
        this.type = type;
        this.questionTemplate = questionTemplate;
    }
}