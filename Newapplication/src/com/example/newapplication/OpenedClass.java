package com.example.newapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class OpenedClass extends Activity implements OnClickListener,
		OnCheckedChangeListener {

	TextView send, outcome;
	Button Return;
	RadioGroup rg;
	String getBread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send);
		initialize();
		Bundle getBasket=getIntent().getExtras();
		getBread=getBasket.getString("key");
		send.setText(getBread);
	}

	private void initialize() {
		// TODO Auto-generated method stub
		send = (TextView) findViewById(R.id.tvSend);
		outcome = (TextView) findViewById(R.id.tvOutcome);
		Return = (Button) findViewById(R.id.bReturn);
		rg = (RadioGroup) findViewById(R.id.rgTalent);
		Return.setOnClickListener(this);
		rg.setOnCheckedChangeListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent=new Intent();
		Bundle basket2=new Bundle();
		basket2.putString("text", outcome.getText().toString());
		intent.putExtras(basket2);
		setResult(RESULT_OK,intent);
		finish();
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		case R.id.radio0:
			outcome.setText("1");

			break;
		case R.id.radio1:
			outcome.setText("2");
			break;
		case R.id.radio2:
			outcome.setText("3");
			break;

		}
	}

}
