package com.codurance.kata.socialnetwork.unit;

import com.codurance.kata.socialnetwork.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalTime;

import static com.codurance.kata.socialnetwork.unit.CollectionsCreation.navigableSetOf;
import static com.codurance.kata.socialnetwork.unit.CollectionsCreation.setOf;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SocialNetworkShould {

    private static final String USER = "Alice";
    private static final String FRIEND = "Bob";
    private static final String MESSAGE = "I love the weather today";

    @Mock
    private MessageRepository messageRepository;
    @Mock
    private MessagePrinter messagePrinter;
    @Mock
    private MessageFactory messageFactory;
    @Mock
    private FriendRepository friendRepository;

    private Message message;

    private SocialNetwork network;

    @Before
    public void setUp() {
        network = new SocialNetwork(messageRepository, messageFactory, messagePrinter, friendRepository);
        message = new Message("user", "message", LocalTime.now());
    }

    @Test
    public void
    print_in_timeline_all_messages_from_user() {
        when(messageRepository.listMessagesFor(USER)).thenReturn(navigableSetOf(message));

        network.readTimeline(USER);

        verify(messagePrinter).print(message);
    }

    @Test
    public void
    store_message_for_user_with_current_time() {
        when(messageFactory.create(USER, MESSAGE)).thenReturn(message);

        network.postMessage(USER, MESSAGE);

        verify(messageRepository).addMessage(message);
    }

    @Test
    public void
    store_friend() {
        network.follow(USER, FRIEND);

        verify(friendRepository).addFriend(USER, FRIEND);
    }

    @Test
    public void
    print_user_wall() {
        when(friendRepository.friendsOf(USER)).thenReturn(setOf(FRIEND));
        when(messageRepository.listMessagesFor(setOf(USER, FRIEND))).thenReturn(navigableSetOf(message));

        network.wall(USER);

        verify(messagePrinter).printWithName(message);
    }
}
