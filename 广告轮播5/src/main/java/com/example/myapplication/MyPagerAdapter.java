package com.example.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
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
    private List<View> list;
//    private int newPosition;

    public MyPagerAdapter(Context context, List<View> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        //使用Integer的最大值，这样基本上就不需要滑动到最后了
        //除非用户很蛋疼
//        return Integer.MAX_VALUE;
        return list.size();
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        //当前位置模上总数等于图片在数组中的位置
//        newPosition =position% list.size();
//        if (newPosition < 0) {
//            newPosition = list.size() + newPosition;
//        }
        SimpleDraweeView imageView = list.get(position).findViewById(R.id.iv);
        //设置轮播点击事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(newPosition==0){
//                    Toast.makeText(context, "test"+"最后一页", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(context, "test"+newPosition, Toast.LENGTH_SHORT).show();
//                }
                Toast.makeText(context, "test"+(position+1), Toast.LENGTH_SHORT).show();

            }
        });
        ViewGroup parent = (ViewGroup) list.get(position).getParent();
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
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    }
}
