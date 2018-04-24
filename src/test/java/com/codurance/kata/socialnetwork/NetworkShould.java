package com.codurance.kata.socialnetwork;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalTime;
import java.util.NavigableSet;
import java.util.TreeSet;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NetworkShould {

    private static final String USER = "Alice";
    private static final String MESSAGE = "I love the weather today";
    private static final LocalTime NOW = LocalTime.now();

    @Mock
    private MessageRepository messageRepository;
    @Mock
    private MessagePrinter messagePrinter;
    @Mock
    private MessageFactory messageFactory;
    @Mock
    private Message message;

    private Network network;

    @Before
    public void setUp() {
        network = new Network(messageRepository, messageFactory, messagePrinter);
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
        when(messageFactory.create(MESSAGE)).thenReturn(message);

        network.postMessage(USER, MESSAGE);

        verify(messageRepository).addMessage(USER, message);
    }
}
