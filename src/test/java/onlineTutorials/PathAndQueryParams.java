package onlineTutorials;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class PathAndQueryParams {

	@Test
	public void testParams()
	{
		
		given().
		pathParam("path", "users").
		queryParam("page", 2).
		queryParam("id", 5).
		when().get("https://reqres.in/api/{path}").
		then().assertThat().statusCode(200).log().all();
		
	}
}
