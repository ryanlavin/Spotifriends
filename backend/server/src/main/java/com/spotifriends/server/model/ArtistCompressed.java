package com.spotifriends.server.model;

import java.util.HashMap;

public class ArtistCompressed {

    HashMap<String, String> external_urls;
    String href;
    String id;
    String name;
    String type;
    String uri;

    public ArtistCompressed(HashMap<String, String> external_urls, String href, String id, String name, String type, String uri) {
        this.external_urls = external_urls;
        this.href = href;
        this.id = id;
        this.name = name;
        this.type = type;
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "ArtistCompressed{" +
                "external_urls=" + external_urls +
                ", href='" + href + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }
}
