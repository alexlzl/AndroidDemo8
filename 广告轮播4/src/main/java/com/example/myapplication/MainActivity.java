package com.example.myapplication;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    public ViewPager viewPager;
    public MyHandler handler;

    private LayoutInflater layoutInflater;
    private View childOne;
    private View childTwo;
    private View childThree;
    private View childFour;
    private RadioGroup radioGroup;
    //当前索引位置以及上一个索引位置
    public int index = 0,preIndex = 0;
    private  List<View> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup=findViewById(R.id.group);
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
        Uri uri4 = Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515416541169&di=8bc94a4be8e64b56dbe90d4241e4651d&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F32fa828ba61ea8d3d8d6c33f9c0a304e251f5810.jpg");
        imageView1.setImageURI(uri1);
        imageView2.setImageURI(uri2);
        imageView3.setImageURI(uri3);
        imageView4.setImageURI(uri4);
        list = new ArrayList<>();
//        list.add(imageView1);
//        list.add(imageView2);
//        list.add(imageView3);
        list.add(childOne);
        list.add(childTwo);
        list.add(childThree);
        list.add(childFour);
        initRadioButton(list.size());
        WeakReference<MainActivity> weakReference = new WeakReference(this);
        MyPagerAdapter adapter = new MyPagerAdapter(this, list,weakReference);
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);

        handler = new MyHandler(weakReference);
        //开始轮播
        handler.sendEmptyMessageDelayed(MyHandler.BANNER_NEXT, 2000);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                index =position% list.size();
            }

            @Override
            public void onPageSelected(int position) {
//                index = position;//当前位置赋值给索引
                index = position%list.size();//当前位置赋值给索引
                setCurrentDot(index);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        //用户正在滑动，暂停轮播
                        handler.sendEmptyMessage(MyHandler.BANNER_PAUSE);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        //滑动结束，继续轮播
                        handler.sendEmptyMessageDelayed(MyHandler.BANNER_NEXT, MyHandler.CHANGE_TIME);
                        break;
                }

            }
        });
    }
    /**
     * 根据图片个数初始化按钮
     * @param length
     */
    private void initRadioButton(int length) {
        for(int i = 0;i<length;i++){
            ImageView imageview = new ImageView(this);
            imageview.setImageResource(R.drawable.bg);//设置背景选择器
            imageview.setPadding(20,0,0,0);//设置每个按钮之间的间距
            //将按钮依次添加到RadioGroup中
            radioGroup.addView(imageview, 40, 40);
            //默认选中第一个按钮，因为默认显示第一张图片
            radioGroup.getChildAt(0).setEnabled(false);
        }
    }

    /**
     * 设置对应位置按钮的状态
     * @param i 当前位置
     */
    private void setCurrentDot(int i) {
        for(int m=0;m<radioGroup.getChildCount();m++){
            if(radioGroup.getChildAt(m)!=null){
                radioGroup.getChildAt(m).setEnabled(true);//当前按钮选中
            }
        }
        if(radioGroup.getChildAt(i)!=null){
            radioGroup.getChildAt(i).setEnabled(false);//当前按钮选中
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(MyHandler.BANNER_NEXT);
    }
}
