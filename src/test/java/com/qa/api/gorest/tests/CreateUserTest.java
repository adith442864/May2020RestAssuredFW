package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.gorest.pojo.User;
import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;

@Epic("Create User GORest API Feature implementation...")
@Feature("create User API feature...")
public class CreateUserTest {
	
	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "SPoKCJ8e4xc3nL9W9JZw77FroylHZsgcQX2t";
	
	@Description("create user API feature, verify user created from post call...")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=1)
	public void createUserAPIPOSTTest() {
		
		User user = new User("Nisha", "John", "female", "01-01-1988", 
				"nisha1@gmail.com", "+91 9999900000", "http://www.naveenautomation.com", 
				"NewYork, USA", "active");
		
		Map<String,String> authTokenMap = new HashMap<String,String>();
		authTokenMap.put("Authorization", "Bearer "+token);
		Response response = RestClient.doPost("JSON", baseURI, basePath, authTokenMap, null, true, user);
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		
	}

	
	@DataProvider
	public Object[][] getUserTestData(){
		Object[][] userData = ExcelUtil.getTestData("createuser");
		return userData;
		
	}
	
	@Test(priority=2, dataProvider="getUserTestData")
	public void createUserPOSTAPI_Test1(String firstname, String lastname, String gender, String dob, 
			String email, String phone, String website, String address, String status) {
		
//		User user = new User("Ricky", "pont", "male", "01-01-1988", "rickyponting@gmail.com", 
//		"+61-123456789", "http://www.rickypunter.com", "Melbourne, AU", "active");
		
		User user = new User(firstname,lastname,gender,dob,email,phone,website,address,status);
		Map<String,String> authTokenMap = new HashMap<String,String>();
		authTokenMap.put("Authorization", "Bearer "+ token);
		Response response = RestClient.doPost("JSON", baseURI, basePath, authTokenMap, null, true, user);
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		System.out.println("==================================================");
		
	}
	
}
