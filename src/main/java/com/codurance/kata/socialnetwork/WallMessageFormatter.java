package com.codurance.kata.socialnetwork;

public class WallMessageFormatter implements MessageFormatter {

    private final MessageFormatter innerFormatter;

    public WallMessageFormatter(MessageFormatter innerFormatter) {
        this.innerFormatter = innerFormatter;
    }

    @Override
    public String format(Message message) {
        return message.user() + " - " + innerFormatter.format(message);
    }
}
