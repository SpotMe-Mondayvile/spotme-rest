package com.mts.spotmerest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="SPOTS")
public class Spot {
    @Id
    @SequenceGenerator(
            name="spot_sequence",
            sequenceName="spot_sequence",
            allocationSize = 1
//            ,initialValue = 1000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "spot_sequence"
    )
    private Long id;
    private Long userId;
    private String gymId;
    private String title;
    private String description;
    private Date createdAt;
    private String status; //open or closed
    private Long routineId;

    public Spot(Long userId, String gymId, String title, String description) {
        this.userId = userId;
        this.gymId = gymId;
        this.title = title;
        this.description = description;
        this.createdAt= getCurrentTime();
    }

    public Spot() {
        this.createdAt= getCurrentTime();
    }

    public Spot(Long id, Long userId, String gymId) {
        this.id = id;
        this.userId = userId;
        this.gymId = gymId;
        this.createdAt= getCurrentTime();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getGymId() {
        return gymId;
    }

    public void setGymId(String gymId) {
        this.gymId = gymId;
    }


    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getRoutineId() {
        return routineId;
    }

    public void setRoutineId(Long routineId) {
        this.routineId = routineId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Spot updateAttributes(Spot s){
        Spot newSpot = new Spot();
        newSpot.setUserId(this.getUserId());
        newSpot.setId(this.getId());
        newSpot.setCreatedAt(this.getCreatedAt());
        newSpot.setDescription(s.getDescription());
        newSpot.setStatus(s.getStatus());
        newSpot.setTitle(s.getTitle());
        newSpot.setGymId(s.getGymId());
        newSpot.setRoutineId(s.getRoutineId());
        return newSpot;
    }

    public Date getCurrentTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        return date;
    }

    @JsonIgnore
    @OneToMany(mappedBy="spot",cascade =CascadeType.ALL, orphanRemoval = true)
    private List<Match> matches = new ArrayList<Match>();

    public List<Match> getMatches() {
        return matches;
    }
}
