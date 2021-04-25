package com.spotifriends.server.api;

import com.spotifriends.server.model.NewUser;
import com.spotifriends.server.service.NewUserService;
//import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("/register-api")
@RestController
public class NewUserController {

//    @Autowired
//    private final JdbcTemplate jdbcTemplate;
    private final NewUserService newUserService;

    @Autowired
    public NewUserController(NewUserService newUserService) {
        this.newUserService = newUserService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public String addNewUser(@RequestBody NewUser nu) {
        String tok = nu.validate();
        if (tok.equals("COULD NOT GET TOKEN")) {
            return "{\"code\":\"" + tok + "\"}";
        }
        if (nu.username.equals("") || nu.password.equals("")) {
            return "{\"code\": \"EMPTY USERNAME OR PASSWORD\" }";
        }
        String response = newUserService.addNewUser(nu);
        return "{\"code\":\"" + response + "\"}";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public String getNewUsers() { return newUserService.getNewUsers(); }
}
