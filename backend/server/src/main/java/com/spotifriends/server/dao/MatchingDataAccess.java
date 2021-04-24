package com.spotifriends.server.dao;

import com.google.gson.Gson;
import com.spotifriends.server.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.*;

@Repository("match")
public class MatchingDataAccess implements MatchingDao {

    private final JdbcTemplate jdbcTemplate;
    public LoggedInQueue queue;
    public SessionManager manager;


    @Autowired
    public MatchingDataAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.queue = new LoggedInQueue();
//        this.manager = new SessionManager(this.queue);
//        this.manager.run();
    }


    @Override
    public String getMatch(String username, String session, String friend_name) {
        boolean valid = this.validate(username, session);

        if (!valid) return "INVALID SESSION";

        List<User> f = null;

        String getData = "SELECT * FROM user_table WHERE username = ?";
        f = jdbcTemplate.query(getData, new Object[]{friend_name}, (rs, rowNum) -> {
            return new User(rs.getString("username"), rs.getString("password"), rs.getArray("artists"), rs.getArray("tracks"), rs.getArray("friends"), rs.getBoolean("private"));
        });

        if (f == null) return "INVALID QUERY";
        if (f.size() == 0) return "INVALID QUERY";

        User friendData = f.get(0);

        if (friendData.priv) {
            boolean found = false;
            for (String n : friendData.friends) {
                if (n.equals(username)) {
                    found = true;
                    break;
                }
            }
            if (!found) return "INVALID PRIVATE";
        }

        List<User> u = null;

//        String getData = "SELECT friends, private FROM user_table WHERE username = ?";
        u = jdbcTemplate.query(getData, new Object[]{username}, (rs, rowNum) -> {
            return new User(rs.getString("username"), rs.getString("password"), rs.getArray("artists"), rs.getArray("tracks"), rs.getArray("friends"), rs.getBoolean("private"));
        });

        if (u == null) return "INVALID QUERY";
        if (u.size() == 0) return "INVALID QUERY";

        User userData = u.get(0);

        return this.calculate(userData, friendData);

    }

    public String calculate(User userData, User friendData) {
        double score = 0;
        for (String uArtist : userData.artists) {
            for (String fArtist : friendData.artists) {
                if (uArtist.equals(fArtist)) {
                    score += .1;
                }
            }
        }

        return String.valueOf(Math.sqrt(score));

    }

    public boolean validate(String username, String session) {
        String sessionID = this.queue.getUser(username);
        if (sessionID.equals("INVALID")) return false;
        if (!sessionID.equals(session)) return false;
        return true;
    }
}

