package com.danscafe.siteapi.dal.user;

import com.danscafe.siteapi.model.ProfileEntity;
import com.danscafe.siteapi.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


@Repository
public class UserDALImpl implements UserDAL{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        mongoTemplate.save(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity getUser(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(username));
        return mongoTemplate.findOne(query, UserEntity.class);
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        mongoTemplate.save(userEntity);
        return userEntity;
    }
}
