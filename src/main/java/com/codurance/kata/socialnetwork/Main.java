package com.codurance.kata.socialnetwork;

import java.time.LocalTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final Clock clock = LocalTime::now;

        final Scanner scanner = new Scanner(System.in);
        final Input input = scanner::nextLine;

        final Output output = System.out::println;

        Dispatcher dispatcher = new Dispatcher(input, new Processor(new Network(new MessageRepository(), new MessageFactory(clock), new MessagePrinter(output, new MessageFormatter(clock)))));
        dispatcher.start();
    }
}
