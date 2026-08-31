package com.myAgeEducation.cbseClass6New;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
//import android.view.MenuInflater;
import android.view.MenuItem;

public class PointSettings extends Activity 
{
	private String array_spinner[];
	private String array_spinner2[];
	
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(com.myAgeEducation.cbseClass6New.R.layout.login_signup);
		
		/*array_spinner = getResources().getStringArray(com.myAgeEducation.cbseClass6New.R.array.subjects);

		Spinner s = findViewById(com.myAgeEducation.cbseClass6New.R.id.spinnerSubject);
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
		android.R.layout.simple_spinner_item, array_spinner);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s.setAdapter(adapter1);
		
		array_spinner2 = getResources().getStringArray(com.myAgeEducation.cbseClass6New.R.array.chapters);
		
		s = findViewById(com.myAgeEducation.cbseClass6New.R.id.spinnerAnyNChapters);
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
		android.R.layout.simple_spinner_item, array_spinner2);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s.setAdapter(adapter2);
		
		array_spinner2 = getResources().getStringArray(com.myAgeEducation.cbseClass6New.R.array.chapters);
		
		s = findViewById(com.myAgeEducation.cbseClass6New.R.id.spinnerChapterFrom);
		adapter2 = new ArrayAdapter<String>(this,
		android.R.layout.simple_spinner_item, array_spinner2);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s.setAdapter(adapter2);
		
		array_spinner2 = getResources().getStringArray(com.myAgeEducation.cbseClass6New.R.array.chapters);
		
		s = findViewById(com.myAgeEducation.cbseClass6New.R.id.spinnerChapterTo);
		adapter2 = new ArrayAdapter<String>(this,
		android.R.layout.simple_spinner_item, array_spinner2);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s.setAdapter(adapter2);
		
		array_spinner2 = getResources().getStringArray(com.myAgeEducation.cbseClass6New.R.array.percentage);
		
		s = (Spinner) findViewById(com.myAgeEducation.cbseClass6New.R.id.spinnerScore);
		adapter2 = new ArrayAdapter<String>(this,
		android.R.layout.simple_spinner_item, array_spinner2);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s.setAdapter(adapter2);
		
		addRadioButtonListener();
		addButtonListener();*/
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		//MenuInflater inflater = getMenuInflater();
		//inflater.inflate(R.menu.rewards_settings, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if (id == com.myAgeEducation.cbseClass6New.R.id.menu_clear_rewards) {
			clearRewards();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void clearRewards()
	{
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Clear Rewards?");
 
		alertDialogBuilder
				.setMessage("Click yes to exit!")
				.setCancelable(false)
				.setPositiveButton("Yes",new DialogInterface.OnClickListener() 
				{
					public void onClick(DialogInterface dialog, int id) 
					{
						File dir = getFilesDir();
						String fileName = "rewards.txt";
						File file = new File(dir, fileName);
						file.delete();
					}
				})
				.setNegativeButton("No",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						dialog.cancel();
					}
				});
 
				AlertDialog alertDialog = alertDialogBuilder.create();

				alertDialog.show();
	}
	
	public void addButtonListener()
	{
		/*Button button = findViewById(com.myAgeEducation.cbseClass6New.R.id.buttonCreate);

		button.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				if(createRewardPoints())
				{
					finish();
					openPointTablePage();
				}
			}
		});*/
	}
	
	public void openPointTablePage()
	{
		Intent subPage = new Intent();
		subPage.setClassName(Util.PACKAGE_NAME, Util.PACKAGE_NAME + ".PointsTable");
		startActivity(subPage);
	}
	
	public void addRadioButtonListener()
	{
		  /*RadioButton radioButtonAllQuestions = findViewById(com.myAgeEducation.cbseClass6New.R.id.radioButtonAllQuestions);
		  radioButtonAllQuestions.setOnClickListener(new OnClickListener(){
			  @Override
				public void onClick(View v) 
			  {
				  RadioButton radioButtonAllQuestions = findViewById(com.myAgeEducation.cbseClass6New.R.id.radioButtonAllQuestions);
				  RadioButton radioButtonMinimum = findViewById(com.myAgeEducation.cbseClass6New.R.id.radioButtonMinimum);
				  
				  if(radioButtonAllQuestions.isChecked())
				  {
					  radioButtonMinimum.setChecked(false);
				  }
			  }
		  });
		  
		  RadioButton radioButtonMinimum = findViewById(com.myAgeEducation.cbseClass6New.R.id.radioButtonMinimum);
		  radioButtonMinimum.setOnClickListener(new OnClickListener(){
			  @Override
				public void onClick(View v) 
			  {
				  RadioButton radioButtonAllQuestions = findViewById(com.myAgeEducation.cbseClass6New.R.id.radioButtonAllQuestions);
				  RadioButton radioButtonMinimum = findViewById(com.myAgeEducation.cbseClass6New.R.id.radioButtonMinimum);
				  
				  if(radioButtonMinimum.isChecked())
				  {
					  radioButtonAllQuestions.setChecked(false);
				  }
			  }
		  });
		  
		  RadioButton radioButtonAny = findViewById(com.myAgeEducation.cbseClass6New.R.id.radioButtonAny);
		  radioButtonAny.setOnClickListener(new OnClickListener(){
			  @Override
				public void onClick(View v) 
			  {
				  RadioButton radioButtonAny = findViewById(com.myAgeEducation.cbseClass6New.R.id.radioButtonAny);
				  RadioButton radioButtonFrom = findViewById(com.myAgeEducation.cbseClass6New.R.id.radioButtonFrom);
				  
				  if(radioButtonAny.isChecked())
				  {
					  radioButtonFrom.setChecked(false);
				  }
			  }
		  });
		  
		  RadioButton radioButtonFrom = findViewById(com.myAgeEducation.cbseClass6New.R.id.radioButtonFrom);
		  radioButtonFrom.setOnClickListener(new OnClickListener(){
			  @Override
				public void onClick(View v) 
			  {
				  RadioButton radioButtonAny = findViewById(com.myAgeEducation.cbseClass6New.R.id.radioButtonAny);
				  RadioButton radioButtonFrom = findViewById(com.myAgeEducation.cbseClass6New.R.id.radioButtonFrom);
				  
				  if(radioButtonFrom.isChecked())
				  {
					  radioButtonAny.setChecked(false);
				  }
			  }
		  });*/
	}
	
	public boolean createRewardPoints()
	{
		String name, subject, chapters, questions;
		name = "";
		subject = "";
		chapters = "";
		questions = "";
		
		/*EditText rewardName = findViewById(com.myAgeEducation.cbseClass6New.R.id.editTextRewardName);
		name = rewardName.getText().toString().trim();
		
		if(name.equalsIgnoreCase(""))
		{
			displayAlertBox("name can't be empty", "Name is empty");
			return false;
		}
		
		Spinner sub = findViewById(com.myAgeEducation.cbseClass6New.R.id.spinnerSubject);
		subject = sub.getSelectedItem().toString();

        if(subject.equalsIgnoreCase("Computer Science"))
        {
            subject = "cs";
        }
		
		RadioButton radioButtonAny = findViewById(com.myAgeEducation.cbseClass6New.R.id.radioButtonAny);
		RadioButton radioButtonFrom = findViewById(com.myAgeEducation.cbseClass6New.R.id.radioButtonFrom);
		RadioButton radioButtonAllQuestions = findViewById(com.myAgeEducation.cbseClass6New.R.id.radioButtonAllQuestions);
		RadioButton radioButtonMinimum = findViewById(com.myAgeEducation.cbseClass6New.R.id.radioButtonMinimum);
		
		if(!radioButtonAny.isChecked() && !radioButtonFrom.isChecked())
		{
			displayAlertBox("Please select the chapters", "Chapters not selected");
			return false;
		}
		
		if(radioButtonAny.isChecked())
		{
			Spinner spinnerAnyNChapters = findViewById(com.myAgeEducation.cbseClass6New.R.id.spinnerAnyNChapters);
			chapters = "Any-" + spinnerAnyNChapters.getSelectedItem().toString();
		}
		else if(radioButtonFrom.isChecked())
		{
			Spinner spinnerChapterFrom = findViewById(com.myAgeEducation.cbseClass6New.R.id.spinnerChapterFrom);
			Spinner spinnerChapterTo = findViewById(com.myAgeEducation.cbseClass6New.R.id.spinnerChapterTo);
			chapters = spinnerChapterFrom.getSelectedItem().toString() + "-" + spinnerChapterTo.getSelectedItem().toString();
		}
		
		if(!radioButtonAllQuestions.isChecked() && !radioButtonMinimum.isChecked())
		{
			displayAlertBox("Please select the Question Range", "Question range not selected");
			return false;
		}
		
		if(radioButtonAllQuestions.isChecked())
		{
			questions = "All";
		}
		else if(radioButtonMinimum.isChecked())
		{
			EditText questionCount = findViewById(com.myAgeEducation.cbseClass6New.R.id.editText2);
			questions = questionCount.getText().toString();
		}
		
		Spinner spinnerScore = findViewById(com.myAgeEducation.cbseClass6New.R.id.spinnerScore);
		EditText editTextPoints = findViewById(com.myAgeEducation.cbseClass6New.R.id.editTextPoints);
		
		String score = spinnerScore.getSelectedItem().toString();
		
		if(score.trim().isEmpty())
		{
			displayAlertBox("Scores can't be empty", "Score is empty");
			spinnerScore.requestFocus();
			return false;
		}
		
		String points = editTextPoints.getText().toString();
		
		if(points.trim().isEmpty())
		{
			displayAlertBox("Points can't be empty", "Points is empty");
			editTextPoints.requestFocus();
			return false;
		}
		
		String rewardString = name + ";" + subject + ";" + chapters + ";" + questions + ";" + score + ";" + points;
		String fileName = "points.txt";
		
		try
		{
			FileOutputStream fos = getApplicationContext().openFileOutput(fileName,Context.MODE_APPEND);
			
			OutputStreamWriter out = new OutputStreamWriter(fos);
			BufferedWriter bwriter = new BufferedWriter(out);
			
			bwriter.write(rewardString);
			bwriter.newLine();
			
			bwriter.close();
			out.close();
			fos.close();
		}
		
		catch(IOException io)
		{
			displayAlertBox("ERROR-501-" + io.getMessage(), "Error");
		}
		return true;*/
		return true;
	}
	
	public void displayAlertBox(String message, String title)
	{
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setMessage(message);
		alert.setTitle(title);
		alert.setPositiveButton("OK", null);
		alert.setCancelable(true);
		alert.create().show();
	}
}
