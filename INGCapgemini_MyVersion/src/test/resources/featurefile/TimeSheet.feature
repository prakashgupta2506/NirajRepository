@TimeSheet
Feature: TimeSheet related scenarios


@ClickTimeLink_a
Scenario: Click on Time link_a TESTING RE-RERUN SCENARIO
Given user "Supervisor_User1" is logged into the application
When user clicks on time link_a
Then verify timesheet page opens up

@ClickTimeLink_b
Scenario: Click on Time link_b TESTING RE-RERUN SCENARIO
Given user "Supervisor_User1" is logged into the application
When user clicks on time link_b
Then verify timesheet page opens up


@ClickTimeLink
Scenario: Click on Time link
Given user "Supervisor_User1" is logged into the application
When user clicks on time link
Then verify timesheet page opens up

