package onlineTutorials;
import static io.restassured.RestAssured.given;

import org.json.JSONArray;
import org.json.JSONObject;

import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ParsingJsonResponse {
	
	@Test
	public void testParseData()
	{
		given().contentType(ContentType.JSON).
		when().get("https://dummy.restapiexample.com/api/v1/employees").
		then().assertThat().statusCode(200).log().body().body("data[4].id",equalTo(5));
		
		Response res = given().
		when().get("https://dummy.restapiexample.com/api/v1/employees");
		String id = res.jsonPath().get("data[4].id").toString();
		System.out.println("id is "+id);
		
		//need to use asString instead of toString
		JSONObject obj = new JSONObject(res.asString());
		//getjsonarray method needs to  be used
		JSONArray array = obj.getJSONArray("data");
		for (int i = 0; i < array.length(); i++) {
		System.out.println("data printed is "+array.getJSONObject(i).getString("employee_name"));
			
		}
		Map<Object, Object> test = res.jsonPath().getMap("data");
		System.out.println(test);
		String idValues = test.get("id").toString();
		System.out.println("ID : " + idValues);
		
		
	}

}
