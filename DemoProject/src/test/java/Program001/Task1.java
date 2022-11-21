package Program001;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class Task1 {

	private JsonPath js = JsonPathCode.jsonParser(PayLoad.stabAPI());
	private int count = js.get("courses.size()");


	@Test
	public void printPurchaseAmount() {
		System.out.println("PurchaseAmount Printing: " + js.getInt("dashboard.purchaseAmount"));
	}

	@Test
	public void printTitleOfFirstCourse() {
		System.out.println("Title of First Course printing: " + js.get("courses[0].title"));
	}

	@Test
	public void printAllCoursesTitlePrice() {
		for (int i = 0; i < count; i++) {
			System.out.println("Title of course of "+i+": "+js.get("courses["+i+"].title"));
			System.out.println("Price of course of "+i+": "+js.getInt("courses["+i+"].price"));

		}
	}

	@Test
	public void copiesOfRPACourse() {
		for (int i = 0; i < count; i++) {
			String title=js.get("courses["+i+"].title");
//			System.out.println(title);
			if(title.equalsIgnoreCase("RPA")) {
				System.out.println("Copies of RPA Course: "+js.getInt("courses["+i+"].copies"));
				break;
			}

		}
	}
	
	
	@Test
	public void purchaseAmountValidation() {

		int sum = 0;
		for (int i = 0; i < count; i++) {
			int price = js.getInt("courses[" + i + "].price");
			int copies = js.getInt("courses[" + i + "].copies");
			sum = sum + (price * copies);
			System.out.println(sum);
		}
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		Assert.assertEquals(sum, purchaseAmount);

	}

}
