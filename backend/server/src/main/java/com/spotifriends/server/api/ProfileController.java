package com.spotifriends.server.api;

//import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import com.spotifriends.server.service.LoginService;
import com.spotifriends.server.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("/profile-api")
@RestController
public class ProfileController {

    //    @Autowired
//    private final JdbcTemplate jdbcTemplate;
    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public String getProfileData(@RequestBody String username, @RequestBody String session) {
        // returns sessionId
        return profileService.getProfileData(username, session);
    }

}