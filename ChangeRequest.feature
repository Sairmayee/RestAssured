Feature: ServiceNow ChangeRequests API

Scenario: Test to Get all change requests API
Given Setup the Base URI for Change Request API
And Setup the authentication for CR with valid credential
When Place the get request for CR
Then Validate the Status Code for CR is 200

Scenario Outline: Test to create a Change Request
Given Setup the Base URI for Change Request API
And Setup the authentication for CR with valid credential
Given Pass the data for CR from <FileName> file
When Place the post request for CR
Then Validation of Status Code for CR creation as <StatusCode>

#When Search for existing CR to delete
#Then Deletion of existing CR

Examples:
|FileName|StatusCode|
|'dataCR.json'|201|

Scenario Outline: Test to update a Change Request
Given Setup the Base URI for Change Request API 
And Setup the authentication for CR with valid credential
Given Pass the data for CR from <FileName> file
When Place the Put request of existing CR
Then Validate the Status Code for CR is 200
When Place a delete request for same CR
Then Validate the Status Code for CR is 204
Then Search for same CR and confirm


Examples:
|FileName|
|'datatoUpdate.json'|


