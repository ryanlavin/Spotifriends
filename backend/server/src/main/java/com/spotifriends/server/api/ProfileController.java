package com.spotifriends.server.api;

//import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import com.spotifriends.server.model.UsernameSession;
import com.spotifriends.server.model.UsernameSessionFriend;
import com.spotifriends.server.model.UsernameSessionPriv;
import com.spotifriends.server.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public String addFriend(@RequestBody UsernameSessionFriend usf) {
        return "{\"code\":\"" + profileService.addFriend(usf.username, usf.session, usf.friend_name) + "\"}";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping
    public String changePrivacy(@RequestBody UsernameSessionPriv usp){
        return "{\"code\":\"" + profileService.changePrivacy(usp.username, usp.session, usp.priv) + "\"}";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public String getProfileData(@RequestBody UsernameSession us) {
        // returns sessionId
        return profileService.getProfileData(us.username, us.session);
    }

}