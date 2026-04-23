Feature: Wikipedia Search

  Scenario: Search keyword from Excel
    Given user is on Wikipedia home page
    When user searches for a keyword from Excel
    Then results page should be displayed