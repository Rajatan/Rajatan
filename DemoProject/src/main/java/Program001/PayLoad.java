package Program001;

public class PayLoad {
	
	public static String resourse() {
		return "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Rajat N\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"English-IN\"\r\n"
				+ "}";
	}
	
	public static String stabAPI() {
		return "{\r\n" + 
				"  \"dashboard\": {\r\n" + 
				"    \"purchaseAmount\": 1162,\r\n" + 
				"    \"website\": \"rahulshettyacademy.com\"\r\n" + 
				"  },\r\n" + 
				"  \"courses\": [\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"Selenium Python\",\r\n" + 
				"      \"price\": 50,\r\n" + 
				"      \"copies\": 6\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"Cypress\",\r\n" + 
				"      \"price\": 40,\r\n" + 
				"      \"copies\": 4\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"RPA\",\r\n" + 
				"      \"price\": 45,\r\n" + 
				"      \"copies\": 10\r\n" + 
				"    },\r\n" + 
				"     {\r\n" + 
				"      \"title\": \"Appium\",\r\n" + 
				"      \"price\": 36,\r\n" + 
				"      \"copies\": 7\r\n" + 
				"    }\r\n" + 
				"    \r\n" + 
				"    \r\n" + 
				"    \r\n" + 
				"  ]\r\n" + 
				"}\r\n" + 
				"";
	}
	
	public static String addBookBody() {
		
		String payloads="{\r\n"
				+ "\r\n"
				+ "\"name\":\"Aqualenc to clear your eyes\",\r\n"
				+ "\"isbn\":\"Lens\",\r\n"
				+ "\"aisle\":\"007\",\r\n"
				+ "\"author\":\"lenskart\"\r\n"
				+ "}\r\n"
				+ "";
		
		return payloads;
		
	}
	
	public static String dynamicaddBookBody(String isbn, String aisle) {
		
		String payloads="{\r\n"
				+ "\r\n"
				+ "\"name\":\"Aqualenc to clear your eyes\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"lenskart\"\r\n"
				+ "}\r\n"
				+ "";
		
		return payloads;
		
	}

	public static String deletedBookBody(String id) {
		// TODO Auto-generated method stub
		return "{\r\n"
				+ "\"ID\" : \""+id+"\"\r\n"
				+ "} ";
	}
}
