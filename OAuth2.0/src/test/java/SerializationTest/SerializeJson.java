package SerializationTest;

import org.testng.annotations.Test;

import Serialization.AddPlace;
import Serialization.Location;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SerializeJson {
	
	@Test
	public void serialization() {
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
		
		
		
		
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		Response res=given().queryParam("key", "qaclick123").body(addPlace).log().all()
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response();
		
		String responseString=res.asString();
		System.out.println("Response from API: "+responseString);
	}
}
