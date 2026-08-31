package com.myAgeEducation.cbseClass6New;

import android.app.Activity;
import android.os.Bundle;

public class Settings extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		// Using login_signup as diagnostic layout
		setContentView(com.myAgeEducation.cbseClass6New.R.layout.login_signup);
		// addButtonListener();
	}
	
	public void addButtonListener()
	{
		/*ImageButton mainMenu = findViewById(com.myAgeEducation.cbseClass6New.R.id.buttonMainMenu);

		mainMenu.setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(View v) 
		{
			finish();
		}
	 });*/
	}
}
