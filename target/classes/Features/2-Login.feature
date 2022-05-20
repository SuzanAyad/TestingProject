@Regression
  @Login
Feature: Login
  Scenario: Valid Login
    When user navigates to homepage url
    And user clicks on login tab
    And user enters "Hossam@Khaled.com" as an email and "123456" as a password
    And clicks on Login
    Then user could successfully logged in