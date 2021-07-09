package com.codecool.userservice.model;

import java.io.Serializable;

public class UserBasicInfo implements Serializable {
    private String username;
    private String password;
    private String email;

    public UserBasicInfo(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UserBasicInfo() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
