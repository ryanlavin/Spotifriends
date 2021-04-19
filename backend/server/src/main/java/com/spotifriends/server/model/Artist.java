package com.spotifriends.server.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Artist {

    HashMap<String,String> external_urls;
    Followers followers;
    ArrayList<String> genres;
    String href;
    String id;
    ArrayList<Image> images;
    String name;
    int popularity;
    String type;
    String uri;

    public Artist(HashMap<String, String> external_urls, Followers followers, ArrayList<String> genres, String href, String id, ArrayList<Image> images, String name, int popularity, String type, String uri) {
        this.external_urls = external_urls;
        this.followers = followers;
        this.genres = genres;
        this.href = href;
        this.id = id;
        this.images = images;
        this.name = name;
        this.popularity = popularity;
        this.type = type;
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "external_urls=" + external_urls +
                ", followers=" + followers +
                ", genres=" + genres +
                ", href='" + href + '\'' +
                ", id='" + id + '\'' +
                ", images=" + images +
                ", name='" + name + '\'' +
                ", popularity=" + popularity +
                ", type='" + type + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }
}
