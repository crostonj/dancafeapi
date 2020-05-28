package com.danscafe.siteapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Document
public class ProfileEntity {
    @Id
    private String profileId;
    private String name;
    private Date creationDate = new Date();
    private Map<String, String> profileSettings = new HashMap<>();

    public String getUserId() {
        return profileId;
    }

    public void setUserId(String profileId) {
        this.profileId = profileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Map<String, String> getProfileSettings() {
        return profileSettings;
    }

    public void setProfileSettings(Map<String, String> profileSettings) {
        this.profileSettings = profileSettings;
    }
}
