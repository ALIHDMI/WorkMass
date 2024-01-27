package com.example.tryingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Locale;

public class MainActivityWarmUp extends BottomSheetDialogFragment {
    BottomSheetDialog dialog;
    BottomSheetBehavior<View> behavior;
    View rootView;
    String[] exercise = new String[6];
    final Handler handler = new Handler();
    boolean isResume = true;
    ImageView imageView;
    TextView textView2;
    TextView textView3;
    int count;
    int i = 0;
    ImageButton imageButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.activity_main_warm_up, container, false);
        imageView = rootView.findViewById(R.id.imageView);
        textView2 = rootView.findViewById(R.id.textView2);
        textView3 = rootView.findViewById(R.id.textView3);
        imageButton = rootView.findViewById(R.id.imageButton);
        count = 20;
        exercise[0] = "head1";
        exercise[1] = "head2";
        exercise[2] = "hand1";
        exercise[3] = "chest1";
        exercise[4] = "legs1";
        exercise[5] = "legs2";

        handler.post(new Runnable() {
            @Override
            public void run() {
                if (!isResume) {
                    if (i == 6) {
                        handler.removeMessages(0);
                    }
                    if (count != 0) {
                        textView3.setText(String.valueOf(count));
                        count--;
                    } else {
                        count = 20;
                        i++;
                        switch (i) {
                            case 1:
                                imageView.setBackground(getResources().getDrawable(R.drawable.head2));
                                break;
                            case 2:
                                imageView.setBackground(getResources().getDrawable(R.drawable.hand1));
                                break;
                            case 3:
                                imageView.setBackground(getResources().getDrawable(R.drawable.chest1));
                                break;
                            case 4:
                                imageView.setBackground(getResources().getDrawable(R.drawable.legs1));
                                break;
                            case 5:
                                imageView.setBackground(getResources().getDrawable(R.drawable.legs2));
                                break;
                        }
                    }
                }
                handler.postDelayed(this, 1000);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isResume) {
                    imageButton.setBackground(getResources().getDrawable(R.drawable.stop));
                    isResume = false;
                } else if (!isResume) {
                    imageButton.setBackground(getResources().getDrawable(R.drawable.play));
                    isResume = true;
                }
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
        LinearLayout linearLayout = dialog.findViewById(R.id.linearwarmup);
        assert linearLayout != null;
        linearLayout.setMinimumHeight(Resources.getSystem().getDisplayMetrics().heightPixels - 50);
    }
}