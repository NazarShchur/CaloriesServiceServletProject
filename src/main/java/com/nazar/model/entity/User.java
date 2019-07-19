package com.nazar.model.entity;

public class User {
    private int id;
    private String login;
    private String password;
    private int age;
    private int dailyCalories;
    private double height;
    private double weight;
    private Gender gender;
    private Role role;
    private LifeStyle lifeStyle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDailyCalories() {
        return dailyCalories;
    }

    public void setDailyCalories(int dailyCalories) {
        this.dailyCalories = dailyCalories;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LifeStyle getLifeStyle() {
        return lifeStyle;
    }

    public void setLifeStyle(LifeStyle lifeStyle) {
        this.lifeStyle = lifeStyle;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", dailyCalories=" + dailyCalories +
                ", height=" + height +
                ", weight=" + weight +
                ", gender=" + gender +
                ", role=" + role +
                ", lifeStyle=" + lifeStyle +
                '}';
    }

    public static Builder builder(){
        return new Builder();
    }
    public static class Builder{
        private int id = 0;
        private String login = "default";
        private String password = "default";
        private int age = 0;
        private int dailyCalories = 0;
        private int height = 0;
        private int weight = 0;
        private Role role = Role.USER;
        private Gender gender = Gender.Man;
        private LifeStyle lifeStyle = LifeStyle.LowActivity;


        public Builder login(String login){
            this.login = login;
            return this;
        }
        public Builder id(int id) {
            this.id = id;
            return this;
        }
        public Builder password(String password) {
            this.password = password;
            return this;
        }
        public Builder age(int age) {
            this.age = age;
            return this;
        }
        public Builder dailyCalories(int dailyCalories) {
            this.dailyCalories = dailyCalories;
            return this;
        }
        public Builder height(int height) {
            this.height = height;
            return this;
        }
        public Builder weight(int weight) {
            this.weight = weight;
            return this;
        }
        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }
        public Builder lifeStyle(LifeStyle lifeStyle) {
            this.lifeStyle = lifeStyle;
            return this;
        }
        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public User build(){
            User user = new User();
            user.setId(id);
            user.setLogin(login);
            user.setPassword(password);
            user.setAge(age);
            user.setDailyCalories(dailyCalories);
            user.setGender(gender);
            user.setHeight(height);
            user.setWeight(weight);
            user.setLifeStyle(lifeStyle);
            user.setRole(role);
            return user;
        }
    }
}
