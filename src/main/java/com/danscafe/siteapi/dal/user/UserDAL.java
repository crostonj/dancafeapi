package com.danscafe.siteapi.dal.user;

import com.danscafe.siteapi.model.User;

public interface UserDAL {
    User createUser(User userEntity);
    User getUser(String username);
    User save(User userEntity);
}
