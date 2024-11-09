#language: en
# Author: Check-in Team

  Feature: Handle errors when retrieving product details

  Scenario: Trying to get product details with an incorrect endpoint path
    Given I am connected as a user.
    When I send a GET request to retrieve all the products to the wrong route
    Then the API should respond with a 404 status code indicating that the resource was not found