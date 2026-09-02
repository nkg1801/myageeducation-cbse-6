package com.myAgeEducation.cbseClass6New;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.app.Activity;
import android.content.Intent;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.myAgeEducation.cbseClass6New.maths.LineAndAngle.AngleQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.LineAndAngle.LineAndAngleQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.charts.BarChartQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.circlegraph.CircleGraphQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.datahandling.DataHandlingConceptQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.decimals.DecimalGridQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.decimals.DecimalImageQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.decimals.DecimalQuestionGenerator;
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
import com.myAgeEducation.cbseClass6New.maths.measurement.MeasurementQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.multiples.MultipleQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.perimeterarea.PerimeterAreaQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.pattern.NumberSeriesQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.pattern.PatternSequenceQuestionGenerator;
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
import com.myAgeEducation.cbseClass6New.maths.geometricalideas.BasicGeometricalIdeasQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.divisibility.DivisibilityQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.hcf.HcfQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.wholenumbers.WholeNumbersQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.lcm.LcmQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.primecomposite.PrimeCompositeQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.knowingournumbers.KnowingOurNumbersQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.integers.IntegerQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.ratioandproportion.RatioAndProportionQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.algebra.AlgebraQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.symmetry.SymmetryQuestionGenerator;
import com.myAgeEducation.cbseClass6New.maths.tabularquestions.TableQuestionGenerator;
import com.myAgeEducation.cbsecommon.Question;

public class SubjectList extends Activity
{
    private static final int SUBJECT_INDEX = 0;
    private static final int QUESTION_SET_INDEX = 1;
	int _randomQuestionSet;
    DatabaseHelper _databaseHelper;
    int _cloudVersion = 0;
    private final ArrayList<String> _downloadLinks = new ArrayList<>();
    private ArrayList<String> _pendingDownloads = new ArrayList<>();
    private final ArrayList<Question> _questionList = new ArrayList<>();

    ListView _listView;
    ProgressDialog ringProgressDialog;
    private SharedPreferences _sharedPreferences;
    private boolean isAddToLocalDatabaseCompleted = true;
    private Runnable runnable;
    private final Handler handler = new Handler();
    private boolean runnableStarted = false;

	final int SCIENCE = 0;
    final int MATHS = 1;
    final int COMPUTERS = 2;
    final int GK = 3;
    final int HCF_LCM_CALCULATOR = 4;
    final int SCORE = 5;
    final int SHARE_APP_LINK = 6;
    final int APP_RATING = 7;
    final int GETMORE = 8;
    final int EXIT = 9;

