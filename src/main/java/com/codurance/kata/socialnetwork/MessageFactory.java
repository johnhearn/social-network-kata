package com.codurance.kata.socialnetwork;

public class MessageFactory {
    private final Clock clock;

    public MessageFactory(Clock clock) {
        this.clock = clock;
    }

    public Message create(String text) {
        return new Message(text, clock.now());
    }
}
