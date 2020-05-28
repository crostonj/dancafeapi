package com.danscafe.siteapi.model;

public class UserEntity {
    private String password;
    private String username;

    public UserEntity(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public UserEntity() {
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
