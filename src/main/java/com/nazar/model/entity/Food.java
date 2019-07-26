package com.nazar.model.entity;

import java.util.Objects;

public class Food {
    protected int id;
    protected String name;
    protected double fats;
    protected double protein;
    protected double carbohydrate;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return id == food.id &&
                Double.compare(food.fats, fats) == 0 &&
                Double.compare(food.protein, protein) == 0 &&
                Double.compare(food.carbohydrate, carbohydrate) == 0 &&
                Objects.equals(name, food.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, fats, protein, carbohydrate);
    }

    public static Builder builder(){
        return new Food().new Builder();
    }
    public class Builder{
        public Builder name(String name){
            Food.this.name = name;
            return this;
        }
        public Builder id(int id){
            Food.this.id = id;
            return this;
        }
        public Builder fats(double fats){
            Food.this.fats = fats;
            return this;
        }
        public Builder protein(double protein){
            Food.this.protein = protein;
            return this;
        }
        public Builder carbohydrate(double carbohydrate){
            Food.this.carbohydrate = carbohydrate;
            return this;
        }
        public Food build(){
            return Food.this;
        }


    }
}
