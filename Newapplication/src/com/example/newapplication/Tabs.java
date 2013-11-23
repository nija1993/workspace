package com.example.newapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class Tabs extends Activity implements OnClickListener,Runnable{

	TabHost th;
	Button add,Start,Stop;
	long start,stop;
	TextView text;
	int millis,seconds,minutes;
	Thread t=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		add=(Button)findViewById(R.id.bAddTab);
		Start=(Button)findViewById(R.id.bStartClock);
		Stop=(Button)findViewById(R.id.bStopClock);
		text=(TextView)findViewById(R.id.textView1);
		start=stop=0;
		add.setOnClickListener(this);
		Start.setOnClickListener(this);
		Stop.setOnClickListener(this);
		th=(TabHost)findViewById(R.id.tabhost);
		th.setup();
		TabSpec specs=th.newTabSpec("tab1");
		specs.setContent(R.id.tab1);
		specs.setIndicator("Tab 1");
		th.addTab(specs);
		specs=th.newTabSpec("tab2");
		specs.setContent(R.id.tab2);
		specs.setIndicator("Tab 2");
		th.addTab(specs);
		specs=th.newTabSpec("tab3");
		specs.setContent(R.id.tab3);
		specs.setIndicator("Tab 3");
		th.addTab(specs);
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		start=0;start=0;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.bAddTab:
			TabSpec ourSpec=th.newTabSpec("tag");
			ourSpec.setContent(new TabHost.TabContentFactory() {
				
				@Override
				public View createTabContent(String tag) {
					// TODO Auto-generated method stub
					TextView tv=new TextView(Tabs.this);
					tv.setText("You have created a new tab");
					return (tv);
				}
			});
			ourSpec.setIndicator("new");
			th.addTab(ourSpec);
			break;
		case R.id.bStartClock:
			start=System.currentTimeMillis();
			t=new Thread();
			runOnUiThread(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					
				}
				
			});
			break;
		case R.id.bStopClock:
			while(true){
			try {
				if(t.isAlive())
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}break;
			}t=null;
			break;
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		stop=System.currentTimeMillis();
		millis=(int) ((stop-start)%1000);
		seconds=(int) ((start-stop)/1000)%60;
		minutes=(int)(start-stop)/60000;
		
		if(start!=0)
			text.setText(String.format("%d:%d:%d",minutes,seconds,millis));

	}

}
