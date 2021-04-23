package com.spotifriends.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsernameSessionPriv {

    public final String username;
    public final String session;
    public final boolean priv;

    public UsernameSessionPriv(@JsonProperty("username") String username,
                               @JsonProperty("session") String session,
                               @JsonProperty("priv") boolean priv)
    {
        this.username = username;
        this.session = session;
        this.priv = priv;
    }
}
