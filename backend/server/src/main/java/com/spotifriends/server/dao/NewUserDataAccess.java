package com.spotifriends.server.dao;

import com.google.gson.Gson;
import com.spotifriends.server.model.Artist;
import com.spotifriends.server.model.NewUser;
import com.spotifriends.server.model.Track;
import com.spotifriends.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository("cloud")
public class NewUserDataAccess implements NewUserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NewUserDataAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//String username, String password, ArrayList<String> artists, ArrayList<String> tracks, ArrayList<String> friends, boolean priv
    @Override
    public int addNewUser(NewUser nu) {

        for ( Track track : nu.trackArray.items) {
            String sql = "SELECT * FROM track_table WHERE id = ?";
            boolean hasRecord = jdbcTemplate.query(sql, new Object[] { track.id }, (ResultSet rs) -> {
                                        if (rs.next()) { return true; }
                                        return false; });
            if (!hasRecord) {
                String addTrack = "INSERT INTO track_table (id, name, popularity, num_references) VALUES (?,?,?, ?);";
                int res = jdbcTemplate.update(addTrack, track.id, track.name, track.popularity, 1);
            } // else {
//               String increment = "UPDATE track_table SET num_references = ";
//            }
        }

        for ( Artist artist : nu.artistArray.items) {
            String sql = "SELECT * FROM artist_table WHERE id = ?";
            boolean hasRecord = jdbcTemplate.query(sql, new Object[] { artist.id }, (ResultSet rs) -> {
                if (rs.next()) { return true; }
                return false; });
            if (!hasRecord) {
                String addTrack = "INSERT INTO track_table (id, name, genres, images, popularity, num_references) VALUES (?,?,?,?,?,?);";
                int res = jdbcTemplate.update(addTrack, artist.id, artist.name, artist.genres, artist.images, artist.popularity, 1);
            }
        }


        String sql = "INSERT INTO user_table (username, password, artists, tracks, friends, private) VALUES (?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, nu.username, nu.password, nu.artistArray, nu.trackArray, new ArrayList<String>(), false);
    }

    @Override
    public String getNewUsers() {
        String sql = "SELECT * FROM user_table;";
        List<User> users = jdbcTemplate.query(sql, (rs, rowNum) -> {
            return new User(rs.getString("username"), rs.getString("password"), rs.getArray("artists"), rs.getArray("tracks"), rs.getArray("friends"), rs.getBoolean("private"));
        });
        Gson gson = new Gson();
        String json = gson.toJson(users);
        return json;
    }
}

