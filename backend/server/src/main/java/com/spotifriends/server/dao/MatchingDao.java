package com.spotifriends.server.dao;

import com.spotifriends.server.model.NewUser;

import java.util.ArrayList;

public interface MatchingDao {

    String getMatch(String username, String session, String friend_name);

}
