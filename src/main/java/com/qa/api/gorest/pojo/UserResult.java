package com.qa.api.gorest.pojo;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.qa.api.gorest.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class UserResult {
	
	public static String token = "SPoKCJ8e4xc3nL9W9JZw77FroylHZsgcQX2t";
	
	@Test
	public void createUserWithFullJson() {

		Self sf = new Self("http://www.sf.com");
		Edit ed = new Edit("http://www.ed.com");
		Avatar av = new Avatar("http://www.av.com");
		
		Links ln = new Links(sf, ed, av);
		
		UserInfo uf = new UserInfo("Tom","Peter","male","01-01-1999","Tom111@gmail.com", 
				"+1-1234567890","http://www.tom.com","india","active",ln);
		
		String userJsonPayload = TestUtil.getSerializedJSON(uf);
		System.out.println(userJsonPayload);
		
//		Map<String, String> authTokenMap = new HashMap<String, String>();
//		authTokenMap.put("Authorization", "Bearer "+token);
//		
//		Response response = RestClient.doPOST("JSON", "https://gorest.co.in", "/public-api/users", authTokenMap, null, userJsonPayload, true);
//		System.out.println(response.prettyPrint());
//		System.out.println(response.getStatusCode());
		
		
		RestAssured.baseURI ="https://gorest.co.in";
		given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer SPoKCJ8e4xc3nL9W9JZw77FroylHZsgcQX2t")
			.body(userJsonPayload)
		.when()	.log().all()
			.post("/public-api/users")
		.then().log().all()
			.assertThat()
				.contentType(ContentType.JSON);

	}

	
	
	
	
}
