package com.danscafe.siteapi.dal.profile;

import com.danscafe.siteapi.model.ProfileEntity;

import java.util.List;

public interface ProfileDAL {

    List<ProfileEntity> getAllProfile();

    ProfileEntity getProfileById(String profileId);

    ProfileEntity getProfileByUserId(String userId);

    ProfileEntity addProfile(ProfileEntity profileEntity);

    Object getAllProfileSettings(String profileId);

    String getProfileSettings(String profileId, String key);

    String addProfileSetting(String profileId, String key, String value);
}
