package rahulShettyAcademy;

import static  io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;


public class OAuthGetToken {
	
	public String accessToken;
	
	public void getToken() {
		String url="https://rahulshettyacademy.com/getCourse.php?code=4%2F0ARtbsJqYD4asfv3AfjdtSJ5gtXCl4GHz6LdMiOuZG8MbLkNj1Y_lNRqroHraqCNpl8d71A&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		
		
		String partialURL=url.split("code=")[1];
		String code=partialURL.split("&scope")[0];
		System.out.println("Code: "+code);
		
		String response=given().urlEncodingEnabled(false)
				.queryParams("code", "4%2F0ARtbsJrS6mIu6MJLe4CwZwZAOz47MTSMJAK_E6JXj8-g_ylJ3ZldF1ud3V0z-sHdhjt0_Q")
				.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("grant_type", "authorization_code")
				.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
//				.queryParams("state", "verifyfjdss")
				.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath jsonPath=new JsonPath(response);
		accessToken=jsonPath.get("access_token");
		System.out.println("Access Token: "+accessToken);
	}

}
