package com.spotifriends.server.model;

public class Image {
    int height;
    String url;
    int width;

    public Image(int height, String url, int width) {
        this.height = height;
        this.url = url;
        this.width = width;
    }

    @Override
    public String toString() {
        return "Image{" +
                "height=" + height +
                ", url='" + url + '\'' +
                ", width=" + width +
                '}';
    }
}
