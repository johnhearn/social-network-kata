package com.codurance.kata.socialnetwork;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalTime;
import java.util.SortedSet;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MessageRepositoryShould {

    private static final String USER = "Alice";
    private static final String OTHER_USER = "Bob";
    private static final Message MESSAGE = new Message("Hello world!", LocalTime.now());

    private MessageRepository messageRepository;

    @Before
    public void setUp() {
        messageRepository = new MessageRepository();
    }

    @Test
    public void
    store_and_list_messages() {
        messageRepository.addMessage(USER, MESSAGE);

        SortedSet<Message> messages = messageRepository.listMessages(USER);

        assertThat(messages.size(), is(1));
        assertThat(messages, hasItem(MESSAGE));
    }

    @Test
    public void
    not_list_messages_from_other_user() {
        messageRepository.addMessage(USER, MESSAGE);

        SortedSet<Message> messages = messageRepository.listMessages(OTHER_USER);

        assertThat(messages.size(), is(0));
    }
}
