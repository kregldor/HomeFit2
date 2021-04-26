package com.example.homefit;

import android.annotation.SuppressLint;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Workout implements Serializable {
    private String name;
    private int duration;
    private Level level;
    private Type type;
    private String description;
    @Nullable
    private String trainer;
    private String image;
    @Nullable
    private List<Step> steps;

    public Workout(String name, int duration, Level level, Type type, String description, @Nullable String trainer, String image, @Nullable List<Step> steps) {
        this.name = name;
        this.duration = duration;
        this.level = level;
        this.type = type;
        this.description = description;
        this.trainer = trainer;
        this.image = image;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public Level getLevel() {
        return level;
    }

    public Type getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    @Nullable
    public String getTrainer() {
        return trainer;
    }

    public String getImage() {
        return image;
    }

    @Nullable
    public List<Step> getSteps() {
        return steps;
    }

    @SuppressLint("NewApi")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Workout)) return false;
        Workout workout = (Workout) o;
        return getDuration() == workout.getDuration() &&
                getName().equals(workout.getName()) &&
                getLevel() == workout.getLevel() &&
                getType() == workout.getType() &&
                getDescription().equals(workout.getDescription()) &&
                Objects.equals(getTrainer(), workout.getTrainer()) &&
                getImage().equals(workout.getImage()) &&
                Objects.equals(getSteps(), workout.getSteps());
    }

    @SuppressLint("NewApi")
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDuration(), getLevel(), getType(), getDescription(), getTrainer(), getImage(), getSteps());
    }

    @Override
    public String toString() {
        return "Workout{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", level=" + level +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", trainer='" + trainer + '\'' +
                ", image='" + image + '\'' +
                ", steps=" + steps +
                '}';
    }
}


enum Level {
    BEGINNER("beginner"),
    ADVANCED("advanced");

    public final String label;

    private Level(String label) {
        this.label = label;
    }

}

enum Type {
    UPPER_BODY("upper body"),
    LOWER_BODY("lower body"),
    FULL_BODY("full body");

    public final String label;

    private Type(String label) {
        this.label = label;
    }

    public static Type fromString(String text) {
        for (Type b : Type.values()) {
            if (b.label.equals(text)) {
                return b;
            }
        }
        return null;
    }
}
