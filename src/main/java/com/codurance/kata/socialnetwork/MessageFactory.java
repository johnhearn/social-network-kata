package com.codurance.kata.socialnetwork;

public class MessageFactory {
    private final Clock clock;

    public MessageFactory(Clock clock) {
        this.clock = clock;
    }

    public Message create(String user, String text) {
        return new Message(user, text, clock.now());
    }
}
