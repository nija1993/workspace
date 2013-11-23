package com.example.newapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Data extends Activity implements View.OnClickListener{
	EditText et;
	Button bsa,bsafr;
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		et=(EditText)findViewById(R.id.etSend);
		bsa=(Button)findViewById(R.id.bSA);
		bsafr=(Button)findViewById(R.id.bSAFR);
		tv=(TextView)findViewById(R.id.tvGet);
		bsa.setOnClickListener(this);
		bsafr.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bSA:
			String bread=et.getText().toString();
			Bundle basket=new Bundle();
			basket.putString("key", bread);
			Intent a=new Intent(Data.this,OpenedClass.class);
			a.putExtras(basket);
			startActivity(a);
			break;
		case R.id.bSAFR:
			String bread1=et.getText().toString();
			Bundle basket1=new Bundle();
			basket1.putString("key", bread1);
			Intent a1=new Intent(Data.this,OpenedClass.class);
			a1.putExtras(basket1);
			startActivityForResult(a1,0);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==RESULT_OK)
		{
			Bundle newbundle=data.getExtras();
			String text;
			text=newbundle.getString("text");
			tv.setText(text);
		}
	}

}
