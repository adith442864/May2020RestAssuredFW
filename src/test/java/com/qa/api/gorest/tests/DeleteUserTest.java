package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.gorest.pojo.User;
import com.qa.api.gorest.restclient.RestClient;

import io.restassured.response.Response;

public class DeleteUserTest {
	
	
	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "SPoKCJ8e4xc3nL9W9JZw77FroylHZsgcQX2t";
	
	@Test
	public void deleteUserAPITest() {
		
		
		User user = new User("Nisha", "John", "female", "01-01-1988", 
				"nisha123@gmail.com", "+91 9999900000", "http://www.naveenautomation.com", 
				
				"NewYork, USA", "active");
		
		
		Map<String,String> authTokenMap = new HashMap<String,String>();
		authTokenMap.put("Authorization", "Bearer "+token);
		Response response = RestClient.doPost("JSON", baseURI, basePath, authTokenMap, null, true, user);
		
		System.out.println(response.prettyPrint());
		System.out.println(response.getStatusCode());
		String id = response.jsonPath().getString("result.id");
		System.out.println("user id is : " +id);
	
		
		System.out.println("====================================>");
		
		
		Response deleteResponse = RestClient.doDelete("JSON",baseURI , basePath +"/" +id, authTokenMap, null, true, null);
		System.out.println(deleteResponse.prettyPrint());
		int responseCode =  deleteResponse.jsonPath().get("_meta.code");
		System.out.println("delete user api respone status code: "+ responseCode);
		Assert.assertEquals(responseCode, 204);
		Assert.assertNull(deleteResponse.jsonPath().getString("result"));
		int limit =  deleteResponse.jsonPath().get("_meta.rateLimit.limit");
		int remaining =  deleteResponse.jsonPath().get("_meta.rateLimit.remaining");
		int reset =  deleteResponse.jsonPath().get("_meta.rateLimit.reset");

		Assert.assertEquals(remaining, limit-reset);
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	

}
