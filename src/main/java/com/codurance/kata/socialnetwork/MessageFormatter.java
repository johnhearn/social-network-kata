package com.codurance.kata.socialnetwork;

import java.time.temporal.ChronoUnit;

public class MessageFormatter {
    private final Clock clock;

    public MessageFormatter(Clock clock) {
        this.clock = clock;
    }

    public String format(Message message) {
        return message.text() + " (" + timeAgoString(message) + " ago)";
    }

    private String timeAgoString(Message message) {
        long elapsedMinutes = elapsedMinutes(message);
        boolean isPlural = elapsedMinutes > 1;

        return elapsedMinutes + " minute" + (isPlural ? "s" : "");
    }

    private long elapsedMinutes(Message message) {
        return ChronoUnit.MINUTES.between(message.time(), clock.now());
    }
}
