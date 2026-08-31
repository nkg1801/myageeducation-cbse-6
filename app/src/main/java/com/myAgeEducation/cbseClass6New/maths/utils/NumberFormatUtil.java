package com.myAgeEducation.cbseClass6New.maths.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatUtil {
    public static String formatIndianNumber(int number) {
        return NumberFormat
                .getInstance(new Locale("en", "IN"))
                .format(number);
    }
}
