@Regression
  @ShoppingCart
Feature: user could add items to shopping cart
  Scenario: user adding HTC ONE M8 to shopping cart
    When user navigates to home page to add product to shopping cart
    And clicks on ADD TO CART under HTC ONE M8 mobile phone
    Then Success message should be displayed for cart
    And Product should be displayed in Shopping cart page