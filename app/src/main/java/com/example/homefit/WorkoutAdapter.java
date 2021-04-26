package com.example.homefit;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homefit.databinding.StepViewBinding;
import com.squareup.picasso.Picasso;


public class WorkoutAdapter extends ListAdapter<Step, WorkoutAdapter.StepViewHolder> {

    private final MyOnClickListener listener;

    WorkoutAdapter(MyOnClickListener listener) {
        super(new StepItemDiffCallback());
        this.listener = listener;
    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StepViewBinding binding = StepViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new StepViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }


    public class StepViewHolder extends RecyclerView.ViewHolder {

        StepViewBinding binding;

        StepViewHolder(StepViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindTo(Step step) {


            binding.stepDescription.setText(step.getName());
            binding.sets.setText(String.valueOf(step.getSets())+"x");
            binding.reps.setText(String.valueOf(step.getReps()));


            Picasso.get().load(step.getImage()).fit().centerCrop().into(binding.stepImg);


            itemView.setOnClickListener(v -> listener.onClick(step));

        }


    }


    interface MyOnClickListener {
        void onClick(Step step);
    }
}

class StepItemDiffCallback extends DiffUtil.ItemCallback<Step> {
    @Override
    public boolean areItemsTheSame(@NonNull Step oldItem, @NonNull Step newItem) {
        return oldItem == newItem;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Step oldItem, @NonNull Step newItem) {
        return oldItem.equals(newItem);
    }
}
