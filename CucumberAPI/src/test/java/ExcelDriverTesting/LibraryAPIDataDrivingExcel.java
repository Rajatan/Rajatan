package ExcelDriverTesting;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.specBuilder;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class LibraryAPIDataDrivingExcel {
	specBuilder builder=new specBuilder();
	@Test
	public void addBook() throws IOException {
		
		LibraryAPIEXCELDATA excelData=new LibraryAPIEXCELDATA();
		ArrayList<String> arrayData=excelData.getData("testCases", "RestAssured");
//		Instead of passing Json payload as String to body we can pass it through HashMap class
//		to do so we need to create HashMap and put the values that it take for payload and pass it in body
		HashMap<String, Object> payLoad=new HashMap<String, Object>();
		payLoad.put("name", arrayData.get(1));
		payLoad.put("isbn", arrayData.get(2));
		payLoad.put("aisle", arrayData.get(3));
		payLoad.put("author", arrayData.get(4));
		
//		payLoad.put("name", "The Complete Reference");
//		payLoad.put("isbn", "KAR");
//		payLoad.put("aisle", "0018");
//		payLoad.put("author", "Steven");
//		
		
//		If there is any complex Json like nested Json like in Map API payload of location 
//		{ "location" : {
//			"lat": 325.2,
//			"lng": 25.5
//			}
//		}		
//		then we will need to create one more HashMap and give its object to main HashMaps Value 
//		HashMap<String, Object> payLoad2=new HashMap<String, Object>();
//		payLoad.put("location", payLoad2);
		
		
		
//		"{\r\n"
//		+ "\r\n"
//		+ "\"name\":\"Learn Python Automation\",\r\n"
//		+ "\"isbn\":\"Kumar\",\r\n"
//		+ "\"aisle\":\"0017\",\r\n"
//		+ "\"author\":\"John foe\"\r\n"
//		+ "}\r\n"
//		+ ""
		
		RestAssured.baseURI="http://216.10.245.166";
		RequestSpecification req=given().log().all().contentType(ContentType.JSON).body(payLoad);
		Response res=req.when().post("Library/Addbook.php");
//		res.then().log().all().assertThat().statusCode(200).extract();
		
//		JsonPath js=new JsonPath(res);
//		String id=js.getString("ID");
//		System.out.println("ID: "+id.toString());
		String id=builder.getJsonPath(res, "ID");
		System.out.println("ID: "+id);
		
		
	}

}
