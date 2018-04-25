package com.codurance.kata.socialnetwork.unit;

import com.codurance.kata.socialnetwork.FriendRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class FriendRepositoryShould {

    private static final String USER = "Alice";
    private static final String FRIEND = "Bob";
    private static final String OTHER_FRIEND = "Charlie";

    private FriendRepository friendRepository;

    @Before
    public void setUp() {
        friendRepository = new FriendRepository();
    }

    @Test
    public void
    return_no_friends_before_adding_anyone() {
        assertThat(friendRepository.friendsOf(USER).size(), equalTo(0));
    }

    @Test
    public void
    return_added_friend() {
        friendRepository.addFriend(USER, FRIEND);

        Set<String> friends = friendRepository.friendsOf(USER);

        assertThat(friends.size(), equalTo(1));
        assertThat(friends, hasItem(FRIEND));
    }

    @Test
    public void
    return_multiple_added_friends() {
        friendRepository.addFriend(USER, FRIEND);
        friendRepository.addFriend(USER, OTHER_FRIEND);

        Set<String> friends = friendRepository.friendsOf(USER);

        assertThat(friends.size(), equalTo(2));
        assertThat(friends, hasItem(FRIEND));
        assertThat(friends, hasItem(OTHER_FRIEND));
    }
}
