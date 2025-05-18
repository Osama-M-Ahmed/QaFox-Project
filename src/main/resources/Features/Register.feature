Feature: Register Functionality
  As a user, I want to register on the website so that I can create an account.

  Background: Navigation to register page
    Given the user is on the home page
    When the user clicks on Account and selects Register

  Scenario Outline: Validate that user can register with valid credentials
    And user fills in the registration form with email "<firstname>", "<lastname>", "<email>","<telephone>", "<password>", "<confirm password>"
    Then the registration should be completed successfully
    And the user should see the confirmation message

    Examples:
      | firstname | lastname | email                    | telephone  | password | confirm password |
      | osama     | mohamed  | testyatler123@manual.com | 1104863740 | 12345678 | 12345678         |

