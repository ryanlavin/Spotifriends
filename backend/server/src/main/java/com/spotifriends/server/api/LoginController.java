package com.spotifriends.server.api;

//import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import com.spotifriends.server.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("/login-api")
@RestController
public class LoginController {

    //    @Autowired
//    private final JdbcTemplate jdbcTemplate;
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public String login(@RequestBody String username, @RequestBody String password) {
        // returns sessionId
        return "{ \"session\": \"" + loginService.login(username, password) + "\"}";
    }

}
