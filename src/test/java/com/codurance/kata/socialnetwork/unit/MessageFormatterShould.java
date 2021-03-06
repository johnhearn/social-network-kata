package com.codurance.kata.socialnetwork.unit;

import com.codurance.kata.socialnetwork.Clock;
import com.codurance.kata.socialnetwork.Message;
import com.codurance.kata.socialnetwork.DefaultMessageFormatter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class MessageFormatterShould {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {LocalTime.of(9, 32), LocalTime.of(9, 30), "foo", "foo (2 minutes ago)"},
                {LocalTime.of(9, 31), LocalTime.of(9, 30), "foo", "foo (1 minute ago)"},
                {LocalTime.of(9, 30), LocalTime.of(9, 30), "foo", "foo (0 minutes ago)"},
        });
    }

    @Mock
    private Message message;
    @Mock
    private Clock clock;

    private DefaultMessageFormatter messageFormatter;

    private final LocalTime now;
    private final LocalTime messageTime;
    private final String messageText;
    private final String formattedMessage;

    public MessageFormatterShould(LocalTime now, LocalTime messageTime, String messageText, String formattedMessage) {
        this.now = now;
        this.messageTime = messageTime;
        this.messageText = messageText;
        this.formattedMessage = formattedMessage;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        messageFormatter = new DefaultMessageFormatter(clock);
    }

    @Test
    public void
    include_minutes_elapsed_from_post() {
        when(clock.now()).thenReturn(now);
        when(message.text()).thenReturn(messageText);
        when(message.time()).thenReturn(messageTime);

        assertThat(messageFormatter.format(message), is(formattedMessage));
    }
}
