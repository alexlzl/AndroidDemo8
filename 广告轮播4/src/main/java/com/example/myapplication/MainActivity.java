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
    public static final int BANNER_NEXT = 0;
    public static final int BANNER_PAUSE = 1;
    public static final int BANNER_RESUME = 2;
    private LayoutInflater layoutInflater;
    private View childOne;
    private View childTwo;
    private View childThree;
    private View childFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutInflater = LayoutInflater.from(this);
        childOne = layoutInflater.inflate(R.layout.item, null);
        childTwo = layoutInflater.inflate(R.layout.item, null);
        childThree = layoutInflater.inflate(R.layout.item, null);
        childFour = layoutInflater.inflate(R.layout.item, null);
        SimpleDraweeView imageView1 = childOne.findViewById(R.id.iv);
        SimpleDraweeView imageView2 = childTwo.findViewById(R.id.iv);
        SimpleDraweeView imageView3 = childThree.findViewById(R.id.iv);
        SimpleDraweeView imageView4 = childFour.findViewById(R.id.iv);
        TextView textView1 = childOne.findViewById(R.id.title);
        TextView textView2 = childTwo.findViewById(R.id.title);
        TextView textView3 = childThree.findViewById(R.id.title);
        TextView textView4 = childFour.findViewById(R.id.title);
        textView1.setText("1");
        textView2.setText("2");
        textView3.setText("3");
        textView4.setText("4");
        Uri uri1 = Uri.parse("http://pic4.nipic.com/20091217/3885730_124701000519_2.jpg");
        Uri uri2 = Uri.parse("http://pic47.nipic.com/20140826/9532020_221427317000_2.jpg");
        Uri uri3 = Uri.parse("http://img3.redocn.com/tupian/20150318/qingxinshuzhibiankuang_4021000.jpg");
        Uri uri4=Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515416541169&di=8bc94a4be8e64b56dbe90d4241e4651d&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F32fa828ba61ea8d3d8d6c33f9c0a304e251f5810.jpg");
        imageView1.setImageURI(uri1);
        imageView2.setImageURI(uri2);
        imageView3.setImageURI(uri3);
        imageView4.setImageURI(uri4);
        List<View> list = new ArrayList<>();
//        list.add(imageView1);
//        list.add(imageView2);
//        list.add(imageView3);
        list.add(childOne);
        list.add(childTwo);
        list.add(childThree);
        list.add(childFour);
        MyPagerAdapter adapter = new MyPagerAdapter(this, list);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);
        //将起始位置设置在中间，让用户看不到边界
//        viewPager.setCurrentItem(Integer.MAX_VALUE / 2);
        WeakReference<MainActivity> weakReference = new WeakReference(this);
        handler = new MyHandler(weakReference);
        //开始轮播
        handler.sendEmptyMessageDelayed(BANNER_NEXT, 2000);

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
                        handler.sendEmptyMessageDelayed(BANNER_NEXT, 2000);
                        break;
                }

            }
        });
    }


}
