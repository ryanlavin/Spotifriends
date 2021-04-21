package com.spotifriends.server.service;

import com.spotifriends.server.dao.NewUserDao;
import com.spotifriends.server.dao.StatsDao;
import com.spotifriends.server.model.NewUser;
import com.spotifriends.server.model.qArtist;
import com.spotifriends.server.model.qTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StatsService {

    private final StatsDao statsDao;

    @Autowired
    public StatsService(@Qualifier("stats") StatsDao statsDao) {
        this.statsDao = statsDao;
    }

    public String getArtists() {
        return statsDao.getArtists();
    }

    public String getTracks() {
        return statsDao.getTracks();
    }

    public String getStats() {
        String artists = this.getArtists();
        String tracks = this.getTracks();

        return "{ artists: " + artists + ", tracks: " + tracks + "}";

    }

}
