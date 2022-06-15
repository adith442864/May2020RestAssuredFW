package com.qa.api.gorest.util;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.path.json.JsonPath;

public class Token {
	
	public static Map<Object, Object> appTokenMap;  // auth token
	public static Map<String, String> tokenMap = new HashMap<String,String>(); //used to fill the key and value.
	public static String clientId = "b1255a073ba4f35";
	
	public static Map<Object,Object> getAccessToken() {
		
		Map<String,String> formParams = new HashMap<String,String>();
		formParams.put("refresh_token", "43c0163ddc39659497eee5d03527b1a2a6080a31");
		formParams.put("client_id", "b1255a073ba4f35");
		formParams.put("client_secret", "48b91a9b7aa3313c6e2df69388e219e752ef4029");
		formParams.put("grant_type", "refresh_token");
		
			JsonPath tokenJson = 
			given()
				.formParams(formParams)
				.when()
					.post("https://api.imgur.com/oauth2/token")
				.then()
					.extract()
						.jsonPath();
			
				System.out.println(tokenJson.getMap(""));
				
				appTokenMap = tokenJson.getMap("");
				return appTokenMap;
		
	}
	
	public static Map<String,String> getAuthToken() {
		String authToken = appTokenMap.get("access_token").toString();
		System.out.println("Auth token ===> " +authToken);
		tokenMap.put("Authorization", "Bearer "+authToken);
		return tokenMap;
		
	}
	
	public static Map<String,String> getClientId() {
		System.out.println("client Id is ===> " +clientId);
		tokenMap.put("Authorization", "Client-ID "+clientId);
		return tokenMap;
		
	}
	
	public static void main(String args[]) {
		getAccessToken();
//		getAuthToken();
//		getClientId();
	}

}
