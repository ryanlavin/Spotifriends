package com.spotifriends.server.model;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class qArtist {

    public String id;
    public String name;
    public int popularity;
    public int num_references;
    public ArrayList<String> genres;
    public ArrayList<String> images;

    public qArtist(String id, String name, int popularity, int num_references, Array genres, Array images) {
        this.id = id;
        this.name = name;
        this.popularity = popularity;
        this.num_references = num_references;
        this.genres = new ArrayList<String>();
        this.images = new ArrayList<String>();
        try{
            String[] g = (String[]) genres.getArray();
            String[] im = (String[]) images.getArray();
            for (String s : g) {
                this.genres.add(s);
            }
            for (String s : im) {
                this.images.add(s);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "qArtist{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", popularity=" + popularity +
                ", num_references=" + num_references +
                ", genres=" + genres +
                ", images=" + images +
                '}';
    }
}
