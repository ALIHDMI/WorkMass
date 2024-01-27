package com.example.tryingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.Inet4Address;
import java.util.ArrayList;


public class FirstFragment extends BaseFragment implements RecycleViewInterface, SetDone {
    ArrayList<ExerciesModel> exerciesModels = new ArrayList<>();
    Exercise_RecycleViewAdapter adapter;
    ExerciseChoose_RecucleViewAdapter adapterChoose;
    RecyclerView recyclerView;
    View itemView;
    ArrayList<Integer> doneElements = new ArrayList<Integer>();

    View rootView;
    TextView show;
    boolean isDone = false;
    ImageView imageView;
    int pos;
    int doneCount = 0;

    MainActivityWarmUp mainActivityWarmUp;
    BottomSheetFragment bottomSheetFragment;

    SharedPreferences sharedPreferences2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_first, container, false);
        recyclerView = rootView.findViewById(R.id.recycleView);
        rootView.setId(pos);
        return rootView;
    }

    public FirstFragment(int layoutId) {
        super(layoutId);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpReps();
        recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sharedPreferences2 = getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        adapterChoose = new ExerciseChoose_RecucleViewAdapter(getContext(), exerciesModels, this);
        adapter = new Exercise_RecycleViewAdapter(getContext(), exerciesModels, this);
        if (!sharedPreferences2.contains("FirstSettings")) {
            recyclerView.setAdapter(adapter);
        } else {
            recyclerView.setAdapter(adapterChoose);
        }

    }

    private void setUpReps() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String[] exerciseName = new String[7];
        String[] exerciseHowMuch = new String[7];
        String[] exerciseReps = new String[7];
        if (sharedPreferences.contains("Selection")) {
            int one = sharedPreferences.getInt("one", 0);
            int two = sharedPreferences.getInt("two", 0);
            int three = sharedPreferences.getInt("three", 0);
            int four = sharedPreferences.getInt("four", 0);
            exerciseName[0] = "Разминка";
            programm(one, two, three, four, exerciseName, exerciseHowMuch, exerciseReps);
            for (int i = 0; i < exerciseName.length; i++) {
                exerciesModels.add(new ExerciesModel(exerciseName[i], exerciseHowMuch[i], exerciseReps[i]));
            }
        } else if (sharedPreferences.contains("FirstSettings")) {
            exerciseName = getResources().getStringArray(R.array.chooseExercise);
            for (int i = 0; i < exerciseName.length; i++) {
                exerciesModels.add(new ExerciesModel(exerciseName[i]));
            }
        } else if (sharedPreferences.contains("MonDone")){
            String str = sharedPreferences.getString("chooseNumber", "");
            String[] arrayExercises = getResources().getStringArray(R.array.chooseExercise);

            ArrayList<Integer> arrayMon = new ArrayList<>();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                int a = Character.getNumericValue(c);
                arrayMon.add(a);
            }
            String[] pushMon = new String[arrayMon.size()];
            for (int i = 0; i < pushMon.length; i++) {
                pushMon[i] = arrayExercises[arrayMon.get(i)];
            }
            for (int i = 0; i < pushMon.length; i++) {
                exerciesModels.add(new ExerciesModel(pushMon[i], "Повторений 10", "Подходов 4"));
            }
        } else if (!sharedPreferences.contains("MonDone")) {
            exerciseName = getResources().getStringArray(R.array.splitday1);
            exerciseHowMuch = getResources().getStringArray(R.array.splitday1howmuch);
            exerciseReps = getResources().getStringArray(R.array.splitday1reps);
            for (int i = 0; i < exerciseName.length; i++) {
                exerciesModels.add(new ExerciesModel(exerciseName[i], exerciseHowMuch[i], exerciseReps[i]));
            }
            editor.putInt("Split", exerciseName.length).apply();

        }
    }
    public void programm(int one, int two, int three, int four, String[] exerciseName, String[] exerciseHowMuch, String[] exerciseReps) {
        int reps = 10;
        String[] NameTemp = getResources().getStringArray(R.array.legs);
        String[] NameTemp2 = getResources().getStringArray(R.array.delts);
        int i = 0;
        if (one == 1 | three == 1) {
            i = 1;
        }
        for (int p = 1; p < 4; p++) {
            exerciseName[p] = NameTemp[i];
            i++;
        }
        i = 0;
        for (int p = 4; p < 7; p++) {
            exerciseName[p] = NameTemp2[i];
            i++;
        }
        for (int u = 1; u < exerciseReps.length; u++) {
            if ((two == 2 & one == 1) | (two == 2 & one == 2) | (two == 1 & one == 1)) {
                exerciseReps[u] = "Повторений 15";
            } else if (two == 1 & one == 2) {
                exerciseReps[u] = "Повторений 11";
            } else {
                exerciseReps[u] = "Повторений 8";
            }
        }
        for (int t = 1; t < exerciseHowMuch.length; t++) {
            if (four == 1) {
                switch (t) {
                    case 1:
                        exerciseHowMuch[t] = "Подходов 5";
                        break;
                    case 2:
                        exerciseHowMuch[t] = "Подходов 5";
                        break;
                    case 3:
                        exerciseHowMuch[t] = "Подходов 5";
                        break;
                }
            }
            if (four != 1 | t >= 4) {
                exerciseHowMuch[t] = "Подходов 4";
            }
        }
        String[] legsTemp = getResources().getStringArray(R.array.legs);
        ArrayList<String> legsArray = new ArrayList<>();
        for (int r = 0; r < legsTemp.length; r++) {
            legsArray.add(legsTemp[r]);
        }
        if (one == 1) {
            for (int o = 0; o < legsArray.size(); o++) {
                if (legsArray.get(o).equals("Приседания")) {
                    legsArray.remove(o);
                }
            }
        }
    }

    public void onItemClick(int position, View itemView) {
        this.itemView = itemView;
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (itemView.findViewById(R.id.textView4).toString().equals("Разминка") | position == 0) {
            mainActivityWarmUp = new MainActivityWarmUp();
//        bottomSheetFragment.changeView(position, "Monday");
            mainActivityWarmUp.show(getActivity().getSupportFragmentManager(), mainActivityWarmUp.getTag());
        }
        if (sharedPreferences.contains("Training")) {
            boolean training = sharedPreferences.getBoolean("Training", false);
            boolean contin = true;
            for (int top: doneElements) {
                if (top == position) {
                    contin = false;
                }
            }
            if (training & contin) {
                if (sharedPreferences.contains("Selection")) {
                    bottomSheetFragment = new BottomSheetFragment("Monday", position, this, false, itemView);
//        bottomSheetFragment.changeView(position, "Monday");
                    bottomSheetFragment.show(getActivity().getSupportFragmentManager(), bottomSheetFragment.getTag());
                    TextView textView = itemView.findViewById(R.id.Done);
                } else {
                    bottomSheetFragment = new BottomSheetFragment("Monday", position, this, false);
//        bottomSheetFragment.changeView(position, "Monday");
                    bottomSheetFragment.show(getActivity().getSupportFragmentManager(), bottomSheetFragment.getTag());
                    TextView textView = itemView.findViewById(R.id.Done);
                }

            }
//            TextView textView = itemView.findViewById(R.id.Done);
//            sharedPreferences = this.getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
//            if (!contin) {
//                if (isDone) {
//                    textView.setText("Done");
//                    doneCount++;
//                    if (doneCount == sharedPreferences.getInt("Split", 999)) {
//                        editor.putInt("TrainingDayOne", 0).apply();
//                    }
//                }
//                isDone = false;
//            }
        } else if (sharedPreferences.contains("FirstSettings")) {
            String mon = "";
            if (sharedPreferences.contains("MondayChoose")) {
                mon = sharedPreferences.getString("MondayChoose", "");
                mon += String.valueOf(position);
                editor.remove("MondayChoose").apply();
                editor.putString("MondayChoose", mon);
                editor.apply();
            }


            TextView textView = itemView.findViewById(R.id.Done);
            textView.setText("Выбрано");
        }
    }
    @Override
    public void setDone(int position, boolean isDone) {
        this.isDone = isDone;
        if (bottomSheetFragment.summ > 0) {
            doneElements.add(position);
        }
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        bottomSheetFragment.dismiss();
        if (isDone) {
            TextView textView = itemView.findViewById(R.id.Done);
            textView.setText("Done");
            doneCount++;
            if (doneCount == sharedPreferences.getInt("Split", 999)) {
                editor.putInt("TrainingDayOne", 0).apply();
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