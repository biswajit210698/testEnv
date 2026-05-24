Feature: Login Functionality
  As a user
  I want to login to the application
  So that I can access the dashboard

  Background:
    Given User navigates to login page

  Scenario: Successful login with valid credentials
    When User enters valid credentials from test data
    And User clicks login button
    Then User should see dashboard

  Scenario: Failed login with invalid credentials
    When User enters "invalid@example.com" and "WrongPassword"
    And User clicks login button
    Then User should see error message

  Scenario: Login with empty credentials
    When User enters "" and ""
    And User clicks login button
    Then User should see error message