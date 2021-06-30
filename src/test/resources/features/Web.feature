@test_web
Feature: Login to web app

  Background:
    Given I load environment property file "uat" into global property map for lbu "sg"

  Scenario: Login to Web portal
    Given I launch browser application "http://www.imdb.com"
    Then I verify element "//h3[text()='Featured today']" is displayed
    And I get browser title into variable "BROWSER_TITLE"
    Then I verify expected values with actual values as below
      | ${BROWSER_TITLE} | ${app.title} |