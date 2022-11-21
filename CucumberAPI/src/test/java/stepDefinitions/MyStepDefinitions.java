package stepDefinitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIresources;
import resources.AddPlaceTestDataBuild;
import resources.specBuilder;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
public class MyStepDefinitions extends specBuilder {
//
	RequestSpecification req;
	Response res;
	 static String place_id;
	
	AddPlaceTestDataBuild addPlace=new AddPlaceTestDataBuild();
	
	@Given("Add place payload {string} {string} {string}")
	public void add_place_payload(String name, String langauge, String address) throws IOException {
		System.out.println("I'm in GIven");
		
//		RestAssured.baseURI="https://rahulshettyacademy.com";
//		Response res=given().queryParam("key", "qaclick123").body(addPlace).log().all()

		 req = given().spec(genericBuilder()).contentType(ContentType.JSON).body(addPlace.addPlaceBuild(name, langauge, address));
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpMethod) {
//		ResponseSpecBuilder
//		ResponseSpecification response = new ResponseSpecBuilder().expectStatusCode(200)
//				.expectContentType(ContentType.JSON).build();
		APIresources apiResource= APIresources.valueOf(resource);
		if(httpMethod.equalsIgnoreCase("POST")) {
			res = req.when().post(apiResource.getResource());
		}else if(httpMethod.equalsIgnoreCase("GET")) {
			res = req.when().get(apiResource.getResource());
		}else if(httpMethod.equalsIgnoreCase("DELETE")) {
			res = req.when().delete(apiResource.getResource());
		}else {
			res = req.when().put(apiResource.getResource());
		}
		

		
	}

	@Then("The API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		
//		then().spec(response).extract()		.response()
		Assert.assertEquals(res.getStatusCode(), 200);
		
		
	}

	@And("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String ExpectedValue) {
		
		
		
		String status=getJsonPath(res, keyValue);
		Assert.assertEquals(getJsonPath(res, keyValue), ExpectedValue);
		System.out.println("status: "+status);
	}
	
	

	@And("Validate the {string} with {string}")
	public void validate_the_with(String expectedName, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		place_id=getJsonPath(res, "place_id");
		System.out.println(place_id);
		req= given().spec(genericBuilder()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String actualName=getJsonPath(res, "name");
		System.out.println("Name: "+actualName);
		Assert.assertEquals(actualName, expectedName);
	
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println(place_id+ " deleted successfully.");
		req=given().spec(genericBuilder())
				.body(
				"{\r\n"
				+ "    \"place_id\":\""+place_id+"\"\r\n"
				+ "}");
	}

	
	
}