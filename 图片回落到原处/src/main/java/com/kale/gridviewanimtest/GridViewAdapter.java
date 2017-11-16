package com.kale.gridviewanimtest;

import com.kale.gridviewanimtest.ZoomTutorial.OnZoomListener;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class GridViewAdapter extends BaseAdapter {

	private Context mContext;
	// 要展示的小图片的id数组
	private int[] mThumbIds;
	
	public GridViewAdapter(Context c, int[] thumbIds) {
		mContext = c;
		mThumbIds = thumbIds;
	}

	/**
	 * 要显示的图片的数目，thumbIds是小图片的resource Id
	 */
	public int getCount() {
		return mThumbIds.length;
	}

	public Object getItem(int position) {
		return mThumbIds[position];
	}

	public long getItemId(int position) {
		return position;
	}

	/**
	 * 创建每个item的视图
	 */
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ImageView imageView;
		if (convertView == null) {
			imageView = new ImageView(mContext);
			// 这里的scaleType是CENTER_CROP
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		} else {
			imageView = (ImageView) convertView;
		}

		imageView.setImageResource(mThumbIds[position]);
		imageView.setTag(mThumbIds[position]);
		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//当前drawable的res的id
				int id = (Integer) v.getTag();
				setViewPagerAndZoom(imageView, position);
			}
		});

		return imageView;
	}
	
	public void setViewPagerAndZoom(View v ,int position) {
		//得到要放大展示的视图界面
		ViewPager expandedView = (ViewPager)((Activity)mContext).findViewById(R.id.detail_view);
		//最外层的容器，用来计算
		View containerView = (FrameLayout)((Activity)mContext).findViewById(R.id.container);
		//实现放大缩小类，传入当前的容器和要放大展示的对象
		ZoomTutorial mZoomTutorial = new ZoomTutorial(containerView, expandedView);
		
		ViewPagerAdapter adapter = new ViewPagerAdapter(mContext, 
				MainActivity.largePicIds,mZoomTutorial);
		expandedView.setAdapter(adapter);
		expandedView.setCurrentItem(position);
		
		// 通过传入Id来从小图片扩展到大图，开始执行动画
		mZoomTutorial.zoomImageFromThumb(v);
		mZoomTutorial.setOnZoomListener(new OnZoomListener() {
			
			@Override
			public void onThumbed() {
				// TODO 自动生成的方法存根
				System.out.println("现在是-------------------> 小图状态");
			}
			
			@Override
			public void onExpanded() {
				// TODO 自动生成的方法存根
				System.out.println("现在是-------------------> 大图状态");
			}
		});
	}
	

}
