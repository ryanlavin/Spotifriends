package com.spotifriends.server.dao;

import com.spotifriends.server.model.NewUser;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository("cloud")
public class NewUserDataAccess implements NewUserDao {
    @Override
    public int addNewUser(NewUser nu) {
        return 0;
    }

    @Override
    public ArrayList<NewUser> getNewUsers() {
        return null;
    }
}
