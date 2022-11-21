package Program001;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DynamicJson {
	
	@Test
	public void addBook() {
		RestAssured.baseURI="http://216.10.245.166";
		String response= given().log().all().header("Content-type", "application/json").body(PayLoad.addBookBody())
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		System.out.println("Response: "+response);
		
		JsonPath js=JsonPathCode.jsonParser(response);
		String ID=js.get("ID");
		System.out.println("ID of Book: "+ID);
	}
	
//	Dynamically build the Json PayLoad with external data input
	@Test
	public void dynamicAddBook()
	{
		RestAssured.baseURI="http://216.10.245.166";
		String response= given().log().all().header("Content-type", "application/json").body(PayLoad.dynamicaddBookBody("Dell", "1110"))
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		System.out.println("Response: "+response);
		
		JsonPath js=JsonPathCode.jsonParser(response);
		String ID=js.get("ID");
		System.out.println("ID of Book: "+ID);
		
		
//		Deleting the added book
		String deleteResponse= given().log().all().header("Content-type", "application/json").body(PayLoad.deletedBookBody("Dell1110"))
				.when().delete("Library/DeleteBook.php")
				.then().log().all().assertThat().statusCode(200)
				.extract().response().asString();
		JsonPath js1=JsonPathCode.jsonParser(deleteResponse);
		String msg=js1.get("msg");
		System.out.println("Message: "+msg);
		
	}
	
//		Parameterize the API Tests with multiple data sets.....
	@Test(dataProvider="Lidrary Data")
	public void dataProvidedAddBook(String isbn, String aisle)
	{
		RestAssured.baseURI="http://216.10.245.166";
		String response= given().log().all().header("Content-type", "application/json").body(PayLoad.dynamicaddBookBody(isbn, aisle))
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		System.out.println("Response: "+response);
		
		JsonPath js=JsonPathCode.jsonParser(response);
		String ID=js.get("ID");
		System.out.println("ID of Book: "+ID);
		
		
//		Deleting the added book
		String deleteResponse= given().log().all().header("Content-type", "application/json").body(PayLoad.deletedBookBody(isbn+aisle))
				.when().delete("Library/DeleteBook.php")
				.then().log().all().assertThat().statusCode(200)
				.extract().response().asString();
		JsonPath js1=JsonPathCode.jsonParser(deleteResponse);
		String msg=js1.get("msg");
		System.out.println("Message: "+msg);
		
	}
	
	@DataProvider(name="Lidrary Data")
	public Object[][] getData() {
		return new Object[][] {{"hp", "0001"}, {"lenovo", "0002"}, {"dell", "0003"}};
	}
	
//	Static JSON input from file 
//	How to send static json files(payload) directly into POST method of RestAssured?
	@Test
	public void staticFileJson() throws IOException {
		RestAssured.baseURI="http://216.10.245.166";
		String response= given().log().all().header("Content-type", "application/json").
				body(new String(Files.readAllBytes(Paths.get("E:\\Rajat Selenium\\RestAPI_projects\\DemoProject\\src\\main\\java\\Program001\\samsango.json"))))
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js=JsonPathCode.jsonParser(response);
		String id=js.get("ID");
		System.out.println("Static JSON file response: "+response);
		
//		Deleting the added book
		String deleteResponse= given().log().all().header("Content-type", "application/json").body(PayLoad.deletedBookBody(id))
				.when().delete("Library/DeleteBook.php")
				.then().log().all().assertThat().statusCode(200)
				.extract().response().asString();
		JsonPath js1=JsonPathCode.jsonParser(deleteResponse);
		String msg=js1.get("msg");
		System.out.println("Message: "+msg);
		
	}
	
	
	

}
