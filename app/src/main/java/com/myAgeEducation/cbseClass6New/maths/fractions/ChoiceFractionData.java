package com.myAgeEducation.cbseClass6New.maths.fractions;

import com.myAgeEducation.cbseClass6New.maths.fractions.FractionData;

public class ChoiceFractionData {
    public FractionData[] fractions = new FractionData[4];
    public int correctIndex;
    public String imageCode;
    public String questionText;
    public String answer;

    public String getCorrectOption()
    {
        return String.valueOf((char)('A' + correctIndex));
    }
}
