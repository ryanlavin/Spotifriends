package com.spotifriends.server.service;

import com.spotifriends.server.dao.ClearDao;
import com.spotifriends.server.dao.NewUserDao;
import com.spotifriends.server.model.NewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClearService {

    private final ClearDao clearDao;

    @Autowired
    public ClearService(@Qualifier("clear") ClearDao clearDao) {
        this.clearDao = clearDao;
    }

    public int clear() {
        return clearDao.clear();
    }

//    public String getNewUsers() { return newUserDao.getNewUsers(); }

}