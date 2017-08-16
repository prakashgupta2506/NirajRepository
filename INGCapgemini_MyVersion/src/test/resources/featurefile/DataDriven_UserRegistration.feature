@UserRegistration
Feature: User registration for multiple users


Scenario Outline: User registration
Given Admin user "linda.anderson" and password "linda.anderson" is logged into the application and User  fill the form fields "<first>" and "<second>" and "<third>"


Examples:
|first |second |third|user|
|abc1   |aDCS 1  |dsgfdgdf|dfgdfg| 
|abc2   |aDCS 2  |dsgfdgdf|dfgdfgf|
|abc3   |aDCS 3  |dsgfdgdf|dffgfdg|

# this is another way of testing or data driven code where u don't need when and then annotations......
#write everything in single annotation...