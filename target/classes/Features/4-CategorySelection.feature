@Regression
  @SelectCategory
Feature: user could hover and select different category
  Scenario: user select Apparel --> Shoes
    When user navigates to home page to select category
    And hovers to Apparel category
    And clicks on Shoes from Apparel menu
    Then user should be directed to Shoes page