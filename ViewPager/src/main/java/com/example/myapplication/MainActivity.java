package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private String TAG="TAG";

    private ViewPager pager;
    //每一个界面
    private List<View> views;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        pager=findViewById(R.id.viewPager);
        views=new ArrayList<View>();
        LayoutInflater li=getLayoutInflater();
        views.add(li.inflate(R.layout.f1, null));
        views.add(li.inflate(R.layout.f2, null));
        views.add(li.inflate(R.layout.f3, null));
        views.add(li.inflate(R.layout.f4, null));
        //需要给ViewPager设置适配器
        PagerAdapter adapter=new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0==arg1;
            }
            //有多少个切换页
            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return views.size();
            }

            //对超出范围的资源进行销毁
            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                //super.destroyItem(container, position, object);
                Log.e(TAG,"destroyItem==="+position);
                container.removeView(views.get(position));
            }
            //对显示的资源进行初始化
            @Override
            public Object instantiateItem(ViewGroup container, final int position) {
                // TODO Auto-generated method stub
                Log.e(TAG,"instantiateItem==="+position);
                views.get(position).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,position+"",Toast.LENGTH_SHORT).show();
                    }
                });
                container.addView(views.get(position));
                return views.get(position);
            }

        };
        pager.setAdapter(adapter);


    }
}
