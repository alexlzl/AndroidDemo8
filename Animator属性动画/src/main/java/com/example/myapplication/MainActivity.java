package com.example.myapplication;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtilsGJ.setStatusBar(this);
    }

    public void testButton1(View view) {
        Toast.makeText(this, "test", Toast.LENGTH_LONG).show();
        ObjectAnimator objectAnimator = new ObjectAnimator();
        objectAnimator.setPropertyName("x");
        objectAnimator.setFloatValues(10, 100, 200, 400);
        objectAnimator.setDuration(2000);
        objectAnimator.setTarget(view);
        objectAnimator.start();
    }


    public void testButton2(final View view) {

        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(1000);
        //这个地方设置了变化值的类型
        valueAnimator.setObjectValues(new PointF(0, 0));
        //设置插值器
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        //设置估值器
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {
            // fraction = t / duration
            @Override
            public PointF evaluate(float fraction, PointF startValue,
                                   PointF endValue) {
                Log.e(TAG, "fraction==" + fraction + "===startValue==" + startValue + "===endValue==" + endValue);
                // x方向200px/s ，则y方向0.5 * 10 * t
                PointF point = new PointF();
                point.x = 200 * fraction * 3 + 100;
                point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3) + 200;
                //返回变化值
                //这个返回值会在addUpdateListener的回调中得到
                return point;
            }
        });

        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 得到估值器里面的那个返回值
                PointF point = (PointF) animation.getAnimatedValue();
                //设置属性值
                view.setX(point.x);
                view.setY(point.y);

            }
        });
    }

    public void testButton3(final View view) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(0, 100);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                Log.e(TAG, "value===" + value);
                ((TextView) view).setText("value===" + value);
            }
        });
        valueAnimator.start();
    }

    public void testButton4(final View view) {
        Toast.makeText(this, "test", Toast.LENGTH_LONG).show();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "x", 0, 300);
        objectAnimator.setDuration(1000);
        objectAnimator.setInterpolator(new AccelerateInterpolator());
        objectAnimator.setRepeatCount(-1);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.start();

    }
}
