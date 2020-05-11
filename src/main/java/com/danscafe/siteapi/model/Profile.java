package com.danscafe.siteapi.model;

import com.microsoft.azure.storage.table.TableServiceEntity;

public class Profile extends TableServiceEntity {
    private String name;
    private String city;
    private String id;
    private String username;
    private String password;

    public Profile(String city, String id){
        this.partitionKey = city;
        this.rowKey = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
