package com.example.sns_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sns_project.R;

import java.util.Random;

public class IntroActivity extends AppCompatActivity {
    ImageView IntroImage;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        IntroImage = (ImageView)findViewById(R.id.introimage);

        int[] img = {R.drawable.ic_logo_2, R.drawable.ic_logo_1};
        Random ram = new Random();
        int num = ram.nextInt(img.length);

        IntroImage.setImageResource(img[num]);

        //xml , java 소스 연결
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override public void run() {
                Intent intent = new Intent (getApplicationContext(), MainActivity.class);
                startActivity(intent); //인트로 실행 후 바로 MainActivity로 넘어감.
                finish(); }
                },3000); //1초 후 인트로 실행
        }

        @Override protected void onPause()  {
        super.onPause();
        finish();
    }
}
