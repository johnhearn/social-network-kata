package acceptance;

import com.codurance.kata.socialnetwork.Main;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class PostingSteps {

    private InputStream originalSystemIn;
    private PrintStream originalSystemOut;

    private PrintStream consoleInput;
    private ByteArrayOutputStream consoleOutput;

    @Before
    public void setUp() throws IOException {
        originalSystemIn = System.in;
        originalSystemOut = System.out;

        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        consoleInput = new PrintStream(pipedOutputStream);
        System.setIn(new PipedInputStream(pipedOutputStream));

        consoleOutput = new ByteArrayOutputStream(2048);
        System.setOut(new PrintStream(consoleOutput));

        new Thread(Main::main).start();
    }

    @After
    public void tearDown() {
        consoleInput.write(0x3);
        consoleInput.close();

        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }

    @Given("^I type command \"([^\"]*)\"$")
    public void i_type_command(String command) {
        consoleInput.println(command);
        consoleInput.flush();
    }

    @Then("^\"([^\"]*)\" should be printed$")
    public void message_should_be_printed(String message) throws InterruptedException {
        Thread.sleep(500);
        assertEquals(message + System.lineSeparator(), consoleOutput.toString());
    }
}