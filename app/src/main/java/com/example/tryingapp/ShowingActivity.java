package com.example.tryingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ShowingActivity extends AppCompatActivity {

    int position = 0;
    String day = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        day = intent.getStringExtra("day");
        setImageView(position, day);
        setText(position, day);
    }

    public void setImageView(int position, String day) {
        ImageView imageView = findViewById(R.id.imageView);
        if (day.equals("monday")) {
            switch (position) {
                case 0:
                    imageView.setImageResource(R.drawable.exercisesquat);
                    break;
                case 1:
                    imageView.setImageResource(R.drawable.exerciselegpress);
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.exerciseonbend);
                    break;
                case 3:
                    imageView.setImageResource(R.drawable.exercisestandingbench);
                    break;
                case 4:
                    imageView.setImageResource(R.drawable.exercise_dumbellfly);
                    break;
            }
        } if (day.equals("wednesday")) {
            switch (position) {
                case 0:
                    imageView.setImageResource(R.drawable.exercisesquat);
                    break;
                case 1:
                    imageView.setImageResource(R.drawable.exerciselegpress);
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.exerciseonbend);
                    break;
                case 3:
                    imageView.setImageResource(R.drawable.exercisestandingbench);
                    break;
                case 4:
                    imageView.setImageResource(R.drawable.exercise_dumbellfly);
                    break;
            }
        } if (day.equals("friday")) {
            switch (position) {
                case 0:
                    imageView.setImageResource(R.drawable.exercisesquat);
                    break;
                case 1:
                    imageView.setImageResource(R.drawable.exerciselegpress);
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.exerciseonbend);
                    break;
                case 3:
                    imageView.setImageResource(R.drawable.exercisestandingbench);
                    break;
                case 4:
                    imageView.setImageResource(R.drawable.exercise_dumbellfly);
                    break;
            }
        }
    }

    public void setText(int position, String day) {
        String[] exercises;
        TextView textView = findViewById(R.id.show);
        if (day.equals("monday")) {
            exercises = getResources().getStringArray(R.array.splitday1);
            textView.setText(exercises[position]);
        } else if (day.equals("wednesday")) {
            exercises = getResources().getStringArray(R.array.splitday2);
            textView.setText(exercises[position]);
        } else if (day.equals("friday")) {
            exercises = getResources().getStringArray(R.array.splitday3);
            textView.setText(exercises[position]);
        }
    }

}