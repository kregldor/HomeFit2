package com.example.homefit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.homefit.databinding.FragmentWorkoutTypesBinding;

import java.util.Arrays;
import java.util.List;

public class WorkoutTypesFragment extends Fragment {
    FragmentWorkoutTypesBinding binding;
    private WorkoutTypesViewModel viewModel;

    private WorkoutTypesAdapter workoutTypesAdapter = new WorkoutTypesAdapter(workout -> {
        NavDirections action = WorkoutTypesFragmentDirections.actionWorkoutTypesToWorkout(workout);
        if (getView() != null) {
            Navigation.findNavController(getView()).navigate(action);
        }
    });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentWorkoutTypesBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(WorkoutTypesViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.init();

        binding.recyclerView.setAdapter(workoutTypesAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.errorMessage.observe(getViewLifecycleOwner(), message -> {
           binding.errorMessage.setText(message);

        });

        viewModel.selectedWorkout.observe(getViewLifecycleOwner(), workouts -> {
            workoutTypesAdapter.submitList(workouts);

        });

        ListPopupWindow listPopupWindow = new ListPopupWindow(requireContext(), null, R.attr.listPopupWindowStyle);

        List<String> items = Arrays.asList(Type.UPPER_BODY.label, Type.FULL_BODY.label, Type.LOWER_BODY.label);
        ArrayAdapter adapter = new ArrayAdapter(requireContext(), R.layout.list_popup_window_item, items);

        listPopupWindow.setAnchorView(binding.listPopupButton);
        listPopupWindow.setAdapter(adapter);
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewModel.filterWorkouts(items.get(position));
                listPopupWindow.dismiss();
            }
        });

        binding.listPopupButton.setOnClickListener(v -> listPopupWindow.show());

        setupToolbar();

    }

    void setupToolbar() {
        binding.myToolbar.setNavigationOnClickListener(v -> Navigation.findNavController(v).popBackStack());
    }
}


