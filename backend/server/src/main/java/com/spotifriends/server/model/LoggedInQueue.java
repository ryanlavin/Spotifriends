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
//            this.manager = new SessionManager(this);
//            this.manager.run();
        }
    }

    public synchronized String getUser(String username) {
        for (LoggedInUser u : users) {
            if (u.username.equals(username)) {
                return u.session;
            }
        }
        return "INVALID";
    }

    public synchronized void removeFirst(String username) {
        if (users.size() == 0) return;
        if (users.get(0).username.equals(username)) {
            users.removeFirst();
            System.out.println("LOGIN SESSION EXPIRED FOR: " + username);
        }
    }
}

