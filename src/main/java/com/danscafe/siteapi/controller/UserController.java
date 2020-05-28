package com.danscafe.siteapi.controller;

import com.danscafe.siteapi.dal.user.UserDAL;
import com.danscafe.siteapi.model.UserEntity;
import com.danscafe.siteapi.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/User")
public class UserController {

    private final UserDAL userDAL;
    private final UserRepository userRepository;

    public UserController(UserDAL userDAL, UserRepository userRepository) {
        this.userDAL = userDAL;
        this.userRepository = userRepository;
    }

    @GetMapping()
    public UserEntity getUser(String username){
        UserEntity userEntity = null;
        if(username != null)
            userEntity = userDAL.getUser(username);

        return userEntity;
    }

    @PostMapping()
    public UserEntity save(UserEntity userEntity){
        return userDAL.createUser(userEntity);
    }
}
