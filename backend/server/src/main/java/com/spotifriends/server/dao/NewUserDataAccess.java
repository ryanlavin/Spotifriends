package com.spotifriends.server.dao;

import com.google.gson.Gson;
import com.spotifriends.server.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.DatabaseMetaData;
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
    public String addNewUser(NewUser nu) {

//        jdbcTemplate.execute("CREATE TABLE user_table (\n" +
//                "\tusername text NOT NULL PRIMARY KEY UNIQUE,\n" +
//                "\tpassword text NOT NULL,\n" +
//                "\tartists text[] NOT NULL,\n" +
//                "\ttracks text[] NOT NULL,\n" +
//                "\tfriends text[] NOT NULL DEFAULT '{}',\n" +
//                "\tprivate boolean NOT NULL DEFAULT FALSE);");
//
//        jdbcTemplate.execute("CREATE TABLE artist_table (\n" +
//                "\tid text NOT NULL PRIMARY KEY UNIQUE,\n" +
//                "\tname text NOT NULL,\n" +
//                "\tgenres text[] NOT NULL,\n" +
//                "\timages text[] NOT NULL,\n" +
//                "\tpopularity int NOT NULL DEFAULT 0,\n" +
//                "\tnum_references int NOT NULL DEFAULT 0);");
//
//        jdbcTemplate.execute("CREATE TABLE track_table (\n" +
//                "\tid text NOT NULL PRIMARY KEY UNIQUE,\n" +
//                "\tname text NOT NULL,\n" +
//                "\tpopularity int NOT NULL DEFAULT 0,\n" +
//                "\tnum_references int NOT NULL DEFAULT 0);\n");

//        jdbcTemplate.execute("DROP TABLE flyway_schema_history;");

        try{
            String ret = jdbcTemplate.getDataSource().getConnection().getCatalog();
            System.out.println("_+_+_+_+_+_+_+_+_+_+_");
            System.out.println(ret);
            System.out.println("_+_+_+_+_+_+_+_+_+_+_");
            DatabaseMetaData metaData = jdbcTemplate.getDataSource().getConnection().getMetaData();
            ResultSet tables = metaData.getTables(null, null, null, new String[] { "TABLE" });
            while (tables.next()) {
                String tableName=tables.getString("TABLE_NAME");
                System.out.println(tableName);
                ResultSet columns = metaData.getColumns(null,  null,  tableName, "%");
                while (columns.next()) {
                    String columnName=columns.getString("COLUMN_NAME");
                    System.out.println("\t" + columnName);
                }
            }
        } catch (Exception ex) {
            System.out.println("EXCEPTION");
        }

//        return 1;

        String checkUsername = "SELECT * FROM user_table WHERE username = ?";
        List<User> u = jdbcTemplate.query(checkUsername, new Object[]{nu.username}, (rs, rowNum) -> {
                    return new User(rs.getString("username"), rs.getString("password"), rs.getArray("artists"), rs.getArray("tracks"), rs.getArray("friends"), rs.getBoolean("private"));
                });
        if (u != null) {
             if (u.size() != 0) {
                return "USERNAME ALREADY EXISTS";
             }
        }
            //String username, String password, Array artists, Array tracks, Array friends, boolean priv


        System.out.println("STARTING TRACKS");

        for ( Track track : nu.trackArray.items) {
            String sql = "SELECT * FROM track_table WHERE id = ?;";
            List<qTrack> tracks = jdbcTemplate.query(sql, new Object[] { track.id }, (rs, rowNum) -> {
                return new qTrack(rs.getString("id"), rs.getString("name"), rs.getInt("popularity"), rs.getInt("num_references"));
            });
            int res = 0;

            for ( qTrack t : tracks) {
                System.out.println(t);
            }

            if (tracks == null) {
                return "COULD NOT GET TRACKS";
            } else if (tracks.size() == 0) {
                String addTrack = "INSERT INTO track_table (id, name, popularity, num_references) VALUES (?,?,?,?);";
                res = jdbcTemplate.update(addTrack, track.id, track.name, track.popularity, 1);
            } else {
               String increment = "UPDATE track_table SET num_references = ? WHERE id = ?;";
               res = jdbcTemplate.update(increment, new Object[] { tracks.get(0).num_references + 1, track.id });
            }
            if (res == 0) {
                return "COULD NOT ADD TRACK";
            }
        }

        System.out.println("STARTING ARTISTS");
        System.out.println(nu.artistArray.size());
        for ( Artist artist : nu.artistArray.items) {
            String sql = "SELECT * FROM artist_table WHERE id = ?;";
            List<qArtist> artists = jdbcTemplate.query(sql, new Object[] { artist.id }, (rs, rowNum) -> {
                return new qArtist(rs.getString("id"), rs.getString("name"), rs.getInt("popularity"), rs.getInt("num_references"), rs.getArray("genres"), rs.getArray("images"));
            });
//            String id, String name, int popularity, int num_references, Array genres, Array images
            System.out.println(artist.genres);
            int res = 0;

            for ( qArtist a : artists) {
                System.out.println(a);
            }

            if (artists == null) {
                return "COULD NOT ADD ARTIST";
            } else if (artists.size() == 0) {
                System.out.println("Adding Artist");
                String addArtist = "INSERT INTO artist_table (id, name, genres, images, popularity, num_references) VALUES (?,?,?,?,?,?);";
                res = jdbcTemplate.update(addArtist, artist.id, artist.name, artist.getGenres(), artist.getImages(),  artist.popularity, 1);
            } else {
                System.out.println("Updating Artist");
                String increment = "UPDATE artist_table SET num_references = ?  WHERE id = ?;";
                int newRefs = artists.get(0).num_references + 1;
                res = jdbcTemplate.update(increment, new Object[] { newRefs, artist.id });
            }
            if (res == 0) {
                return "COULD NOT GET ARTIST";
            }
        }

        System.out.println("STARTING USERS");

        String sql = "INSERT INTO user_table (username, password, artists, tracks, private) VALUES (?,?,?,?,?)";
        int res = jdbcTemplate.update(sql, nu.username, nu.password, nu.getArtists(), nu.getTracks(), false);
        if (res == 0) return "COULD NOT ADD USER";
        else return "ADDED USER";
    }

    @Override
    public String getNewUsers() {
        String sql = "SELECT * FROM user_table;";
        List<User> users = jdbcTemplate.query(sql, (rs, rowNum) -> {
            return new User(rs.getString("username"), rs.getString("password"), rs.getArray("artists"), rs.getArray("tracks"), rs.getArray("friends"), rs.getBoolean("private"));
        });

        for (User u : users) {
            System.out.println(u);
        }

        Gson gson = new Gson();
        String json = gson.toJson(users);
        return json;
    }
}

