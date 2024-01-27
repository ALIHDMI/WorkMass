package com.example.tryingapp;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetFragment extends BottomSheetDialogFragment{
    BottomSheetDialog dialog;
    BottomSheetBehavior<View> behavior;
    View rootView, view1;
    ImageView imageView;
    TextView show, show1;
    Button buttonStart;
    SetDone setDone;
    String[] exerciseName;
    String textName;
    int position;
    String day;
    boolean selection;

    EditText editText;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;

    EditText editTextt;
    EditText editText11;
    EditText editText22;
    EditText editText33;
    EditText editText44;
    View itemView;
    public BottomSheetFragment(String day, int pos, SetDone setDone, boolean selection) {
        position = pos;
        this.day = day;
        this.setDone = setDone;
        this.selection = selection;
    }

    public BottomSheetFragment(String day, int pos, SetDone setDone, boolean selection, View itemView) {
        position = pos;
        this.day = day;
        this.setDone = setDone;
        this.selection = selection;
        this.itemView = itemView;
    }
    public int summ = 0;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
        show = (TextView) rootView.findViewById(R.id.show);
        show1 = (TextView) rootView.findViewById(R.id.textView7);
        buttonStart = rootView.findViewById(R.id.buttonStart);

        editText = rootView.findViewById(R.id.editkg0);
        editText1 = rootView.findViewById(R.id.editkg1);
        editText2 = rootView.findViewById(R.id.editkg2);
        editText3 = rootView.findViewById(R.id.editkg3);
        editText4 = rootView.findViewById(R.id.editkg4);

        editTextt = rootView.findViewById(R.id.editreps0);
        editText11 = rootView.findViewById(R.id.editreps1);
        editText22 = rootView.findViewById(R.id.editreps2);
        editText33 = rootView.findViewById(R.id.editreps3);
        editText44 = rootView.findViewById(R.id.editreps4);

        sharedPreferences = this.getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        imageView = rootView.findViewById(R.id.imageViewSheet);

        if (itemView != null) {
            TextView textView = itemView.findViewById(R.id.textView4);
            show.setText(textView.getText().toString());
        }
        if (day.equals("Monday") & itemView == null) {
            exerciseName = getResources().getStringArray(R.array.splitday1);
            switch (position) {
                case 0:
                    imageView.setImageResource(R.drawable.exercisesquat);
                    show.setText(exerciseName[position]);
                    break;
                case 1:
                    imageView.setImageResource(R.drawable.exerciselegpress);
                    show.setText(exerciseName[position]);
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.exerciseonbend);
                    show.setText(exerciseName[position]);
                    break;
                case 3:
                    imageView.setImageResource(R.drawable.exercisestandingbench);
                    show.setText(exerciseName[position]);
                    break;
                case 4:
                    imageView.setImageResource(R.drawable.exercise_dumbellfly);
                    show.setText(exerciseName[position]);
                    break;
            }
        } else if (day.equals("Wednesday")) {
            exerciseName = getResources().getStringArray(R.array.splitday2);
            switch (position) {
                case 0:
                    imageView.setImageResource(R.drawable.exercise_hyper);
                    show.setText(exerciseName[position]);
                    break;
                case 1:
                    imageView.setImageResource(R.drawable.exercise_deadlift);
                    show.setText(exerciseName[position]);
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.exercise_pulldown);
                    show.setText(exerciseName[position]);
                    break;
                case 3:
                    imageView.setImageResource(R.drawable.exercise_lowrow);
                    show.setText(exerciseName[position]);
                    break;
                case 4:
                    imageView.setImageResource(R.drawable.exercise_biceps);
                    show.setText(exerciseName[position]);
                    break;
                case 5:
                    imageView.setImageResource(R.drawable.exercise_molotok);
                    show.setText(exerciseName[position]);
                    break;
            }
        } else if (day.equals("Friday")) {
            exerciseName = getResources().getStringArray(R.array.splitday3);
            switch (position) {
                case 0:
                    imageView.setImageResource(R.drawable.exercise_benchpress);
                    show.setText(exerciseName[position]);
                    break;
                case 1:
                    imageView.setImageResource(R.drawable.exercise_dumbellpress);
                    show.setText(exerciseName[position]);
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.exercise_crossover);
                    show.setText(exerciseName[position]);
                    break;
                case 3:
                    imageView.setImageResource(R.drawable.exercise_frenchbench);
                    show.setText(exerciseName[position]);
                    break;
                case 4:
                    imageView.setImageResource(R.drawable.exercise_tricepsblock);
                    show.setText(exerciseName[position]);
                    break;
            }
        }

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sum = 0;
                sum += checkNull(editText, editTextt);
                sum += checkNull(editText1, editText11);
                sum += checkNull(editText2, editText22);
                sum += checkNull(editText3, editText33);
                sum += checkNull(editText4, editText44);
                summ = sum;
                if (!sharedPreferences.contains("summa")) {
                    editor.putInt("summa", sum).apply();
                } else if (sharedPreferences.contains("summa")) {
                    int summa = sharedPreferences.getInt("summa", 0);
                    editor.remove("summa").commit();
                    summa += sum;
                    editor.putInt("summa", summa).apply();
                    if (!sharedPreferences.contains("trenki")) {
                        editor.putInt("trenki", 1);
                    } else if (sharedPreferences.contains("trenki")) {
                        int trenki = sharedPreferences.getInt("trenki", 0);
                        editor.remove("trenki").commit();
                        editor.putInt("trenki", trenki + 1).apply();
                    }
                }
                if (sum > 0) {
                    if (!sharedPreferences.contains("kg")) {
                        editor.putInt("kg", sum).apply();
                    } else {
                        int tempSum = sharedPreferences.getInt("kg", 0);
                        tempSum += sum;
                        editor.remove("kg").commit();
                        editor.putInt("kg", tempSum);
                    }
                    setDone.setDone(position, true);
                } else setDone.setDone(position, false);
            }
        });
        return rootView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        return dialog;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        behavior = BottomSheetBehavior.from((View) view.getParent());
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        LinearLayout linearLayout = dialog.findViewById(R.id.showinglinear);
        assert linearLayout != null;
        linearLayout.setMinimumHeight(Resources.getSystem().getDisplayMetrics().heightPixels - 50);
    }


    public int checkNull(EditText editText, EditText editText1) {
        if (editText != null & editText1 != null) {
            if (!(editText.getText().toString().equals("")) & !(editText1.getText().toString().equals(""))) {
                int sum1 = Integer.parseInt(editText.getText().toString());
                int sum2 = Integer.parseInt(editText1.getText().toString());
                return sum1 * sum2;
            }
        }
        return 0;
    }
}