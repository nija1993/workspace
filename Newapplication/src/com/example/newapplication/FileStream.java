package com.example.newapplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FileStream extends Activity implements OnClickListener {
	EditText data;
	Button load, save;
	TextView result;
	String filename = "ourData";
	FileOutputStream fos;
	FileInputStream fis;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreferences);
		initialise();
	}

	private void initialise() {
		// TODO Auto-generated method stub
		data = (EditText) findViewById(R.id.editText1);
		load = (Button) findViewById(R.id.bLoad);
		save = (Button) findViewById(R.id.bSave);
		result = (TextView) findViewById(R.id.tvloadsave);
		load.setOnClickListener(this);
		save.setOnClickListener(this);
		/*try {
			fos = openFileOutput(filename, Context.MODE_PRIVATE);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bSave:
			try {
				fos=openFileOutput(filename,Context.MODE_PRIVATE);
				fos.write(data.getText().toString().getBytes());
				fos.close();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.bLoad:
			new loadStuff().execute(filename);
			
			break;
		}
	}
	
	public class loadStuff extends AsyncTask<String,Integer,String>{

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String collected=null;
			try {
				fis=new FileInputStream(filename);
				byte[] dataArray=new byte[fis.available()];
				while(fis.read(dataArray)!=-1){
					collected=new String(dataArray);
				}
				fis.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return collected;
		}
		
		protected void onPostExecute(String results){
			result.setText(results);
		}
	}
}
