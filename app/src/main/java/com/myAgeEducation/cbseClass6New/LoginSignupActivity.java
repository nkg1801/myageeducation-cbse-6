package com.myAgeEducation.cbseClass6New;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;

import static android.content.ContentValues.TAG;

public class LoginSignupActivity extends Activity {
    SharedPreferences sharedPreferences;
    String email;
    private FirebaseAuth mAuth;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_signup);
        FirebaseApp.initializeApp(this);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        email = sharedPreferences.getString("email", "");

        if(!TextUtils.isEmpty(email.trim()))
        {
            ((EditText)(findViewById(R.id.editTextEmail))).setText(email);
			findViewById(R.id.editTextPin).requestFocus();
        }

        addBannerAd();
    }

    private void addBannerAd()
    {
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);
    }

    private void createUser(final String email, final String password)
    {
        Firebase ref = new Firebase(Util.FirebaseRoot);
        ref.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_LONG).show();
                loginAndSaveNewUserData(email, password);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                switch(firebaseError.getCode())
                {
                    case FirebaseError.EMAIL_TAKEN:
                        break;

                    default:
                        displayAlertBox("Error", firebaseError.getMessage());
                        break;
                }
            }
        });
    }

    private void saveUserData(String uid)
    {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor prefEdit = sharedPreferences.edit();
        prefEdit.putString("uuid", uid);
        prefEdit.commit();

        Util.UserUid = uid;

        String name = ((EditText)findViewById(R.id.editTextEmail)).getText().toString().trim();
        String city = ((EditText)findViewById(R.id.editTextPin)).getText().toString().trim();

        Firebase ref = new Firebase(Util.UserRoot);
        ref.child(uid).child("name").setValue(name);
        ref.child(uid).child("city").setValue(city);
        ref.child(uid).child("dateOfSignUp").setValue(Util.getCurrentDateTime());
        ref.child(uid).child("numberOfTimesTestTaken").child("science").setValue(0);
        ref.child(uid).child("numberOfTimesTestTaken").child("maths").setValue(0);
        ref.child(uid).child("numberOfTimesTestTaken").child("computers").setValue(0);
        ref.child(uid).child("numberOfTimesTestTaken").child("moralscience").setValue(0);
        ref.child(uid).child("numberOfTimesTestTaken").child("english").setValue(0);
        ref.child(uid).child("numberOfTimesTestTaken").child("gk").setValue(0);
    }

    private void loginAndSaveNewUserData(final String emailAddress, String password)
    {
        Firebase.goOnline();
        findViewById(R.id.textViewWaitingForData).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.textViewWaitingForData)).setText("Connecting to the server, please wait...");
        Firebase ref = new Firebase(Util.FirebaseRoot);

        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(emailAddress, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Util.IsUserSignedIn = true;
                            Util.UserUid = mAuth.getCurrentUser().getUid();
                            enableControl(true);
                            if (email.compareToIgnoreCase(emailAddress) != 0) {
                                saveUserInfoLocally(emailAddress);
                            }
                            findViewById(R.id.textViewWaitingForData).setVisibility(View.INVISIBLE);
                            Intent returnIntent = new Intent();
                            returnIntent.putExtra("result", "login_successful");
                            setResult(Activity.RESULT_OK, returnIntent);
                            finish();
                        }
                        else
                        {
                            Firebase.goOffline();
                            enableControl(true);
                            String errorMessage = task.getException().getMessage();
                            findViewById(R.id.textViewWaitingForData).setVisibility(View.VISIBLE);
                            ((TextView)findViewById(R.id.textViewWaitingForData)).setText("Login failed: " + errorMessage);
                        }
                    }
                });

        /*ref.authWithPassword(emailAddress, password, new Firebase.AuthResultHandler(){
            @Override
            public void onAuthenticated(AuthData authData){
                Firebase.goOffline();
                Util.IsUserSignedIn = true;
                Util.UserUid = authData.getUid();
                enableControl(true);
                if(email.compareToIgnoreCase(emailAddress) != 0)
                {
                    saveUserInfoLocally(emailAddress);
                }
                findViewById(R.id.textViewWaitingForData).setVisibility(View.INVISIBLE);
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", "login_successful");
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError){
                Firebase.goOffline();
                enableControl(true);
                String errorMessage = "";
                switch (firebaseError.getCode())
                {
                    case FirebaseError.USER_DOES_NOT_EXIST:
                    case FirebaseError.INVALID_PASSWORD:
                        errorMessage = "Invalid email and/or password";
                        break;

                    default:
                        errorMessage = firebaseError.getMessage() + ". You may try after sometime";
                        break;
                }
                //displayAlertBox("Error", firebaseError.getMessage());
                findViewById(R.id.textViewWaitingForData).setVisibility(View.VISIBLE);
                ((TextView)findViewById(R.id.textViewWaitingForData)).setText("Login failed: " + errorMessage);
            }
        });*/
    }

	public void displayAlertBox(String title, String message)
    {
        if(!LoginSignupActivity.this.isFinishing()) {
            Util.displayAlert(message, title, LoginSignupActivity.this);
        }
    }

    private void enableControl(boolean isEnable)
    {
        Button signupButton = findViewById(R.id.buttonRegister);
        Button buttonLogin = findViewById(R.id.buttonLogin);

        signupButton.setEnabled(isEnable);
        buttonLogin.setEnabled(isEnable);
        findViewById(R.id.editTextEmail).setEnabled(isEnable);
        findViewById(R.id.editTextPin).setEnabled(isEnable);

        if(isEnable)
        {
            signupButton.setBackgroundColor(Color.BLACK);
            buttonLogin.setBackgroundColor(Color.BLACK);
        }
        else
        {
            signupButton.setBackgroundColor(Color.GRAY);
            buttonLogin.setBackgroundColor(Color.GRAY);
        }
    }

    public void onClickButtonRegister(View view)
    {
        Intent intentRegistration = new Intent(LoginSignupActivity.this, Registration.class);
        startActivity(intentRegistration);
    }

    public void onClickButtonLogin(View view)
    {
        enableControl(false);
        String email = ((EditText)(findViewById(R.id.editTextEmail))).getText().toString();
        String pin = ((EditText)(findViewById(R.id.editTextPin))).getText().toString();
        loginAndSaveNewUserData(email.trim(), pin);
    }

    private void saveUserInfoLocally(String email)
    {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor prefEdit = sharedPreferences.edit();
        prefEdit.putString("email", email);
        prefEdit.commit();
    }

    public void onClickForgotPin(View view)
    {
        final String email = ((EditText)(findViewById(R.id.editTextEmail))).getText().toString();
        if(TextUtils.isEmpty(email.trim()))
        {
            Util.displayAlert("Please enter your email address", "Enter email", this);
            return;
        }

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Are you sure you want to reset your PIN? A reset link will be sent to your email to reset the PIN/Password");
        alert.setTitle("Reset PIN confirmation");
        alert.setPositiveButton("Yes", null);
        alert.setCancelable(true);

        alert.setPositiveButton("Yes",new DialogInterface.OnClickListener()
        {
            public void onClick (DialogInterface dialog, int which){
                sendPasswordResetLink(email);
            }
        });

        alert.setNegativeButton("No",null);
        alert.create().show();
    }

    private void sendPasswordResetLink(String email)
    {
        final TextView textView = findViewById(R.id.textViewForgotPin);
        textView.setText("Emailing PIN/Password reset link...");
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Email sent.");
                            textView.setText("Password reset email sent to your email address, please follow the instructions to reset your PIN/Password");
                        }
                    }
                });
    }
}
