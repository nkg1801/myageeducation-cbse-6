package com.myAgeEducation.cbseClass6New;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class SupportiveTextLargeView extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Using login_signup as diagnostic layout
        setContentView(com.myAgeEducation.cbseClass6New.R.layout.login_signup);
        setSupportiveTextAndQuestion();
    }

    private void setSupportiveTextAndQuestion()
    {
        Bundle bundle = getIntent().getExtras();
        String supportiveText = bundle.getString("SupportiveText", "");
        String question = bundle.getString("Question", "");
        // ((TextView)findViewById(com.myAgeEducation.cbseClass6New.R.id.textViewSupportiveText)).setText(supportiveText);
        // ((TextView)findViewById(com.myAgeEducation.cbseClass6New.R.id.textViewQuestion)).setText(question);
    }

    public void onClickBackToQuestion(View view)
    {
        finish();
    }
}
