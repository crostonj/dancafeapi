package com.danscafe.siteapi.controller;

import com.danscafe.siteapi.model.Profile;
import com.danscafe.siteapi.service.ProfileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Profile")
public class ProfileController {
    @Autowired
    private ProfileManager profileManager;

    public void setProfileManager(ProfileManager profileManager) {this.profileManager = profileManager;}


    @PostMapping(value = "/add")
    public ResponseEntity<Profile> addProfile(Profile profile){
        Profile newProfile = profileManager.addProfile(profile);
        return ResponseEntity.ok(newProfile);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Profile> getProfile(@PathVariable String id){
        return ResponseEntity.ok(profileManager.getProfile(id));
    }
}
