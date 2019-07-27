package com.nazar.model.entity;

import java.time.LocalDate;
import java.util.Map;

public class Meal {
    private int id;
    private int userId;
    private String description;
    private LocalDate addTime;
    private int allCalories;
    private Map<Food, Integer> foodMap;

    public Map<Food, Integer> getFoodMap() {
        return foodMap;
    }

    public void setFoodMap(Map<Food, Integer> foodMap) {
        this.foodMap = foodMap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", userId=" + userId +
                ", description='" + description + '\'' +
                ", addTime=" + addTime +
                ", allCalories=" + allCalories +
                ", foodMap=" + foodMap +
                '}';
    }
    public static Builder builder(){
        return new Meal().new Builder();
    }
    public class Builder {
        private Builder(){}

        public Builder id(int id){
            Meal.this.id = id;
            return this;
        }
        public Builder userId(int userId){
            Meal.this.userId = userId;
            return this;
        }
        public Builder description(String description){
            Meal.this.description = description;
            return this;
        }
        public Builder addTime(LocalDate addTime){
            Meal.this.addTime = addTime;
            return this;
        }
        public Builder allCalories(int id){
            Meal.this.id = id;
            return this;
        }
        public Builder foodMap(Map<Food, Integer> foodMap){
            Meal.this.foodMap = foodMap;
            return this;
        }

        public Meal build(){
            return Meal.this;
        }
    }
}
