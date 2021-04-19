package com.spotifriends.server.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Album {

    String album_type;
    ArrayList<ArtistCompressed> artists;
    ArrayList<String> available_markets;
    HashMap<String, String> external_urls;
    String href;
    String id;
    ArrayList<Image> images;
    String name;
    String release_date;
    String release_date_precision;
    int total_tracks;
    String type;
    String uri;

    public Album(String album_type, ArrayList<ArtistCompressed> artists, ArrayList<String> available_markets, HashMap<String, String> external_urls, String href, String id, ArrayList<Image> images, String name, String release_date, String release_date_precision, int total_tracks, String type, String uri) {
        this.album_type = album_type;
        this.artists = artists;
        this.available_markets = available_markets;
        this.external_urls = external_urls;
        this.href = href;
        this.id = id;
        this.images = images;
        this.name = name;
        this.release_date = release_date;
        this.release_date_precision = release_date_precision;
        this.total_tracks = total_tracks;
        this.type = type;
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "Album{" +
                "album_type='" + album_type + '\'' +
                ", artists=" + artists +
                ", available_markets=" + available_markets +
                ", external_urls=" + external_urls +
                ", href='" + href + '\'' +
                ", id='" + id + '\'' +
                ", images=" + images +
                ", name='" + name + '\'' +
                ", release_date='" + release_date + '\'' +
                ", release_date_precision='" + release_date_precision + '\'' +
                ", total_tracks=" + total_tracks +
                ", type='" + type + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }
}
