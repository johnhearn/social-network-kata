Feature: Posting to wall

  Scenario: displaying wall messages
    Given "Alice -> I love the weather today" has been typed at "09:25"
    Given "Charlie -> I'm in New York today! Anyone want to have a coffee?" has been typed at "09:28"
    Given "Charlie follows Alice" has been typed

    When I type command "Charlie wall" at "09:30"

    Then "Charlie - I'm in New York today! Anyone want to have a coffee? (2 minutes ago)" should be printed
    Then "Alice - I love the weather today (5 minutes ago)" should be printed
