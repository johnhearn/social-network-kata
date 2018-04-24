package com.codurance.kata.socialnetwork;

import java.time.LocalTime;
import java.util.Objects;

public class Message implements Comparable<Message> {
    private final String text;
    private final LocalTime time;

    public Message(String text, LocalTime time) {
        this.text = text;
        this.time = time;
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
        return Objects.equals(text, message.text) &&
                Objects.equals(time, message.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, time);
    }

    @Override
    public int compareTo(Message o) {
        return time.compareTo(o.time);
    }
}
