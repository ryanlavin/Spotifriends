package com.spotifriends.server.api;

//import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import com.spotifriends.server.model.UsernamePassword;
import com.spotifriends.server.model.UsernameSessionFriend;
import com.spotifriends.server.service.LoginService;
import com.spotifriends.server.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("/matching-api")
@RestController
public class MatchingController {

    //    @Autowired
//    private final JdbcTemplate jdbcTemplate;
    private final MatchingService matchingService;

    @Autowired
    public MatchingController(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public String getMatch(@RequestBody UsernameSessionFriend upf) {
        // returns sessionId
        return "{ \"score\": \"" + matchingService.getMatch(upf.username, upf.session, upf.friend_name) + "\"}";
    }

}