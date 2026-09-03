package com.myAgeEducation.cbseClass6New;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Size;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.myAgeEducation.cbseClass6New.maths.LineAndAngle.AngleQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.LineAndAngle.LineAndAngleQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.algebra.AlgebraQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.charts.BarChartQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.circlegraph.CircleGraphQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.datahandling.DataHandlingConceptQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.decimals.DecimalGridQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.decimals.DecimalImageQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.decimals.DecimalQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.divisibility.DivisibilityQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.factors.FactorQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.fractions.EquivalentFractionQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.fractions.FractionAgeQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.fractions.FractionComparisonQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.fractions.FractionConceptQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.fractions.FractionOfMeasurementQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.fractions.FractionOfNumberQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.fractions.FractionQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.fractions.FractionSeriesQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.fractions.FractionStoryQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.fractions.FractionTimeStoryQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.fractions.FractionTrueFalseQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.fractions.FractionTypes;
import com.myAgeEducation.cbseClass6New.maths.geometricalideas.BasicGeometricalIdeasQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.hcf.HcfQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.integers.IntegerQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.knowingournumbers.KnowingOurNumbersQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.lcm.LcmQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.measurement.MeasurementQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.multiples.MultipleQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.pattern.NumberSeriesQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.pattern.PatternSequenceQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.perimeterarea.PerimeterAreaQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.pictograph.PictographQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.placevalue.arrangedigits.ArrangeDigitsQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.placevalue.arrangenumbers.ArrangeNumbersQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.placevalue.digitplace.DigitAtPlaceQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.placevalue.digitplace.DigitPlaceValueQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.placevalue.greatestsmallest.GreatestSmallestQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.placevalue.numbercomparison.ComparisonSymbolQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.placevalue.numbercomparison.NumberComparisonQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.placevalue.numberorder.NumberOrderQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.placevalue.numberword.NumberWordsQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.placevalue.palindromes.PalindromeNumberQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.placevalue.rounding.RoundingQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.placevalue.standardform.StandardFormQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.placevalue.successorpredecessor.SuccessorPredecessorQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.primecomposite.PrimeCompositeQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.ratioandproportion.RatioAndProportionQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.symmetry.SymmetryQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.tabularquestions.TableQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.wholenumbers.WholeNumbersQuestionGenerator;
import com.myAgeEducation.cbsecommon.Question;

import java.util.List;
import java.util.Random;

