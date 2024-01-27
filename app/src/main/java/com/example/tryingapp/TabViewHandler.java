package com.example.tryingapp;

import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class TabViewHandler {
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;

    public TabViewHandler(TabLayout tabLayout, ViewPager2 viewPager2) {
        this.tabLayout = tabLayout;
        this.viewPager2 = viewPager2;
    }
}
