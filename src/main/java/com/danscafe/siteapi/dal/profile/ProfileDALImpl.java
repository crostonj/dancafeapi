package com.danscafe.siteapi.dal.profile;

import com.danscafe.siteapi.model.ProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProfileDALImpl implements ProfileDAL {


    public static final String PROFILE_ID = "profileId";
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<ProfileEntity> getAllProfile() {
        return mongoTemplate.findAll(ProfileEntity.class);
    }

    @Override
    public ProfileEntity getProfileById(String profileId) {
        Query query = new Query();
        query.addCriteria(Criteria.where(PROFILE_ID).is(profileId));
        return mongoTemplate.findOne(query, ProfileEntity.class);

    }

    @Override
    public ProfileEntity getProfileByUserId(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.findOne(query, ProfileEntity.class);
    }

    @Override
    public ProfileEntity addProfile(ProfileEntity profileEntity) {
        mongoTemplate.save(profileEntity);
        return profileEntity;
    }

    @Override
    public Object getAllProfileSettings(String profileId) {
        Query query = new Query();
        query.addCriteria(Criteria.where(PROFILE_ID).is(profileId));
        ProfileEntity profileEntity = mongoTemplate.findOne(query, ProfileEntity.class);
        return profileEntity != null ? profileEntity.getProfileSettings() : "Profile not found.";
    }

    @Override
    public String getProfileSettings(String profileId, String key) {
        Query query = new Query();
        query.fields().include("profileSettings");
        query.addCriteria(Criteria.where(PROFILE_ID).is(profileId).andOperator(Criteria.where("profileSettings." + key).exists(true)));
        ProfileEntity profileEntity = mongoTemplate.findOne(query, ProfileEntity.class);
        return profileEntity != null ? profileEntity.getProfileSettings().get(key) : "Not found.";
    }

    @Override
    public String addProfileSetting(String profileId, String key, String value) {
        Query query = new Query();
        query.addCriteria(Criteria.where(PROFILE_ID).is(profileId));
        ProfileEntity profileEntity = mongoTemplate.findOne(query, ProfileEntity.class);
        if (profileEntity != null) {
            profileEntity.getProfileSettings().put(key, value);
            mongoTemplate.save(profileEntity);
            return "Key added.";
        } else {
            return "Profile not found.";
        }
    }
}
