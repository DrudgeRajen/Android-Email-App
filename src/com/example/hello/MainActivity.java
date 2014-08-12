package com.example.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.Email.R;

public class MainActivity extends Activity {

	Button btnSubmit;
	EditText EditText;
	Spinner Spinner;
	CheckBox CheckBox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnSubmit = (Button) findViewById(R.id.ButtonSendFeedback);
		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final EditText nameField = (EditText) findViewById(R.id.EditTextName);
				String name = nameField.getText().toString();
				final EditText emailField = (EditText) findViewById(R.id.EditTextEmail);
				String email = emailField.getText().toString();
				final EditText feedbackField = (EditText) findViewById(R.id.EditTextFeedbackBody);
				String feedback = feedbackField.getText().toString();
				final Spinner feedbackSpinner = (Spinner) findViewById(R.id.SpinnerFeedbackType);
				String feedbackType = feedbackSpinner.getSelectedItem()
						.toString();
				final CheckBox responseCheckBox = (CheckBox) findViewById(R.id.CheckBoxResponse);
				boolean bRequiresResponse = responseCheckBox.isChecked();
				if(bRequiresResponse)
				{
				final Intent emailIntent = new Intent(Intent.ACTION_SEND);
				emailIntent.putExtra(Intent.EXTRA_EMAIL,
						new String[] { "festival.bhetu@gmail.com" });
				emailIntent.putExtra(Intent.EXTRA_SUBJECT, feedbackType);
				emailIntent.putExtra(Intent.EXTRA_TEXT, feedback);
				emailIntent.setType("plain/text");
				try{
				startActivity(Intent.createChooser(emailIntent, "Send mail..."));
				}
				catch(android.content.ActivityNotFoundException ex){
					Toast.makeText(getApplicationContext(),"There is no email clients installed .",Toast.LENGTH_SHORT).show();
				}
				}
				else
				{
					Toast.makeText(getApplicationContext(),"Sorry You can only send the feedback through mail .",Toast.LENGTH_LONG).show();
				}

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
