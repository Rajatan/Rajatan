package JIRA_RestAssured;

import org.testng.annotations.Test;


import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.io.File;

import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class JiraTest001 {
	
	@Test
	public void loginComment() {
		RestAssured.baseURI="http://localhost:9090";
//		Creating a session by logging to Jira
		SessionFilter session=new SessionFilter();
		System.out.println("Session to be created..");
		String response=given().header("Content-type", "application/json").body("{ \"username\": \"rajatan98\", \"password\": \"Rajat@24\" }")
		.filter(session).log().all().when().post("/rest/auth/1/session")
		.then().log().all().extract().response().asString();
		System.out.println("Session created successfully...");
		
		System.out.println("\n\n\n");
//		Will add comment to existing issue and its ID is : 10101
		System.out.println("Comment to be added....");
		
		String expectedMessage = "Hey,  My laptop is not working";
		String addedCommentResponse=given().log().all().pathParam("key", "10101").header("Content-type","application/json").body("{\r\n"
				+ "    \"body\": \""+expectedMessage+"\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session).when().post("/rest/api/2/issue/{key}/comment").
		then().log().all().assertThat().statusCode(201).extract().response().asString();
		
		JsonPath js=new JsonPath(addedCommentResponse);
		String commentID=js.getString("id");
		System.out.println("Generated comment id; "+commentID);
		
		
		System.out.println("Comment added successfully.....");
		
		
//		Adding attachment to the 10101 issue
/*		given().pathParam("key", "10101").header("X-Atlassian-Token","no-check").log().all().header("content-type", "multipart/form-data")
		.multiPart("file", new File("E:\\Rajat Selenium\\RestAPI_projects\\DemoProject\\src\\main\\java\\JIRA_RestAssured\\Issue.txt")).filter(session)
		.when().post("rest/api/2/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200);
		*/
		
		
//		Get issue information of added comment  /rest/api/2/issue/{key}
		String issueDetails=given().filter(session).log().all().pathParam("key", "10101").queryParam("fields", "comment")
				.when().get("/rest/api/2/issue/{key}")
				.then().log().all().extract().response().asString();
		System.out.println(issueDetails);
		
		JsonPath js1=new JsonPath(issueDetails);
		int commentSize=js1.getInt("fields.comment.comments.size()");
		
		for(int i=0;i<commentSize;i++) {
			String commentIDissue=js1.get("fields.comment.comments["+i+"].id").toString();
			System.out.println("CommentIDIssue: "+commentIDissue);
			if(commentID.equalsIgnoreCase(commentIDissue)) {
				String message=js1.get("fields.comment.comments["+i+"].body").toString();
				System.out.println(message);
				Assert.assertEquals(message, expectedMessage);
				break;
				
			}
		}
		
		
		
		
	}

}




