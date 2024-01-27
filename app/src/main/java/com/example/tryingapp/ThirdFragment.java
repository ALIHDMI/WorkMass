package com.example.tryingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Set;


public class ThirdFragment extends BaseFragment implements RecycleViewInterface, SetDone {
    ArrayList<ExerciesModel> exerciesModels = new ArrayList<>();
    Exercise_RecycleViewAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<Integer> doneElements2 = new ArrayList<Integer>();

    View rootView;
    TextView show;
    ImageView imageView;
    boolean isDone = false;
    int pos;
    int doneCount2 = 0;
    View itemView2;


    BottomSheetFragment bottomSheetFragment2;

    public ThirdFragment(int layoutId) {
        super(layoutId);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_third, container, false);
        recyclerView = rootView.findViewById(R.id.recycleView);
        rootView.setId(pos);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpReps();
        recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Exercise_RecycleViewAdapter(getContext(), exerciesModels, this);
        recyclerView.setAdapter(adapter);
    }

    private void setUpReps() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String[] exerciseName = getResources().getStringArray(R.array.splitday3);
        String[] exerciseHowMuch = getResources().getStringArray(R.array.splitday3howmuch);
        String[] exerciseReps = getResources().getStringArray(R.array.splitday3reps);

        editor.putInt("Split2", exerciseName.length).apply();
        for (int i = 0; i < exerciseName.length; i++) {
            exerciesModels.add(new ExerciesModel(exerciseName[i], exerciseHowMuch[i], exerciseReps[i]));
        }
    }

    public void onItemClick(int position, View itemView) {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (sharedPreferences.contains("Training")) {
            boolean training = sharedPreferences.getBoolean("Training", false);
            boolean contin = true;
            for (int top: doneElements2) {
                if (top == position) {
                    contin = false;
                }
            }
            if (training & contin) {
                bottomSheetFragment2 = new BottomSheetFragment("Friday", position, this, false);
//        bottomSheetFragment.changeView(position, "Monday");
                bottomSheetFragment2.show(getActivity().getSupportFragmentManager(), bottomSheetFragment2.getTag());
                TextView textView = itemView.findViewById(R.id.Done);

            }
//            TextView textView = itemView.findViewById(R.id.Done);
//            sharedPreferences = this.getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
//            if (!contin) {
//                if (isDone) {
//                    textView.setText("Done");
//                    doneCount++;
//                    if (doneCount == sharedPreferences.getInt("Split", 999)) {
//                        editor.putInt("TrainingDayThree", 0).apply();
//                    }
//                }
//                isDone = false;
//            }
        }
    }

    @Override
    public void setDone(int position, boolean isDone) {
        this.isDone = isDone;
        if (bottomSheetFragment2.summ > 0) {
            doneElements2.add(position);
        }
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        bottomSheetFragment2.dismiss();
        if (isDone) {
            TextView textView = itemView2.findViewById(R.id.Done);
            textView.setText("Done");
            doneCount2++;
            if (doneCount2 == sharedPreferences.getInt("Split", 999)) {
                editor.putInt("TrainingDayThree", 0).apply();
            }
        }
        isDone = false;
    }


//        Intent intent = new Intent(getContext(), ShowingActivity.class);
//        intent.putExtra("position", position);
//        intent.putExtra("day", "monday");
//        startActivity(intent);

//    @Override
//    public void getView(View view) {
//        rootView = view;
//        show = rootView.findViewById(R.id.show);
//        imageView = rootView.findViewById(R.id.imageViewSheet);
//    }
}