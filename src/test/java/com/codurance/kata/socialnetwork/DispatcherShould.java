package com.codurance.kata.socialnetwork;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DispatcherShould {

    @Mock
    private Input input;
    @Mock
    private Processor processor;

    private Dispatcher dispatcher;

    @Before
    public void setUp() {
        dispatcher = new Dispatcher(input, processor);
    }

    @Test
    public void
    dispatch_multiple_commands_until_receiving_null() {
        when(input.readln())
                .thenReturn("foo")
                .thenReturn("bar")
                .thenReturn(null);

        dispatcher.start();

        verify(processor).process("foo");
        verify(processor).process("bar");
    }
}
