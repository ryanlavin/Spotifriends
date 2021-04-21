package com.spotifriends.server.api;

import com.spotifriends.server.model.NewUser;
import com.spotifriends.server.service.NewUserService;
//import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("/register")
@RestController
public class NewUserController {

//    @Autowired
//    private final JdbcTemplate jdbcTemplate;
    private final NewUserService newUserService;

    @Autowired
    public NewUserController(NewUserService newUserService) {
        this.newUserService = newUserService;
    }

    @PostMapping
    public String addNewUser(@RequestBody NewUser nu) {
        String tok = nu.validate();
        if (tok.equals("COULD NOT GET TOKEN")) {
            return tok;
        }
        if (nu.username.equals("") || nu.password.equals("")) {
            return "EMPTY USERNAME OR PASSWORD";
        }
        String response = newUserService.addNewUser(nu);
        return response;
    }

    @GetMapping
    public String getNewUsers() { return newUserService.getNewUsers(); }
}
