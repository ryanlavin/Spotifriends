package com.spotifriends.server.model;

public class qTrack {
    public String id;
    public String name;
    public int popularity;
    public int num_references;

    public qTrack(String id, String name, int popularity, int num_references) {
        this.id = id;
        this.name = name;
        this.popularity = popularity;
        this.num_references = num_references;
    }

    @Override
    public String toString() {
        return "qTrack{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", popularity=" + popularity +
                ", num_references=" + num_references +
                '}';
    }
}
