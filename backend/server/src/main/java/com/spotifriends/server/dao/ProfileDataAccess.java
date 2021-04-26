package com.spotifriends.server.dao;

import com.google.gson.Gson;
import com.spotifriends.server.model.LoggedInQueue;
import com.spotifriends.server.model.ProfileData;
import com.spotifriends.server.model.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.SQLException;
import java.util.List;

@Repository("profile")
public class ProfileDataAccess implements ProfileDao {

    private final JdbcTemplate jdbcTemplate;
    public LoggedInQueue queue;
    public SessionManager manager;

    @Autowired
    public ProfileDataAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.queue = new LoggedInQueue();
//        this.manager = new SessionManager(this.queue);
//        this.manager.run();
    }

    @Override
    public String addFriend(String username, String session, String friend_name) {
        boolean valid = this.validate(username, session);

        if (!valid) return "INVALID SESSION";

        List<ProfileData> u = null;

        String getData = "SELECT friends, private FROM user_table WHERE username = ?";
        u = jdbcTemplate.query(getData, new Object[]{username}, (rs, rowNum) -> {
            return new ProfileData(rs.getBoolean("private"), rs.getArray("friends"));
        });

        if (u == null) return "INVALID QUERY";
        if (u.size() != 0) {
            for (String name : u.get(0).friends) {
                if (name.equals(friend_name)) {
                    return "SUCCESS";
                }
            }
        }

        String addFriend = "UPDATE user_table SET friends = array_append(friends, ?::text) WHERE username = ?";
        int res = jdbcTemplate.update(addFriend, friend_name, username);

        if (res == 0) return "INVALID QUERY";
        return "SUCCESS";
    }

    @Override
    public String changePrivacy(String username, String session, boolean priv) {
        boolean valid = this.validate(username, session);

        if (!valid) return "{\"priv\":\"INVALID SESSION\"}";

        String addFriend = "UPDATE user_table SET private = ? WHERE username = ?";
        int res = jdbcTemplate.update(addFriend, priv, username);

        if (res == 0) return "INVALID QUERY";
        return "SUCCESS";

    }

    @Override
    public String getProfileData(String username, String session) {
        // VALIDATE that username is logged in
        boolean valid = this.validate(username, session);

        if (!valid) return "{\"priv\":\"INVALID SESSION\"}";

        List<ProfileData> u = null;

        String getData = "SELECT friends, private FROM user_table WHERE username = ?";
        u = jdbcTemplate.query(getData, new Object[]{username}, (rs, rowNum) -> {
            return new ProfileData(rs.getBoolean("private"), rs.getArray("friends"));
        });

        if (u == null) return "{\"priv\":\"INVALID QUERY\"}";
        if (u.size() == 0) return "{\"priv\":\"INVALID QUERY\"}";

        Gson gson = new Gson();
        String json = gson.toJson(u.get(0));
        return json;

    }

    public boolean validate(String username, String session) {
        String sessionID = this.queue.getUser(username, session);
        if (sessionID.equals("INVALID")) return false;
        if (!sessionID.equals(session)) return false;
        return true;
    }

}
