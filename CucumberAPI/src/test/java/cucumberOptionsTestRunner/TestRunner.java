package cucumberOptionsTestRunner;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;

import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
//E:\\Rajat Selenium\\RestAPI_projects\\CucumberAPI\\src\\test\\java\\features\\validateAPI.feature
@CucumberOptions(features="src/test/java/features",
				glue= "stepDefinitions",
				plugin="json:target/jsonReports/cucumber-report.json")//, tags="@deletePlace")
public class TestRunner {

}
