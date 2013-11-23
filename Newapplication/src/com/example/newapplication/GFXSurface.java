package com.example.newapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GFXSurface extends Activity implements OnTouchListener{

	SurfaceClass ourSurface;
	float x,y,sx,sy,fx,fy;
	Bitmap gball;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		x=50;
		y=50;
		sx=sy=fx=fy=0;
		ourSurface=new SurfaceClass(this);
		setContentView(ourSurface);
		ourSurface.setOnTouchListener(this);
		gball=BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSurface.pause();
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ourSurface.resume();
	}

	public class SurfaceClass extends SurfaceView implements Runnable{

		SurfaceHolder holder;
		Thread t=null;
		boolean running=true;
		public SurfaceClass(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			holder=getHolder();
			t=new Thread(this);
			t.start();
		}
		
		public void pause(){
			running=false;
			while(true)
			{
				try {
					t.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			t=null;
		}
		
		public void resume(){
			running=true;
			t=new Thread(this);
			t.start();
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(running){
				if(!holder.getSurface().isValid())
					continue;
				Canvas canvas;
				canvas=holder.lockCanvas();
				canvas.drawRGB(10, 10, 170);
				if(x!=0 && y!=0){
					canvas.drawBitmap(gball, x-gball.getWidth()/2, y-gball.getHeight()/2, null);
				}
				holder.unlockCanvasAndPost(canvas);
			}
			
		}

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		x=event.getX();
		y=event.getY();
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			sx=event.getX();
			sy=event.getY();
			break;
		case MotionEvent.ACTION_UP:
			fx=event.getX();
			fy=event.getY();
			
			break;
		}
		return true;
	}

}
