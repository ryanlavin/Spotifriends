package com.spotifriends.server.service;

import com.spotifriends.server.dao.LoginDao;
import com.spotifriends.server.dao.NewUserDao;
import com.spotifriends.server.dao.ProfileDao;
import com.spotifriends.server.model.NewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@Service
public class ProfileService {

    private final ProfileDao profileDao;

    @Autowired
    public ProfileService(@Qualifier("profile") ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    public String addFriend(String username, String session, String friend_name) {
        return profileDao.addFriend(username, session, friend_name);
    }

    public String changePrivacy(String username, String session, boolean priv) {
        return profileDao.changePrivacy(username, session, priv);
    }

    public String getProfileData(String username, String session) {
        return profileDao.getProfileData(username, session);
    }

//    public String getNewUsers() { return newUserDao.getNewUsers(); }

}
