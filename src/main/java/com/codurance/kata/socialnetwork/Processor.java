package com.codurance.kata.socialnetwork;

public class Processor {
    private final Network network;

    public Processor(Network network) {
        this.network = network;
    }

    public void process(String command) {
        if (command.contains(" -> ")) {
            String[] split = command.split(" -> ");
            network.postMessage(split[0], split[1]);
        } else {
            network.readTimeline(command);
        }
    }
}
