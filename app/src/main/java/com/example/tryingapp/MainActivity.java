package com.example.tryingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class MainActivity extends AppCompatActivity {
    Animation scaleup,scaledown, scaleinfinite;
    boolean first = false;
    boolean firstt = false;
    boolean second = false;
    boolean secondd = false;
    boolean threadDone = false;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle instance) {
        super.onCreate(instance);
        SharedPreferences sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (sharedPreferences.contains("main")) {
            Intent intent = new Intent(this, MainActivity3.class);
        } else {
            editor.putInt("main", 0).apply();
        }
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Button go, stop, reset;
        TextView name = findViewById(R.id.name);
        TextView name1 = findViewById(R.id.name1);
        go = findViewById(R.id.go);

        ImageView imageView = findViewById(R.id.imageView);
        ImageView imageView1 = findViewById(R.id.imageView1);

        Animation scaleup,scaledown, scaleinfinite;
        scaleup = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        scaledown = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        scaleinfinite = AnimationUtils.loadAnimation(this, R.anim.scaleinfinite);

        scaleup.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (first) {
                    name.startAnimation(scaleinfinite);
                    imageView.startAnimation(scaleinfinite);
                } else if (second) {
                    name1.startAnimation(scaleinfinite);
                    imageView1.startAnimation(scaleinfinite);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        scaleinfinite.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (second) {
                    name.startAnimation(scaledown);
                    imageView.startAnimation(scaledown);
                } else if (!second) {
                    name1.startAnimation(scaledown);
                    imageView1.startAnimation(scaledown);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        name.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.playSoundEffect(SoundEffectConstants.CLICK);
                    first = true;
                    second = false;
                    name1.clearAnimation();
                    imageView1.clearAnimation();
                    name.startAnimation(scaleup);
                    imageView.startAnimation(scaleup);

                }
                return true;
            }
        });
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.playSoundEffect(SoundEffectConstants.CLICK);
                    first = true;
                    second = false;
                    name1.clearAnimation();
                    imageView1.clearAnimation();
                    name.startAnimation(scaleup);
                    imageView.startAnimation(scaleup);

                }
                return true;
            }
        });
        name1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.playSoundEffect(SoundEffectConstants.CLICK);
                    second = true;
                    first = false;
                    name.clearAnimation();
                    imageView.clearAnimation();
                    name1.startAnimation(scaleup);
                    imageView1.startAnimation(scaleup);
                }
                return true;
            }
        });
        imageView1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.playSoundEffect(SoundEffectConstants.CLICK);
                    second = true;
                    first = false;
                    name.clearAnimation();
                    imageView.clearAnimation();
                    name1.startAnimation(scaleup);
                    imageView1.startAnimation(scaleup);
                }
                return true;
            }
        });
        go.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.playSoundEffect(android.view.SoundEffectConstants.CLICK);
                    go.startAnimation(scaleup);
                    onNext(go);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    go.startAnimation(scaledown);
                }
                return true;
            }
        });
    }
    public void onNext(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        if (first) {
            intent.putExtra("sex", "Мужчина");
        } else if (second) {
            intent.putExtra("sex", "Женщина");
        }
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }
}