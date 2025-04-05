package com.mts.spotmerest.models;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
@Entity
@Table
public class Exercise {
    @Id
    @SequenceGenerator(
            name="exercise_sequence",
            sequenceName="exercise_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "exercise_sequence"
    )
   private String id;
    private String name;
    private Integer reps;
    private Integer sets;
    private Integer weight;
    private String category;

    public Exercise(String id, String name, Integer reps, Integer sets, Integer weight, String category) {
        this.id = id;
        this.name = name;
        this.reps = reps;
        this.sets = sets;
        this.weight = weight;
        this.category = category;
    }

    public Exercise() {
    }

    public Exercise(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", reps=" + reps +
                ", sets=" + sets +
                ", weight=" + weight +
                ", category='" + category + '\'' +
                '}';
    }
}
