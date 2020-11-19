Feature: Wex Task

#
#  In this scenario, open the browser and navigate to Google's search page,
#  fill it with "Amazon Brasil" and the result with "www.amazon.com.br" should be clicked.
#
  @ID-0001 @TestSetup
  Scenario: Test Setup
    Given I open browser on Google
    When I search for "Amazon Brasil"
    Then I navigate to "www.amazon.com.br"
#
#  For this scenario, browse to Amazon's webpage, locate the Amazon's search bar, type "iPhone" and submit.
#  Count how many products are in the result list, how many of its items starts with "iPhone" name and how many contains the keyword "iPhone".
#  Then validate if the number of items which the name starts with iPhone is greater than 30% of total items and if the number of items that contains the keyword "iPhone" is greater than 80% of total items.
#
  @ID-0002 @80Percent
  Scenario: 80% Of Shown Products Should Be Exclusively The Searched Product
    Given I open browser on Amazon
    Then I search "iPhone"
    And I count the items which its name starts with "iPhone"
    And I make sure "30"% or more of Items found has its name starting with "iPhone"
    And I make sure "80"% or more of the result list contains the keyword "iPhone"
    And I count the total list of found products

#
#  Navigate to Amazon and search for "iPhone", compare and find what is the most expensive item which its name starts with "iPhone"
#  Then convert its value to US$ using an external API (https://exchangeratesapi.io/) and make sure the converted value is under US$2000
#
  @ID-0003 @USD2000
  Scenario: The Higher Price In The First Page Can't Be Greater Than U$2000
    Given I open browser on Amazon
    Then I search "iPhone"
    And I find the most expensive item which its name starts with "iPhone" and converted value must not be greater than US"2000"
#
#  Navigate to Amazon and search for "iPhone", compare and find what is the most expensive item which its name does not start with "iPhone"
#  Then make sure its value is cheaper than the cheapest item which its name starts with "iPhone".
#  During internal tests, this scenario was failing due to one item which does not have "iPhone" on its name and costs over than R$8,000
#
  @ID-0004 @CheapestItem
  Scenario: Products different than the searched product should be cheaper than the searched product
    Given I open browser on Amazon
    Then I search "iPhone"
    And I find items which its name doesn't start with "iPhone" and make sure the most expensive is cheaper than the cheapest item which its name starts with "iPhone"

