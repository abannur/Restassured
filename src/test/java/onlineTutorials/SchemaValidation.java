package onlineTutorials;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;

public class SchemaValidation {

	
	@Test
	public void testJsonSchema()
	{
		given().
		when().get("https://mystoreapi.com/catalog/products").
		then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemaTest.json"));
		
	}
	
	
	@Test
	public void testxmlSchema()
	{
		given().
		when().get("http://restapi.adequateshop.com/api/Traveler").
		then().assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("traveler.xsd"));
		
	}
}
