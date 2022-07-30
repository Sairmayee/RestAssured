package steps;

import java.io.File;

import org.hamcrest.Matchers;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class StepDefinition_ChangeRequest {
	public static Response response;
	public static RequestSpecification request;
	public static String sys_id;
	
	@Given("Setup the Base URI for Change Request API")
	public void setupBaseURI_CR() {
		RestAssured.baseURI = "https://dev119043.service-now.com/api/now/table/change_request";
	}
	
	@And("Setup the authentication for CR with valid credential")
	public void setupAuthentication_CR(){
		RestAssured.authentication = RestAssured.basic("admin", "lg-6jBMl5ZU@");
	}
	
	@Given("Pass the data for CR from {string} file")
	public void passBodyData_CR(String FileName) {
		File file = new File("./data/" + FileName);
		request = RestAssured
				.given()
				.log()
				.all()
				.contentType(ContentType.JSON)
				.body(file);
	}
	@When("Place the get request for CR")
	public void placeGetRequest_CR(){
		response = RestAssured.given().log().all().get();
	}
	@When("Place the post request for CR")
	public void placePostRequest_CR() {
		response = request.post();
	}
	@Then("Validate the Status Code for CR is 200")
	public void verifyStatusCode(){
		response.then().assertThat().statusCode(200);
		
	}
	@Then("Validation of Status Code for CR creation as {int}")
	public void verifyStatusCodePOST(int StatusCode) {
		response.then().assertThat().statusCode(StatusCode);
		response.prettyPrint();
		
		sys_id = response.jsonPath().getString("result.sys_id");
		System.out.println(sys_id);
	}
	
	@When("Place the Put request of existing CR")
	public void putRequest() {
		response = request.put(sys_id);
	}
	@When("Place a delete request for same CR")
	public void deleteRequest() {
		response = request.delete(sys_id);
	}
	@Then("Search for same CR and confirm")
	public void searchSameCR(){
		response = RestAssured.given().log().all().get(sys_id);
		response.prettyPrint();
		response.then().assertThat().body("status", 
				Matchers.equalTo("failure"));
	}
	@Then("Validate the Status Code for CR is 204")
	
	public void verifyStatusCodeDELETE() {
		response.then().assertThat().statusCode(204);
	}
}
