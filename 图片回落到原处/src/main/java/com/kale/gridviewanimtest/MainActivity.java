package com.kale.gridviewanimtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;


/**
 * @from:
 * http://blog.csdn.net/huluhong/article/details/40379767
 * https://github.com/ywenblocker/Android-Photo-Zoom
 */
/**
 * @author:Jack Tony
 * @tips :
 * @date :2014-11-11
 */
public class MainActivity extends Activity {
	GridView gridview;
	// References to our images in res > drawable
	public static int[] thumbPicIds = { R.drawable.sample_0, R.drawable.sample_1,
			R.drawable.sample_2, R.drawable.sample_3, R.drawable.sample_4,
			R.drawable.sample_5, R.drawable.sample_6, R.drawable.sample_7,
			R.drawable.sample_8, R.drawable.sample_9, R.drawable.sample_10,
			R.drawable.sample_11, R.drawable.sample_12, R.drawable.sample_13,
			R.drawable.sample_14, R.drawable.sample_0, R.drawable.sample_1,
			R.drawable.sample_2, R.drawable.sample_3, R.drawable.sample_4,
			R.drawable.sample_5, R.drawable.sample_6, R.drawable.sample_7,
			R.drawable.sample_8, R.drawable.sample_9, R.drawable.sample_10,
			R.drawable.sample_11, R.drawable.sample_12, R.drawable.sample_13,
			R.drawable.sample_14 };
	
	//大图片的的id，这里为了简单弄成和小图一样的
	public static int[] largePicIds = thumbPicIds;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(new GridViewAdapter(this, thumbPicIds));
		
		
		
		
	}
}
