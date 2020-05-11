package com.danscafe.siteapi.service;

import com.danscafe.siteapi.model.Profile;

public interface ProfileManager {
    Profile addProfile(Profile profile);
    Profile getProfile(String profileID);
    void deleteProfile(String profileID);
}
