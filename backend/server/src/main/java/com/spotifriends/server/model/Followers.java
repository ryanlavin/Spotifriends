package com.spotifriends.server.model;

public class Followers {

    String href;
    int total;

    public Followers(String href, int total) {
        this.href = href;
        this.total = total;
    }

    @Override
    public String toString() {
        return "Followers{" +
                "href='" + href + '\'' +
                ", total=" + total +
                '}';
    }
}
