Feature: Register Provider
  In order to start sharing my data
  As a provider
  I want to register myself and get an account

  Scenario: Register new provider
    Given There is no registered provider with username "provider"
    And I'm not logged in
    When I register a new provider with username "provider", email "provider@example.com" and password "password"
    Then The response code is 201
    And It has been created a provider with username "provider" and email "provider@example.com", the password is not returned
    And I can login with username "provider" and password "password"

  Scenario: Register existing provider username
    Given There is a registered provider with username "provider" and password "existing" and email "existing@example.com"
    And I'm not logged in
    When I register a new provider with username "provider", email "provider@example.com" and password "password"
    Then The response code is 409
    And I cannot login with username "provider" and password "password"

  Scenario: Register provider when already authenticated
    Given I login as "demo" with password "password"
    When I register a new provider with username "provider", email "provider@example.com" and password "password"
    Then The response code is 403
    And It has not been created a provider with username "provider"