package com.spotifriends.server.service;

import com.spotifriends.server.dao.LoginDao;
import com.spotifriends.server.dao.MatchingDao;
import com.spotifriends.server.dao.NewUserDao;
import com.spotifriends.server.model.NewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MatchingService {

    private final MatchingDao matchingDao;

    @Autowired
    public MatchingService(@Qualifier("match") MatchingDao matchingDao) {
        this.matchingDao = matchingDao;
    }

    public String getMatch(String username, String session, String friend_name) {
        return matchingDao.getMatch(username, session, friend_name);
    }

//    public String getNewUsers() { return newUserDao.getNewUsers(); }

}
