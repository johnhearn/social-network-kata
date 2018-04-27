package com.codurance.kata.socialnetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;

public class Main {

    public static void main(String... args) {
        final Clock clock = LocalTime::now;

        final BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
        final Input input = () -> {
            try {
                return inputStream.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        final Output output = System.out::println;

        final MessageFormatter defaultMessageFormatter = new DefaultMessageFormatter(clock);

        Dispatcher dispatcher = new Dispatcher(input, new Processor(new SocialNetwork(new MessageRepository(), new MessageFactory(clock), new MessagePrinter(output, defaultMessageFormatter, new WallMessageFormatter(defaultMessageFormatter)), new FriendRepository())));
        dispatcher.start();
    }
}
