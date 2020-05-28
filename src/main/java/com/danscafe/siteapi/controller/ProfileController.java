package com.danscafe.siteapi.controller;

import com.danscafe.siteapi.dal.profile.ProfileDAL;
import com.danscafe.siteapi.model.ProfileEntity;
import com.danscafe.siteapi.repository.ProfileRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/Profile")
public class ProfileController {

    private final ProfileRepository profileRepository;

    private final ProfileDAL profileDAL;

    public ProfileController(ProfileRepository profileRepository, ProfileDAL profileDAL) {
        this.profileRepository = profileRepository;
        this.profileDAL = profileDAL;
    }

    @GetMapping()
    public List<ProfileEntity> getAllUsers() {
        return profileRepository.findAll();
    }

    @GetMapping(value = "/{profileId}")
    public ProfileEntity getProfile(@PathVariable String profileId) {
        //LOG.info("Getting user with ID: {}.", userId);
        return profileRepository.findById(profileId).orElse(null);
    }

    @PostMapping(value = "/create")
    public ProfileEntity addNewProfiles(@RequestBody ProfileEntity profileEntity) {
        //LOG.info("Saving user.");
        return profileRepository.save(profileEntity);
    }

    @GetMapping(value = "/settings/{profileId}")
    public Object getAllProfileSetting(@PathVariable String profileId) {
        ProfileEntity profileEntity = profileRepository.findById(profileId).orElse(null);
        if (profileEntity != null) {
            return profileDAL.getAllProfileSettings(profileId);
        } else {
            return "User not found.";
        }
    }


    @GetMapping(value = "/settings/{profileId}/{key}")
    public String getProfileSetting(@PathVariable String profileId, @PathVariable String key) {
        ProfileEntity profileEntity = profileRepository.findById(profileId).orElse(null);
        if (profileEntity != null) {
            return profileDAL.getProfileSettings(profileId, key);
        } else {
            return "Profile not found.";
        }
    }
}
