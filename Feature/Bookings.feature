Feature: All bookings.com scenarios

# 1
  @starverify
  Scenario Outline: Verify user can only view the result by selected property class
    Given I am on default locations search result screen
    When I select property class <stars>
    Then I verify system displays only <stars> hotels on search result
    Examples:
      | stars   |
      | 5 stars |
     # | 4 stars |
     # | 3 stars |

#2

  @DistList
  Scenario: List of all of hotel within 15 km radius from the center
    Given I am on default locations search result screen
    Then I verify system displays all hotels within "15" Km radius of airport


#3
  @hotelsSearch
  Scenario: verify given hotel is present in the list
    Given I am on default locations search result screen
    Then I verify "Hilton" is within radius