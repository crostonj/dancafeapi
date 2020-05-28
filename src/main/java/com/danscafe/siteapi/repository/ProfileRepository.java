package com.danscafe.siteapi.repository;

import com.danscafe.siteapi.model.ProfileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfileRepository extends MongoRepository<ProfileEntity, String> {
}
