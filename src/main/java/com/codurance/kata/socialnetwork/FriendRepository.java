package com.codurance.kata.socialnetwork;

import java.util.*;

public class FriendRepository {
    private Map<String, Set<String>> friends = new HashMap<>();

    public void addFriend(String user, String friend) {
        friends.computeIfAbsent(user, i -> new HashSet<>()).add(friend);
    }

    public Set<String> friendsOf(String user) {
        return friends.getOrDefault(user, Collections.emptySet());
    }
}
