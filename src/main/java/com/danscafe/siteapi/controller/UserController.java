package com.danscafe.siteapi.controller;

import com.danscafe.siteapi.dal.user.UserDAL;
import com.danscafe.siteapi.model.UserEntity;
import com.danscafe.siteapi.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController()
@RequestMapping("/User")
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

    @PostMapping(value = "/Create")
    public UserEntity create(@RequestBody UserEntity userEntity){
        return userDAL.createUser(userEntity);
    }
}
