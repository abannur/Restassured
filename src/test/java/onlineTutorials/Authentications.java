package onlineTutorials;

import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class Authentications {
	
	@Test(priority=1)
	public void testBasicDigestPreemptive()
	{
		 given().auth().basic("postman", "password").
		when().
		get("https://postman-echo.com/basic-auth").
		then().statusCode(200).body("authenticated",equalTo(true)).log().all();
		 
		 given().auth().digest("postman", "password").
			when().
			get("https://postman-echo.com/basic-auth").
			then().statusCode(200).body("authenticated",equalTo(true)).log().all();
			 
		 
		 given().auth().preemptive().basic("postman", "password").
			when().
			get("https://postman-echo.com/basic-auth").
			then().assertThat().statusCode(200);
			//statusCode(200).body("authenticated",equalTo(true)).log().all();
 
		 
	}
	
	@Test
	public void testBearerTokenAuthentication()
	{
		String bearer ="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";
		given().headers("Authorization","Bearer "+bearer)
		.when().get("https://api.github.com/user/repos")
		.then().log().body().statusCode(200).body("owner[0].login", equalTo("abannur"));
	}

	
	@Test
	public void testOAuth1Authentication()
	{
		
		given().auth().oauth("consumerKey","consumerSecrete","accessToken","tokenSecrete")
		.when().get("url")
		.then().log().body().statusCode(200).body("owner[0].login", equalTo("abannur"));
	}
	
	@Test
	public void testOAuth2Authentication()
	{
		
		given().auth().oauth2("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")
		.when().get("https://api.github.com/user")
		.then().log().body().statusCode(200).body("login", equalTo("abannur"));
	}
	
	@Test
	public void testAPIKeyAuthentication()
	{
		
		given().queryParam("appid", "2a44eaefbf6146add7a5eb5640914b20")
		.when().get("https://api.openweathermap.org/data/2.5/forecast/daily?lat=44.34&lon=10.99&cnt=7")
		.then().log().body().assertThat().statusCode(200);
	}
}
