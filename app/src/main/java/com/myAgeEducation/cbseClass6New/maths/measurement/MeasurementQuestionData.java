package com.myAgeEducation.cbseClass6New.maths.measurement;

public class MeasurementQuestionData
{
    public MeasurementQuestionType questionType;
    public String questionText;
    public String correctAnswer;
    public String[] options;

    // Used for questions involving people
    public String[] names;


    public MeasurementQuestionData(
            MeasurementQuestionType questionType,
            String questionText,
            String correctAnswer)
    {
        this.questionType = questionType;
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
    }
}