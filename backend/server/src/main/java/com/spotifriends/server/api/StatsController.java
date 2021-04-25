package com.spotifriends.server.api;

import com.spotifriends.server.model.NewUser;
import com.spotifriends.server.service.NewUserService;
import com.spotifriends.server.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("/dashboard-api")
@RestController
public class StatsController {

    private final StatsService statsService;

    @Autowired
    public StatsController(StatsService StatsService) {
        this.statsService = StatsService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public String getStats() {
        return statsService.getStats();
    }

}
