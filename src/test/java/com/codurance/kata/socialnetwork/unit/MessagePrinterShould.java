package com.codurance.kata.socialnetwork.unit;

import com.codurance.kata.socialnetwork.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MessagePrinterShould {

    private static final String FORMATTED_MESSAGE = "foo";

    @Mock
    private Output output;
    @Mock
    private Message message;
    @Mock
    private DefaultMessageFormatter messageFormatter;
    @Mock
    private WallMessageFormatter wallMessageFormatter;

    private MessagePrinter printer;

    @Before
    public void setUp() {
        printer = new MessagePrinter(output, messageFormatter, wallMessageFormatter);
    }

    @Test
    public void
    print_formatted_message_to_console() {
        when(messageFormatter.format(message)).thenReturn(FORMATTED_MESSAGE);

        printer.print(message);

        verify(output).println(FORMATTED_MESSAGE);
    }

    @Test
    public void
    print_formatted_message_with_user_name_to_console() {
        when(wallMessageFormatter.format(message)).thenReturn(FORMATTED_MESSAGE);

        printer.printWithName(message);

        verify(output).println(FORMATTED_MESSAGE);
    }
}
