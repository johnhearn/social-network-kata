Feature: Posting to timeline

  Scenario: posting and reading my messages
    Given "Alice -> Hello!" has been typed at "09:30"
    When I type command "Alice" at "09:32"
    Then "Hello! (2 minutes ago)" should be printed