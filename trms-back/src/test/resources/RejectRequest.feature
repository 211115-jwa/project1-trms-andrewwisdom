Feature: Reject pending TRMS request 

Scenario: 
	Given the user is on the request review page
	When the user rejects a request 
	Then confirm that the request was rejected and update the database
	
Scenario:
	Given the user is on the request review page
	When the user rejects a request with a comment
	Then confirm that the request was rejected and the comment was posted

