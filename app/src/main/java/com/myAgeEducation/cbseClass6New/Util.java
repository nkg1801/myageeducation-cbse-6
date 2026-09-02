package com.myAgeEducation.cbseClass6New;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myAgeEducation.cbsecommon.AdData;
import com.myAgeEducation.cbsecommon.Question;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Random;

public class Util
{
    static Ads AdDetail;
    static String Android_id = "";
	static ArrayList<Question> allQuestions = new ArrayList<>();
	static ArrayList<Question> filteredQuestions = new ArrayList<>();
	static ArrayList<Question> revisionQuestions = new ArrayList<>();

    static String UserUid = "";
    static String ClassName = "class-6";
    static String SyllabusAndGrade = "cbse-6";
    static final String SCHOOL_NAME = "CBSE";

    static String forLogD = "";
    static AdData adData;
    static String UserNamePrefix = "cbse6";
    static String TestName = "cbse6";
    static String AppUriInPlayStore = "market://details?id=com.myAgeEducation.cbseClass6New";
    static boolean isFullPageAdDisplayed = false;

    /// Firebase related
    static String DefaultDatabaseLocation = "";
    static String FirebaseRoot = "";
    static String Firebase_DatabaseLocationSetting = "settings/database_location";
    static String DatabaseLocation = DefaultDatabaseLocation;
    static String TestReportRoot = "testReport/cbse/" + ClassName;
    static String UserRoot = "users/" + SCHOOL_NAME.toLowerCase() + "/" + ClassName;
    //static String SubjectRoot = "schools/" + SCHOOL_NAME.toLowerCase() + "/" + ClassName;
    static String SubjectRoot = "";

    ///================ Contest related============================================
    public static String ContestClassRoot = "contest/" + SyllabusAndGrade;
    public static String ContestUserRoot = "contest/users";
    public static int TestTimeOut = 15; // timeout in minute
    public static boolean IsUserSignedIn = false;
    public static boolean IsContestTest = false;
    public static int QuestionCountForContest = 25;
    public static LinkedHashMap SubjectTestAttemptDetailsMaps = new LinkedHashMap();
    public static int CurrentDate = 0;
    public static Date ServerDate;
    public static ArrayList<PojoPrizeDetails> Pojo_prizeDetails = new ArrayList<>();
    //================== contest related data ends here===============================

    public static String AdDataRoot = SubjectRoot + "/extras/ads/activeAd";

    public static final String PACKAGE_NAME = "com.myAgeEducation.cbseClass6New";

    public static final String GRADE = "6";
    public static String Subject = "";
    public static boolean isFreeApp = true;
    public static boolean isReleaseVersion = true;
    public static final String AdMobInterstitialAdUnitId = "ca-app-pub-4837855590190532/5532474346"; //cbse class-6 - New
    public static final String AdMobInterstitialAdUnitDummyId = "ca-app-pub-3940256099942544/1033173712";
    //public static final String ADMOB_APP_ID = "ca-app-pub-4837855590190532~3924821159"; // class-6

    public static final String PlayStoreLink = "https://play.google.com/store/apps/details?id=com.myAgeEducation.cbseClass" + GRADE + "New";
    public static final String ShareLinkTitle = "Link for " + SCHOOL_NAME + "-" + GRADE + " app download";
    public static Double ServerTime;
    public static String ServerTimeInMonth = "";

    public static int getRandomQuestionSet()
    {
        Random random = new Random();
        return random.nextInt(9) + 11;
    }

    public static String getCurrentDateTime()
    {
        Date date = new Date();
        SimpleDateFormat monthFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");
        return monthFormat.format(date);
    }

    public static String getCurrentTime()
    {
        Date date = new Date();
        SimpleDateFormat monthFormat = new SimpleDateFormat("hh:mm a");
        return monthFormat.format(date);
    }

    public static String getCurrentDate()
    {
        Date date = new Date();
        SimpleDateFormat monthFormat = new SimpleDateFormat("dd-MM-yyyy");
        return monthFormat.format(date);
    }

    public static void displayAlert(String message, String title, Context context)
    {
        if(!((Activity)context).isFinishing()) {
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setMessage(message);
            alert.setTitle(title);
            alert.setPositiveButton("OK", null);
            alert.setCancelable(true);
            alert.create().show();
        }
    }

    public static Bitmap LoadBitmapFromBase64Encoding(String imageData)
    {
        imageData = imageData.replace("data:image/png;base64,",""); // introduced in Release 1.19
        byte[] decodedString = Base64.decode(imageData, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}
