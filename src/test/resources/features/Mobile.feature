@test_tat
Feature: Login to mobile app

  Background:
    Given I load property file "/locators/android.properties" into global property map
    And I load environment property file "uat" into global property map for lbu "sg"
    And I load csv file "/locators/sg/en_android.csv" with separator "=" into global property map

  Scenario: Login to App using TAP step without glue code
    Given I get device platform into variable "DEVICE_PLATFORM"
    When I launch "${DEVICE_PLATFORM}" mobile application
    And I enter text "${login.id.global}" on element "${text.field.email}"
    And I clear text on element "${text.field.email}"
    And I enter text "${login.id.global}" on element "${text.field.email}"
    And I swipe mobile down by duration "6000"
    And I swipe mobile down till element displayed "${button.submit}"
    And I click on element "${button.submit}"
    And I sleep for 5 sec
    Then I verify element "${text.home.page}" is displayed

    Scenario: Example of how to write custom gluecode
      Given I get device platform into variable "DEVICE_PLATFORM"
      When I launch "${DEVICE_PLATFORM}" mobile application
      And I login to app using email "${login.id.global}"