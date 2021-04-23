package com.spotifriends.server.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class LoggedInQueue {

    public static LinkedList<LoggedInUser> users;
    public static SessionManager manager;

    public LoggedInQueue() {
        if (this.users == null) {
            System.out.println("NEW QUEUE CREATED!!!");
            this.users = new LinkedList<LoggedInUser>();
            this.manager = new SessionManager(this);
            this.manager.run();
        }
    }

    public synchronized String getUser(String username) {
        for (LoggedInUser u : users) {
            if (u.username == username) {
                return u.session;
            }
        }
        return "INVALID";
    }


}
