package api.chaining;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class UpdateUser {

	@Test
	@Parameters({ "bearerToken" })
	public void updateUserTest(ITestContext context,String bearerToken)
	{
		//Faker faker = new Faker();
		JSONObject data = new JSONObject();
		//data.put("name", faker.name().fullName());
		data.put("gender","male");
		//data.put("email",faker.internet().emailAddress());
		data.put("status","active");
		//String bearerToken = "31f2be4ea23155074ee51f643be2ac2c1f483edcbe997723e7d810c120100135";
		//int id = (int) context.getAttribute("user_id");
		int id = (int) context.getSuite().getAttribute("user_id") ;
		 given().
		 	header("Authorization","Bearer "+bearerToken).contentType("application/json").	
		 	body(data.toString()).pathParam("id",id).
		 when().
		 	put("https://gorest.co.in/public/v2/users/{id}").
		 then().
		 	assertThat().statusCode(200).log().all();

	}
}
