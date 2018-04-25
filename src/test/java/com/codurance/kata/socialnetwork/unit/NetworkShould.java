package com.codurance.kata.socialnetwork.unit;

import com.codurance.kata.socialnetwork.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NetworkShould {

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
    @Mock
    private Message message;

    private Network network;

    @Before
    public void setUp() {
        network = new Network(messageRepository, messageFactory, messagePrinter, friendRepository);
    }

    @Test
    public void
    print_in_timeline_all_messages_from_user() {
        NavigableSet<Message> messages = new TreeSet<Message>() {{
            add(message);
        }};
        when(messageRepository.listMessages(USER)).thenReturn(messages);

        network.readTimeline(USER);

        verify(messagePrinter).print(message);
    }

    @Test
    public void
    store_message_for_user_with_current_time() {
        when(messageFactory.create(USER, MESSAGE)).thenReturn(message);

        network.postMessage(USER, MESSAGE);

        verify(messageRepository).addMessage(USER, message);
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
        Set<String> friends = new HashSet<String>() {{
            add(FRIEND);
        }};
        Set<String> userAndFriends = new HashSet<String>() {{
            add(USER);
            add(FRIEND);
        }};
        NavigableSet<Message> messages = new TreeSet<Message>() {{
            add(message);
        }};

        when(friendRepository.friendsOf(USER)).thenReturn(friends);
        when(messageRepository.listMessagesOfUsers(userAndFriends)).thenReturn(messages);

        network.wall(USER);

        verify(messagePrinter).printWithName(message);
    }
}
