package com.example.tryingapp;

public class ExerciesModel {
    String exerciseName;
    String howMuch;
    String reps;

    public ExerciesModel(String exerciseName, String howMuch, String reps) {
        this.exerciseName = exerciseName;
        this.howMuch = howMuch;
        this.reps = reps;
    }
    public ExerciesModel(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public String getHowMuch() {
        return howMuch;
    }

    public String getReps() {
        return reps;
    }
}
