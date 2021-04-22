package com.spotifriends.server.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    @RequestMapping("/home")
    public static String welcome() {
        return "index";
    }

//    @RequestMapping("/statsArtists")
//    public String getStatsArtists() {
//        return "login.html";
//    }

}
