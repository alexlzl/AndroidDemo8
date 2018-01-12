package com.hensen.marqueeview;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by liuzhouliang on 2018/1/8.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
          super.onCreate();
        Fresco.initialize(this);
    }
}
