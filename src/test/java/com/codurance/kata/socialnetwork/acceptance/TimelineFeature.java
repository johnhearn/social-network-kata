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
public class TimelineFeature {

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
        
        dispatcher = new Dispatcher(input, new Processor(new SocialNetwork(new MessageRepository(), new MessageFactory(clock), new MessagePrinter(output, defaultMessageFormatter, new WallMessageFormatter(defaultMessageFormatter)), new FriendRepository())));
    }

    @Test
    public void
    post_messages_and_read_timeline() {
        // given
        when(clock.now())
                .thenReturn(LocalTime.of(9, 30))
                .thenReturn(LocalTime.of(9, 31))
                .thenReturn(LocalTime.of(9, 32));

        when(input.readln())
                .thenReturn("Bob -> Damn! We lost!")
                .thenReturn("Bob -> Good game though.")
                .thenReturn("Bob")
                .thenReturn(null);

        // when
        dispatcher.start();

        // then
        verify(output).println("Good game though. (1 minute ago)");
        verify(output).println("Damn! We lost! (2 minutes ago)");
    }
}
