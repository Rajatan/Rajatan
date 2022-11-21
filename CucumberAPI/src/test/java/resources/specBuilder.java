package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import org.codehaus.groovy.runtime.callsite.PogoGetPropertySite;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class specBuilder {
	static RequestSpecification req;
	
// Lets create a generalised method to RequestSpecBuilder because this is repeatative step for all HTTP methods. 
	public RequestSpecification genericBuilder() throws IOException {
		
// Lets log all the execution steps using addFiler method to spacBuilder for Request and Response and this replaces logs of logging file
// every time if we perform Parameterize testing API so to resolve this will use if else over it and make the req static which avoids 
// creating new instance for second parameter.
		if(req==null) {
			PrintStream log= new PrintStream(new FileOutputStream("logging.log"));
		req= new RequestSpecBuilder().setBaseUri(getPropertiesData("baseUri"))
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
		return req;
		}
		return req;
	}
	
//	Lets create method to get the properties file data by passing the required data as key to it
	public  String getPropertiesData(String key) throws IOException {
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\data.properties");
		prop.load(fis);
		String data=prop.getProperty(key);
		return data;
		
	}
	
	
//	We are parsing the response to Json
	public  String getJsonPath(Response res, String keyValue) {
		// TODO Auto-generated method stub
		String responseString = res.asString();
		JsonPath js=new JsonPath(responseString);
		return js.get(keyValue).toString();
	}

}
