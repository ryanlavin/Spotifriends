package com.spotifriends.server.service;

import com.spotifriends.server.dao.NewUserDao;
import com.spotifriends.server.model.NewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NewUserService {

    private final NewUserDao newUserDao;

    @Autowired
    public NewUserService(@Qualifier("cloud") NewUserDao newUserDao) {
        this.newUserDao = newUserDao;
    }

    public int addNewUser(NewUser nu) {
        return newUserDao.addNewUser(nu);
    }

    public ArrayList<NewUser> getNewUsers() { return newUserDao.getNewUsers(); }

}
