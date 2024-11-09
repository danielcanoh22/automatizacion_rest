#language: en
Feature: As a supplier, I want to retrieve a product by its ID

  Scenario: Retrieve a product by ID
    Given I am connected as a supplier
    When I send a GET request to retrieve the product by its ID
    Then I should receive a response with status code 200
    And the product details should be returned