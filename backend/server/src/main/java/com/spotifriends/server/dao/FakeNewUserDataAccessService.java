package com.spotifriends.server.dao;

import com.spotifriends.server.model.NewUser;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository("fakeDao")
public class FakeNewUserDataAccessService implements NewUserDao {

    private static ArrayList<NewUser> DB = new ArrayList<NewUser>();

    @Override
    public int addNewUser(NewUser nu) {
        DB.add(nu);
        return 1;
    }

    @Override
    public ArrayList<NewUser> getNewUsers() {
        return DB;
    }
}
