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
        } else if (command.contains(" follows ")) {
            String[] split = command.split(" follows ");
            network.follow(split[0], split[1]);
        } else if (command.endsWith(" wall")) {
            String[] split = command.split(" wall");
            network.wall(split[0]);
        } else {
            network.readTimeline(command);
        }
    }
}
