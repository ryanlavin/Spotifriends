package com.spotifriends.server.model;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfileData {

    public final boolean priv;
    public final ArrayList<String> friends;

    public ProfileData(boolean priv, Array friends) throws SQLException {
        this.priv = priv;
        this.friends = new ArrayList<String>();
        String[] g = (String[]) friends.getArray();
        for (String s : g) {
            this.friends.add(s);
        }
    }
}
