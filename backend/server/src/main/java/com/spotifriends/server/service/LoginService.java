package com.spotifriends.server.service;

import com.spotifriends.server.dao.LoginDao;
import com.spotifriends.server.dao.NewUserDao;
import com.spotifriends.server.model.NewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LoginService {

    private final LoginDao loginDao;

    @Autowired
    public LoginService(@Qualifier("login") LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    public String login(String username, String password) {
        return loginDao.login(username, password);
    }

//    public String getNewUsers() { return newUserDao.getNewUsers(); }

}
