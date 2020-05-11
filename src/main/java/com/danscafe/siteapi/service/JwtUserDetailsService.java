package com.danscafe.siteapi.service;

import com.danscafe.siteapi.dao.ProfileDAO;
import com.danscafe.siteapi.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService, ProfileManager {
    @Autowired ProfileDAO profileDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("danscafe".equals(username)) {
            return new User("danscafe", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    @Override
    public Profile addProfile(Profile profile) {
       return profileDAO.addProfile(profile);
    }

    @Override
    public Profile getProfile(String profileID) {
        return profileDAO.getProfile(profileID);
    }

    @Override
    public void deleteProfile(String profileID) {
        profileDAO.deleteProfile(profileID);
    }
}
