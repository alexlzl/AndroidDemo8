package com.hensen.marqueeview;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gj_home_list_steward_remind);

        final ViewFlipper vf = (ViewFlipper) findViewById(R.id.vf);
        View view1 = View.inflate(this, R.layout.view_advertisement01, null);
        SimpleDraweeView simpleDraweeView1= (SimpleDraweeView) view1.findViewById(R.id.simple);
        view1.setTag("1");
        View view2 = View.inflate(this, R.layout.view_advertisement01, null);
        view2.setTag("2");
        SimpleDraweeView simpleDraweeView2= (SimpleDraweeView) view2.findViewById(R.id.simple);
        View view3 = View.inflate(this, R.layout.view_advertisement01, null);
        view3.setTag("3");
        SimpleDraweeView simpleDraweeView3= (SimpleDraweeView) view3.findViewById(R.id.simple);
        vf.addView(view1);
//        vf.stopFlipping();
        vf.addView(view2);
        vf.addView(view3);
        Uri uri1 = Uri.parse("http://pic4.nipic.com/20091217/3885730_124701000519_2.jpg");
        Uri uri2 = Uri.parse("http://pic47.nipic.com/20140826/9532020_221427317000_2.jpg");
        Uri uri3 = Uri.parse("http://img3.redocn.com/tupian/20150318/qingxinshuzhibiankuang_4021000.jpg");
        Uri uri4 = Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515416541169&di=8bc94a4be8e64b56dbe90d4241e4651d&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F32fa828ba61ea8d3d8d6c33f9c0a304e251f5810.jpg");
        simpleDraweeView1.setImageURI(uri1);
        simpleDraweeView2.setImageURI(uri2);
        simpleDraweeView3.setImageURI(uri3);
//        imageView4.setImageURI(uri4);
        vf.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //动画开始
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束
                String tag = (String) vf.getCurrentView().getTag();
                if ("1".equals(tag)) {
                    Toast.makeText(MainActivity.this, "1"+"=="+vf.getChildCount(), Toast.LENGTH_SHORT).show();
                }
                if ("2".equals(tag)) {
                    Toast.makeText(MainActivity.this, "2", Toast.LENGTH_SHORT).show();
                }
                if ("3".equals(tag)) {
                    Toast.makeText(MainActivity.this, "3", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        vf.startFlipping();
    }

}
