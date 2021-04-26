package com.example.homefit;

import java.util.List;

import retrofit2.Response;
import retrofit2.http.GET;
import rx.Observable;

public interface WorkoutApi {
    @GET("/list")
    public Observable<List<Workout>> getWorkouts();

}
