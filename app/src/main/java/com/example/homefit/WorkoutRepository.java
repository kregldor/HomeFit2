package com.example.homefit;

import java.util.List;

import retrofit2.Response;
import rx.Observable;

public interface WorkoutRepository {
    public Observable<List<Workout>> getWorkouts();
}
