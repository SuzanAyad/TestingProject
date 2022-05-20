@Regression
  @Slider
Feature: Slider is clickable
  Scenario: user can click on slider
    When user navigates to home page
    And hovers with mouse to slider wrapper
    And clicks on the slider
    Then user could be navigated to item page

