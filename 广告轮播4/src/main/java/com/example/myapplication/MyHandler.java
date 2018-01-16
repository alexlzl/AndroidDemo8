package com.example.myapplication;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by liuzhouliang on 2018/1/8.
 */

public class MyHandler extends Handler {
    public static final int BANNER_NEXT = 0;
    public static final int BANNER_PAUSE = 1;
    public static final int BANNER_RESUME = 2;
    public static final int CHANGE_TIME=2000;
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
        if (activity.handler.hasMessages(BANNER_NEXT)) {
            activity.handler.removeMessages(BANNER_NEXT);
        }
        switch (msg.what) {
            case BANNER_NEXT:
                //跳到下一页，

                int currentItem = activity.viewPager.getCurrentItem();
//                Log.e("TAG","BANNER_NEXT==当前页"+currentItem);
                activity.viewPager.setCurrentItem(++currentItem);

                //2秒后继续轮播
//                activity.handler.sendEmptyMessageDelayed(BANNER_NEXT, CHANGE_TIME);
                break;
            case BANNER_PAUSE:
                //暂停,不需要做任务操作
               removeMessages(BANNER_NEXT);
                break;
            case BANNER_RESUME:
                //继续轮播
//                activity.handler.sendEmptyMessageDelayed(BANNER_NEXT, CHANGE_TIME);
                break;
        }
    }
}
