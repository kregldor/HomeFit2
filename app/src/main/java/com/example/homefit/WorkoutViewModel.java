package com.example.homefit;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WorkoutViewModel extends ViewModel {
    MutableLiveData<Workout> workout = new MutableLiveData<>();

    void setWorkout(Workout workout){
        this.workout.postValue(workout);
    }
}
