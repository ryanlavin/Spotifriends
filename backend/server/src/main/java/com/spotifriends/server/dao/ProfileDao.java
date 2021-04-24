package com.spotifriends.server.dao;

import com.spotifriends.server.model.NewUser;

import java.util.ArrayList;

public interface ProfileDao {

    String addFriend(String username, String session, String friend_name);

    String changePrivacy(String username, String session, boolean priv);

    String getProfileData(String username, String session);

}
