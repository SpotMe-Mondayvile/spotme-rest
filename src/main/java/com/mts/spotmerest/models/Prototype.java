package com.mts.spotmerest.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Prototype {
    @Id
    @SequenceGenerator(
            name = "prototype_sequence",
            sequenceName = "prototype_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "prototype_sequence"
    )
    private Long id;
    private String title;
    private Integer age;
    private String gender;

    public Prototype() {

    }

    public Prototype(Long id, String title, Integer age, String gender) {
        this.id = id;
        this.title = title;
        this.age = age;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Prototype{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
