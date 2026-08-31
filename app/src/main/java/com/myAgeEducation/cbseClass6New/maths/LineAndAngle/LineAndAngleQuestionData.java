package com.myAgeEducation.cbseClass6New.maths.LineAndAngle;

public class LineAndAngleQuestionData {
    public String question;
    public String answer;
    public String[] options;
    public LineAndAngleQuestionType type;

    public LineAndAngleQuestionData(String question, String answer, String[] options, LineAndAngleQuestionType type) {
        this.question = question;
        this.answer = answer;
        this.options = options;
        this.type = type;
    }
}
