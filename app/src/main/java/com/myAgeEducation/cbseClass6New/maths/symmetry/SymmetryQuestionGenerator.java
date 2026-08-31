package com.myAgeEducation.cbseClass6New.maths.symmetry;
import com.myAgeEducation.cbseClass6New.utils.OptionUtils;
import com.myAgeEducation.cbsecommon.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SymmetryQuestionGenerator {
    private static final Random RANDOM = new Random();

    /*public static Question generateQuestion() {
        SymmetryQuestionData data = generateQuestionData();
        return convertToQuestion(data);
    }*/

    public static ArrayList<Question> generateAllQuestions()
    {
        ArrayList<Question> questions = new ArrayList<>();
        for(Object[] s : symmetryQuestionBank)
        {
            SymmetryQuestionData data = generateQuestionData(s);
            Question question = convertToQuestion(data);
            questions.add(question);
        }

        Collections.shuffle(questions);

        return questions;
    }

    private static SymmetryQuestionData generateQuestionData(Object[] questionData) {
        String question = (String) questionData[0];
        String answer = (String) questionData[1];
        String[] options = (String[]) questionData[2];
        String image = (questionData.length > 3) ? (String) questionData[3] : null;

        // Shuffle options if they are not TRUE/FALSE to add variety
        if (options.length > 2) {
            List<String> optionList = new ArrayList<>(Arrays.asList(options));
            Collections.shuffle(optionList, RANDOM);
            options = optionList.toArray(new String[0]);
        }

        return new SymmetryQuestionData(question, answer, options, image);
    }

    private static Question convertToQuestion(SymmetryQuestionData data) {
        Question question = new Question();
        question.setQuestion(data.question);
        question.setAnswer(data.answer);
        question.setImage(data.image);
        OptionUtils.setQuestionOptions(question, data.options);
        return question;
    }

    private static final Object[][] symmetryQuestionBank = {
            {"How many lines of symmetry does a rectangle have?", "2", new String[]{"2", "1", "4", "infinite"}},
            {"How many lines of symmetry does a square have?", "4", new String[]{"4", "2", "1", "8"}},
            {"How many lines of symmetry does a pair of scissors have?", "1", new String[]{"1", "2", "0", "4"}},
            {"TRUE or FALSE. A circle has only one line of symmetry.", "FALSE", new String[]{"FALSE", "TRUE"}},
            {"A circle has ___________ lines of symmetry.", "infinite", new String[]{"infinite", "one", "two", "four"}},
            {"How many lines of symmetry does an equilateral triangle have?", "3", new String[]{"3", "1", "2", "0"}},
            {"How many lines of symmetry does a regular pentagon have?", "5", new String[]{"5", "1", "4", "infinite"}},
            {"How many lines of symmetry does the letter 'H' have?", "2", new String[]{"2", "1", "0", "4"}},
            {"Which of these letters has no line of symmetry?", "F", new String[]{"F", "A", "M", "T"}},
            {"A line that divides a figure into two identical halves is called a line of _________.", "symmetry", new String[]{"symmetry", "boundary", "intersection", "division"}},
            {"TRUE or FALSE. No shape can have more than four line of symmetry.", "FALSE", new String[]{"FALSE", "TRUE"}},
            {"TRUE or FALSE. A square and a circle has the same number of line of symmetry.", "FALSE", new String[]{"FALSE", "TRUE"}},
            {"TRUE or FALSE. All circles has the same number of line of symmetry.", "TRUE", new String[]{"TRUE", "FALSE"}},
            {"TRUE or FALSE. Any line passing through the center of a circle is the line of symmetry of the circle.", "TRUE", new String[]{"TRUE", "FALSE"}},
            {"TRUE or FALSE. All kinds of triangles have same number of lines of symmetry.", "FALSE", new String[]{"FALSE", "TRUE"}},
            {"TRUE or FALSE. All kinds of quadrilaterals have same number of lines of symmetry.", "FALSE", new String[]{"FALSE", "TRUE"}},
            {"TRUE or FALSE. A rectangle has TWO lines of symmetry but a square has FOUR lines of symmetry.", "TRUE", new String[]{"TRUE", "FALSE"}},
            {"Which of the following shape does not have any line of symmetry?", "Scalene triangle", new String[]{"Scalene triangle", "Isosceles triangle", "Equilateral triangle", "Square"}},
            {"TRUE or FALSE. An isosceles triangle has no lines of symmetry.", "FALSE", new String[]{"FALSE", "TRUE"}},
            {"TRUE or FALSE. An isosceles triangle has two lines of symmetry.", "FALSE", new String[]{"FALSE", "TRUE"}},
            {"Which of the following figure has more than three lines of symmetry?", "Square", new String[]{"Square", "Equilateral triangle", "Isosceles triangle", "Rectangle"}},
            {"Which of the following triangle has three lines of symmetry?", "Equilateral triangle", new String[]{"Equilateral triangle", "Isosceles triangle", "Scalene triangle", "Right angled triangle"}},
            {"Which of the following triangle has only one lines of symmetry?", "Isosceles triangle", new String[]{"Isosceles triangle", "Equilateral triangle", "Scalene triangle", "All of these"}},
            {"Which of the following triangle has no lines of symmetry?", "Scalene triangle", new String[]{"Scalene triangle", "Isosceles triangle", "Equilateral triangle", "None of these"}},
            {"Which of the following figure has three lines of symmetry?", "Equilateral triangle", new String[]{"Equilateral triangle", "Square", "Rectangle", "Circle"}},
            {"Which of the following figure has infinite lines of symmetry?", "Circle", new String[]{"Circle", "Square", "Rectangle", "Equilateral triangle"}},
            {"Which of the following figure has two lines of symmetry?", "Rectangle", new String[]{"Rectangle", "Square", "Circle", "Equilateral triangle"}},
            {"Which of the following figure has four lines of symmetry?", "Square", new String[]{"Square", "Rectangle", "Circle", "Equilateral triangle"}},
            {"How many lines of symmetry does a circle has?", "Infinite", new String[]{"Infinite", "4", "2", "1"}},
            {"TRUE or FALSE. A circle has four line of symmetry.", "FALSE", new String[]{"FALSE", "TRUE"}},
            {"How many lines of symmetry does the shape given in the below image have?", "4", new String[]{"4", "2", "1", "infinite"}, "SYMMETRY_STAR_4"},
            {"How many lines of symmetry does the shape given in the below image have?", "1", new String[]{"1", "2", "0", "infinite"}, "SYMMETRY_ARROW"},
            {"How many lines of symmetry does the shape given in the below image have?", "4", new String[]{"4", "2", "8", "infinite"}, "SYMMETRY_PLUS"},
            {"How many lines of symmetry does the shape given in the below image have?", "2", new String[]{"2", "4", "1", "infinite"}, "SYMMETRY_DIAMOND"},
            {"How many lines of symmetry does the shape given in the below image have?", "1", new String[]{"1", "2", "0", "infinite"}, "SYMMETRY_HEART"},
            {"In the following figure which line is the line of symmetry?", "Line A", new String[]{"Line A", "Line B", "Both", "None"}, "SYMMETRY_RECT_LINES"},
            {"In the following figure which line is the line of symmetry?", "Both", new String[]{"Line A", "Line B", "Both", "None"}, "SYMMETRY_SQUARE_LINES"},
            {"In the following figure which line is the line of symmetry?", "Line A", new String[]{"Line A", "Line B", "Both", "None"}, "SYMMETRY_TRIANGLE_LINES"}
    };
}
