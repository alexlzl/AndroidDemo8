package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by liuzhouliang on 2017/12/13.
 */

public class MyImageView extends ImageView implements View.OnClickListener,View.OnTouchListener {
    private static final String TAG = "Event";
    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        Log.d(TAG,"MyImageView init");
        setOnClickListener(this);
        setOnTouchListener(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG,"MyImageView dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG,"MyImageView onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onTouch(View arg0, MotionEvent arg1) {
        // TODO Auto-generated method stub
        Log.d(TAG,"MyImageView onTouch");
        return false;
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        Log.d(TAG,"MyImageView onClick");
    }
}
