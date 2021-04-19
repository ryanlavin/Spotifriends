package com.spotifriends.server.model;

import java.util.ArrayList;

public class TrackArray {
    ArrayList<Track> items;
    int total;
    int limit;
    int offset;
    String previous;
    String href;
    String next;

    public TrackArray(ArrayList<Track> items, int total, int limit, int offset, String previous, String href, String next) {
        this.items = items;
        this.total = total;
        this.limit = limit;
        this.offset = offset;
        this.previous = previous;
        this.href = href;
        this.next = next;
    }

    @Override
    public String toString() {
        return "TrackArray{" +
                "items=" + items +
                ", total=" + total +
                ", limit=" + limit +
                ", offset=" + offset +
                ", previous='" + previous + '\'' +
                ", href='" + href + '\'' +
                ", next='" + next + '\'' +
                '}';
    }
}
