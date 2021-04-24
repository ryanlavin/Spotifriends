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
        while (true) {
            System.out.print("run.");
            if (queue.users.size() == 0) {
                Thread.yield();
                continue;
            }
            LoggedInUser firstUser = queue.users.get(0);
            boolean elapsed = Duration.between(Instant.now(), firstUser.start).getSeconds() > 1200;
            if (!elapsed) {
                Thread.yield();
                continue;
            }
            queue.removeFirst(firstUser.username);
        }
    }

}
