package com.example.homefit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.homefit.databinding.FragmentWorkoutBinding;
import com.squareup.picasso.Picasso;

public class WorkoutFragment extends Fragment {


    private FragmentWorkoutBinding binding;
    private WorkoutViewModel viewModel;

    private WorkoutAdapter workoutAdapter = new WorkoutAdapter(step -> {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(step.getName());
        builder.setMessage(step.getDescription());
        AlertDialog alert = builder.create();

        if (alert != null) {
            alert.show();
        }

    });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentWorkoutBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(WorkoutViewModel.class);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerView.setAdapter(workoutAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Workout args = WorkoutFragmentArgs.fromBundle(getArguments()).getWorkout();
        viewModel.setWorkout(args);


        viewModel.workout.observe(getViewLifecycleOwner(), workout -> {
            Picasso.get().load(workout.getImage()).fit().centerCrop().into(binding.image);
            binding.toolbar.setTitle(workout.getName().toUpperCase());
            binding.toolbar.setSubtitle(workout.getDuration() + " minutes");
            workoutAdapter.submitList(workout.getSteps());

        });

        setupToolbar();
    }

    void setupToolbar() {
        binding.toolbar.setNavigationOnClickListener(v -> Navigation.findNavController(v).popBackStack());
    }
}

