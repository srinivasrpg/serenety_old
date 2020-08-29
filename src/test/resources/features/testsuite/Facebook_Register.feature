Feature: Facebook Register
  As a user
  I want to register on Facebook
  So that I can be part of social media

  @facebook
  Scenario: Visitor can register on the Face Book Page
    Given User navigates to the page FaceBook Home Page
    When User clicks on Create New Account Button
    And the User can see the Registration Form is displayed
    Then User enters the values into the fields of Registration Form:
      | FormFieldSelector | Value              |
      | First Name        | Srinivas           |
      | Last Name         | Reddy              |
      | EmailID           | test.test@test.com |
      | Password          | test123            |
