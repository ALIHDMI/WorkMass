package com.example.tryingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity3 extends AppCompatActivity implements AreFragmentsReady{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    TabViewHandler[] tabViewHandlers = new TabViewHandler[12];
    FragmentReplacer fragmentReplacer;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    static int mainPosition = 0;
    TabLayout tabLayout, tabLayout1;
    Button buttonStart;

    int seconds = 0;
    boolean isRunning = false;
    boolean called = false;
    Context context;


    ViewPager2 viewPager2;
    ExerciseFragmentAdapter fragmentAdapter;

    FirstFragment[] firstFragments = new FirstFragment[12];
    SecondFragment[] secondFragments = new SecondFragment[12];
    ThirdFragment[] thirdFragments = new ThirdFragment[12];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        getWindow().setStatusBarColor(getResources().getColor(R.color.black));

        tabLayout = findViewById(R.id.tablayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout1 = findViewById(R.id.tablayout1);
        viewPager2 = findViewById(R.id.viewPager2);

        buttonStart = findViewById(R.id.buttonStart);

        sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        tabLayout.addTab(tabLayout.newTab().setText("1"));
        tabLayout.addTab(tabLayout.newTab().setText("2"));
        tabLayout.addTab(tabLayout.newTab().setText("3"));
        tabLayout.addTab(tabLayout.newTab().setText("4"));
        tabLayout.addTab(tabLayout.newTab().setText("5"));
        tabLayout.addTab(tabLayout.newTab().setText("6"));
        tabLayout.addTab(tabLayout.newTab().setText("7"));
        tabLayout.addTab(tabLayout.newTab().setText("8"));
        tabLayout.addTab(tabLayout.newTab().setText("9"));
        tabLayout.addTab(tabLayout.newTab().setText("10"));
        tabLayout.addTab(tabLayout.newTab().setText("11"));
        tabLayout.addTab(tabLayout.newTab().setText("12"));
        tabLayout.setBackgroundColor(getColor(R.color.rowcolor));

        context = getApplicationContext();

        ViewGroup viewGroup = (ViewGroup) tabLayout.getChildAt(0);
        int count = viewGroup.getChildCount();
        for (int i = 1; i < count; i++) {
            ViewGroup viewGroup1 = (ViewGroup) viewGroup.getChildAt(i);
            viewGroup1.setEnabled(false);
        }


        tabLayout1.addTab(tabLayout1.newTab().setText("Пн"));
        tabLayout1.addTab(tabLayout1.newTab().setText("Ср"));
        tabLayout1.addTab(tabLayout1.newTab().setText("Пт"));


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentAdapter = new ExerciseFragmentAdapter(fragmentManager, getLifecycle(), this.getApplicationContext(), this);

        viewPager2.setAdapter(fragmentAdapter);



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() != 0) {
                    sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                    editor.putInt("MAIN_POSITION", tab.getPosition()).apply();
                    firstFragments[tab.getPosition()] = new FirstFragment(R.layout.fragment_first);
                    editor.putInt("Color", 1).apply();
                    secondFragments[tab.getPosition()] = new SecondFragment(R.layout.fragment_second);
                    thirdFragments[tab.getPosition()] = new ThirdFragment(R.layout.fragment_third);
                    fragmentAdapter.replace(0, firstFragments[tab.getPosition()], true, true);
                    fragmentAdapter.replace(1, secondFragments[tab.getPosition()], true, true);
                    fragmentAdapter.replace(2, thirdFragments[tab.getPosition()], true, true);

                } else {
                    sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                    editor.putInt("MAIN_POSITION", tab.getPosition()).apply();
                    fragmentAdapter.replaceDef(0, false, false);
                    fragmentAdapter.replaceDef(1, false, false);
                    fragmentAdapter.replaceDef(2, false, false);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout1.selectTab(tabLayout1.getTabAt(position));
            }
        });


        buttonStart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (buttonStart.getText().equals("НАЧАТЬ")) {
                        sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        editor.putBoolean("Training", true).apply();

                        v.playSoundEffect(SoundEffectConstants.CLICK);
                        if (!sharedPreferences.contains("TrainingDayOne")) {
                            tabLayout1.selectTab(tabLayout1.getTabAt(0));
                        } else if (!sharedPreferences.contains("TrainingDayTwo")) {
                            tabLayout1.selectTab(tabLayout1.getTabAt(1));
                        } else if (!sharedPreferences.contains("TrainingDayThree")){
                            tabLayout1.selectTab(tabLayout1.getTabAt(2));
                        } else if (sharedPreferences.contains("TrainingDayThree")) {
                            tabLayout.selectTab(tabLayout.getTabAt(tabLayout.getSelectedTabPosition() + 1));
                            editor.remove("TrainingDayOne").commit();
                            editor.remove("TrainingDayTwo").commit();
                            editor.remove("TrainingDayThree").commit();
                        }
                        isRunning = true;
                        if (!called) {
                            clockRun();
                            called = true;
                        }

                        int whichPage = 0;
                        switch (tabLayout1.getSelectedTabPosition()) {
                            case 0:
                                break;
                            case 1:
                                whichPage = 1;
                                break;
                            case 2:
                                whichPage = 2;
                                break;
                        }
                        ViewGroup viewGroup = (ViewGroup) tabLayout1.getChildAt(0);
                        int count = viewGroup.getChildCount();
                        for (int i = 0; i < count; i++) {
                            if (i != whichPage) {
                                ViewGroup viewGroup1 = (ViewGroup) viewGroup.getChildAt(i);
                                viewGroup1.setEnabled(false);
                            }
                        }
                        viewPager2.setUserInputEnabled(false);
                    }
                    if (!buttonStart.getText().equals("СТОП")) {
                        buttonStart.setText("СТОП");
                        buttonStart.setBackgroundColor(getColor(R.color.greendark));
                    } else {
                        isRunning = false;
                        seconds = 0;
                        sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        editor.remove("Training").commit();
                        buttonStart.setText("НАЧАТЬ");
                        buttonStart.setBackgroundColor(getColor(R.color.startbuttoncolor));
                        ViewGroup viewGroup = (ViewGroup) tabLayout1.getChildAt(0);
                        int count = viewGroup.getChildCount();
                        for (int i = 0; i < count; i++) {
                            ViewGroup viewGroup1 = (ViewGroup) viewGroup.getChildAt(i);
                            viewGroup1.setEnabled(true);
                        }
                        viewPager2.setUserInputEnabled(true);
                    }
                }
                return true;
            }
        });


        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navView);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Toast.makeText(MainActivity3.this, "Home selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.record:
                        Intent intent = new Intent(context, RecordActivity.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity3.this, "Record selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.food:
                        Intent intent1 = new Intent(context, FoodActivity.class);
                        startActivity(intent1);
                        Toast.makeText(MainActivity3.this, "Food selected", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        super.onBackPressed();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void changeMainTab() {
        Bundle bundle = new Bundle();
        String str = bundle.getString("READY3");
        int mainPos = bundle.getInt("MAIN_POSITION");
        if (str != null) {
            if (str.equals("READY3")) {
                tabLayout.selectTab(tabLayout.getTabAt(mainPos + 1));
            }
        }
        tabLayout.selectTab(tabLayout.getTabAt(1));
    }

    public void clockRun() {
        final Handler handler = new Handler();
        TextView textView = findViewById(R.id.clock);
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs);
                textView.setText(time);
                if (isRunning) {
                    seconds++;
                } else {
                    if (!sharedPreferences.contains("seconds")) {
                        editor.putInt("seconds", seconds).apply();
                    } else {
                        int tempSeconds = sharedPreferences.getInt("seconds", 0);
                        editor.remove("seconds").commit();
                        tempSeconds += seconds;
                        editor.putInt("seconds", tempSeconds);
                    }
                    textView.setText("");
                    handler.removeMessages(0);
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}

