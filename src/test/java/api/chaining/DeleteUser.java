package api.chaining;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
public class DeleteUser {
	
	@Test
	@Parameters({ "bearerToken" })
	public void deleteUserTest(ITestContext context,String bearerToken)
	{

		//int id = (int) context.getAttribute("user_id") ;
		int id = (int) context.getSuite().getAttribute("user_id") ;
		//String bearerToken ="31f2be4ea23155074ee51f643be2ac2c1f483edcbe997723e7d810c120100135";
		given().
			header("Authorization","Bearer "+bearerToken).
			pathParam("id", id).
		when().
			delete("https://gorest.co.in/public/v2/users/{id}").
		then().
			assertThat().
			statusCode(204).
			log().all();
		
	}

}
