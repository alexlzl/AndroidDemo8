package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView ivOne;
    private ImageView ivTwo;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivOne=findViewById(R.id.iv_one);
        ivTwo=findViewById(R.id.iv_two);
        ivTwo.getBackground().setAlpha(20);
        ivTwo.setImageResource(R.mipmap.ic_launcher);
        button=findViewById(R.id.button_test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"test",Toast.LENGTH_LONG).show();
                ivOne.getBackground().setAlpha(120);
            }
        });
    }
}
