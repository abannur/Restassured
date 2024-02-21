package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import api.endpoints.StudentEnpoint;
import api.payload.StudentInfo;

import io.restassured.response.Response;

public class StudentTests {
	
	Faker faker ;
	StudentInfo studentPayload;
	int userId ;
	public Logger logger;
	@BeforeClass
	public void setUp() throws JsonProcessingException
	{
		faker = new Faker();
		studentPayload = new StudentInfo();
		studentPayload.setFirstName(faker.name().firstName());
		studentPayload.setLastName(faker.name().lastName());
		studentPayload.setSubjectId(2);
		ObjectMapper objMap = new ObjectMapper();
		String jsonData = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(studentPayload);
		System.out.println("json data"+jsonData);
		//logs
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("*************creating student record******************");
		Response response = StudentEnpoint.createUser(studentPayload);
		System.out.println("Payload is "+studentPayload.getFirstName());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 201);
		userId = response.jsonPath().getInt("id");
		logger.info("*********************creating student record completed***********");
	}
	
	@Test(priority=2)
	public void testReadUserByUserName()
	{
		logger.debug("*********************testReadUserByUserName started***********");
		System.out.println("username "+ studentPayload.getFirstName());
		System.out.println("username this "+this.studentPayload.getFirstName() +userId);
		Response response = StudentEnpoint.readUser(userId);
		response.then().log().all();
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.debug("*********************testReadUserByUserName ended***********");
	}
	
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		studentPayload.setFirstName(faker.name().firstName());
		studentPayload.setLastName(faker.name().lastName());
		studentPayload.setSubjectId(2);
		Response response = StudentEnpoint.updateUser(userId, studentPayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		Response responseAfterUpdate = StudentEnpoint.readUser(userId);
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	}
	
	@Test(priority = 4)
	public void testDeleteUser()
	{
		Response response = StudentEnpoint.deleteUser(userId);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