    @Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subject_list);
        FirebaseApp.initializeApp(this);
        _listView = findViewById(android.R.id.list);
        _sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        ringProgressDialog = new ProgressDialog(SubjectList.this);

        try {
            // this is the first time the database is opened.
            openDatabase();
        }
        catch(Exception e)
        {
            Util.displayAlert("Error-SUB-001: " + e.getMessage(), "ERROR-SUB-001", SubjectList.this);
        }

        _listView.setOnItemClickListener((parent, view, position, id) -> {
            switch(position)
            {
                case SCIENCE:
                    Util.Subject = "science";
                    break;

                case MATHS:
                    Util.Subject = "maths";
                    break;

                case COMPUTERS:
                    Util.Subject = "computers";
                    break;

                case GK:
                    Util.Subject = "gk";
                    break;

                case SCORE:
                    Util.Subject = "";
                    Intent subPage = new Intent();
                    subPage.setClassName(Util.PACKAGE_NAME, Util.PACKAGE_NAME + ".ScoreHistory");
                    startActivity(subPage);
                    break;

                case SHARE_APP_LINK:
                    Util.Subject = "";
                    shareAppLink();
                    break;

                case APP_RATING:
                    Util.Subject = "";
                    openPlayStoreForRating();
                    break;

                 case HCF_LCM_CALCULATOR:
                    startHcfCalculatorActivity();
                    break;

                case GETMORE:
                    startGetMoreActivity();
                    break;

                case EXIT:
                    Util.Subject = "";
                    finish();
                    break;

                default:
                    break;
            }

            if (!Util.Subject.isEmpty())
            {
                if(Util.Subject.equalsIgnoreCase("maths"))
                {
                    Intent intent = new Intent();
                    intent.setClassName(Util.PACKAGE_NAME, Util.PACKAGE_NAME + ".QuestionLoaderActivity");
                    startActivity(intent);
                }
                else {
                    GetDatabaseLocation();
                }
            }
        });

        populateAdapter();

        MobileAds.initialize(this); // admob app id for cbse-6

        addBannerAd();
		findViewById(R.id.adView).setVisibility(View.VISIBLE);
		findViewById(R.id.openPlaystore).setVisibility(View.GONE);

        if(Util.AdDetail == null) {
            FirebaseManager.readAds(value -> {

            });
        }
	}

    @Override
    public void onStop()
    {
        if(runnableStarted) {
            handler.removeCallbacks(runnable);
        }
        super.onStop();
    }

    private void startHcfCalculatorActivity()
    {
        Util.Subject = "";
        Intent intentGetMore = new Intent(SubjectList.this, HcfCalculatorActivity.class);
        startActivity(intentGetMore);
    }

    private void startGetMoreActivity()
    {
        Util.Subject = "";
        Intent intentGetMore = new Intent(SubjectList.this, GetMore.class);
        startActivity(intentGetMore);
    }

    private void saveLastCloudVersion(int cloudVersion)
    {
        SharedPreferences.Editor prefEdit = _sharedPreferences.edit();
        prefEdit.putInt("CloudVersion", cloudVersion);
        prefEdit.apply();
    }

	private void addBannerAd()
	{
		AdView mAdView = findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder()
				.build();
		mAdView.loadAd(adRequest);
	}

	private void openDatabase()
	{
		_databaseHelper = new DatabaseHelper(getApplicationContext());
		try
		{
			_databaseHelper.createDataBase();
		}
		catch(IOException e)
        {
            Log.d("CBSE_ERROR_OPENDATABASE", e.getMessage());
        }

		_databaseHelper.openDataBase();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if (id == R.id.menu_settings) {
			openSettingsPage();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void openSettingsPage()
	{
		Intent subPage = new Intent();
		subPage.setClassName(Util.PACKAGE_NAME, Util.PACKAGE_NAME + ".SettingsActivity");
		startActivity(subPage);
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
                    question = FractionSeriesQuestionGenerator.generateQuestion();
                    break;

                case STORY_TYPE:
                    question = FractionStoryQuestionGenerator.generateRemainingQuestion();
                    break;

                case FRACTION_WITH_AGE:
                    question = FractionAgeQuestionGenerator.generateQuestion();
                    break;

                case FRACTION_OF_NUMBER:
                    question = FractionOfNumberQuestionGenerator.generateQuestion();
                    break;

                case FRACTION_OF_MEASUREMENT_DATA:
                    question = FractionOfMeasurementQuestionGenerator.generateQuestion();
                    break;

                case FRACTION_TIME_STORY:
                    question = FractionTimeStoryQuestionGenerator.generateQuestion();
                    break;

                case FRACTION_TRUE_FALSE:
                    question = FractionTrueFalseQuestionGenerator.generateQuestion();
                    break;

                case FRACTION_CONCEPTS:
                    question = FractionConceptQuestionGenerator.generateQuestion();
                    break;

                case EQUIVALENT_FRACTIONS:
                    //checked
                    question = EquivalentFractionQuestionGenerator.generateQuestion();
                    break;

                case FRACTION_COMPARISON:
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

    private void addGeneratedQuestionsForMaths()
    {
        addQuestionsForChapterOne();
        addQuestionsForChapterTwo();
        addQuestionsForChapterThree();
        addQuestionsForChapterFour();
        addQuestionsForChapterFive();
        addQuestionsForChapterSix();
        addQuestionsForChapterSeven();;
        addQuestionsForChapterEight();
        addQuestionsForChapterNine();
        addQuestionsForChapterTen();
        addQuestionsForChapterEleven();
        addQuestionsForChapterTwelve();
        addQuestionsForChapterThirteen();
    }

	public void openChapters(String questionSet)
	{
        if(Util.Subject.equalsIgnoreCase("maths")) {
            Util.allQuestions.clear();
            addGeneratedQuestionsForMaths();
        }
        else if(Util.Subject.equalsIgnoreCase("science"))
        {
            //addScienceQuestions();
        }
        else if(Util.Subject.equalsIgnoreCase("computers"))
        {
            //addComputerQuestions();
        }

		Intent chapterIntent = new Intent();
		chapterIntent.setClassName(Util.PACKAGE_NAME, Util.PACKAGE_NAME + ".Chapters");
		chapterIntent.putExtra("question_set", questionSet);
		startActivity(chapterIntent);
	}
	
	private int getRandomQuestionSet()
	{
		Random random = new Random();
		return random.nextInt(9) + 11;
	}

	public void onClickOpenPlayStore(View view)
	{
        openPlayStore();
	}

    private void openPlayStore()
    {
        try {
            //saveIfAdClicked();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=com.myAgeEducation.cbseClass6NewPaid"));
            startActivity(intent);
        }
        catch(Exception e)
        {
            Util.displayAlert("Cannot open play store. Open play store manually and search for CBSE Class 5", "Error", SubjectList.this);
        }
    }

    private void openPlayStoreForRating()
    {
        try {
            //saveIfAdClicked();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(Util.AppUriInPlayStore));
            startActivity(intent);
        }
        catch(Exception e)
        {
            Util.displayAlert("Cannot open play store. Open play store manually and search for CBSE Class 5", "Error", SubjectList.this);
        }
    }

    private void shareAppLink()
    {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = Util.ShareLinkTitle + System.getProperty("line.separator") + Util.PlayStoreLink;
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, Util.ShareLinkTitle);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
        //setShareIntent(sharingIntent);
    }

    private void openOfflineVersionActivity()
    {
        try {
            Intent subPage = new Intent();
            subPage.setClassName(Util.PACKAGE_NAME, Util.PACKAGE_NAME + ".OfflineVersionActivity");
            startActivity(subPage);
        }
        catch(Exception e)
        {
            Util.displayAlert(e.getMessage(), "Error", SubjectList.this);
        }
    }

    private void DownloadQuestionsOnlyIfAllowed()
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("questionDatabaseVersion/cbse/settings/disableDownload");
        //Firebase ref = new Firebase("schools/question_database_version/cbse/settings/disableDownload");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                int temp = snapshot.getValue(Integer.class);
                int isDownloadDisabled = 0;
                try
                {
                    isDownloadDisabled = temp;
                    Log.d("CBSE_isDownloadDisabled", String.valueOf(isDownloadDisabled));
                }

                catch(Exception e)
                {
                    Log.d("CBSE_Exception", e.getMessage());
                }

                if(isDownloadDisabled == 1) // download is disabled
                {
                    ArrayList<Integer> downloadedSets = _databaseHelper.getDownloadedQuestionSets(Util.Subject);
                    int downloadedSetsSize = downloadedSets.size();

                    // :-( no sets available, must download
                    if(downloadedSetsSize == 0)
                    {
                        downloadQuestions(_downloadLinks.get(0));
                    }
                    else // some downloads are available, will use the downloaded sets
                    {
                        Log.d("CBSE_downloaddisabled", "Download disabled, using local database, size is:" + String.valueOf(downloadedSetsSize));
                        Random random = new Random();
                        _randomQuestionSet = random.nextInt(downloadedSets.size());
                        _randomQuestionSet = downloadedSets.get(_randomQuestionSet);

                        runnable = () -> {
                            runnableStarted = true;
                            readQuestionsFromLocalDatabase();
                            runnableStarted = false;
                        };
                        new Thread(runnable).start();
                    }
                }
                else
                {
                    downloadQuestions(_downloadLinks.get(0));
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
                //ringProgressDialog.dismiss();
                Log.d("Exception: ", firebaseError.getMessage());
                //displayAlert("Unable to connect", "Unable to connect to the server. Make sure you are connected to the internet and try again");
            }
        });
    }

	private void GetCloudQuestionDatabaseVersion()
	{
        showProgressDialog("Checking database for newer version...");
		FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
		DatabaseReference databaseReference = firebaseDatabase.getReference("questionDatabaseVersion/cbse/" + Util.Subject +"/cbseClass" + Util.GRADE);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot snapshot) {
				int version = snapshot.getValue(Integer.class);

                dismissProgressDialog();
				Log.d("myVersion", String.valueOf(version));

				try
				{
                    _cloudVersion = version;
                    saveLastCloudVersion(_cloudVersion);
					Log.d("CloudVersion", String.valueOf(_cloudVersion));
				}

				catch(Exception e)
				{
					Log.d("CloudVersionException", e.getMessage());
		    	}

                try {
                    int localVersion = _databaseHelper.getLocalQuestionDatabaseVersion(Util.Subject);
                    Log.d("CBSE_LocalVersion", String.valueOf(localVersion));

                    if (_cloudVersion > localVersion) {
                        Log.d("CBSE_CloudVersion", String.valueOf(_cloudVersion));
                        Log.d("CBSE_CloudVersionInfo", "Cloud Version is greater than local version");
                        _databaseHelper.resetDownloadStatus(Util.Subject);
                    }
                }
                catch(Exception e)
                {
                    Util.displayAlert("ERROR-121: " + e.getMessage(), "ERROR-121", SubjectList.this);
                }

                getQuestions();
			}

			@Override
			public void onCancelled(DatabaseError firebaseError) {
                dismissProgressDialog();
				Log.d("CBSE_Exception: ", firebaseError.getMessage());
                Util.displayAlert("Unable to connect to the server. Make sure you are connected to the internet and try again","Unable to connect", SubjectList.this);
            }
		});
	}

    private void getQuestions()
    {
        _randomQuestionSet = getRandomQuestionSet();
        _pendingDownloads = _databaseHelper.pendingDownloads(Util.Subject, _randomQuestionSet);
        Log.d("CBSE_PendingDownloads", String.valueOf(_pendingDownloads.size()));

        if(_pendingDownloads.size() > 0)
        {
            addDownloadLinksToDownload();
            DownloadQuestionsOnlyIfAllowed();
        }
        else {
            try {
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        runnableStarted = true;
                        readQuestionsFromLocalDatabase();
                        runnableStarted = false;
                    }
                };
                new Thread(runnable).start();
            }

            catch(Exception e)
            {
                Util.displayAlert("reading questions from local database failed. " + e.getMessage(), "Error", SubjectList.this);
            }
        }
    }

	public void readQuestionsFromLocalDatabase()
	{
		String tableName = Util.SCHOOL_NAME + "_" + Util.Subject;
		if(Util.allQuestions != null) {
            Util.allQuestions.clear();
        }
		Util.allQuestions = _databaseHelper.getAllQuestions(tableName.toUpperCase(), _randomQuestionSet);
        if(Util.allQuestions == null)
        {
            //Something went wrong, the database has returned null .. will use the questions from the cloud .. hope it does not rain
            //String downloadLink = Util.SubjectRoot + "/" + Util.Subject + "/set" + String.valueOf(_randomQuestionSet);
            String downloadLink = Util.Subject + "/set" + String.valueOf(_randomQuestionSet);
            downloadQuestions(downloadLink);
            return;
        }
		Log.d("CBSE_QuestionSet", String.valueOf(_randomQuestionSet));
		Log.d("CBSE_QuestionCount", String.valueOf(Util.allQuestions.size()));

		if(Util.allQuestions.size() > 0) {
			{
				openChapters("set" + String.valueOf(_randomQuestionSet));
			}
		}
	}

	private void GetDatabaseLocation()
    {
        GetCloudQuestionDatabaseVersion();

        /*showProgressDialog("Connecting to the online database..., make sure you are connected to the net");
        Firebase.goOnline();

        Firebase ref = new Firebase(Util.Firebase_DatabaseLocationSetting);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String database_location = snapshot.getValue(String.class);
                if(database_location.trim().isEmpty())
                {
                    database_location = Util.DefaultDatabaseLocation;
                }

                AssignFirebaseLocations(database_location);

                dismissProgressDialog();

                Log.d("CBSE_DatabaseLocation", Util.DatabaseLocation);

                //if(!isLastAccessToday())
                {
                    GetCloudQuestionDatabaseVersion();
                }
                //else
                {
                    //_cloudVersion = GetLocallySavedCloudVersion();
                    //getQuestions();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                dismissProgressDialog();
                Log.d("CBSE_Exception: ", firebaseError.getMessage());
                Util.displayAlert("Unable to connect to the server. Make sure you are connected to the internet and try again", "Unable to connect", SubjectList.this);
            }
        });*/
    }

    private void addQuestionToLocalDatabase(String downloadLink) {
        isAddToLocalDatabaseCompleted = false;
        String tokens[] = downloadLink.split("/");
        String subject = tokens[SUBJECT_INDEX];
        String set = tokens[QUESTION_SET_INDEX]; // will be of the form SetNN (NN = 11 to NN = 19)
        int setNumber = Integer.parseInt(set.substring(set.length() - 2)); // get the last 2 chars

        ArrayList<Question> questions = (ArrayList<Question>)_questionList.clone();
        _questionList.clear();

        if (_databaseHelper.addQuestions("CBSE_" + subject, questions, setNumber)) {
            _databaseHelper.updateDownloadStatus(subject, setNumber);
            if(_cloudVersion > 0) {
                _databaseHelper.updateLocalQuestionDatabaseVersionInfo(subject, _cloudVersion);
            }
        }

        isAddToLocalDatabaseCompleted = true;
    }

    private void showProgressDialog(final String message)
    {
        runOnUiThread(() -> {
            try {
                ringProgressDialog.setTitle("Please wait ...");
                ringProgressDialog.setMessage(message);
                ringProgressDialog.setCancelable(false);
                ringProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                if (ringProgressDialog != null && (!ringProgressDialog.isShowing())) {
                    ringProgressDialog.show();
                    ringProgressDialog.setCancelable(true);
                }
            }
            catch (Exception e)
            {
            }
        });
    }

    private void dismissProgressDialog()
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(ringProgressDialog!=null && ringProgressDialog.isShowing()) {
                    ringProgressDialog.dismiss();
                }
            }
        });
    }

    public void downloadQuestions(final String downloadLink)
    {
        showProgressDialog("Connecting to online question database...");
        //Firebase ref = new Firebase(downloadLink);
        //Query queryRef = ref.orderByChild("chapter");

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(downloadLink);
        Query queryRef = databaseReference.orderByChild("chapter");

        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        try {
                            Question question = postSnapshot.getValue(Question.class);
                            _questionList.add(question);
                        } catch (Exception e) {
                            Log.d("CBSE_ERROR", Objects.requireNonNull(e.getMessage()));
                        }
                    }

                    Log.d("CBSE_", String.valueOf(_questionList.size()) + " were downloaded");

                    if (!_questionList.isEmpty()) {
                        Util.allQuestions = (ArrayList<Question>) _questionList.clone();
                        if (isAddToLocalDatabaseCompleted)  // if the previous addition is completed, then only we add this, otherwise just ignore adding this set
                        {
                            Runnable runnable = new Runnable() {
                                @Override
                                public void run() {
                                    addQuestionToLocalDatabase(downloadLink);
                                }
                            };
                            new Thread(runnable).start();
                        }
                        else {
                            _questionList.clear();
                        }
                    } else {
                        Util.allQuestions.clear();
                        dismissProgressDialog();
                        Util.displayAlert("No questions available for this subject", "Questions not available", SubjectList.this);
                        return;
                    }

                    dismissProgressDialog();

                    openChapters("set" + _randomQuestionSet);
                }
                catch(Exception e)
                {
                    Util.displayAlert(e.getMessage(), "ERROR_SUB_589", SubjectList.this);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError firebaseError) {
                Log.d("Exception: ", firebaseError.getMessage());
            }
        });
    }

    private void addDownloadLinksToDownload()
    {
        _downloadLinks.clear();
        final String subjectRoot = Util.SubjectRoot;

        for(int i = 0; i < _pendingDownloads.size(); i++)
        {
            _downloadLinks.add(_pendingDownloads.get(i));
            Log.d("CBSE_PENDING_DOWNLOADS", _pendingDownloads.get(i));
        }
    }

	private void populateAdapter() {
		ArrayList<Integer> subjectImage = new ArrayList<>();
		ArrayList<String> subjectName = new ArrayList<>();
		ArrayList<String> tagLine = new ArrayList<>();

		subjectImage.add(SCIENCE, R.drawable.science);
		subjectImage.add(MATHS, R.drawable.maths);
		subjectImage.add(COMPUTERS, R.drawable.computers);
		subjectImage.add(GK, R.drawable.gk);
		subjectImage.add(HCF_LCM_CALCULATOR, R.drawable.ic_hcf_lcm_calculator);
		subjectImage.add(SCORE, R.drawable.score);
        subjectImage.add(SHARE_APP_LINK, R.drawable.share);
        subjectImage.add(APP_RATING, R.drawable.rating);
        subjectImage.add(GETMORE, R.drawable.getmore);
        subjectImage.add(EXIT, R.drawable.exit);

		//subjectName.add(CONTEST, "Knowledge Contest");
		subjectName.add(SCIENCE, "Science");
		subjectName.add(MATHS, "Maths");
		subjectName.add(COMPUTERS, "Computers");
		subjectName.add(GK, "GK");
        subjectName.add(HCF_LCM_CALCULATOR, "HCF-LCM Calculator");
		subjectName.add(SCORE, "Score");
        subjectName.add(SHARE_APP_LINK, "Share App Link");
        subjectName.add(APP_RATING, "Rate this app");
        subjectName.add(GETMORE, "Get More");
        subjectName.add(EXIT, "Exit");

        //tagLine.add(CONTEST, "Participate and win exciting prizes.");
        tagLine.add(SCIENCE, "");
        tagLine.add(MATHS, "");
        tagLine.add(COMPUTERS, "");
        tagLine.add(GK, "");
        tagLine.add(HCF_LCM_CALCULATOR, "");
        tagLine.add(SCORE, "");
        tagLine.add(SHARE_APP_LINK, "");
        tagLine.add(APP_RATING, "");
        tagLine.add(GETMORE, "");
        tagLine.add(EXIT, "");

        BaseAdapter _listAdapter = new ListViewAdapterForSubjectList(SubjectList.this, subjectImage, subjectName, tagLine);
		_listView.setAdapter(_listAdapter);
	}

    /*private void saveIfShareButtonClicked() {
        Firebase.goOnline();
        String ShareButtonClickedReportRoot = "https://schooltests.firebaseio.com/sharebuttonclicked";
        Firebase ref = new Firebase(ShareButtonClickedReportRoot);
        Firebase childRef = ref.child("000_lastSharedButtonClicked-" + Util.ClassName);
        childRef.setValue(Util.getCurrentDateTime());
        childRef = ref.child(UUID.randomUUID().toString());
        childRef.setValue(Util.ClassName + "/" + Util.getCurrentDateTime());
    }*/
}
