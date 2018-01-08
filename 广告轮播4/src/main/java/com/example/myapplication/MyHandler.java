package com.example.myapplication;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by liuzhouliang on 2018/1/8.
 */

public class MyHandler extends Handler {
    private Context context;
    //使用弱引用，避免handler造成内存泄露
    private WeakReference<MainActivity> weakReference;


    public MyHandler(WeakReference<MainActivity> weakReference) {
        this.weakReference = weakReference;
    }


    @Override
    public void handleMessage(Message msg) {
        MainActivity activity = weakReference.get();
        //Activity不存在了，就不需要再处理了
        if (activity == null) {
            return;
        }
        //如果已经有消息了，先移除消息
        if (activity.handler.hasMessages(MainActivity.BANNER_NEXT)) {
            activity.handler.removeMessages(MainActivity.BANNER_NEXT);
        }
        switch (msg.what) {
            case MainActivity.BANNER_NEXT:
                //跳到下一页，
                int currentItem = activity.viewPager.getCurrentItem();
                activity.viewPager.setCurrentItem(++currentItem);
                //5秒后继续轮播
                activity.handler.sendEmptyMessageDelayed(MainActivity.BANNER_NEXT, 2000);
                break;
            case MainActivity.BANNER_PAUSE:
                //暂停,不需要做任务操作
                break;
            case MainActivity.BANNER_RESUME:
                //继续轮播
                activity.handler.sendEmptyMessageDelayed(MainActivity.BANNER_NEXT, 2000);
                break;
        }
    }
}
