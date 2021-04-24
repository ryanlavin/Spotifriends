package com.spotifriends.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsernameSession {

    public final String username;
    public final String session;

    public UsernameSession (@JsonProperty("username") String username,
                             @JsonProperty("session") String session)
    {
        this.username = username;
        this.session = session;
    }
}
