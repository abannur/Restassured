package api.chaining;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import static io.restassured.RestAssured.given;

public class CreateUser {
	
	
	
	@Test
	@Parameters({ "bearerToken" })
	public void createUserTest(ITestContext context,String bearerToken)
	{
		Faker faker = new Faker();
		JSONObject data = new JSONObject();
		data.put("name", faker.name().fullName());
		data.put("gender","female");
		data.put("email",faker.internet().emailAddress());
		data.put("status","inactive");
		//String bearerToken = "31f2be4ea23155074ee51f643be2ac2c1f483edcbe997723e7d810c120100135";
		
		int id = 
		given().
			header("Authorization","Bearer "+bearerToken).
			contentType("application/json").
			body(data.toString()).
		when().
			post("https://gorest.co.in/public/v2/users").
			jsonPath().
			getInt("id");
		
		System.out.println("Id generated is "+id);
		
		//access the variable at the test level with <test> tag of testNG.xml
		//
		context.setAttribute("user_id", id);
		//access the variable at the suite level with <suite> tag of testNG.xml
		context.getSuite().setAttribute("user_id", id);
	}

}
