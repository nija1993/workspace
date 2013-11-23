package com.example.newapplication;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Text extends Activity implements View.OnClickListener{

	Button chkcmd;
	ToggleButton tb;
	EditText et;
	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text);
		chkcmd=(Button) findViewById(R.id.bCommand);
		tb=(ToggleButton)findViewById(R.id.toggleButton1);
		et=(EditText)findViewById(R.id.etCommand);
		tv=(TextView)findViewById(R.id.tvCommand);
		tb.setOnClickListener(this);
		chkcmd.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bCommand:
			String text=et.getText().toString();
			if(text.equals("left")){
				tv.setGravity(Gravity.LEFT);
			}else if(text.equals("center")){
				tv.setGravity(Gravity.CENTER);
			}else if(text.equals("right")){
				tv.setGravity(Gravity.RIGHT);
			}else if(text.equals("blue")){
				tv.setTextColor(Color.BLUE);
			}else if(text.equals("random")){
				Random random=new Random();
				tv.setTextSize(random.nextInt(75));
				tv.setTextColor(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
			}else{
				tv.setText("Invalid");
			}
			break;
		case R.id.toggleButton1:
			if(tb.isChecked()){
				et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
			}else{
				et.setInputType(InputType.TYPE_CLASS_TEXT);
			}
			break;
		}
	}

}
