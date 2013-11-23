package com.example.newapplication;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

@SuppressWarnings("deprecation")
public class Slider extends Activity implements OnClickListener, OnCheckedChangeListener, OnDrawerOpenListener{

	Button b1,b2,b3,b4;
	SlidingDrawer sd;
	CheckBox cb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sliding);
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		b3=(Button)findViewById(R.id.button3);
		b4=(Button)findViewById(R.id.button4);
		cb=(CheckBox)findViewById(R.id.checkBox1);
		sd=(SlidingDrawer)findViewById(R.id.sliding);
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);
		cb.setOnCheckedChangeListener(this);
		sd.setOnDrawerOpenListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.button1:
			sd.open();
			break;
		case R.id.button2:
			
			break;
		case R.id.button3:
			sd.toggle();
			break;
		case R.id.button4:
			sd.close();
		}
	}
	@Override
	public void onDrawerOpened() {
		// TODO Auto-generated method stub
		MediaPlayer mp=MediaPlayer.create(this, R.raw.explosion);
		mp.start();
	}
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		if(buttonView.isChecked()){
			sd.lock();
		}else{
			sd.unlock();
		}
	}

}
