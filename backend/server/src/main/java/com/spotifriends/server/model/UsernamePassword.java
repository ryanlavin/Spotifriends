package com.spotifriends.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsernamePassword {

    public final String username;
    public final String password;

    public UsernamePassword (@JsonProperty("username") String username,
                      @JsonProperty("password") String password)
    {
        this.username = username;
        this.password = password;
    }
}
