package com.myAgeEducation.cbseClass6New.maths.placevalue.rounding;

import java.util.Random;

public class RoundingUtils
{
    private RoundingUtils()
    {
        // Prevent object creation
    }

    public static boolean hasSameRoundingResult(int number, int firstPlace, int secondPlace)
    {
        int firstResult =
                roundToPlace(
                        number,
                        firstPlace);

        int secondResult =
                roundToPlace(
                        number,
                        secondPlace);

        return firstResult == secondResult;
    }

    private static int roundToPlace(int number, int place)
    {
        return Math.round(number / (float) place) * place;
    }

    public static int generateValidNumber(Random random)
    {
        int thousand = (2 + random.nextInt(8)) * 1000;
        int firstNumber;
        firstNumber = thousand - 50 + random.nextInt(100);
        return firstNumber;
    }

    public static RoundingNumberPair generateInvalidPair(Random random)
    {
        int thousand = (2 + random.nextInt(8)) * 1000;

        int firstNumber;
        int secondNumber;

        do
        {
            firstNumber =
                    generateNumberAroundThousand(
                            random,
                            thousand);

            secondNumber =
                    generateNumberAroundThousand(
                            random,
                            thousand);
        }
        while (
                hasSameRoundingResult(
                        firstNumber,
                        100,
                        1000)
                        &&
                        hasSameRoundingResult(
                                secondNumber,
                                100,
                                1000));

        return new RoundingNumberPair(firstNumber, secondNumber);
    }

    private static int generateNumberAroundThousand(Random random, int thousand)
    {
        /*
         * Generate numbers from:
         *
         * thousand - 150
         * through
         * thousand + 149
         *
         * This gives us both valid and invalid
         * numbers around the selected thousand.
         */
        return thousand - 150 + random.nextInt(300);
    }
}
