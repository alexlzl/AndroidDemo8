package com.example.myapplication;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    public ViewPager viewPager;
    public MyHandler handler;
    public static  final  int BANNER_NEXT=0;
    public static  final  int BANNER_PAUSE=1;
    public static  final  int BANNER_RESUME=2;
    private LayoutInflater layoutInflater;
    private View childOne;
    private View childTwo;
    private View childThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        viewPager=findViewById(R.id.view_pager);
        //这里放了3张图片，使用Gilde加载
        layoutInflater=LayoutInflater.from(this);
        childOne=layoutInflater.inflate(R.layout.item,null);
        childTwo=layoutInflater.inflate(R.layout.item,null);
        childThree=layoutInflater.inflate(R.layout.item,null);
        SimpleDraweeView imageView1 = childOne.findViewById(R.id.iv);
        SimpleDraweeView imageView2 = childTwo.findViewById(R.id.iv);
        SimpleDraweeView imageView3 = childThree.findViewById(R.id.iv);
        TextView textView1=childOne.findViewById(R.id.title);
        TextView textView2=childTwo.findViewById(R.id.title);
        TextView textView3=childThree.findViewById(R.id.title);
        textView1.setText("1");
        textView2.setText("2");
        textView3.setText("3");
        Uri uri1 = Uri.parse("http://pic4.nipic.com/20091217/3885730_124701000519_2.jpg");
        Uri uri2 = Uri.parse("http://pic47.nipic.com/20140826/9532020_221427317000_2.jpg");
        Uri uri3 = Uri.parse("http://img3.redocn.com/tupian/20150318/qingxinshuzhibiankuang_4021000.jpg");
        imageView1.setImageURI(uri1);
        imageView2.setImageURI(uri2);
        imageView3.setImageURI(uri3);
        List<View> list = new ArrayList<>();
//        list.add(imageView1);
//        list.add(imageView2);
//        list.add(imageView3);
        list.add(childOne);
        list.add(childTwo);
        list.add(childThree);
        MyPagerAdapter adapter = new MyPagerAdapter(this, list);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);
        //将起始位置设置在中间，让用户看不到边界
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2);
        WeakReference<MainActivity> weakReference=new WeakReference(this);
        handler=new MyHandler(weakReference);
        //开始轮播
        handler.sendEmptyMessageDelayed(BANNER_NEXT, 1000);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        //用户正在滑动，暂停轮播
                        handler.sendEmptyMessage(BANNER_PAUSE);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        //滑动结束，继续轮播
                        handler.sendEmptyMessageDelayed(BANNER_NEXT, 3000);
                        break;
                }

            }
        });
    }

//    private class MyHandler extends Handler {
//
//    }


}
