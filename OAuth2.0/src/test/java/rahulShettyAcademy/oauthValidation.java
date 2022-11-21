package rahulShettyAcademy;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.tools.javac.code.Attribute.Array;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class oauthValidation {

	@Test
	public void validation() throws InterruptedException {

//		https://rahulshettyacademy.com/getCourse.php?code=4%2F0ARtbsJrvRyFMXpR4GdLvb_nk6BrrtswJTGIXSR6BQe-X0S-HY1s5DucwpA4MRvoB2kkxxA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0ARtbsJrS6mIu6MJLe4CwZwZAOz47MTSMJAK_E6JXj8-g_ylJ3ZldF1ud3V0z-sHdhjt0_Q&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
//		
//		Google Authecation is stop the company so will have manually perform 

//		System.setProperty("webdriver.chrome.driver", "C:\\Maven\\WebDriver\\chromedriver.exe");
//		WebDriver driver=new ChromeDriver();
//		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email"
//				+ "&auth_uri=https://accounts.google.com/o/oauth2/v2/auth"
//				+ "&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com"
//				+ "&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
//		
//		Thread.sleep(3000);
//		driver.findElement(By.className("whsOnd zHQkBf")).sendKeys("rajatan98@gmail.com", Keys.ENTER);
//		Thread.sleep(1000);
//		driver.findElement(By.)
//		String url=driver.getCurrentUrl();
//		System.out.println("URL: "+url);

		/*
		 * String partialURL=url.split("code=")[1]; String
		 * code=partialURL.split("&scope")[0]; System.out.println("Code: "+code);
		 * 
		 * String response=given().urlEncodingEnabled(false) .queryParams("code",
		 * "4%2F0ARtbsJrS6mIu6MJLe4CwZwZAOz47MTSMJAK_E6JXj8-g_ylJ3ZldF1ud3V0z-sHdhjt0_Q")
		 * .queryParams("client_id",
		 * "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		 * .queryParams("grant_type", "authorization_code")
		 * .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W") //
		 * .queryParams("state", "verifyfjdss") .queryParams("redirect_uri",
		 * "https://rahulshettyacademy.com/getCourse.php") .when().log().all()
		 * .post("https://www.googleapis.com/oauth2/v4/token").asString();
		 * 
		 * JsonPath jsonPath=new JsonPath(response); String
		 * accessToken=jsonPath.get("access_token");
		 * System.out.println("Access Token: "+accessToken);
		 */

//		String response2=given().contentType("application/json")
//				.queryParams("access_token", accessToken)
//				.expect().defaultParser(Parser.JSON)
//				.when().get("https://rahulshettyacademy.com/getCourse.php").asString();
//		System.out.println("Response 2: "+response2);

		// JsonPath to Parse the Json (i.e., to deserialize the json response

//		For time being will store the accessToken in to one variable
		String accessToken = "ya29.a0Aa4xrXPGWo8WgVN9Lk_1gGQwz-0lCLuwabHlhsPhNP_DaKCsizOsSvky6sgtKyt7KXjqitSS07r2ad-grjs4D_zKeE9Ca89liV9HaE2aBMcAdtUsDf9ToZg1p-u5Txnps7_WtTlcOJQcp_1cz_k8DC9-FluoCAaCgYKATASARESFQEjDvL99NiNLRcQtN6YuTodOr1QqQ0165";
		GetCourse gc = given().contentType("application/json").queryParams("access_token", accessToken).expect()
				.defaultParser(Parser.JSON).when().get("https://rahulshettyacademy.com/getCourse.php")
				.as(GetCourse.class);

		System.out.println("Instructor: " + gc.getInstructor());
		System.out.println("URL: " + gc.getUrl());
		System.out.println("Services: " + gc.getServices());
		System.out.println("Expertise: " + gc.getExpertise());
		System.out.println("LinkedIn: " + gc.getLinkedIn());
		System.out.println("Courses of Api: " + gc.getCourses().getApi().get(0).getCourseTitle());

//		Task 001  print all the WebAutomation CourseTiles
		System.out.println("Courses of WebAutomation: " + gc.getCourses().getApi().get(0).getCourseTitle());
		List<WebAutomation> webAuto = gc.getCourses().getWebAutomation();

		for (WebAutomation title : webAuto) {
			System.out.println(title.getCourseTitle());
		}

//		Task 002 Compare and check the json response WebAutomation courses matches the out database 
		String[] courses = { "Selenium Webdriver Java", "Cypress", "Protractor" };
		ArrayList<String> actualCourses=new ArrayList<String>();
		List<WebAutomation> webAutomate = gc.getCourses().getWebAutomation();

		for (WebAutomation title : webAutomate) {
			actualCourses.add(title.getCourseTitle());
		}
		System.out.println(actualCourses);
		
		List<String> expected= Arrays.asList(courses);
		
		Assert.assertEquals(actualCourses, expected);
//		Check the courses of API 
		String[] apiCourse= {"Rest Assured Automation using Java", "SoapUI Webservices testing"};
		compare(apiCourse, gc.getCourses().getApi());
		
		String[] mobileCourse= {"Appium-Mobile Automation using Java"};
		compareMobile(mobileCourse, gc.getCourses().getMobile());
		
		
	}
	
	public void compare(String[] apiCourse, List<Api> list) {
		ArrayList<String> actualCourses=new ArrayList<String>();

		for (Api title : list) {
			actualCourses.add(title.getCourseTitle());
		}
		System.out.println(actualCourses);
		
		List<String> expected= Arrays.asList(apiCourse);
		
		Assert.assertEquals(actualCourses, expected);
	}
	public void compareMobile(String[] mobileCourse, List<Mobile> list) {
		ArrayList<String> actualCourses=new ArrayList<String>();

		for (Mobile title : list) {
			actualCourses.add(title.getCourseTitle());
		}
		System.out.println(actualCourses);
		
		List<String> expected= Arrays.asList(mobileCourse);
		
		Assert.assertEquals(actualCourses, expected);
	}
	
}
