package com.example.tryingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodShower extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_shower);

        TextView textView = findViewById(R.id.textfood);
        ImageView imageView = findViewById(R.id.imageView);
        Intent intent = getIntent();
        String str = intent.getStringExtra("food");

        if (str.equals("БЖУ")) {
            imageView.setImageResource(R.drawable.belki);
            textView.setText(R.string.foodAboutBju);
        }
    }
}