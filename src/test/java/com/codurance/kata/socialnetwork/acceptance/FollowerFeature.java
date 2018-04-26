package com.codurance.kata.socialnetwork.acceptance;

import com.codurance.kata.socialnetwork.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalTime;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FollowerFeature {

    @Mock
    private Input input;
    @Mock
    private Output output;
    @Mock
    private Clock clock;

    private Dispatcher dispatcher;

    @Before
    public void setUp() {
        MessageFormatter defaultMessageFormatter = new DefaultMessageFormatter(clock);

        dispatcher = new Dispatcher(input, new Processor(new Network(new MessageRepository(), new MessageFactory(clock), new MessagePrinter(output, defaultMessageFormatter, new WallMessageFormatter(defaultMessageFormatter)), new FriendRepository())));
    }

    @Test
    public void
    displays_wall_with_messages_from_you_and_your_friends() {
        // given
        when(clock.now())
                .thenReturn(LocalTime.of(9, 25))
                .thenReturn(LocalTime.of(9, 28))
                .thenReturn(LocalTime.of(9, 30));

        when(input.readln())
                .thenReturn("Alice -> I love the weather today")
                .thenReturn("Charlie -> I'm in New York today! Anyone want to have a coffee?")
                .thenReturn("Charlie follows Alice")
                .thenReturn("Charlie wall")
                .thenReturn(null);

        // when
        dispatcher.start();

        // then
        verify(output).println("Charlie - I'm in New York today! Anyone want to have a coffee? (2 minutes ago)");
        verify(output).println("Alice - I love the weather today (5 minutes ago)");
    }
}
