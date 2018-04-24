package com.codurance.kata.socialnetwork;

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
    private Console console;
    @Mock
    private Clock clock;

    private Dispatcher dispatcher;

    @Before
    public void setUp() {
        dispatcher = new Dispatcher(console, new Processor(new Network(new MessageRepository(), new MessageFactory(clock), new MessagePrinter(console, new MessageFormatter(clock)))));
    }

    @Test
    public void
    post_messages_and_read_timeline() {
        // given
        when(clock.now())
                .thenReturn(LocalTime.of(9, 30))
                .thenReturn(LocalTime.of(9, 31))
                .thenReturn(LocalTime.of(9, 32));

        when(console.readln())
                .thenReturn("Bob -> Damn! We lost!")
                .thenReturn("Bob -> Good game though.")
                .thenReturn("Bob")
                .thenReturn(null);

        // when
        dispatcher.start();

        // then
        verify(console).println("Good game though. (1 minute ago)");
        verify(console).println("Damn! We lost! (2 minutes ago)");
    }
}
