package com.spotifriends.server.dao;

import com.google.gson.Gson;
import com.spotifriends.server.model.LoggedInQueue;
import com.spotifriends.server.model.ProfileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository("profile")
public class ProfileDataAccess implements ProfileDao {

    private final JdbcTemplate jdbcTemplate;
    public LoggedInQueue queue;


    @Autowired
    public ProfileDataAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.queue = new LoggedInQueue();
    }

    @Override
    public String getProfileData(String username, String session) {
        // VALIDATE that username is logged in
        String sessionID = this.queue.getUser(username);
        if (sessionID.equals("INVALID")) return sessionID;
        if (!sessionID.equals(session)) return "INVALID";

        List<ProfileData> u = null;

        String getData = "SELECT friends, private FROM user_table WHERE username = ?";
         u = jdbcTemplate.query(getData, new Object[]{username}, (rs, rowNum) -> {
            return new ProfileData(rs.getBoolean("private"), rs.getArray("friends"));
        });

        if (u == null) return "INVALID";
        if (u.size() == 0) return "INVALID";

        Gson gson = new Gson();
        String json = gson.toJson(u);
        return json;

    }
}
