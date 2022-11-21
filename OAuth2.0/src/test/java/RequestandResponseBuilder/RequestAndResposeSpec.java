package RequestandResponseBuilder;

import org.testng.annotations.Test;

import Serialization.AddPlace;
import Serialization.Location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class RequestAndResposeSpec {
	
	@Test
	public void builder() {
		AddPlace addPlace=new AddPlace();
		
//		set all the setter methods to build the Json file
		addPlace.setAccuracy(40);
		addPlace.setAddress("Hn. 08, Patil Galli, Khanapur, Belagavi");
		addPlace.setLanguage("Kannada-KAR-IN");
		addPlace.setName("Suresh Ramesh Medar");
		addPlace.setPhone_number("(+91) 890 765 4321");
		
		List<String> type=new ArrayList<String>();
		type.add("Malprabha river");
		type.add("Forest");
		type.add("Wild animals can be seen here");
		
		addPlace.setTypes(type);
		
		Location location=new Location();
		location.setLat(-30.2564136);
		location.setLng(33.365248);
		
		addPlace.setLocation(location);
		
//		RequestSpecBuilder
		RequestSpecification reqV=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON).build();
		
//		ResponseSpecBuilder
		ResponseSpecification response=new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();/**/
		
		
//		RestAssured.baseURI="https://rahulshettyacademy.com";
//		Response res=given().queryParam("key", "qaclick123").body(addPlace).log().all()
		
		RequestSpecification req=given().spec(reqV).log().all()
				.body(addPlace);
		
		 
		 Response res=req.when().post("/maps/api/place/add/json")
		.then().log().all().spec(response).extract().response();
		
		String responseString=res.asString();
		System.out.println("Response from API: "+responseString);
		
		

		
	}
}
