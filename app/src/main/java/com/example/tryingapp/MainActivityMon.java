package com.example.tryingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivityMon extends AppCompatActivity{
    ExerciseFragmentAdapter fragmentAdapter;
    ViewPager2 viewPager2;
    AreFragmentsReady areFragmentsReady;
    Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mon);
        buttonNext = findViewById(R.id.buttonNext);

        SharedPreferences sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("FirstSettings", 0);
        editor.putString("MondayChoose", "");
        editor.apply();

        viewPager2 = findViewById(R.id.viewPager2Mon);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentAdapter = new ExerciseFragmentAdapter(fragmentManager, getLifecycle(), this.getApplicationContext());
        viewPager2.setAdapter(fragmentAdapter);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.remove("FirstSettings").apply();
                editor.putInt("MonDone", 0);
                editor.apply();
                Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                startActivity(intent);
            }
        });
    }
}