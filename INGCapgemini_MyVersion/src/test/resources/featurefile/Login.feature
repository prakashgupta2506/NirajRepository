@Login
Feature:  check the login page scenario


@loginscenario1
Scenario: login to gmail...simple scenario with one set of argument
Given user enter the  url "http://www.gmail.com"
When user enter the username "callnirajgupta" and password "abc"
Then verify that user is able to login into "Gmail"