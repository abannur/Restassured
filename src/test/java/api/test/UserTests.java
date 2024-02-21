package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;


import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userPayload;
	@BeforeClass
	public void setUpData() throws JsonProcessingException
	{
		faker = new Faker();
		userPayload = new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUserName(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		System.out.println("user payload is "+ userPayload.getUserName());
		ObjectMapper obj = new ObjectMapper();
		String jsonData = obj.writerWithDefaultPrettyPrinter().writeValueAsString(userPayload);
		System.out.println("json data" + jsonData);	
	}

	@Test(priority=1)
	public void testPostUser()
	{
		Response response = UserEndpoints.createUser(userPayload);
		System.out.println("Payload is "+userPayload.getUserName()+ " "+userPayload.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2)
	public void testReadUserByUserName()
	{
		System.out.println("username "+ userPayload.getUserName());
		System.out.println("username this "+this.userPayload.getUserName());
		Response response = UserEndpoints.readUser(this.userPayload.getUserName());
		response.then().log().all();
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		Response response = UserEndpoints.updateUser(this.userPayload.getUserName(), userPayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		Response responseAfterUpdate = UserEndpoints.readUser(this.userPayload.getUserName());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	}
	
	//@Test(priority = 4)
	public void testDeleteUser()
	{
		Response response = UserEndpoints.deleteUser(this.userPayload.getUserName());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
