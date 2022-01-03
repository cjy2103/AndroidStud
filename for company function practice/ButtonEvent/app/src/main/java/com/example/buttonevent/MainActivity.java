package com.example.buttonevent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button imageChange;
    ImageView image;

    int flag = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageChange = (Button)findViewById(R.id.btn);
        image = (ImageView)findViewById(R.id.imageView);
        imageChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag *= -1;
                if(flag == 1){
                    image.setImageResource(R.drawable.img_1);
                }
                else{
                    image.setImageResource(R.drawable.img_2);
                }
            }
        });

    }
}