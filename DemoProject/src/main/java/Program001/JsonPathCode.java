package Program001;

import io.restassured.path.json.JsonPath;

public class JsonPathCode {
	
	public static JsonPath jsonParser(String jsonString) {
		return new JsonPath(jsonString);
	}
}
