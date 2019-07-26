package com.nazar.model.entity;

public class PrivateFood extends Food {
    private int userID;
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public static PrivateBuilder builder(){
        return new PrivateFood().new PrivateBuilder();
    }

    public class PrivateBuilder extends Food.Builder{
        public PrivateBuilder userID(int userID){
            PrivateFood.this.userID = userID;
            return this;
        }
        public PrivateBuilder name(String name){
            PrivateFood.this.name = name;
            return this;
        }
        public PrivateBuilder id(int id){
            PrivateFood.this.id = id;
            return this;
        }
        public PrivateBuilder fats(double fats){
            PrivateFood.this.fats = fats;
            return this;
        }
        public PrivateBuilder protein(double protein){
            PrivateFood.this.protein = protein;
            return this;
        }
        public PrivateBuilder carbohydrate(double carbohydrate){
            PrivateFood.this.carbohydrate = carbohydrate;
            return this;
        }
        public PrivateFood build(){
            return PrivateFood.this;
        }
    }
}
