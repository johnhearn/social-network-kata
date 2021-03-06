package com.codurance.kata.socialnetwork.unit;

import com.codurance.kata.socialnetwork.SocialNetwork;
import com.codurance.kata.socialnetwork.Processor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProcessorShould {

    @Mock
    private SocialNetwork network;

    private Processor processor;

    @Before
    public void setUp() {
        processor = new Processor(network);
    }

    @Test
    public void
    execute_post_message() {
        processor.process("Alice -> I love the weather today!");

        verify(network).postMessage("Alice", "I love the weather today!");
    }
    
    @Test
    public void
    execute_read_timeline() {
        processor.process("Alice");

        verify(network).readTimeline("Alice");
    }

    @Test
    public void
    execute_follow_user() {
        processor.process("Alice follows Bob");

        verify(network).follow("Alice", "Bob");
    }

    @Test
    public void
    execute_user_wall() {
        processor.process("Alice wall");

        verify(network).wall("Alice");
    }
}
