package com.spotifriends.server.model;

import java.sql.Array;
import java.sql.SQLException;
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

        this.artists = new ArrayList<String>();
        this.tracks = new ArrayList<String>();
        this.friends = new ArrayList<String>();
        try{
            String[] a = (String[]) artists.getArray();
            String[] t = (String[]) tracks.getArray();
            String[] f = (String[]) friends.getArray();
            for (String s : a) {
                this.artists.add(s);
            }
            for (String s : t) {
                this.tracks.add(s);
            }
            for (String s : f) {
                this.friends.add(s);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        this.priv = priv;
    }
}
