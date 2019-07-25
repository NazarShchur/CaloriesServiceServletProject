package com.nazar.model.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    private int id;
    private String login;
    private String password;
    private int age;
    private int height;
    private int weight;
    private Gender gender;
    private Set<Role> roles;
    private LifeStyle lifeStyle;
    private Set<Food> privateFood;//todo ???



    public Set<Food> getPrivateFood() {
        return privateFood;
    }

    public void setPrivateFood(Set<Food> privateFood) {
        this.privateFood = privateFood;
    }

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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login=" + login +
                ", password=" + password +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                ", gender=" + gender +
                ", roles=" + roles +
                ", lifeStyle=" + lifeStyle +
                '}';
    }

    public static Builder builder() {
        return new User().new Builder();
    }

    public class Builder {
        private Builder() {

        }

        public Builder login(String login) {
            User.this.login = login;
            return this;
        }

        public Builder id(int id) {
            User.this.id = id;
            return this;
        }

        public Builder password(String password) {
            User.this.password = password;
            return this;
        }

        public Builder age(int age) {
            User.this.age = age;
            return this;
        }

        public Builder height(int height) {
            User.this.height = height;
            return this;
        }

        public Builder weight(int weight) {
            User.this.weight = weight;
            return this;
        }

        public Builder gender(Gender gender) {
            User.this.gender = gender;
            return this;
        }

        public Builder lifeStyle(LifeStyle lifeStyle) {
            User.this.lifeStyle = lifeStyle;
            return this;
        }

        public Builder role(Set<Role> roles) {
            User.this.roles = new HashSet<>(roles);
            return this;
        }

        public User build() {
            return User.this;
        }
    }
}