public class QuestionLoaderActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_loader);
        setHeaderImage();
        Util.allQuestions.clear();
        
        new Thread(() -> {
            try {
                long startTime = System.currentTimeMillis();
                addGeneratedQuestionsForMaths();
                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;
                android.util.Log.d("QuestionLoader", "Total loading time: " + duration + "ms");

                // Ensure loader stays for at least 3 seconds
                if (duration < 2000) {
                    try {
                        Thread.sleep(2000 - duration);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                android.util.Log.e("QuestionLoader", "Error generating questions", e);
            } finally {
                runOnUiThread(() -> openChapters("set1"));
            }
        }).start();
    }

    public void openChapters(String questionSet)
    {
        Intent chapterIntent = new Intent();
        chapterIntent.setClassName(Util.PACKAGE_NAME, Util.PACKAGE_NAME + ".Chapters");
        chapterIntent.putExtra("question_set", questionSet);
        startActivity(chapterIntent);
        finish();
    }

    private void updateLoadingText(String text) {
        runOnUiThread(() -> {
            android.widget.TextView textView = findViewById(R.id.progressMessage);
            if (textView != null) {
                textView.setText(text);
            }
        });
    }

    private void addGeneratedQuestionsForMaths()
    {
        long start;
        
        start = System.currentTimeMillis();
        updateLoadingText("Loading questions for Chapter #1");
        addQuestionsForChapterOne();
        android.util.Log.d("QuestionLoader", "Chapter 1 took: " + (System.currentTimeMillis() - start) + "ms");

        updateLoadingText("Loading questions for Chapter #2");
        start = System.currentTimeMillis();
        addQuestionsForChapterTwo();
        android.util.Log.d("QuestionLoader", "Chapter 2 took: " + (System.currentTimeMillis() - start) + "ms");

        updateLoadingText("Loading questions for Chapter #3");
        start = System.currentTimeMillis();
        addQuestionsForChapterThree();
        android.util.Log.d("QuestionLoader", "Chapter 3 took: " + (System.currentTimeMillis() - start) + "ms");

        updateLoadingText("Loading questions for Chapter #4");
        start = System.currentTimeMillis();
        addQuestionsForChapterFour();
        android.util.Log.d("QuestionLoader", "Chapter 4 took: " + (System.currentTimeMillis() - start) + "ms");

        updateLoadingText("Loading questions for Chapter #5");
        start = System.currentTimeMillis();
        addQuestionsForChapterFive();
        android.util.Log.d("QuestionLoader", "Chapter 5 took: " + (System.currentTimeMillis() - start) + "ms");

        updateLoadingText("Loading questions for Chapter #6");
        start = System.currentTimeMillis();
        addQuestionsForChapterSix();
        android.util.Log.d("QuestionLoader", "Chapter 6 took: " + (System.currentTimeMillis() - start) + "ms");

        updateLoadingText("Loading questions for Chapter #7");
        start = System.currentTimeMillis();
        addQuestionsForChapterSeven();
        android.util.Log.d("QuestionLoader", "Chapter 7 took: " + (System.currentTimeMillis() - start) + "ms");

        updateLoadingText("Loading questions for Chapter #8");
        start = System.currentTimeMillis();
        addQuestionsForChapterEight();
        android.util.Log.d("QuestionLoader", "Chapter 8 took: " + (System.currentTimeMillis() - start) + "ms");

        updateLoadingText("Loading questions for Chapter #9");
        start = System.currentTimeMillis();
        addQuestionsForChapterNine();
        android.util.Log.d("QuestionLoader", "Chapter 9 took: " + (System.currentTimeMillis() - start) + "ms");

        updateLoadingText("Loading questions for Chapter #10");
        start = System.currentTimeMillis();
        addQuestionsForChapterTen();
        android.util.Log.d("QuestionLoader", "Chapter 10 took: " + (System.currentTimeMillis() - start) + "ms");

        updateLoadingText("Loading questions for Chapter #11");
        start = System.currentTimeMillis();
        addQuestionsForChapterEleven();
        android.util.Log.d("QuestionLoader", "Chapter 11 took: " + (System.currentTimeMillis() - start) + "ms");

        updateLoadingText("Loading questions for Chapter #12");
        start = System.currentTimeMillis();
        addQuestionsForChapterTwelve();
        android.util.Log.d("QuestionLoader", "Chapter 12 took: " + (System.currentTimeMillis() - start) + "ms");

        updateLoadingText("Loading questions for Chapter #13");
        start = System.currentTimeMillis();
        addQuestionsForChapterThirteen();
        android.util.Log.d("QuestionLoader", "Chapter 13 took: " + (System.currentTimeMillis() - start) + "ms");
        updateLoadingText("Generating questions");
    }

    private void addQuestionsForChapterOne()
    {
        int chapterNumber = 1;
        String chapterName = "Knowing our Numbers";
        final Random RANDOM = new Random();
        int randomNumber;

        Question question;

        for(int i = 0; i < 20; i++)
        {
            randomNumber = RANDOM.nextInt(17);

            switch(randomNumber)
            {
                case 0:
                    //checked
                    question = ArrangeDigitsQuestionGenerator.generateQuestion();
                    break;

                case 1:
                    //checked
                    question = ArrangeNumbersQuestionGenerator.generateQuestion();
                    break;

                case 2:
                    //checked
                    question = NumberComparisonQuestionGenerator.generateQuestion();
                    break;

                case 3:
                    //checked
                    question = ComparisonSymbolQuestionGenerator.generateQuestion();
                    break;

                case 4:
                    //checked
                    question = DigitPlaceValueQuestionGenerator.generateQuestion();
                    break;

                case 5:
                    //checked
                    question = GreatestSmallestQuestionGenerator.generateQuestion();
                    break;

                case 6:
                    question = SuccessorPredecessorQuestionGenerator.generateQuestion();
                    break;

                case 7:
                    //checked
                    question = NumberOrderQuestionGenerator.generateQuestion();
                    break;

                case 8:
                    //checked
                    question = StandardFormQuestionGenerator.generateQuestion();
                    break;

                case 9:
                    question = com.myAgeEducation.cbseClass6New.maths.RomanNumerals.RomanNumeralsQuestionGenerator.generateQuestion();
                    break;

                case 10:
                    question = DigitAtPlaceQuestionGenerator.generateQuestion();
                    break;

                case 11:
                    question = NumberSeriesQuestionGenerator.generateQuestion();
                    break;

                case 12:
                    question = PatternSequenceQuestionGenerator.generateQuestion();
                    break;

                case 13:
                    //checked
                    question = RoundingQuestionGenerator.generateQuestion();
                    break;

                case 14:
                    question = PalindromeNumberQuestionGenerator.generateQuestion();
                    break;

                case 15:
                    question = KnowingOurNumbersQuestionGenerator.generateQuestion();
                    break;

                default:
                    //checked
                    question = NumberWordsQuestionGenerator.generateQuestion();
            }
            question.setChapter(chapterNumber);
            question.setChapterName(chapterName);
            Util.allQuestions.add(question);
        }
    }

    private void addQuestionsForChapterTwo()
    {
        int chapterNumber = 2;
        String chapterName = "Whole Numbers";

        for(int i = 0; i < 20; i++)
        {
            Question question = WholeNumbersQuestionGenerator.generateQuestion();
            question.setChapter(chapterNumber);
            question.setChapterName(chapterName);
            Util.allQuestions.add(question);
        }
    }

    private void addQuestionsForChapterThree()
    {
        int chapterNumber = 3;
        String chapterName = "Playing with Numbers";
        final Random RANDOM = new Random();
        int randomNumber;

        for(int i=0; i < 20; i++)
        {
            Question question;
            randomNumber = RANDOM.nextInt(100);
            if(randomNumber < 15) {
                question = LcmQuestionGenerator.generateQuestion();
            }
            else if(randomNumber < 30) {
                question = HcfQuestionGenerator.generateQuestion();
            }
            else if(randomNumber < 45) {
                question = PrimeCompositeQuestionGenerator.generateQuestion();
            }
            else if(randomNumber < 60) {
                question = DivisibilityQuestionGenerator.generateQuestion();
            }
            else if(randomNumber < 80){
                question = MultipleQuestionGenerator.generateQuestion();
            }
            else{
                question = FactorQuestionGenerator.generateQuestion();
            }

            question.setChapter(chapterNumber);
            question.setChapterName(chapterName);
            Util.allQuestions.add(question);
        }
    }

    private void addQuestionsForChapterFour()
    {
        int chapterNumber = 4;
        String chapterName = "Basic Geometrical Ideas";
        final Random RANDOM = new Random();
        int randomNumber;

        for(int i=0; i < 20; i++)
        {
            Question question;
            randomNumber = RANDOM.nextInt(100);
            if(randomNumber < 35) {
                question = AngleQuestionGenerator.generateQuestion();

            }
            else if(randomNumber < 70){
                question = BasicGeometricalIdeasQuestionGenerator.generateQuestion();
            }
            else {
                question = LineAndAngleQuestionGenerator.generateQuestion();
            }

            question.setChapter(chapterNumber);
            question.setChapterName(chapterName);
            Util.allQuestions.add(question);
        }
    }

    private void addQuestionsForChapterFive()
    {
        int chapterNumber = 5;
        String chapterName = "Elementary Shapes";
        final Random RANDOM = new Random();
        int randomNumber;

        for(int i=0; i < 20; i++)
        {
            Question question;
            randomNumber = RANDOM.nextInt(100);
            if(randomNumber < 50) {
                question = AngleQuestionGenerator.generateQuestion();

            }
            else {
                question = LineAndAngleQuestionGenerator.generateQuestion();
            }
            question.setChapter(chapterNumber);
            question.setChapterName(chapterName);
            Util.allQuestions.add(question);
        }
    }

    private void addQuestionsForChapterSix()
    {
        int chapterNumber = 6;
        String chapterName = "Integers";

        for(int i = 0; i < 20; i++)
        {
            Question question = IntegerQuestionGenerator.generateQuestion();
            question.setChapter(chapterNumber);
            question.setChapterName(chapterName);
            Util.allQuestions.add(question);
        }
    }

    private void addQuestionsForChapterSeven()
    {
        int chapterNumber = 7;
        String chapterName = "Fractions";
        Random RANDOM = new Random();
        Question question;

        for(int i = 0; i < 20; i++) {
            FractionTypes[] types = FractionTypes.values();
            FractionTypes type = types[RANDOM.nextInt(types.length)];

            switch(type)
            {
                case FRACTION_SERIES:
                    updateLoadingText("Loading questions for Chapter #7");
                    question = FractionSeriesQuestionGenerator.generateQuestion();
                    break;

                case STORY_TYPE:
                    updateLoadingText("Loading questions for Chapter #7");
                    question = FractionStoryQuestionGenerator.generateRemainingQuestion();
                    break;

                case FRACTION_WITH_AGE:
                    updateLoadingText("Loading questions for Chapter #7");
                    question = FractionAgeQuestionGenerator.generateQuestion();
                    break;

                case FRACTION_OF_NUMBER:
                    updateLoadingText("Loading questions for Chapter #7");
                    question = FractionOfNumberQuestionGenerator.generateQuestion();
                    break;

                case FRACTION_OF_MEASUREMENT_DATA:
                    updateLoadingText("Loading questions for Chapter #7");
                    question = FractionOfMeasurementQuestionGenerator.generateQuestion();
                    break;

                case FRACTION_TIME_STORY:
                    updateLoadingText("Loading questions for Chapter #7");
                    question = FractionTimeStoryQuestionGenerator.generateQuestion();
                    break;

                case FRACTION_TRUE_FALSE:
                    updateLoadingText("Loading questions for Chapter #7");
                    question = FractionTrueFalseQuestionGenerator.generateQuestion();
                    break;

                case FRACTION_CONCEPTS:
                    updateLoadingText("Loading questions for Chapter #7");
                    question = FractionConceptQuestionGenerator.generateQuestion();
                    break;

                case EQUIVALENT_FRACTIONS:
                    //checked
                    updateLoadingText("Loading questions for Chapter #7");
                    question = EquivalentFractionQuestionGenerator.generateQuestion();
                    break;

                case FRACTION_COMPARISON:
                    updateLoadingText("Loading questions for Chapter #7");
                    question = FractionComparisonQuestionGenerator.generateQuestion();
                    break;

                default:
                    question = FractionQuestionGenerator.generateQuestion();
            }
            question.setChapter(chapterNumber);
            question.setChapterName(chapterName);
            Util.allQuestions.add(question);
        }
    }

    private void addQuestionsForChapterEight()
    {
        int chapterNumber = 8;
        String chapterName = "Decimals";
        Question question;
        Random RANDOM = new Random();

        for(int i = 0; i < 20; i++) {
            int randomNumber = RANDOM.nextInt(100);

            if(randomNumber < 35) // 40%
            {
                question = DecimalQuestionGenerator.generateQuestion();
            }
            else if(randomNumber < 70)
            {
                question = DecimalImageQuestionGenerator.generateQuestion();
            }
            else {
                question = DecimalGridQuestionGenerator.generateQuestion();
            }

            question.setChapter(chapterNumber);
            question.setChapterName(chapterName);
            Util.allQuestions.add(question);
        }
    }

    private void addQuestionsForChapterNine()
    {
        int chapterNumber = 9;
        String chapterName = "Data Handling";
        final Random RANDOM = new Random();
        int randomNumber;

        Question question;

        for(int i = 0; i < 20; i++) {
            randomNumber = RANDOM.nextInt(100);

            if(randomNumber < 40) // 40%
            {
                question = TableQuestionGenerator.generateQuestion();
            }
            else if(randomNumber < 80) // 40%
            {
                question = BarChartQuestionGenerator.generateQuestion();
            }
            else if(randomNumber < 90) // 10%
            {
                question = CircleGraphQuestionGenerator.generateQuestion();
            }
            else if(randomNumber < 95) // 5%
            {
                question = PictographQuestionGenerator.generateQuestion();
            }
            else // 5%
            {
                question = DataHandlingConceptQuestionGenerator.generateQuestion();
            }

            question.setChapter(chapterNumber);
            question.setChapterName(chapterName);
            Util.allQuestions.add(question);
        }
    }

    private void addQuestionsForChapterTen()
    {
        int chapterNumber = 10;
        String chapterName = "Mensuration";
        final Random RANDOM = new Random();

        for(int i = 0; i < 20; i++) {
            Question question;
            if (RANDOM.nextInt(100) < 60) {
                question = PerimeterAreaQuestionGenerator.generateQuestion();
            } else {
                question = MeasurementQuestionGenerator.generateQuestion();
            }
            question.setChapter(chapterNumber);
            question.setChapterName(chapterName);
            Util.allQuestions.add(question);
        }
    }

    private void addQuestionsForChapterEleven()
    {
        int chapterNumber = 11;
        String chapterName = "Algebra";

        for(int i = 0; i < 20; i++)
        {
            Question question = AlgebraQuestionGenerator.generateQuestion();
            question.setChapter(chapterNumber);
            question.setChapterName(chapterName);
            Util.allQuestions.add(question);
        }
    }

    private void addQuestionsForChapterTwelve()
    {
        int chapterNumber = 12;
        String chapterName = "Ratio and Proportion";

        for(int i = 0; i < 20; i++)
        {
            Question question = RatioAndProportionQuestionGenerator.generateQuestion();
            question.setChapter(chapterNumber);
            question.setChapterName(chapterName);
            Util.allQuestions.add(question);
        }
    }

    private void addQuestionsForChapterThirteen()
    {
        int chapterNumber = 13;
        String chapterName = "Symmetry";

        List<Question> questions = SymmetryQuestionGenerator.generateAllQuestions();
        int numberOfQuestions = 20;

        List<Question> top20Questions = questions.subList(0, Math.min(numberOfQuestions, questions.size()));
        for(Question question : top20Questions)
        {
            question.setChapter(chapterNumber);
            question.setChapterName(chapterName);
            Util.allQuestions.add(question);
        }
    }


    private void setHeaderImage()
    {
        ImageView img = findViewById(R.id.imgEducation);
        String imageName = IMAGES[RANDOM.nextInt(IMAGES.length)];
        int resourceIdentifier = getResources().getIdentifier(imageName, "drawable", getPackageName());
        img.setImageResource(resourceIdentifier);
        Size size = getDrawableSize(this, imageName);

        int IMAGE_WIDTH = 600;
        float factor = (float) IMAGE_WIDTH / size.getWidth();
        int width = (int) (size.getWidth() * factor);
        int height = (int)(size.getHeight() * factor);
        setImageViewWidthHeight(img, width/2, height/2);
    }

    public Size getDrawableSize(Context context, String drawableName) {
        int resId = context.getResources().getIdentifier(drawableName,"drawable",context.getPackageName());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(),resId,options);
        return new Size(options.outWidth, options.outHeight);
    }

    private void setImageViewWidthHeight(ImageView img, int width, int height)
    {
        ViewGroup.LayoutParams params = img.getLayoutParams();
        params.width = width;
        params.height = height;
        img.setLayoutParams(params);
        img.setTop(20);
    }

    private static final Random RANDOM = new Random();

    private static final String[] IMAGES = {
            "thinking_owl",
            "blue_bird",
            "ant_thinking",
            "boy_thinking",
            "girl_thinking",
            "tortoise",
            "snail_thinking",
            "slate_thinking",
            "school_bag_thinking",
            "puppy",
            "protector_thinking",
            "plus_thinking",
            "pie_thinking"
    };
}
