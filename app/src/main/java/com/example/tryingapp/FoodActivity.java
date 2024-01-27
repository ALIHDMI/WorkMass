package com.example.tryingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FoodActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        context = getApplicationContext();
        TextView textView = findViewById(R.id.foodName);
        CardView cardView = findViewById(R.id.foodCardView);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FoodShower.class);
                intent.putExtra("food", (String)textView.getText());
                startActivity(intent);
            }
        });
    }
}