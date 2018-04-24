package com.codurance.kata.socialnetwork;

public class Dispatcher {

    private final Input input;
    private final Processor processor;

    public Dispatcher(Input input, Processor processor) {
        this.input = input;
        this.processor = processor;
    }

    public void start() {
        String command = input.readln();

        while (command != null) {
            processor.process(command);
            command = input.readln();
        }
    }
}
