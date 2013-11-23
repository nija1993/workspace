package com.example.newapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

public class MyBringBack extends View{

	float y;
	Bitmap gBall;
	Rect rectangle=new Rect();
	Paint paint=new Paint();
	Paint text=new Paint();
	Typeface font;
	public MyBringBack(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		gBall=BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
		y=0;
		paint.setColor(Color.BLUE);
		font=Typeface.createFromAsset(context.getAssets(), "leadcoat.ttf");
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		text.setARGB(50, 255, 255, 50);
		//text.setTextAlign(Align.CENTER);
		text.setTextSize(50);
		text.setTypeface(font);
		
		canvas.drawColor(Color.BLACK);
		canvas.drawBitmap(gBall, (canvas.getWidth()/2),y, null);
		if(y<canvas.getHeight())
			y+=2;
		else
			y=0;
		rectangle.set(0, 200, canvas.getWidth(), 300);
		canvas.drawRect(rectangle, paint);
		canvas.drawText("hello",canvas.getWidth()/2,100, text);
		invalidate();
	}

}
