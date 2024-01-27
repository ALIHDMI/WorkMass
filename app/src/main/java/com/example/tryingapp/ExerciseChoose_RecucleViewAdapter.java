package com.example.tryingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExerciseChoose_RecucleViewAdapter extends RecyclerView.Adapter<Exercise_RecycleViewAdapter.MyViewHolder> {
    private final RecycleViewInterface recycleViewInterface;
    Context context;
    ArrayList<ExerciesModel> exerciesModels;
    public ExerciseChoose_RecucleViewAdapter(Context context, ArrayList<ExerciesModel> exerciesModels, RecycleViewInterface recycleViewInterface) {
        this.context = context;
        this.exerciesModels = exerciesModels;
        this.recycleViewInterface = recycleViewInterface;
    }

    @NonNull
    @Override
    public Exercise_RecycleViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclechoose_view_row, parent, false);
        return new Exercise_RecycleViewAdapter.MyViewHolder(view, recycleViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull Exercise_RecycleViewAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(exerciesModels.get(position).getExerciseName());
    }

    @Override
    public int getItemCount() {
        return exerciesModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        TextView textView, textView1, textView2;
        public MyViewHolder(@NonNull View itemView, RecycleViewInterface recycleViewInterface) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView4);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recycleViewInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recycleViewInterface.onItemClick(pos, itemView);
                        }
                    }
                }
            });

            SharedPreferences sharedPreferences = itemView.getContext().getSharedPreferences("settings", Context.MODE_PRIVATE);
            int value = sharedPreferences.getInt("COLOR", 0);
            if (value == 1) {
                cardView.setBackgroundColor(Color.YELLOW);
            }
        }
    }
}
