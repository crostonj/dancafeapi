package com.danscafe.siteapi.dal;

import com.danscafe.siteapi.model.Profile;

import java.util.List;

public interface ProfileDAL {

    List<Profile> getAllProfile();

    Profile getProfileById(String profileId);

    Profile getProfileByUserId(String userId);

    Profile addProfile(Profile profile);

    Object getAllProfileSettings(String profileId);

    String getProfileSettings(String profileId, String key);

    String addProfileSetting(String profileId, String key, String value);
}
