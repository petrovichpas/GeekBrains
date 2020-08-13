Feature: Login

  Scenario Outline: Successful Login to the page and logout after
    Given I open web browser
    When I navigate to login page
    And I provide username as "<username>" and password as "<password>"
    And I click on login button
    And I click on Users
    And I click on Brands
    When I Open dropdown menu
    And I click logout button

    Examples:
      | username | password | name |
      | admin | admin | admin |