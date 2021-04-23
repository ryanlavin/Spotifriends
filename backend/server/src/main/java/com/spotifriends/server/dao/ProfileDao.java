package com.spotifriends.server.dao;

import com.spotifriends.server.model.NewUser;

import java.util.ArrayList;

public interface ProfileDao {

    String getProfileData(String username, String session);

}
