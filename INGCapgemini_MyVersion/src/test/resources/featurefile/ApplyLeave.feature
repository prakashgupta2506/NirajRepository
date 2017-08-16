@leave
Feature: Apply and cancel the leave

@ApplyLeave1 @smoke @integration @regression
Scenario: Apply leave by ESS user 1
Given user "ESS_User1" is logged into the application
When user apply leave for date from "2017-06-11" to "2017-06-11" with leave type as "FMLA US" with comments as "comments.."
Then verify that leave is applied successfully

@ApplyLeaveandCancelLeave @regression
Scenario: Apply leave by ESS user 2
Given user "ESS_User1" is logged into the application
When user apply leave for date from "2017-05-31" to "2017-05-31" with leave type as "FMLA US" with comments as "comments.."
Then verify that leave is applied successfully
When user cancels the leaves from "2017-05-31" to "2017-05-31"
Then verify cancellation is successful


@ApplyLeave_a
Scenario: Apply leave by ESS user 1 testing TESTING RE-RERUN SCENARIO
Given Testing the re-run scenario


