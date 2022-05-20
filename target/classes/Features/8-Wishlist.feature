@Regression
  @Wishlist
Feature: user could add products to wishlist
  Scenario: user could add HTC ONE M8 mobile to wishlist
    When user navigates to home page to add product to wishlist
    And clicks on heart symbol under HTC ONE M8 mobile phone
    Then Success message should be displayed
    And Product should be displayed in Wishlist page