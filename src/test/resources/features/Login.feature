Feature: Login Functionality

  Scenario: Successful login with valid credentials
    Given User navigates to login page
    When User enters valid credentials from test data
    And User clicks login button
    Then User should see dashboard

  Scenario: Failed login with invalid credentials
    Given User navigates to login page
    When User enters "invalid@example.com" and "WrongPassword"
    And User clicks login button
    Then User should see error message
