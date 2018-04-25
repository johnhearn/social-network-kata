package com.codurance.kata.socialnetwork.unit;

import com.codurance.kata.socialnetwork.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class WallMessageFormatterShould {

    @Mock
    private Message message;
    @Mock
    private MessageFormatter innerFormatter;

    private WallMessageFormatter messageFormatter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        messageFormatter = new WallMessageFormatter(innerFormatter);
    }

    @Test
    public void
    include_user_name() {
        when(innerFormatter.format(message)).thenReturn("foo");
        when(message.user()).thenReturn("alice");

        assertThat(messageFormatter.format(message), is("alice - foo"));
    }
}
