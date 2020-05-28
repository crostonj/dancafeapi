package com.danscafe.siteapi.dal.user;

import com.danscafe.siteapi.model.UserEntity;

public interface UserDAL {
    UserEntity createUser(UserEntity userEntity);
    UserEntity getUser(String username);
    UserEntity save(UserEntity userEntity);
}
