package com.example.homefit;

import android.annotation.SuppressLint;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.Objects;


public class Step implements Serializable {
    private String name;
    private int sets;
    private int reps;
    private String image;
    @Nullable
    private String description;

    public Step(String name, int sets, int reps, String image, @Nullable String description) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.image = image;

        if (description != null) {
            this.description = description;
        }

    }

    public String getName() {
        return name;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    public String getImage() {
        return image;
    }

    @Nullable
    public String getDescription() {
        return description;
    }


    @SuppressLint("NewApi")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Step)) return false;
        Step step = (Step) o;
        return getSets() == step.getSets() &&
                getReps() == step.getReps() &&
                getName().equals(step.getName()) &&
                getImage().equals(step.getImage()) &&
                Objects.equals(getDescription(), step.getDescription());
    }

    @SuppressLint("NewApi")
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSets(), getReps(), getImage(), getDescription());
    }

    @Override
    public String toString() {
        return "Step{" +
                "name='" + name + '\'' +
                ", sets=" + sets +
                ", reps=" + reps +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}


