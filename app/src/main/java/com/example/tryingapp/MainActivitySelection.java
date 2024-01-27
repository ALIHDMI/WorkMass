package com.example.tryingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivitySelection extends AppCompatActivity {
    Animation scaleUp, scaleDown, scaleinfinite;
    boolean first, second, third, fourth;
    boolean firstExp, secondExp, thirdExp, pol1, pol2, jointLeg, jointArm, jointNo, accentLegs, accentArms, accentSpine;
    Button imageButton1, imageButton2, imageButton3, buttonPol1, buttonPol2, buttonJointLeg, buttonJointArm, buttonJointNo, buttonAccentLegs,
    buttonAccentArms, buttonAccentSpine, buttonNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_selection);
        scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        scaleinfinite = AnimationUtils.loadAnimation(this, R.anim.scaleinfinite);

        imageButton1 = findViewById(R.id.imageButton1);
        imageButton2 = findViewById(R.id.imageButton2);
        imageButton3 = findViewById(R.id.imageButton3);
        buttonPol1 = findViewById(R.id.buttonPol);
        buttonPol2 = findViewById(R.id.buttonPol2);
        buttonJointLeg = findViewById(R.id.buttonJointLeg);
        buttonJointArm = findViewById(R.id.buttonJointArm);
        buttonJointNo = findViewById(R.id.buttonJointsNo);
        buttonAccentLegs = findViewById(R.id.buttonAccentLegs);
        buttonAccentArms = findViewById(R.id.buttonAccentArms);
        buttonAccentSpine = findViewById(R.id.buttonAccentSpine);
        buttonNext = findViewById(R.id.buttonNext);

        buttonAccentLegs.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.playSoundEffect(SoundEffectConstants.CLICK);
                buttonAccentLegs.setBackground(getResources().getDrawable(R.drawable.circleselection2));
                buttonAccentArms.setBackground(getResources().getDrawable(R.drawable.circleselection));
                buttonAccentSpine.setBackground(getResources().getDrawable(R.drawable.circleselection));
                accentLegs = true;
                accentArms = false;
                accentSpine = false;
                fourth = true;
                return true;
            }
        });
        buttonAccentArms.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.playSoundEffect(SoundEffectConstants.CLICK);
                buttonAccentArms.setBackground(getResources().getDrawable(R.drawable.circleselection2));
                buttonAccentLegs.setBackground(getResources().getDrawable(R.drawable.circleselection));
                buttonAccentSpine.setBackground(getResources().getDrawable(R.drawable.circleselection));
                accentArms = true;
                accentLegs = false;
                accentSpine = false;
                fourth = true;
                return true;
            }
        });
        buttonAccentSpine.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.playSoundEffect(SoundEffectConstants.CLICK);
                buttonAccentSpine.setBackground(getResources().getDrawable(R.drawable.circleselection2));
                buttonAccentArms.setBackground(getResources().getDrawable(R.drawable.circleselection));
                buttonAccentLegs.setBackground(getResources().getDrawable(R.drawable.circleselection));
                accentSpine = true;
                accentArms = false;
                accentLegs = false;
                fourth = true;
                return true;
            }
        });
        buttonJointLeg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.playSoundEffect(SoundEffectConstants.CLICK);
                buttonJointLeg.setBackground(getResources().getDrawable(R.drawable.circleselection2));
                buttonJointArm.setBackground(getResources().getDrawable(R.drawable.circleselection));
                buttonJointNo.setBackground(getResources().getDrawable(R.drawable.circleselection));
                jointLeg = true;
                jointArm = false;
                jointNo = false;
                third = true;
                return true;
            }
        });
        buttonJointArm.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.playSoundEffect(SoundEffectConstants.CLICK);
                buttonJointArm.setBackground(getResources().getDrawable(R.drawable.circleselection2));
                buttonJointLeg.setBackground(getResources().getDrawable(R.drawable.circleselection));
                buttonJointNo.setBackground(getResources().getDrawable(R.drawable.circleselection));
                jointArm = true;
                jointLeg = false;
                jointNo = false;
                third = true;
                return true;
            }
        });
        buttonJointNo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.playSoundEffect(SoundEffectConstants.CLICK);
                buttonJointNo.setBackground(getResources().getDrawable(R.drawable.circleselection2));
                buttonJointLeg.setBackground(getResources().getDrawable(R.drawable.circleselection));
                buttonJointArm.setBackground(getResources().getDrawable(R.drawable.circleselection));
                jointNo = true;
                jointArm = false;
                jointLeg = false;
                third = true;
                return true;
            }
        });
        imageButton1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    v.playSoundEffect(SoundEffectConstants.CLICK);
