package com.spotifriends.server.dao;

import com.google.gson.Gson;
import com.spotifriends.server.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.*;

@Repository("login")
public class LoginDataAccess implements LoginDao {

    private final JdbcTemplate jdbcTemplate;
    public LoggedInQueue queue;


    @Autowired
    public LoginDataAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.queue = new LoggedInQueue();

    }


    @Override
    public String login(String username, String password) {
        // FIRST validate username and password
        String checkUsername = "SELECT password FROM user_table WHERE username = ?";
        List<String> u = jdbcTemplate.query(checkUsername, new Object[]{username}, (rs, rowNum) -> {
            return rs.getString("password");
        });

        if (u == null) return "INVALID";
        if (u.size() == 0) return "INVALID";
        if (!u.get(0).equals(password)) return "INVALID";

        // SECOND generate sessionID and add

        String session = UUID.randomUUID().toString();

        this.queue.users.add(new LoggedInUser(username, session));

        return session;
    }
}

