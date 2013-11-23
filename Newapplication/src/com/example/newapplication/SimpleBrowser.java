package com.example.newapplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SimpleBrowser extends Activity implements OnClickListener{

	EditText url;
	WebView browser;
	Button back,forward,refresh,clear,go;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simplebrowser);
		browser=(WebView)findViewById(R.id.wvBrowser);
		browser.getSettings().setJavaScriptEnabled(true);
		browser.getSettings().setUseWideViewPort(true);
		browser.getSettings().setLoadWithOverviewMode(true);
		browser.getSettings().setDisplayZoomControls(true);
		browser.getSettings().setBuiltInZoomControls(true);		
		browser.setWebViewClient(new WebViewClient());
		browser.loadUrl("http://google.com");
		url=(EditText)findViewById(R.id.etUrl);
		back=(Button)findViewById(R.id.bBack);
		forward=(Button)findViewById(R.id.bForward);
		refresh=(Button)findViewById(R.id.bRefresh);
		clear=(Button)findViewById(R.id.bHistory);
		go=(Button)findViewById(R.id.bGo);
		back.setOnClickListener(this);
		forward.setOnClickListener(this);
		refresh.setOnClickListener(this);
		clear.setOnClickListener(this);
		go.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bBack:
			if(browser.canGoBack())
				browser.goBack();
			break;
		case R.id.bForward:
			if(browser.canGoForward())
				browser.goForward();
			break;
		case R.id.bRefresh:
			browser.reload();
			break;
		case R.id.bHistory:
			browser.clearHistory();
			break;
		case R.id.bGo:
			browser.loadUrl(url.getText().toString());
			//Hiding the keyboard
			InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(url.getWindowToken(), 0);
			break;
		}
	}

}
