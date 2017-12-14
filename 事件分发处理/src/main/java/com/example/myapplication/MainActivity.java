package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
    private MyFrameLayout myFrameLayout;
    private  MyImageView myImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myFrameLayout=findViewById(R.id.mFrame);
        myImageView=findViewById(R.id.mImage);
        myFrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"framelayout",Toast.LENGTH_LONG).show();
            }
        });
        myImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"imageview",Toast.LENGTH_LONG).show();
            }
        });
    }
}
