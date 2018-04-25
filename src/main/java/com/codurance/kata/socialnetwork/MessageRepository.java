package com.codurance.kata.socialnetwork;

import java.util.*;

public class MessageRepository {
    private Map<String, NavigableSet<Message>> messages = new HashMap<>();

    public NavigableSet<Message> listMessages(String user) {
        return messages.getOrDefault(user, Collections.emptyNavigableSet());
    }

    public void addMessage(String user, Message message) {
        messages.computeIfAbsent(user, k -> new TreeSet<>()).add(message);
    }

    public NavigableSet<Message> listMessagesOfUsers(Set<String> users) {
        NavigableSet<Message> messages = new TreeSet<>();
        for (String user : users) {
            messages.addAll(listMessages(user));
        }
        return messages;
    }
}
