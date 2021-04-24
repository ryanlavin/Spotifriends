package com.spotifriends.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsernameSessionFriend {

    public final String username;
    public final String session;
    public final String friend_name;

    public UsernameSessionFriend (@JsonProperty("username") String username,
                            @JsonProperty("session") String session,
                            @JsonProperty("friend_name") String friend_name)
    {
        this.username = username;
        this.session = session;
        this.friend_name = friend_name;
    }
}

