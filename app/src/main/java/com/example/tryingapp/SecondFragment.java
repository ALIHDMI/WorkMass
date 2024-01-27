package com.example.tryingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SecondFragment extends BaseFragment implements RecycleViewInterface, SetDone{

    ArrayList<Integer> doneElements1 = new ArrayList<>();
    ArrayList<ExerciesModel> exerciesModels = new ArrayList<>();
    Exercise_RecycleViewAdapter adapter;
    RecyclerView recyclerView;
    boolean isDone;
    View itemView1;
    int doneCount1 = 0;
    MainActivityWarmUp mainActivityWarmUp;
    BottomSheetFragment bottomSheetFragment1;

    public SecondFragment(int layoutId) {
        super(layoutId);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_second, container, false);
        recyclerView = rootView.findViewById(R.id.recycleView);


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
//        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        String[] exerciseName = getResources().getStringArray(R.array.splitday2);
//        String[] exerciseHowMuch = getResources().getStringArray(R.array.splitday2howmuch);
//        String[] exerciseReps = getResources().getStringArray(R.array.splitday2reps);
//
//        editor.putInt("Split1", exerciseName.length).apply();
//        for (int i = 0; i < exerciseName.length; i++) {
//            exerciesModels.add(new ExerciesModel(exerciseName[i], exerciseHowMuch[i], exerciseReps[i]));
//        }
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
            exerciseName = getResources().getStringArray(R.array.splitday2);
            exerciseHowMuch = getResources().getStringArray(R.array.splitday2howmuch);
            exerciseReps = getResources().getStringArray(R.array.splitday2reps);
            for (int i = 0; i < exerciseName.length; i++) {
                exerciesModels.add(new ExerciesModel(exerciseName[i], exerciseHowMuch[i], exerciseReps[i]));
            }
            editor.putInt("Split", exerciseName.length).apply();

        }
    }
    public void programm(int one, int two, int three, int four, String[] exerciseName, String[] exerciseHowMuch, String[] exerciseReps) {
        int reps = 10;
        String[] NameTemp = getResources().getStringArray(R.array.spine);
        String[] NameTemp2 = getResources().getStringArray(R.array.arms);
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
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (sharedPreferences.contains("Training")) {
            boolean training = sharedPreferences.getBoolean("Training", false);
            boolean contin = true;
            for (int top: doneElements1) {
                if (top == position) {
                    contin = false;
                }
            }
            if (training & contin) {
                bottomSheetFragment1 = new BottomSheetFragment("Wednesday", position, this, false);
//        bottomSheetFragment.changeView(position, "Monday");
                bottomSheetFragment1.show(getActivity().getSupportFragmentManager(), bottomSheetFragment1.getTag());
                TextView textView = itemView.findViewById(R.id.Done);

            }
//            TextView textView = itemView.findViewById(R.id.Done);
//            sharedPreferences = this.getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
//            if (!contin) {
//                if (isDone) {
//                    textView.setText("Done");
//                    doneCount1++;
//                    if (doneCount1 == sharedPreferences.getInt("Split", 999)) {
//                        editor.putInt("TrainingDayTwo", 0).apply();
//                    }
//                }
//                isDone = false;
//            }
        }
    }

    @Override
    public void setDone(int position, boolean isDone) {
        this.isDone = isDone;
        if (bottomSheetFragment1.summ > 0) {
            doneElements1.add(position);
        }
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        bottomSheetFragment1.dismiss();
        if (isDone) {
            TextView textView = itemView1.findViewById(R.id.Done);
            textView.setText("Done");
            doneCount1++;
            if (doneCount1 == sharedPreferences.getInt("Split", 999)) {
                editor.putInt("TrainingDayTwo", 0).apply();
            }
        }
        isDone = false;
    }
}