package com.mts.spotmerest.models;

import com.mts.spotmerest.models.Exercise;
import com.mts.spotmerest.models.User;

import java.util.List;

import jakarta.persistence.*;
//import org.springframework.data.annotation.Id;

@Entity
@Table(name="ROUTINES")
public class Routine {  // this Is where we initialize
    private static int nextRoutineNumber = 1;
    //added to both methods, with if-else statements


    private String routineName;
    @Id
    @SequenceGenerator(
            name = "routine_sequence",
            sequenceName = "routine_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "routine_sequence"
    )
    private Long id; // for readability, should we lowercase the i//
    private Long userId;
    private String workoutIntensity;
    private List<String> exerciseList;
    private String routineType;


    public Routine(String routineName, Long routineId, Long userId, String workoutIntensity, List<String> exerciseList, String routineType) {

        if (routineName.isBlank()) {
            this.routineName = "Routine " + nextRoutineNumber;
            nextRoutineNumber++;
        } else {
            this.routineName = routineName;
        }
        this.id = routineId;
        this.userId = userId;// from user model file
        this.workoutIntensity = workoutIntensity;
        this.exerciseList = exerciseList;
        this.routineType = routineType;

    }

    public Routine() {
    }

    public Routine(String routineName, String workoutIntensity, String routineType) {
        if (routineName.isBlank()) {
            this.routineName = "Routine " + nextRoutineNumber;
            nextRoutineNumber++;
        } else {
            this.routineName = routineName;
        }

        this.workoutIntensity = workoutIntensity;
        this.routineType = routineType;
    }


    public Routine editRoutine(Routine routine) {


        return routine;
    }

    public Long getRoutineId() {
        return id;
    }

    public void setRoutineId(Long routineId) {
        this.id = routineId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getWorkoutIntensity() {
        return workoutIntensity;
    }

    public void setWorkoutIntensity(String workoutIntensity) {
        this.workoutIntensity = workoutIntensity;
    }

    public List<String> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<String> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public String getRoutineType() {
        return routineType;
    }

    public void setRoutineType(String routineType) {
        this.routineType = routineType;
    }

    public String getRoutineName() {
        return routineName;
    }

    public void setRoutineName(String routineName) {
        this.routineName = routineName;
    }


    @Override
    public String toString() {
        return "Routine{" +
                "routineName='" + routineName + '\'' +
                ", routineId=" + id +
                ", userId=" + userId +
                ", workoutIntensity='" + workoutIntensity + '\'' +
                ", exerciseList=" + exerciseList +
                ", routineType='" + routineType + '\'' +
                '}';
    }

}