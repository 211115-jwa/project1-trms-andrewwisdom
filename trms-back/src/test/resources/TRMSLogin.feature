Feature: Logging in to the TRMS App

Scenario: Logging in correctly
	Given the user is on the login page
	When the user enters the username and password
	Then the user should be taken to a different page
	
Scenario: Logging in incorrectly
	Given the user is on the login page
	When the user enters an incorrect username and password
	Then the appropiate error message should appear