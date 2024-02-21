package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.StudentInfo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StudentEndpointProperties {

	public static ResourceBundle getUrl() {
		// load the properties file
		ResourceBundle routes = ResourceBundle.getBundle("Routes");
		return routes;
	}

	public static Response createUser(StudentInfo payload) {
		String post_url = getUrl().getString("post_url");
		System.out.println("Post url is "+post_url);
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(post_url);

		return response;

	}

	public static Response readUser(String userId) {
		String get_url = getUrl().getString("get_url");
		Response response = given().pathParam("userId", userId).when().get(get_url);
		System.out.println("get url is " + get_url);
		return response;

	}

	public static Response updateUser(String userId, StudentInfo payload) {
		String update_url = getUrl().getString("update_url");
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("userId", userId)
				.body(payload).when().put(update_url);

		return response;

	}

	public static Response deleteUser(String userId) {
		String delete_url = getUrl().getString("delete_url");
		Response response = given().pathParam("userId", userId)

				.when().put(delete_url);

		return response;

	}
}
