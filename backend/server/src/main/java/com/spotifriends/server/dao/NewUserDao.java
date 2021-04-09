package com.spotifriends.server.dao;

import com.spotifriends.server.model.NewUser;

import java.util.ArrayList;

public interface NewUserDao {

    int addNewUser(NewUser nu);

    ArrayList<NewUser> getNewUsers();

}
