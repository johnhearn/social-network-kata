package com.codurance.kata.socialnetwork;

import java.time.LocalTime;
import java.util.Objects;

public class Message implements Comparable<Message> {
    private final String user;
    private final String text;
    private final LocalTime time;

    public Message(String user, String text, LocalTime time) {
        this.user = user;
        this.text = text;
        this.time = time;
    }

    public String user() {
        return user;
    }

    public String text() {
        return text;
    }

    public LocalTime time() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(user, message.user) &&
                Objects.equals(text, message.text) &&
                Objects.equals(time, message.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, text, time);
    }

    @Override
    public int compareTo(Message o) {
        int compareTo = time.compareTo(o.time);
        return compareTo;
    }
}
