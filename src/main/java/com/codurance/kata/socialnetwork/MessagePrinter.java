package com.codurance.kata.socialnetwork;

public class MessagePrinter {
    private final Output output;
    private final MessageFormatter messageFormatter;
    private final WallMessageFormatter wallMessageFormatter;

    public MessagePrinter(Output output, MessageFormatter messageFormatter, WallMessageFormatter wallMessageFormatter) {
        this.output = output;
        this.messageFormatter = messageFormatter;
        this.wallMessageFormatter = wallMessageFormatter;
    }

    public void print(Message message) {
        output.println(messageFormatter.format(message));
    }

    public void printWithName(Message message) {
        output.println(wallMessageFormatter.format(message));
    }
}
