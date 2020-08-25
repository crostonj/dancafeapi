package com.danscafe.siteapi.controller;

import com.danscafe.siteapi.dal.profile.ProfileDAL;
import com.danscafe.siteapi.model.Profile;
import com.danscafe.siteapi.repository.ProfileRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/Profile")
public class ProfileController {

    private final ProfileRepository profileRepository;

    private final ProfileDAL profileDAL;

    public ProfileController(ProfileRepository profileRepository, ProfileDAL profileDAL) {
        this.profileRepository = profileRepository;
        this.profileDAL = profileDAL;
    }

    @GetMapping()
    public List<Profile> getAllUsers() {
        return profileRepository.findAll();
    }

    @GetMapping(value = "/{profileId}")
    public Profile getProfile(@PathVariable String profileId) {
        //LOG.info("Getting user with ID: {}.", userId);
        return profileRepository.findById(profileId).orElse(null);
    }

    @PostMapping(value = "/create")
    public Profile addNewProfiles(@RequestBody Profile profileEntity) {
        //LOG.info("Saving user.");
        return profileRepository.save(profileEntity);
    }

    @GetMapping(value = "/settings/{profileId}")
    public Object getAllProfileSetting(@PathVariable String profileId) {
        Profile profileEntity = profileRepository.findById(profileId).orElse(null);
        if (profileEntity != null) {
            return profileDAL.getAllProfileSettings(profileId);
        } else {
            return "User not found.";
        }
    }


    @GetMapping(value = "/settings/{profileId}/{key}")
    public String getProfileSetting(@PathVariable String profileId, @PathVariable String key) {
        Profile profileEntity = profileRepository.findById(profileId).orElse(null);
        if (profileEntity != null) {
            return profileDAL.getProfileSettings(profileId, key);
        } else {
            return "Profile not found.";
        }
    }
}
