package com.example.myapplication;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by liuzhouliang on 2018/1/8.
 */

public class MyPagerAdapter extends PagerAdapter {
    private Context context;
    List<View> list;

    public MyPagerAdapter(Context context, List<View> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        //使用Integer的最大值，这样基本上就不需要滑动到最后了
        //除非用户很蛋疼
        return Integer.MAX_VALUE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        //当前位置模上总数等于图片在数组中的位置
        position %= list.size();
        if (position < 0) {
            position = list.size() + position;
        }
        SimpleDraweeView imageView = list.get(position).findViewById(R.id.iv);
//        SimpleDraweeView imageView = (SimpleDraweeView) list.get(position);
        //设置轮播点击事件
        final int finalPosition = position;

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "test", Toast.LENGTH_SHORT).show();

            }
        });
//        ViewGroup parent = (ViewGroup) imageView.getParent();
//        if (parent != null) {
//            parent.removeView(imageView);
//        }
        ViewGroup parent= (ViewGroup) list.get(position).getParent();
        if (parent != null) {
            parent.removeView(list.get(position));
        }
        container.addView(list.get(position));
        return list.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //注意这里不要使用
        //container.removeView(list.get(position));
    }

}
