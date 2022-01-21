Feature: Get Pending TRMS Requests

Scenario: An employee with pending reuqests is entered
	Given A user sends a request to view an employee's pending TRMS requests
	When the user enters an employee with pending TRMS requests
	Then the employee's pending TRMS requets are returned
	
Scenario: An employee without pending requests is entered
 Given A user sends a request to view an employee's pending TRMS requests
 When the user enters an employee without pending TRMS requests
 Then The appropriate error message is shown