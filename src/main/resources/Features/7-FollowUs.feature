@Regression
  @FollowUs
Feature: Follow us links open corresponding urls
  Scenario: user uses facebook follow us option
    When user navigates to homepage to try follow us option
    And user clicks on facebook logo under follow us label
    Then a new tab directing to facebook page should be opened