package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;


import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;
import api.utilities.DataProviders;

public class DDTests {

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String userId, String userName, String firstName, String lastName, String userEmail,
			String password, String phoneNumber) {
		User userPayload = new User();
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUserName(userName);
		userPayload.setFirstName(firstName);
		userPayload.setLastName(lastName);
		userPayload.setEmail(userEmail);
		userPayload.setPassword(password);
		userPayload.setPhone(phoneNumber);
		Response response = UserEndpoints.createUser(userPayload);
		System.out.println("Payload is " + userPayload.getUserName() + " " + userPayload.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUser(String userName) {
		Response response = UserEndpoints.deleteUser(userName);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
