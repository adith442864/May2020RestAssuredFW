package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;

@Epic("Get User GORest API Feature implementation...")
@Feature("GetUser API feature...")
public class GetUserTest {
	
	String baseURI= "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "SPoKCJ8e4xc3nL9W9JZw77FroylHZsgcQX2t";
	
	@Description("get all user list api test...verify all users list from get call...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=1)
	public void getAllUserListAPI_Test() {
		Map<String,String> authTokenMap = new HashMap<String,String>();
		authTokenMap.put("Authorization", "Bearer "+ token);
		
		Response response = RestClient.doGet("JSON", baseURI, basePath, authTokenMap, null, true);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		
	}
	
	@Description("get all user list api test with query params...verify list from get call with query params...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void getUserListAPI_WithQueryParams_Test() {
		Map<String,String> params = new HashMap<String, String>();
		params.put("first_name", "Adithya");
		params.put("gender", "male");
		
		Map<String,String> authTokenMap = new HashMap<String,String>();
		authTokenMap.put("Authorization", "Bearer "+ token);
		
		Response response = RestClient.doGet("JSON", baseURI, basePath, authTokenMap, params, true);
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		
	}

}
