package com.spotifriends.server.model;

import java.time.Instant;
import java.util.UUID;

public class LoggedInUser {

    public final String username;
    public final String session;
    public final Instant start;

    public LoggedInUser(String username, String session) {
        this.username = username;
        this.session = session;
        this.start = Instant.now();
    }
}
