package com.example.homefit;

import java.util.List;

import retrofit2.Response;
import rx.Observable;

public class WorkoutRepositoryImpl implements WorkoutRepository {

    WorkoutApi workoutApi = new RetrofitClientInstance().getRetrofitInstance().create(WorkoutApi.class);

    @Override
    public Observable<List<Workout>> getWorkouts() {
        return workoutApi.getWorkouts();
    }
}

