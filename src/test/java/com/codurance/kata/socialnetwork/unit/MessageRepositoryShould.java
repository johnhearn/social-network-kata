package com.codurance.kata.socialnetwork.unit;

import com.codurance.kata.socialnetwork.Message;
import com.codurance.kata.socialnetwork.MessageRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

import static com.codurance.kata.socialnetwork.unit.CollectionsCreation.setOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MessageRepositoryShould {

    private static final String USER = "Alice";
    private static final String OTHER_USER = "Bob";
    private static final Message MESSAGE = new Message(USER, "Hello world!", LocalTime.of(3, 0, 30));
    private static final Message OTHER_MESSAGE = new Message(OTHER_USER, "Bye cruel world!", LocalTime.of(3, 0, 31));

    private MessageRepository messageRepository;

    @Before
    public void setUp() {
        messageRepository = new MessageRepository();
    }

    @Test
    public void
    store_and_list_messages() {
        messageRepository.addMessage(MESSAGE);

        SortedSet<Message> messages = messageRepository.listMessagesFor(USER);

        assertThat(messages.size(), is(1));
        assertThat(messages, hasItem(MESSAGE));
    }

    @Test
    public void
    not_list_messages_from_other_user() {
        messageRepository.addMessage(MESSAGE);

        SortedSet<Message> messages = messageRepository.listMessagesFor(OTHER_USER);

        assertThat(messages.size(), is(0));
    }

    @Test
    public void
    list_messages_from_multiple_users() {
        messageRepository.addMessage(MESSAGE);
        messageRepository.addMessage(OTHER_MESSAGE);

        SortedSet<Message> messages = messageRepository.listMessagesFor(setOf(USER, OTHER_USER));

        assertThat(messages.size(), is(2));
        assertThat(messages, hasItem(MESSAGE));
        assertThat(messages, hasItem(OTHER_MESSAGE));
    }
}
