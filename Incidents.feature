Feature: ServiceNow Incidents API

Scenario: Test to Get all incidents API
Given Setup the Base URI for incident API
And Setup the authentication with valid credential
When Place the get request
Then Validate the Status Code is 200

Scenario Outline: Test to create incident
Given Setup the Base URI for incident API
And Setup the authentication with valid credential
Given Pass the data from <FileName> file
When Place the post request
Then Validation of Status Code as <StatusCode>

Examples: 
|FileName|StatusCode|
|'data1.json'|201|
|'data2.json'|201|
