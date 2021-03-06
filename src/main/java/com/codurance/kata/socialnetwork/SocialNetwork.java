package com.codurance.kata.socialnetwork;

import java.util.*;

public class SocialNetwork {

    private final MessageRepository messageRepository;
    private final MessageFactory messageFactory;
    private final MessagePrinter messagePrinter;
    private final FriendRepository friendRepository;

    public SocialNetwork(MessageRepository messageRepository, MessageFactory messageFactory, MessagePrinter messagePrinter, FriendRepository friendRepository) {
        this.messageRepository = messageRepository;
        this.messageFactory = messageFactory;
        this.messagePrinter = messagePrinter;
        this.friendRepository = friendRepository;
    }

    public void postMessage(String user, String text) {
        messageRepository.addMessage(messageFactory.create(user, text));
    }

    public void readTimeline(String user) {
        NavigableSet<Message> userMessages = messageRepository.listMessagesFor(user);

        for (Message message : userMessages.descendingSet()) {
            messagePrinter.print(message);
        }
    }

    public void follow(String user, String friend) {
        friendRepository.addFriend(user, friend);
    }

    public void wall(String user) {
        Set<String> friends = friendRepository.friendsOf(user);
        friends.add(user);

        NavigableSet<Message> messages = messageRepository.listMessagesFor(friends);

        for (Message message : messages.descendingSet()) {
            messagePrinter.printWithName(message);
        }
    }
}
