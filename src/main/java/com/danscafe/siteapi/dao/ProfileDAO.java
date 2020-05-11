package com.danscafe.siteapi.dao;

import com.danscafe.siteapi.model.Profile;


public interface ProfileDAO {
    Profile addProfile(Profile profile);
    Profile getProfile(String profileID);
    void deleteProfile(String profileID);
}
