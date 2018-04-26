Feature: Posting

  Scenario: posting and reading my messages
    Given I type command "Alice -> Hello!"
    When I type command "Alice"
    Then "Hello! (0 minutes ago)" should be printed