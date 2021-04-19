package com.spotifriends.server.api;

import com.spotifriends.server.model.NewUser;
import com.spotifriends.server.service.NewUserService;
import org.flywaydb.core.internal.jdbc.JdbcTemplate;
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
    public void addNewUser(@RequestBody NewUser nu) {
        newUserService.addNewUser(nu);
    }

    @GetMapping
    public String getNewUsers() { return newUserService.getNewUsers(); }
}
