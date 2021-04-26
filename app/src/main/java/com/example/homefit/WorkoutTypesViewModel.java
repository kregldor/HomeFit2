package com.example.homefit;

import android.content.res.Resources;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.HttpException;
import retrofit2.Response;
import rx.Observable;
import rx.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

public class WorkoutTypesViewModel extends ViewModel {
    public WorkoutRepository workoutRepository = new WorkoutRepositoryImpl();
    public MutableLiveData<List<Workout>> allWorkout = new MutableLiveData<>();
    public MutableLiveData<List<Workout>> selectedWorkout = new MutableLiveData<>();
    public MutableLiveData<String> errorMessage = new MutableLiveData<>();

    void init() {
//        workoutRepository.getWorkouts().subscribeOn(Schedulers.io()).subscribe(value ->
//                allWorkout.postValue(value)
//        );
//
//        workoutRepository.getWorkouts().subscribeOn(Schedulers.io()).subscribe(value ->
//                selectedWorkout.postValue(value)
//        );

        getWorkouts();

    }


    private void getWorkouts() {
        Observable<List<Workout>> result = workoutRepository.getWorkouts();


        workoutRepository
                .getWorkouts()
                .doOnError(error -> handleError(error))
                .subscribeOn(Schedulers.io()).subscribe(value -> {

            if (value.get(0) instanceof Workout) {
                allWorkout.postValue(value);
                selectedWorkout.postValue(value);
            }

        });


    }


    private void handleError(Throwable error) {
        NetworkState networkState;

        if (error instanceof HttpException) {
            String message = ((HttpException) error).message();

            switch (((HttpException) error).code()) {
                case 403:
                    networkState = new NetworkState.HttpErrors.ResourceForbidden(message);
                    handleMessage(networkState);
                    break;
                case 404:
                    networkState = new NetworkState.HttpErrors.ResourceNotFound(message);
                    handleMessage(networkState);
                    break;
                case 500:
                    networkState = new NetworkState.HttpErrors.InternalServerError(message);
                    handleMessage(networkState);
                    break;
                case 502:
                    networkState = new NetworkState.HttpErrors.BadGateWay(message);
                    handleMessage(networkState);
                    break;
                case 301:
                    networkState = new NetworkState.HttpErrors.ResourceRemoved(message);
                    handleMessage(networkState);
                    break;
                case 302:
                    networkState = new NetworkState.HttpErrors.RemovedResourceFound(message);
                    handleMessage(networkState);
                    break;
                default:
                    networkState = new NetworkState.HttpErrors.Error("Network error, try it again");
                    handleMessage(networkState);
                    break;
            }
        }


    }

    private void handleMessage(NetworkState networkState) {
        errorMessage.postValue(networkState.getMessage());
    }


    void filterWorkouts(String selected) {
        @Nullable Type selectedType = Type.fromString(selected);
        filter(selectedType);
    }

    private void filter(@Nullable Type type) {

        List<Workout> temp = new ArrayList<>();
        for (Workout w : allWorkout.getValue()) {
            if (w.getType() == type) {
                temp.add(w);
            }
        }
        selectedWorkout.postValue(temp);
    }


}


