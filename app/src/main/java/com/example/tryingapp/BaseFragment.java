package com.example.tryingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;


public class BaseFragment extends Fragment {
    long pageId = (long)((Math.random() * ((2021 * 3) - 2021) ) + 2021);
    long pagePos = -1;
    int layoutId;
    protected FragmentReplacer fragmentReplacer;

    public BaseFragment(int layoutId) {
        this.layoutId = layoutId;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(layoutId, container, false);
    }

    public void setPageInfo(int pagePos, FragmentReplacer fragmentReplacer) {
        this.pagePos = pagePos;
        this.fragmentReplacer = fragmentReplacer;
    }
}
