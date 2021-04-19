//package com.spotifriends.server.api;
//
//import com.spotifriends.server.model.NewUser;
//import com.spotifriends.server.service.NewUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//
//@RequestMapping("register")
//@RestController
//public class StatsController {
//
//    private final StatsService newUserService;
//
//    @Autowired
//    public StatsController(StatsService StatsService) {
//        this.StatsService = StatsService;
//    }
//
////    @PostMapping
////    public void addNewUser(@RequestBody NewUser nu) {
////        newUserService.addNewUser(nu);
////    }
//
//    @GetMapping
//    public ArrayList<NewUser> getNewUsers() {
//        return StatsService.getStats();
//    }
//
//}
