@Login
Feature: Check login related scenarios

@LoginAndLogout
Scenario: login to http://automationpractice.com/index.php..simple scenario with one set of argument--Admin user

Given User "admin@p.com" with password "Password-11" login into the application with correct credentials
Given User successfully log out of the application after clicking on log out button



