package com.nazar.model.dto.userdto;

import com.nazar.model.entity.Gender;
import com.nazar.model.entity.LifeStyle;
import com.nazar.model.entity.Role;

import java.util.Set;

public class RegistrationUserDTO {
    private String login;
    private String password;
    private int age;
    private double height;
    private double weight;
    private Gender gender;
    private LifeStyle lifeStyle;

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

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
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
}
