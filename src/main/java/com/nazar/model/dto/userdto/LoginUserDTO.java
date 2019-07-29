package com.nazar.model.dto.userdto;

public class LoginUserDTO {
    private String login;
    private String password;

    public LoginUserDTO(String login, String password){
        this.login = login;
        this.password = password;
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

    @Override
    public String toString() {
        return "LoginUserDTO{" +
                "login='" + login + '\'' +
                '}';
    }
}
