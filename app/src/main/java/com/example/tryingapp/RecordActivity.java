package com.example.tryingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        SharedPreferences sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        TextView textViewTime = findViewById(R.id.time);
        TextView textViewKg = findViewById(R.id.kgs);

        int minutes = sharedPreferences.getInt("seconds", 0);
        minutes = minutes / 60;
        textViewTime.setText(String.valueOf(minutes));

        textViewKg.setText(String.valueOf(sharedPreferences.getInt("kg", 0)));
    }
}