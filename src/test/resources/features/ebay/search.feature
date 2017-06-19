Feature: Search results in Ebay queries show expected data

  # If there were multiple scenarios I'd use a Background to pool the common tests

  Scenario: Bids are displayed on auctions in the search results
    Given browser is opened
    When I navigate to url https://www.ebay.co.uk/
    And I query for item iphone
    And I filter by auction
#    And I filter by lowest price
    Then each result displays the number of bids

