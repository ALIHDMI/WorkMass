package com.example.tryingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivityTheMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_the_main);

        Context context = getApplicationContext();
        SharedPreferences sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Animation first;
        first = AnimationUtils.loadAnimation(this, R.anim.alpha);
        Intent intent = new Intent(this, MainActivity11.class);
        TextView textView = findViewById(R.id.textView);
        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);
        textView.startAnimation(first);
        textView1.startAnimation(first);
        textView2.startAnimation(first);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if ((!sharedPreferences.contains("main")) | (!sharedPreferences.contains("MonDone"))) {
                    startActivity(intent);
                } else {
                    Intent intent1 = new Intent(context, MainActivity3.class);
                    startActivity(intent1);
                }
                finish();
            }
        }, 1000);

    }
}