package api.endpoints;
import static io.restassured.RestAssured.given;

import api.payload.StudentInfo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;



public class StudentEnpoint {
	
	public static Response createUser(StudentInfo payload)
	{
		Response response = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(Routes.post_url);
		
		return response;
		
	}

	public static Response readUser(int userId)
	{
		Response response = given()
				.pathParam("userId", userId)
		.when()
		.get(Routes.get_url);
		System.out.println("get url is "+Routes.get_url);
		return response;
		
	}
	
	public static Response updateUser(int userId,StudentInfo payload)
	{
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("userId", userId)
				.body(payload)
		.when()
		.put(Routes.update_url);
		
		return response;
		
	}
	
	public static Response deleteUser(int userId)
	{
		Response response = given()
				.pathParam("userId", userId)
				
		.when()
		.put(Routes.delete_url);
		
		return response;
		
	}
}
