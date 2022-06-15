package com.qa.api.gorest.restclient;

import java.io.File;
import java.util.Map;

import org.mozilla.javascript.ast.SwitchCase;

import com.qa.api.gorest.util.TestUtil;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * This class is having all http methods which will call the apis and having generic methods
 * for getting the response and fetch the values from response.
 * @author Adith
 *
 */

public class RestClient {
	
//HTTP Methods : GET POST PUT DELETE
	
	/**
	 * This method is used to call the get API.
	 * @param contentType
	 * @param baseURI
	 * @param basePath
	 * @param token
	 * @param paramsMap
	 * @param log
	 * @return this method is returning response from the GET Call.
	 */
	@Step("post call with {0), {1}, {2}, {3}, {4}")
	public static Response doGet(String contentType, String baseURI, String basePath, 
			Map<String,String> token, Map<String,String> paramsMap, boolean log) {
		
		if(setBaseURI(baseURI)) {
			RequestSpecification request =	createRequest(contentType, token, paramsMap, log);
			Response response = getResponse("GET", request, basePath);
			return response;
		}
		
		return null;
		
	}
	
	/**
	 * This method is used to call the POST API
	 * @param contentType
	 * @param baseURI
	 * @param basePath
	 * @param token
	 * @param paramsMap
	 * @param log
	 * @param obj
	 * @return this method is returning response from POST Call.
	 */
	
	public static Response doPost(String contentType, String baseURI, String basePath, 
			Map<String,String> token, Map<String,String> paramsMap, boolean log, Object obj) {
		
		if(setBaseURI(baseURI)) {
			RequestSpecification request =	createRequest(contentType, token, paramsMap, log);
			addRequestPayload(request, obj);
			Response response = getResponse("POST", request, basePath);
			return response;
		}

	return null;
		
	}
	
	
	
	/**
	 * This method is used to call the POST API
	 * @param contentType
	 * @param baseURI
	 * @param basePath
	 * @param token
	 * @param paramsMap
	 * @param log
	 * @param obj
	 * @return this method is returning response from DELETE Call.
	 */
	
	public static Response doDelete(String contentType, String baseURI, String basePath, 
			Map<String,String> token, Map<String,String> paramsMap, boolean log, Object obj) {
		
		if(setBaseURI(baseURI)) {
			RequestSpecification request =	createRequest(contentType, token, paramsMap, log);
			Response response = getResponse("DELETE", request, basePath);
			return response;
		}

	return null;
		
	}
	
	
	/**
	 * This method is used to call the POST API
	 * @param contentType
	 * @param baseURI
	 * @param basePath
	 * @param token
	 * @param paramsMap
	 * @param log
	 * @param obj
	 * @return this method is returning response from PUT Call.
	 */
	
	public static Response doPut(String contentType, String baseURI, String basePath, 
			Map<String,String> token, Map<String,String> paramsMap, boolean log, Object obj) {
		
		if(setBaseURI(baseURI)) {
			RequestSpecification request =	createRequest(contentType, token, paramsMap, log);
			addRequestPayload(request, obj);
			Response response = getResponse("PUT", request, basePath);
			return response;
		}

	return null;
		
	}
	
	
	
	

	private static void addRequestPayload(RequestSpecification request, Object obj) {
			
			if(obj instanceof Map) {
				request.formParams((Map<String,String>)obj);
			}else {
				String jsonPayload = TestUtil.getSerializedJSON(obj);
				request.body(jsonPayload);
			}
			
		}
	
	
	
	private static boolean setBaseURI(String baseURI) {
		
		if(baseURI == null || baseURI.isEmpty()) {
			System.out.println("Please pass the correct base URI either it is null or blank/empty...");
			return false;
		}
		try {
			RestAssured.baseURI = baseURI;
			return true;
		} catch(Exception e) {
			System.out.println("some exception got occured while assigning the baseURI with Rest Assured...");
			return false;
		}
		
	}
	

private static RequestSpecification createRequest(String contentType, Map<String,String> token, Map<String,String> paramsMap, boolean log ) {
		
		//generate log is true or false
		RequestSpecification request;
		if(log) {
			request = RestAssured.given().log().all();
		}else {
			request = RestAssured.given();
		}
		
		if(token.size()>0) {
			//request.header("Authorization", "Bearer "+token);
			request.headers(token);
		}
		
		if(!(paramsMap==null)) {
			request.queryParams(paramsMap);
		}
		
		if(contentType!=null) {
			if(contentType.equalsIgnoreCase("JSON")) {
				request.contentType(ContentType.JSON);
			}
			else if (contentType.equalsIgnoreCase("XML")) {
				request.contentType(ContentType.XML);
			}
			else if (contentType.equalsIgnoreCase("TEXT")) {
				request.contentType(ContentType.TEXT);
			}
			else if (contentType.equalsIgnoreCase("multipart")) {
				//request.contentType(ContentType.multipart);
				request.multiPart("image", new File("C:\\Users\\Adith\\Desktop\\API\\Feb2019.jpg"));
			}
		}
		
		
		return request;
		
	}
	
	private static Response getResponse(String httpMethod, RequestSpecification request, String basePath) {
		return executeAPI(httpMethod, request, basePath);
	}
	
	private static Response executeAPI(String httpMethod, RequestSpecification request, String basePath) {
		
		Response response = null;
		
		switch (httpMethod) {
		case "GET":
			response = request.get(basePath);
			break;
		case "POST":
			response = request.post(basePath);
			break;
		case "PUT":
			response = request.put(basePath);
			break;
		case "DELETE":
			response = request.delete(basePath);
			break;
			
		default:
			System.out.println("Please pass the correct http method...");
			break;
		}
		
		return response;
		
	}

}
