package com.codurance.kata.socialnetwork;

import java.util.*;

public class Network {

    private final MessageRepository messageRepository;
    private final MessageFactory messageFactory;
    private final MessagePrinter messagePrinter;

    public Network(MessageRepository messageRepository, MessageFactory messageFactory, MessagePrinter messagePrinter) {
        this.messageRepository = messageRepository;
        this.messageFactory = messageFactory;
        this.messagePrinter = messagePrinter;
    }

    public void postMessage(String user, String text) {
        messageRepository.addMessage(user, messageFactory.create(text));
    }

    public void readTimeline(String user) {
        NavigableSet<Message> userMessages = messageRepository.listMessages(user);

        for (Message message : userMessages.descendingSet()) {
            messagePrinter.print(message);
        }
    }
}
