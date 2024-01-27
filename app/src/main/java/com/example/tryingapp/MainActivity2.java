package com.example.tryingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    String sex = "Human";
    boolean choice1, choice2;
    Animation scaleup, scaledown, scaleinfinite;
    Button button, button1, button2, button3, button4;
    ImageView imageView;
    Dialog dialog;
    boolean first, second, third, fourth, fifth;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intentGet = getIntent();
        sex = intentGet.getStringExtra("sex");

        scaleup = AnimationUtils.loadAnimation(this, R.anim.lightscaleup);
        scaledown = AnimationUtils.loadAnimation(this, R.anim.lightscaledown);
        scaleinfinite = AnimationUtils.loadAnimation(this, R.anim.lightscaleinfinite);

        button = findViewById(R.id.buttonBodyParts);
        button1 = findViewById(R.id.buttonBodyParts1);
        button2 = findViewById(R.id.buttonBodyParts2);

        button3 = findViewById(R.id.buttonBodyParts3);
        button4 = findViewById(R.id.buttonBodyParts4);

        imageView = findViewById(R.id.imageView);


        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    choice1 = true;
                    v.playSoundEffect(SoundEffectConstants.CLICK);
                    button1.clearAnimation();
                    button2.clearAnimation();
                    button.startAnimation(scaleup);
                    first = true;
                    if (second) {
                        button1.startAnimation(scaledown);
                    } else if (third) {
                        button2.startAnimation(scaledown);
                    }
                    second = false;
                    third = false;
                }
                return true;
            }
        });
        button1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    choice1 = true;
                    v.playSoundEffect(SoundEffectConstants.CLICK);
                    button.clearAnimation();
                    button2.clearAnimation();
                    button1.startAnimation(scaleup);
                    second = true;
                    if (first) {
                        button.startAnimation(scaledown);
                    } else if (third) {
                        button2.startAnimation(scaledown);
                    }
                    first = false;
                    third = false;
                }
                return true;
            }
        });
        button2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    choice1 = true;
                    v.playSoundEffect(SoundEffectConstants.CLICK);
                    button.clearAnimation();
                    button1.clearAnimation();
                    button2.startAnimation(scaleup);
                    third = true;
                    if (first) {
                        button.startAnimation(scaledown);
                    } else if (second) {
                        button1.startAnimation(scaledown);
                    }
                    first = false;
                    second = false;
                }
                return true;
            }
        });

        scaleup.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (first) {
                    button.startAnimation(scaleinfinite);
                } else if (second) {
                    button1.startAnimation(scaleinfinite);
                } else if (third) {
                    button2.startAnimation(scaleinfinite);
                }
                if (fourth) {
                    button3.startAnimation(scaleinfinite);
                } else if (fifth) {
                    button4.startAnimation(scaleinfinite);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        button3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    choice2 = true;
                    v.playSoundEffect(SoundEffectConstants.CLICK);
                    if (fifth) {
                        button4.startAnimation(scaledown);
                    }
                    fourth = true;
                    fifth = false;
                    button3.startAnimation(scaleup);
                }
                return true;
            }
        });
        button4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    choice2 = true;
                    v.playSoundEffect(SoundEffectConstants.CLICK);
                    if (fourth) {
                        button3.startAnimation(scaledown);
                    }
                    fifth = true;
                    fourth = false;
                    button4.startAnimation(scaleup);
                }
                return true;
            }
        });
    }

    public void onQuestion(View view) {
        view.playSoundEffect(SoundEffectConstants.CLICK);
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_main2dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void onClickTrainType(View view) {
        if (!(choice1 & choice2)) {
            Toast.makeText(this, "Выберите варианты!", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, MainActivity3.class);
            intent.putExtra("sex", sex);
            if (first) {
                intent.putExtra("hands", "hands");
            } else if (second) {
                intent.putExtra("back", "back");
            } else if (third) {
                intent.putExtra("legs", "legs");
            }
            if (fourth) {
                intent.putExtra("split", "split");
            } else if (fifth) {
                intent.putExtra("fullbody", "fullbody");
            }
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}