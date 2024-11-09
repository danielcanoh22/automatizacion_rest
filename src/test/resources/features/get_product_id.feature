#language: en
# Author: Check-in Team

Feature: As a supplier, I want to retrieve a product by its ID

  Scenario: Attempt to retrieve product details with an incorrect endpoint path
    Given the user wants to retrieve a product
    When the user sends a GET request to an incorrect endpoint path
    Then the API should respond with status code 404 indicating the resource was not found
