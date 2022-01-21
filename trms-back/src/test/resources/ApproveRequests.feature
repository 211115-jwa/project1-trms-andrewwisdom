Feature: Approve TRMS Requests

Scenario: A TRMS request is approved
	Given A TRMS request is pending approval
	When The request is approved
	Then The reuqest is set to the next status