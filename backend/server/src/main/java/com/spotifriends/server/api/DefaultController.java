package com.spotifriends.server.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    @RequestMapping("/login")
    public String login() {
        return "login.html";
    }
    
}
