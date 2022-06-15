package com.qa.api.gorest.util;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;

import io.restassured.response.Response;

public class GetImgurAPITest {
	
	Map<Object,Object> tokenMap;
	String accessToken;
	String accountUserName;
	String refreshToken;
	
	
	String baseURI = "https://api.imgur.com";
	String basePath = "/account/v1/"+accountUserName+"/block";
	
	
	@BeforeMethod
	public void setUp() {
		tokenMap = Token.getAccessToken();
		accessToken = tokenMap.get("access_token").toString();
		accountUserName = tokenMap.get("account_username").toString();
		refreshToken = tokenMap.get("refresh_token").toString();
	}
	
	@Test(priority=1)
	public void getAccountBlockStatusTest() {
	Map<String,String> authTokenMap = Token.getAuthToken();
	Response response = RestClient.doGet(null, baseURI, basePath, authTokenMap, null, true);
	System.out.println(response.getStatusCode());
	System.out.println(response.prettyPrint());
		
	}
	
	
	@Test(priority=2)
	public void getAccountImageTest() {
	Map<String,String> authTokenMap = Token.getAuthToken();
	Response response = RestClient.doGet(null, "https://api.imgur.com","/3/account/me/images", authTokenMap, null, true);
	System.out.println(response.getStatusCode());
	System.out.println(response.prettyPrint());	
	}
	
	
//	@Test(priority=3)
//	public void getAccountBaseTest() {
//	Map<String,String> clientToken = Token.getClientId();
//	ResponRestClient.doGet(null, "https://api.imgur.com", "/3/account/adiautomation", clientToken, null, true);
//	System.out.println(response.getStatusCode());
//	System.out.println(response.prettyPrint());	
//	}
	
	
	
	
//	
	//multipart --> content type
	@Test
	public void uploadImagePostAPITest() {
		
		Map<String,String> clientIdMap = Token.getClientId();
		
		Map<String,String> formMap = new HashMap<String,String>();
		formMap.put("title", "test title API");
		formMap.put("description", "test description API");
		
		Response response = RestClient.doPost("multipart", "https://api.imgur.com", "/3/upload", clientIdMap, null, true, formMap);
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	
	
	
	
	
	
	
	
	

}
