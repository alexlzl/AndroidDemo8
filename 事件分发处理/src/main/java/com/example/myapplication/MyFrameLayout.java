package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by liuzhouliang on 2017/12/13.
 */

public class MyFrameLayout extends FrameLayout implements View.OnClickListener, View.OnTouchListener {
    private static final String TAG = "Event";

    public MyFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        Log.d(TAG, "MyFrameLayout init");
        setOnClickListener(this);
        setOnTouchListener(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, "MyFrameLayout dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "MyFrameLayout onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View view) {
        // TODO Auto-generated method stub
        Log.d(TAG, "MyFrameLayout onClick");
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        // TODO Auto-generated method stub
        Log.d(TAG, "MyFrameLayout onTouch");
        return false;
    }
}
