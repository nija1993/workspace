package com.example.newapplication;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPrefs extends Activity implements OnClickListener{

	EditText data;
	Button load,save;
	TextView result;
	SharedPreferences ourPref;
	static public String filename="Data";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreferences);
		initialise();
		ourPref=getSharedPreferences(filename,0);
	}

	private void initialise() {
		// TODO Auto-generated method stub
		data=(EditText)findViewById(R.id.editText1);
		load=(Button)findViewById(R.id.bLoad);
		save=(Button)findViewById(R.id.bSave);
		result=(TextView)findViewById(R.id.tvloadsave);
		load.setOnClickListener(this);
		save.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.bSave:
			SharedPreferences.Editor editor=ourPref.edit();
			editor.putString("savevalue", data.getText().toString());
			editor.commit();
			break;
		case R.id.bLoad:
			ourPref=getSharedPreferences(filename,0);
			result.setText(ourPref.getString("savevalue", "Does not exist"));
			break;
		}
	}

}
