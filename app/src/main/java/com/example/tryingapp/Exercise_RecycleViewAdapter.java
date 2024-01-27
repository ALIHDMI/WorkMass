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

public class Exercise_RecycleViewAdapter extends RecyclerView.Adapter<Exercise_RecycleViewAdapter.MyViewHolder> {
    private final RecycleViewInterface recycleViewInterface;
    Context context;
    ArrayList<ExerciesModel> exerciesModels;
    public Exercise_RecycleViewAdapter(Context context, ArrayList<ExerciesModel> exerciesModels, RecycleViewInterface recycleViewInterface) {
        this.context = context;
        this.exerciesModels = exerciesModels;
        this.recycleViewInterface = recycleViewInterface;
    }

    @NonNull
    @Override
    public Exercise_RecycleViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycle_view_row, parent, false);
        return new Exercise_RecycleViewAdapter.MyViewHolder(view, recycleViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull Exercise_RecycleViewAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(exerciesModels.get(position).getExerciseName());
        holder.textView1.setText(exerciesModels.get(position).getHowMuch());
        holder.textView2.setText(exerciesModels.get(position).getReps());


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
            textView1 = itemView.findViewById(R.id.textView5);
            textView2 = itemView.findViewById(R.id.textView6);
            cardView = itemView.findViewById(R.id.cardView);
            SharedPreferences sharedPreferences = itemView.getContext().getSharedPreferences("settings", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recycleViewInterface != null) {
                        int pos = getAdapterPosition();
                        if (sharedPreferences.contains("chooseNumber")) {
                            String str = sharedPreferences.getString("chooseNumber", "");
                            str += String.valueOf(pos);
                            editor.remove("chooseNumber").apply();
                            editor.putString("chooseNumber", str).apply();
                        } else {
                            String str = String.valueOf(pos);
                            editor.putString("chooseNumber", str).apply();
                        }
                        if (pos != RecyclerView.NO_POSITION) {
                            recycleViewInterface.onItemClick(pos, itemView);
                        }
                    }
                }
            });


            int value = sharedPreferences.getInt("COLOR", 0);
            if (value == 1) {
                cardView.setBackgroundColor(Color.YELLOW);
            }
        }
    }
}
