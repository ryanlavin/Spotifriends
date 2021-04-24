package com.spotifriends.server.model;

import org.apache.juli.logging.Log;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;

public class SessionManager extends Thread {

    LoggedInQueue queue;

    public SessionManager(LoggedInQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int sessionMax = 1200;
        while (true) {
            // CHECK IF THERE ARE ANY LOGGED IN USERS
            if (queue.users.size() == 0) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    continue;
                }
                continue;
            }
            // CHECK IF FIRST USERS SESSION HAS EXPIRED
            LoggedInUser firstUser = queue.users.get(0);
            long difference = Instant.now().getEpochSecond() - firstUser.start.getEpochSecond();
            boolean expired = difference > sessionMax;
            if (!expired) {
                try {
                    Thread.sleep((sessionMax-difference)*1000);
                } catch (InterruptedException e) {
                    continue;
                }
                continue;
            }
            queue.removeFirst(firstUser.username);
        }
    }

}