//                    imageButton2.clearAnimation();
//                    imageButton3.clearAnimation();
//                    imageButton1.setAnimation(scaleUp);
//                    first = true;
//                    if (second) {
//                        imageButton2.startAnimation(scaleDown);
//                    } else if (third) {
//                        imageButton3.startAnimation(scaleDown);
//                    }
//                    second = false;
//                    third = false;
                    v.playSoundEffect(SoundEffectConstants.CLICK);
                    imageButton1.setBackground(getResources().getDrawable(R.drawable.circleselection2));
                    imageButton2.setBackground(getResources().getDrawable(R.drawable.circleselection));
                    imageButton3.setBackground(getResources().getDrawable(R.drawable.circleselection));
                    firstExp = true;
                    secondExp = false;
                    thirdExp = false;
                    first = true;
                }
                return true;
            }
        });
        imageButton2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    v.playSoundEffect(SoundEffectConstants.CLICK);
//                    imageButton1.clearAnimation();
//                    imageButton3.clearAnimation();
//                    imageButton2.setAnimation(scaleUp);
//                    second = true;
//                    if (first) {
//                        imageButton1.startAnimation(scaleDown);
//                    } else if (third) {
//                        imageButton3.startAnimation(scaleDown);
//                    }
//                    first = false;
//                    third = false;
                    v.playSoundEffect(SoundEffectConstants.CLICK);
                    imageButton2.setBackground(getResources().getDrawable(R.drawable.circleselection2));
                    imageButton1.setBackground(getResources().getDrawable(R.drawable.circleselection));
                    imageButton3.setBackground(getResources().getDrawable(R.drawable.circleselection));
                    secondExp = true;
                    firstExp = false;
                    thirdExp = false;
                    first = true;
                }
                return true;
            }
        });

        imageButton3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.playSoundEffect(SoundEffectConstants.CLICK);
                imageButton3.setBackground(getResources().getDrawable(R.drawable.circleselection2));
                imageButton2.setBackground(getResources().getDrawable(R.drawable.circleselection));
                imageButton1.setBackground(getResources().getDrawable(R.drawable.circleselection));
                thirdExp = true;
                firstExp = false;
                secondExp = false;
                first = true;
                return true;
            }
        });
        buttonPol1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.playSoundEffect(SoundEffectConstants.CLICK);
                buttonPol1.setBackground(getResources().getDrawable(R.drawable.circleselection2));
                buttonPol2.setBackground(getResources().getDrawable(R.drawable.circleselection));
                pol1 = true;
                pol2 = false;
                second = true;
                return true;
            }
        });
        buttonPol2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.playSoundEffect(SoundEffectConstants.CLICK);
                buttonPol2.setBackground(getResources().getDrawable(R.drawable.circleselection2));
                buttonPol1.setBackground(getResources().getDrawable(R.drawable.circleselection));
                pol2 = true;
                pol1 = false;
                second = true;
                return true;
            }
        });
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                int one = 0, two = 0, three = 0, four = 0;
                if (first & second & third & fourth) {
                    if (firstExp) one = 1;
                    else if (secondExp) one = 2;
                    else if (thirdExp) one = 3;
                    if (pol1) two = 1;
                    else if (pol2) two = 2;
                    if (jointLeg) three = 1;
                    else if (jointArm) three = 2;
                    else if (jointNo) three = 3;
                    if (accentLegs) four = 1;
                    else if (accentArms) four = 2;
                    else if (accentSpine) four = 3;
                    editor.putInt("one", one);
                    editor.putInt("two", two);
                    editor.putInt("three", three);
                    editor.putInt("four", four);
                    editor.putInt("Selection", 0).apply();
                    Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                    startActivity(intent);
                }
            }
        });

//        imageButton3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imageButton1.clearAnimation();
//                imageButton2.clearAnimation();
//                imageButton3.setAnimation(scaleUp);
//                third = true;
//                if (second) {
//                    imageButton2.startAnimation(scaleDown);
//                } else if (first) {
//                    imageButton1.startAnimation(scaleDown);
//                }
//                first = false;
//                second = false;
//            }
//        });

    }

}
