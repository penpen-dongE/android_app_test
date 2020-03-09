package com.capsulemedia.myapp.drawEx;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {


    private int mDrawWhat=-1; //0:Rect 1:circle

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //이 함수는 최초 실행시 한번 호출됩니다.
        //따라서, 재 호출하기 위해서는 invalidate(); 를 호출해주어야 합니다.

        switch(mDrawWhat)
        {
            case 0://네모를 그린다.
                drawRect(canvas);
                break;
            case 1://원을 그린다.
                drawCircle(canvas);
                break;
        }



    }

    public void drawRect()
    {
        mDrawWhat=0;
        //다시 그리라는 함수
        invalidate();
    }
    private void drawRect(Canvas canvas)
    {
        int w=getWidth()/3;
        int h=w;


        int left=(getWidth()-w)/2;
        int top=(getHeight()-h)/2;
        int right=left+w;
        int bottom=top+h;
        Paint paint=new Paint();
        paint.setColor(Color.BLUE);

        canvas.drawRect(left,top,right,bottom,paint);


    }

    public void drawCircle()
    {
        mDrawWhat=1;

        //다시 그리라는 함수
        invalidate();
    }
    private void drawCircle(Canvas canvas)
    {
        int radius=getWidth()/3/2;

        int x=getWidth()/2;
        int y=getHeight()/2;

        Paint paint=new Paint();
        paint.setColor(Color.BLUE);

        canvas.drawCircle(x,y,radius,paint);


    }



}
