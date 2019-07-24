package com.nazar.model.entity;

import java.time.LocalDate;

public class Meal {
    private int id;
    private User user;
    private String description;
    private LocalDate addTime;
    private int allCalories;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDate addTime) {
        this.addTime = addTime;
    }

    public int getAllCalories() {
        return allCalories;
    }

    public void setAllCalories(int allCalories) {
        this.allCalories = allCalories;
    }
}
