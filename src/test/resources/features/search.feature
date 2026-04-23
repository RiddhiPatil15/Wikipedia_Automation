Feature: Wikipedia Search

  Scenario Outline: Search keyword
    Given user is on Wikipedia home page
    When user searches for "<keyword>"
    Then results should be validated and stored

  Examples:
    | keyword |
    | Selenium |
    | Java |
    | Automation |
    | Cucumber Framework |
    | TestNG |
    | RestAssured |
