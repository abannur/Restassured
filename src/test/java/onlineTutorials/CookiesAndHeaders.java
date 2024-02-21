package onlineTutorials;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import java.util.Map;

public class CookiesAndHeaders {

	@Test
	public void testCookies() {
		given().when().get("https://www.google.com").then()
				.cookies("AEC", "Ae3NU9NpyvBVIUr9H3mh_nHxDIKdDZXN13IX8HPFEqN8KNHk8wSgRufXN18").log().cookies();

	}

	@Test
	public void testAllCookies() {
		Response res = given().when().get("https://www.google.com");
		System.out.println("response is " + res.getCookie("AEC"));
		Map<String, String> cookies = res.getCookies();
		for (String key : cookies.keySet()) {
			System.out.println("key and values " + key + "   " + cookies.get(key));
		}
	}
	
	@Test
	public void testHeaders()
	{
		 given().when().get("https://www.google.com").
		 then().header("Content-Type", "text/html; charset=ISO-8859-1").and()
		 .header("Content-Encoding", "gzip").and().header("Server", "gws").log().headers();
		 //log all headers
		 
	}
	
	@Test
	public void allHeaders()
	{
		 Response res = given().when().get("https://www.google.com");
		 Headers headerOfRequest = res.headers();
		 
		 for(Header header: headerOfRequest)
		 {
			 System.out.println("Header name "+header.getName()+"Header values "+ header.getValue());
		 }
		
		 
	}
	
}
