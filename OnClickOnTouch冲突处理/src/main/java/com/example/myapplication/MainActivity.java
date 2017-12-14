package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {
    private Button mButton;
    private float x;
    private float y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton=findViewById(R.id.test);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TAG", "button被点击");
            }
        });
        mButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.e("TAG", "button被长按");
                return false;
            }
        });
        mButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Log.e("TAG", "button按下");
                        //记录按下时的位置
                        x = motionEvent.getRawX();
                        y = motionEvent.getRawY();
                        break;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        Log.e("TAG", "button移动");
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        //检测移动的距离，如果很微小可以认为是点击事件
                        if (Math.abs(motionEvent.getRawX() - x) < 10 && Math.abs(motionEvent.getRawY() - y) < 10) {
                            try {
                                Field field = View.class.getDeclaredField("mListenerInfo");
                                field.setAccessible(true);
                                Object object = field.get(view);
                                field = object.getClass().getDeclaredField("mOnClickListener");
                                field.setAccessible(true);
                                object = field.get(object);
                                if (object != null && object instanceof View.OnClickListener) {
                                    ((View.OnClickListener) object).onClick(view);
                                }
                            } catch (Exception e) {

                            }
                        } else {
                            Log.e("TAG", "button已移动");
                        }
                        break;
                    }
                }
                return true;
            }
        });




    }


}
