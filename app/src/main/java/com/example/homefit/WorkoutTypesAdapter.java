package com.example.homefit;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ListAdapter;

import com.example.homefit.databinding.WorkoutViewBinding;
import com.squareup.picasso.Picasso;

public class WorkoutTypesAdapter extends ListAdapter<Workout, WorkoutTypesAdapter.WorkoutViewHolder> {

    private final MyOnClickListener listener;

    WorkoutTypesAdapter(MyOnClickListener listener) {
        super(new CharacterItemDiffCallback());
        this.listener = listener;
    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WorkoutViewBinding binding = WorkoutViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new WorkoutViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }


    public class WorkoutViewHolder extends RecyclerView.ViewHolder {

        WorkoutViewBinding binding;

        WorkoutViewHolder(WorkoutViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindTo(Workout workout) {


            binding.workoutName.setText(workout.getName().toUpperCase());
            binding.workoutLevel.setText(workout.getLevel().label);
            binding.workoutDuration.setText(workout.getDuration() + " min");


            Picasso.get().load(workout.getImage()).fit().centerCrop().into(binding.workoutImg);


            itemView.setOnClickListener(v -> listener.onClick(workout));

        }


    }


    interface MyOnClickListener {
        void onClick(Workout workout);
    }
}

class CharacterItemDiffCallback extends DiffUtil.ItemCallback<Workout> {

    @Override
    public boolean areItemsTheSame(@NonNull Workout oldItem, @NonNull Workout newItem) {
        return oldItem == newItem;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Workout oldItem, @NonNull Workout newItem) {
        return oldItem.equals(newItem);
    }
}

