package com.epam.training.sportsbetting.domain;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class User {
    private String email;
    private String password;
    
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}
