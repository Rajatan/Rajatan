package Ecom;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Automation_ECom {
	String accessToken;
	String productID;
	String userID;

	@Test(priority = 0)
	public void eCommerce() {

		RequestSpecification baseSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();
//		Login to site
		LoginInfo login = new LoginInfo();
		login.setUserEmail("rajatan98@gmail.com");
		login.setUserPassword("Neelammanavar@24");

//		If we get any SSL certificate issue like not authorized or not secured, we can pass that ssl and perform our HTTP methods by
//		relaxedHTTPSValidation() method

		RequestSpecification reqLog = given().relaxedHTTPSValidation().log().all().spec(baseSpec).body(login);

		LoginResponse resLog = reqLog.when().post("/api/ecom/auth/login").then().log().all().extract().response()
				.as(LoginResponse.class);

		accessToken = resLog.getToken();
		userID = resLog.getUserId();

		System.out.println("AccessToken: " + accessToken + "\nuserID: " + userID);

	}

	@Test(priority = 1)
	public void createProduct() {
//		Creating a product
//		Here, we are passing the Form Data (Form Parameters) instead of body we will use param() method
		RequestSpecification addProductBaseSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", accessToken).build();

		RequestSpecification reqAddProd = given().log().all().spec(addProductBaseSpec).param("productName", "Laptop")
				.param("productAddedBy", "63416e57c4d0c51f4f3891a7").param("productCategory", "Gaming")
				.param("productSubCategory", "Pavilion").param("productPrice", 45800)
				.param("productDescription", "Super fast refresh rate 3.5MHz").param("productFor", "Professional")
				.multiPart("productImage",
						new File("E:\\Rajat Selenium\\RestAPI_projects\\OAuth2.0\\src\\main\\java\\Ecom\\Laptop.png"));

		String resAddProduct = reqAddProd.when().post("api/ecom/product/add-product").then().log().all().extract()
				.response().asString();

		JsonPath js = new JsonPath(resAddProduct);
		productID = js.get("productId");
		System.out.println("ProductID: " + productID);
	}

	@Test(priority = 2)
	public void createOrder() {
//		Create Order
		RequestSpecification createOrderBaseSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", accessToken).setContentType(ContentType.JSON).build();

//		Body that needs to be passed is complex JSON so will be using POJO classes 
		OrderDetailsPOJO orderDetails = new OrderDetailsPOJO();
		orderDetails.setCountry("India");
		orderDetails.setProductOrderedId(productID);

//		The order is list of json so will need to pass list to OrderPOJO class
		List<OrderDetailsPOJO> listOfOrder = new ArrayList<OrderDetailsPOJO>();
		listOfOrder.add(orderDetails);

		OrderPOJO orders = new OrderPOJO();
		orders.setOrders(listOfOrder);

		RequestSpecification reqCreate = given().log().all().spec(createOrderBaseSpec).body(orders);

		String resCreateOrder = reqCreate.when().post("/api/ecom/order/create-order").then().log().all().extract()
				.response().asString();

		System.out.println("Response of create order: " + resCreateOrder);
	}

	@Test(priority = 3)
	public void deleteProduct() {

//		Delete Product 
		RequestSpecification deleteProductBaseSpec = new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com").addHeader("authorization", accessToken).build();

		RequestSpecification reqDeleteProduct = given().log().all().spec(deleteProductBaseSpec).pathParam("productId",
				productID);

		String resDeleteProduct = reqDeleteProduct.when().delete("api/ecom/product/delete-product/{productId}").then()
				.log().all().extract().response().asString();

		System.out.println("Response of Delete Product: " + resDeleteProduct);
	}
//Need to fix the image issue that is how to add and where to find the image code.......
	@Test(priority = 4)
	public void addToCart() {
		System.out.println(accessToken);
		RequestSpecification addToCartBaseSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", accessToken).build();
		
		AddToCartPOJOProduct addProduct=new AddToCartPOJOProduct();
		addProduct.set_id(productID);
		addProduct.setProductName("Laptop");
		addProduct.setProductCategory("Gaming");
		addProduct.setProductSubCategory("Pavilion");
		addProduct.setProductPrice(45800);
		addProduct.setProductDescription("Super fast refresh rate 3.5MHz");
		addProduct.setProductImage("https://rahulshettyacademy.com/api/ecom/uploads/productImage_1665341315252.png");
		addProduct.setProductRating("0");
		addProduct.setProductTotalOrders("2"); 
		addProduct.setProductStatus(true);
		addProduct.setProductFor("Professional");
		addProduct.setProductAddedBy(userID);
		addProduct.set__v(0);
		
		AddToCartPOJO addCart=new AddToCartPOJO();
		addCart.set_id(userID);
		addCart.setProduct(addProduct);
		
		
		
		
		RequestSpecification reqAddToCart=given().log().all().spec(addToCartBaseSpec).body(addCart);
		
		String resAddToCart=reqAddToCart.when().post("api/ecom/user/add-to-cart")
		.then().log().all().extract().response().asString();
		
		System.out.println("Response of Add to cart: "+resAddToCart);
	}

}
