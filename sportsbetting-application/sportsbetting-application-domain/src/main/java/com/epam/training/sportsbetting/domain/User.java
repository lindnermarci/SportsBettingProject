package com.epam.training.sportsbetting.domain;

public abstract class User {
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    private String email;
    private String password;
}
