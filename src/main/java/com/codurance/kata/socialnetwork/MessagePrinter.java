package com.codurance.kata.socialnetwork;

public class MessagePrinter {
    private final Output output;
    private final MessageFormatter messageFormatter;

    public MessagePrinter(Output output, MessageFormatter messageFormatter) {
        this.output = output;
        this.messageFormatter = messageFormatter;
    }

    public void print(Message message) {
        output.println(messageFormatter.format(message));
    }
}
