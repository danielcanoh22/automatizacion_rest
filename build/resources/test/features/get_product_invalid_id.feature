#language: en
# Author: Check-in Team

Feature: Retrieve a product by invalid ID

  Scenario: Attempt to retrieve a product with an invalid ID
    Given I am connected as a supplier (invalid id)
    When I send a GET request to retrieve a product with an invalid ID
    Then the API should respond with status code 200
    And the response should indicate that the product was not found
