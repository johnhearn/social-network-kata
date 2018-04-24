package com.codurance.kata.socialnetwork;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MessageFactoryShould {

    private static final LocalTime NOW = LocalTime.of(3, 30);
    private static final String TEXT = "foo";

    @Mock
    private Clock clock;

    private MessageFactory messageFactory;

    @Before
    public void setUp() {
        messageFactory = new MessageFactory(clock);
    }

    @Test
    public void
    store_message_for_user_with_current_time() {
        when(clock.now()).thenReturn(NOW);

        Message message = messageFactory.create(TEXT);

        assertThat(message.text(), is(TEXT));
        assertThat(message.time(), is(NOW));
    }
}
