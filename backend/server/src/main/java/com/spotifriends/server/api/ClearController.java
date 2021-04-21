package com.spotifriends.server.api;

import com.spotifriends.server.model.NewUser;
import com.spotifriends.server.service.ClearService;
import com.spotifriends.server.service.NewUserService;
//import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("/clear")
@RestController
public class ClearController {

    //    @Autowired
//    private final JdbcTemplate jdbcTemplate;
    private final ClearService clearService;

    @Autowired
    public ClearController(ClearService clearService) {
        this.clearService = clearService;
    }

    @PostMapping
    public void clear() {
        clearService.clear();
    }

//    @GetMapping
//    public String getNewUsers() { return newUserService.getNewUsers(); }
}
