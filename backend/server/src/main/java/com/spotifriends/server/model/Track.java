package com.spotifriends.server.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Track {

    ArrayList<ArtistCompressed> artists;
    ArrayList<String> available_markets;
    int disc_number;
    int duration_ms;
    boolean explicit;
    HashMap<String,String> external_ids;
    HashMap<String,String> external_urls;
    String href;
    String id;
    boolean is_local;
    String name;
    int popularity;
    String preview_url;
    int track_number;
    String type;
    String uri;

    public Track(ArrayList<ArtistCompressed> artists, ArrayList<String> available_markets, int disc_number, int duration_ms, boolean explicit, HashMap<String, String> external_ids, HashMap<String, String> external_urls, String href, String id, boolean is_local, String name, int popularity, String preview_url, int track_number, String type, String uri) {
        this.artists = artists;
        this.available_markets = available_markets;
        this.disc_number = disc_number;
        this.duration_ms = duration_ms;
        this.explicit = explicit;
        this.external_ids = external_ids;
        this.external_urls = external_urls;
        this.href = href;
        this.id = id;
        this.is_local = is_local;
        this.name = name;
        this.popularity = popularity;
        this.preview_url = preview_url;
        this.track_number = track_number;
        this.type = type;
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "Track{" +
                "artists=" + artists +
                ", available_markets=" + available_markets +
                ", disc_number=" + disc_number +
                ", duration_ms=" + duration_ms +
                ", explicit=" + explicit +
                ", external_ids=" + external_ids +
                ", external_urls=" + external_urls +
                ", href='" + href + '\'' +
                ", id='" + id + '\'' +
                ", is_local=" + is_local +
                ", name='" + name + '\'' +
                ", popularity=" + popularity +
                ", preview_url='" + preview_url + '\'' +
                ", track_number=" + track_number +
                ", type='" + type + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }
}
