package com.spotifriends.server.model;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class User {
    String username;
    String password;
    ArrayList<String> artists;
    ArrayList<String> tracks;
    ArrayList<String> friends;
    boolean priv;

    public User(String username, String password, Array artists, Array tracks, Array friends, boolean priv) {
        this.username = username;
        this.password = password;
        this.artists = (ArrayList<String>) artists;
        this.tracks = (ArrayList<String>) tracks;
        this.friends = (ArrayList<String>) friends;
        this.priv = priv;
    }
}
