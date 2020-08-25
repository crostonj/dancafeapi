package com.danscafe.siteapi.controller;

import com.danscafe.siteapi.dal.user.UserDAL;
import com.danscafe.siteapi.model.User;
import com.danscafe.siteapi.repository.UserRepository;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/api/User")
public class UserController {

    private final UserDAL userDAL;

    public UserController(UserDAL userDAL) {
        this.userDAL = userDAL;
    }

    @GetMapping()
    public User getUser(String username){
        User userEntity = null;
        if(username != null)
            userEntity = userDAL.getUser(username);

        return userEntity;
    }

    @PostMapping(value = "/Create")
    public User create(@RequestBody User userEntity){
        return userDAL.createUser(userEntity);
    }

    public User update(@RequestBody User userEntity){
        return userDAL.save(userEntity);
    }
}
