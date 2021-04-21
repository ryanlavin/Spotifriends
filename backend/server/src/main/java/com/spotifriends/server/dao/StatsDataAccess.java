package com.spotifriends.server.dao;

import com.google.gson.Gson;
import com.spotifriends.server.model.User;
import com.spotifriends.server.model.qArtist;
import com.spotifriends.server.model.qTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("stats")
public class StatsDataAccess implements StatsDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StatsDataAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String getArtists() {

        String getArtists = "SELECT * FROM artist_table ORDER BY num_references LIMIT 5;";
        List<qArtist> artistsList = jdbcTemplate.query(getArtists, (rs, rowNum) -> {
            return new qArtist(rs.getString("id"), rs.getString("name"), rs.getInt("popularity"), rs.getInt("num_references"), rs.getArray("genres"), rs.getArray("images"));
        });

        if (artistsList == null) return "COULD NOT GET ARTISTS";
        if (artistsList.size() == 0) return "COULD NOT GET ARTISTS";

        Gson gson = new Gson();
        String json = gson.toJson(artistsList);
        return json;
    }

    @Override
    public String getTracks() {
        String getTracks = "SELECT * FROM track_table ORDER BY num_references LIMIT 5;";
        List<qTrack> tracksList = jdbcTemplate.query(getTracks, (rs, rowNum) -> {
            return new qTrack(rs.getString("id"), rs.getString("name"), rs.getInt("popularity"), rs.getInt("num_references"));
        });

        if (tracksList == null) return "COULD NOT GET TRACKS";
        if (tracksList.size() == 0) return "COULD NOT GET TRACKS";

        Gson gson = new Gson();
        String json = gson.toJson(tracksList);
        return json;
    }
}
