package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@deletePlace")
	public void deletePlaceApi() throws IOException {

		MyStepDefinitions step = new MyStepDefinitions();
		System.out.println(MyStepDefinitions.place_id);
		if (MyStepDefinitions.place_id == null) {
			step.add_place_payload("Mannu Bhau", "Bengali", "Kolkatta");
			step.user_calls_with_http_request("AddPlaceAPI", "POST");
			step.validate_the_with("Mannu Bhau", "GetPlaceAPI");
		}
	}

}
