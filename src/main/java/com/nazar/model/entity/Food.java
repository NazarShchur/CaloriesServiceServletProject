package com.nazar.model.entity;

public class Food {
    private int id;
    private String name;
    private double fats;
    private double protein;
    private double carbohydrate;
    private boolean publicFood;
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

    public boolean isPublicFood() {
        return publicFood;
    }

    public void setPublicFood(boolean publicFood) {
        this.publicFood = publicFood;
    }

    public static Builder builder(){
        return new Food().new Builder();
    }
    public class Builder{
        private Builder(){}
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
        public Builder publicFood(boolean publicFood){
            Food.this.publicFood = publicFood;
            return this;
        }
        public Food build(){
            return Food.this;
        }


    }
}
