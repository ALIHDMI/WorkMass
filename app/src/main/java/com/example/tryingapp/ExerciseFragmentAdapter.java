package com.example.tryingapp;


import static androidx.viewpager.widget.PagerAdapter.POSITION_NONE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.ArrayMap;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.Random;

public class ExerciseFragmentAdapter extends FragmentStateAdapter implements FragmentReplacer{
    private ArrayList<BaseFragment> fragments = new ArrayList<>();
    FirstFragment[] firstFragments = new FirstFragment[12];
    SecondFragment[] secondFragments = new SecondFragment[12];
    ThirdFragment[] thirdFragments = new ThirdFragment[12];

    AreFragmentsReady areFragmentsReady;

    private static final int PAGE_COUNT = 3;

    SharedPreferences sharedPreferences1;
    SharedPreferences.Editor editor;
    Context context;


    public ExerciseFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, Context context, AreFragmentsReady areFragmentsReady) {
        super(fragmentManager, lifecycle);
        this.context = context;
        this.areFragmentsReady = areFragmentsReady;
    }
    public ExerciseFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, Context context) {
        super(fragmentManager, lifecycle);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return replaceDef(position, false, false);
    }
    @Override
    public int getItemCount() {
        sharedPreferences1 = this.context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        if (!sharedPreferences1.contains("FirstSettings")) {
            return PAGE_COUNT;
        } else {
            return 1;
        }
    }


    @Override
    public void replace(int position, BaseFragment newFragment, Boolean isNotify, Boolean isYes) {
        Boolean isNotify2 = isNotify;
        if (!isYes) {
            isNotify2 = true;
        }
        newFragment.setPageInfo(position, this);
        fragments.add(position, newFragment);
        if (isNotify2) {
            Handler handler = new Handler();

            final Runnable r = new Runnable() {
                public void run() {
                    notifyItemChanged(position);
                }
            };
            handler.post(r);

        }
    }

    @Override
    public BaseFragment replaceDef(int position, Boolean isNotify, Boolean isYes) {
        BaseFragment fragment = new FirstFragment(R.layout.fragment_first);
        switch (position) {
            case 0:
                firstFragments[0] = new FirstFragment(R.layout.fragment_first);
                fragment = firstFragments[0];
                break;
            case 1:
                secondFragments[0] = new SecondFragment(R.layout.fragment_second);
                fragment = secondFragments[0];
                break;
            case 2:
                thirdFragments[0] = new ThirdFragment(R.layout.fragment_third);
                fragment = thirdFragments[0];
                break;
        }

        replace(position, fragment, isNotify, isYes);
        return fragment;
    }

    @Override
    public boolean containsItem(long itemId) {
        boolean isContains = false;
        for (int i = 0; i < fragments.size(); i++) {
            if (fragments.get(i).pageId == itemId) {
                isContains = true;
                break;
            }
        }
        return isContains;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
