package com.danscafe.siteapi.dal.user;

import com.danscafe.siteapi.model.User;
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
    public User createUser(User userEntity) {
        mongoTemplate.save(userEntity);
        return userEntity;
    }

    @Override
    public User getUser(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(username));
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public User save(User userEntity) {
        mongoTemplate.save(userEntity);
        return userEntity;
    }
}
