package steps;

import java.io.File;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class StepDefinition {
	public static Response response;
	public static RequestSpecification request;
	
	@Given("Setup the Base URI for incident API")
	public void setupBaseURI() {
		RestAssured.baseURI = "https://dev119043.service-now.com/api/now/table/incident";
	}
	@And("Setup the authentication with valid credential")
	public void setupAuthentication(){
		RestAssured.authentication = RestAssured.basic("admin", "lg-6jBMl5ZU@");
	}
	
	@Given("Pass the data from {string} file")
	public void passBodyData(String FileName) {
		File file = new File("./data/" + FileName);
		request = RestAssured
				.given()
				.log()
				.all()
				.contentType(ContentType.JSON)
				.body(file);
	}
	@When("Place the get request")
	public void placeGetRequest(){
		response = RestAssured.given().log().all().get();
	}
	@When("Place the post request")
	public void placePostRequest() {
		response = request.post();
	}
	@Then("Validate the Status Code is 200")
	public void verifyStatusCode(){
		response.then().assertThat().statusCode(200);
		
	}
	@Then("Validation of Status Code as {int}")
	public void verifyStatusCodePOST(int StatusCode) {
		response.then().assertThat().statusCode(StatusCode);
		response.prettyPrint();
	}
}
