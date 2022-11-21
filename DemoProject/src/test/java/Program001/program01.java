package Program001;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.apache.http.util.Asserts;


public class program01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String responseBack=given().log().all().queryParam("key", "qaclick123").header("Context-type", "application/json").body(PayLoad.resourse())
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		System.out.println("Response of the RSA maps API: "+responseBack);
		
//		Now will store the json resonse into one variable of String type and this help in further code if we want to access any of the 
//		field and perform some task.
//		To the above we need to Parse the Json body in order to access eacg field of it otherwise it will be there like one String, to parse we need JsonPath library
		
//		JsonPath js=new JsonPath(responseBack);
		JsonPath js=JsonPathCode.jsonParser(responseBack);

//		Suppose we want to access place_id of the json body
		String placeID=js.getString("place_id");
		System.out.println("Place ID: "+placeID);
		
		System.out.println("*************Put method*****************");
		
//		will update the address to already added information, to do this will use PUT HTTP method
		String toUpdateAdd="70 winter walk, USA";
		
		given().log().all().queryParam("key", "qaclick123").header("Context-type", "application/json").body("{\r\n"
				+ "\"place_id\":\""+placeID+"\",\r\n"
				+ "\"address\":\""+toUpdateAdd+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "").
		when().put("maps/api/place/update/json").
		then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		
		System.out.println("***************Get Method***********************");
		String getAddressResponse=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID)
		.when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).body("address", equalTo(toUpdateAdd)).extract().response().asString();
		
		System.out.println("Updated Address response: "+getAddressResponse);
		
//		JsonPath js1=new JsonPath(getAddressResponse);
		JsonPath js1=JsonPathCode.jsonParser(getAddressResponse);
		String addressV=js1.getString("address");
		System.out.println("Updated address: "+addressV);
		
		Assert.assertEquals(addressV, toUpdateAdd);
	}

}
