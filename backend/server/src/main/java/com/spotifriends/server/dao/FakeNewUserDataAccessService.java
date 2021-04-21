package com.spotifriends.server.dao;

import com.google.gson.Gson;
import com.spotifriends.server.model.NewUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository("local")
public class FakeNewUserDataAccessService implements NewUserDao {

    private static ArrayList<NewUser> DB = new ArrayList<NewUser>();

    @Override
    public String addNewUser(NewUser nu) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
//        System.out.println(jdbcTemplate.queryForList("SELECT * FROM user_table;"));
        DB.add(nu);
        return "1";
    }

    @Override
    public String getNewUsers() {
        Gson gson = new Gson();
        String json = gson.toJson(DB);
        return json;
    }
}
