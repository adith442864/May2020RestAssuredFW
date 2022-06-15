package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.gorest.pojo.User;
import com.qa.api.gorest.restclient.RestClient;

import io.restassured.response.Response;

public class UpdateUserTest {
	
	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "SPoKCJ8e4xc3nL9W9JZw77FroylHZsgcQX2t";
	
	
	@Test
	public void updateUserAPITest() {
		
		User user = new User("John", "Peter", "male", "01-01-1988", 
				"john.peter@gmail.com", "+91 9999900000", "http://www.naveenautomation.com", 
				"NewYork, USA", "active");
		
		
		Map<String,String> authTokenMap = new HashMap<String,String>();
		authTokenMap.put("Authorization", "Bearer "+token);
		Response response = RestClient.doPost("JSON", baseURI, basePath, authTokenMap, null, true, user);
		
		System.out.println(response.prettyPrint());
		System.out.println(response.getStatusCode());
		String id = response.jsonPath().getString("result.id");
		System.out.println("user id is : " +id);
	
		System.out.println("==============================================================================");
		
		user.setEmail("johnpeter123@gmail.com");
		user.setAddress("MG Road, Bangalore");
		user.setPhone("+91-090909090909");
		
		Response response1 = RestClient.doPut("JSON", baseURI, basePath +"/" +id, authTokenMap, null, true, user);
		System.out.println(response1.prettyPrint());
		System.out.println(response1.getStatusCode());
	
		System.out.println("===============================================================================");
		Response response2 = RestClient.doGet("JSON", baseURI, basePath +"/"+id, authTokenMap, null, true);
		System.out.println(response2.prettyPrint());
		System.out.println(response2.getStatusCode());
			
		
		}
		
	}
	
