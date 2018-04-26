package com.codurance.kata.socialnetwork.acceptance;

import com.codurance.kata.socialnetwork.*;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;

import java.time.LocalTime;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SocialNetworkSteps {

    @Mock
    private Input input;
    @Mock
    private Output output;
    @Mock
    private Clock clock;

    private Dispatcher dispatcher;
    private OngoingStubbing<LocalTime> whenClockNow;
    private OngoingStubbing<String> whenInputReadln;

    @Before
    public void setUp() {
        initMocks(this);
        whenClockNow = null;
        whenInputReadln = null;

        MessageFormatter defaultMessageFormatter = new DefaultMessageFormatter(clock);
        dispatcher = new Dispatcher(input, new Processor(new Network(new MessageRepository(), new MessageFactory(clock), new MessagePrinter(output, defaultMessageFormatter, new WallMessageFormatter(defaultMessageFormatter)), new FriendRepository())));
    }

    @Given("^\"([^\"]*)\" has been typed at \"([^\"]*)\"$")
    public void has_been_typed_at(String command, String time) {
        prepareCommand(command, time);
    }


    @Given("^\"([^\"]*)\" has been typed$")
    public void has_been_typed(String command) {
        prepareCommand(command);
    }

    @When("^I type command \"([^\"]*)\" at \"([^\"]*)\"$")
    public void when_i_type_command_at_given_time(String command, String time) {
        prepareCommand(command, time);
        prepareCommand(null);
        dispatcher.start();
    }

    @Then("^\"([^\"]*)\" should be printed$")
    public void message_should_be_printed(String message) {
        verify(output).println(message);
    }

    private void prepareCommand(String command, String time) {
        if (whenClockNow == null) {
            whenClockNow = when(clock.now());
        }
        whenClockNow = whenClockNow.thenReturn(LocalTime.parse(time));
        prepareCommand(command);
    }

    private void prepareCommand(String command) {
        if (whenInputReadln == null) {
            whenInputReadln = when(input.readln());
        }
        whenInputReadln = whenInputReadln.thenReturn(command);
    }

}
