package com.codurance.kata.socialnetwork;

import java.util.*;

public class MessageRepository {
    private Map<String, NavigableSet<Message>> messages = new HashMap<>();

    public NavigableSet<Message> listMessagesFor(String user) {
        return messages.getOrDefault(user, Collections.emptyNavigableSet());
    }

    public void addMessage(Message message) {
        messages.computeIfAbsent(message.user(), k -> new TreeSet<>()).add(message);
    }

    public NavigableSet<Message> listMessagesFor(Set<String> users) {
        NavigableSet<Message> messages = new TreeSet<>();
        for (String user : users) {
            messages.addAll(listMessagesFor(user));
        }
        return messages;
    }
}
