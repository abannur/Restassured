package onlineTutorials;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

public class HTTPRequests {

	int id;

	@Test(priority = 1)
	public void getUserData() {
		given().pathParam("value", "users").queryParam("id", 2).when().get("https://reqres.in/api/{value}").then()
				.statusCode(200).log().all();
	}

	@Test(priority = 2)
	public void createUserData() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("name", "hunter");
		data.put("job", "teacher");
		id = given().contentType("application/json").body(data)

				.when().post("https://reqres.in/api/users").jsonPath().getInt("id");
		System.out.println("id generated is " + id);
		// .then().jsonPath().getInt
		// statusCode(201).log().all()
	}

	@Test(priority = 3)
	public void updateUserData() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("name", "hunter");
		data.put("job", "sportman");
		given().contentType("application/json")

				.body(data)

				.when().put("https://reqres.in/api/users/" + id).then().statusCode(200).log().all();
		// .then().jsonPath().getInt
		// statusCode(201).log().all()
	}

	@Test(priority = 4)
	public void deleteUserData() {
		given().pathParam("value", "users").queryParam("id", id).when().delete("https://reqres.in/api/{value}").then()
				.statusCode(204).log().all();
	}

}
