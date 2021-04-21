package com.spotifriends.server.dao;

import com.google.gson.Gson;
import com.spotifriends.server.model.NewUser;
import com.spotifriends.server.model.qArtist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("clear")
public class ClearDataAccess implements ClearDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ClearDataAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int clear() {
        String trackClear = "TRUNCATE TABLE track_table;";
        String artistClear = "TRUNCATE TABLE artist_table;";
        String userClear = "TRUNCATE TABLE user_table;";

        jdbcTemplate.execute(trackClear);
        jdbcTemplate.execute(artistClear);
        jdbcTemplate.execute(userClear);

//        String checkType = "SELECT * FROM track_table;";
//        List<String> types = jdbcTemplate.query(checkType, (rs, rowNum) -> {
//            return rs.getString("data_type");
//        });
//
//        for (String s : types) {
//            System.out.println(s);
//        }


        return 1;
    }

}
