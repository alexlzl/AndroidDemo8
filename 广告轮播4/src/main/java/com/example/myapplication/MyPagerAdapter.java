package com.example.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by liuzhouliang on 2018/1/8.
 */

public class MyPagerAdapter extends PagerAdapter {
    private Context context;
    private List<View> list;
    private int newPosition;
    private WeakReference<MainActivity> mweakReference;
    MainActivity activity;
    public MyPagerAdapter(Context context, List<View> list,WeakReference<MainActivity> weakReference) {
        this.context = context;
        this.list = list;
        this.mweakReference = weakReference;
         activity = mweakReference.get();


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
        newPosition =position% list.size();
        if (newPosition < 0) {
            newPosition = list.size() + newPosition;
        }
        SimpleDraweeView imageView = list.get(newPosition).findViewById(R.id.iv);
        //设置轮播点击事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(newPosition==0){
//                    Toast.makeText(context, "test"+"最后一页", Toast.LENGTH_SHORT).show();
//                }else{
//                    //Activity不存在了，就不需要再处理了
//                    if (activity == null) {
//                        return;
//                    }
//                    Toast.makeText(context, "test"+activity.index+1, Toast.LENGTH_SHORT).show();
//                }
                //Activity不存在了，就不需要再处理了
                if (activity == null) {
                    return;
                }
                Toast.makeText(context, "test"+(activity.index+1), Toast.LENGTH_SHORT).show();

            }
        });
        ViewGroup parent = (ViewGroup) list.get(newPosition).getParent();
        if (parent != null) {
            parent.removeView(list.get(newPosition));
        }
        container.addView(list.get(newPosition));
        return list.get(newPosition);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    }
}
