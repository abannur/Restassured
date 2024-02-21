package onlineTutorials;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class PostRequestBodyTypes {

	@Test(priority = 1)
	public void testHashMapData() {
		HashMap<String, String[]> data = new HashMap<String, String[]>();
		String[] courseArray = { "c++", "c" };
		data.put("name", new String[] { "anita" });
		data.put("courses", courseArray);

		given().contentType("application/json").body(data).when().post().then().statusCode(200).and()
				.body("name", equalTo("scott")).and().log().all();
	}

	@Test(priority = 3)
	public void testJSONObjectData() {
		JSONObject data = new JSONObject();

		String[] courseArray = { "c++", "c" };
		data.put("name", "anita");
		data.put("courses", courseArray);

		given().contentType("application/json").body(data.toString()).when().post().then().statusCode(200).and()
				.body("name", equalTo("scott")).and().log().all();
		;
	}

	@Test(priority = 3)
	public void testPostRequestBodyUsingPojo() {

		Booker data = new Booker();

		data.setFirstname("mathew");
		data.setLastname("Brown");
		data.setDepositpaid(true);
		JSONObject checkinout = new JSONObject();
		checkinout.put("checkin", "2023-01-29");
		checkinout.put("checkout", "2024-01-29");
		data.setBookingdates(checkinout);
		data.setTotalprice(340);
		data.setAdditionalneeds("breakfast");

		given().contentType("application/json").body(data).when().post("https://restful-booker.herokuapp.com/booking")
				.then().log().all();

		// statusCode(200).and()
		// .body("firstname",equalTo("mathew")).and().

		// log().headers();
	}

	@Test(priority = 4)
	public void testPostRequestBodyUsingJsonFile() throws FileNotFoundException {
		File file = new File(".\\postBody.json");
		FileReader fr = new FileReader(file);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject json = new JSONObject(jt);

		given().contentType("application/json").body(json.toString()).when()
				.post("https://restful-booker.herokuapp.com/booking").then()
				.body("booking.firstname", equalTo("Ramesh")).body("booking.lastname", equalTo("Brown"))
				.body("booking.bookingdates.checkin", equalTo("2023-01-29")).body("bookingid", equalTo(5847)).log()
				.all();

	}
}
