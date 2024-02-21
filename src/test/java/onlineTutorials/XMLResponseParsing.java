package onlineTutorials;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;


public class XMLResponseParsing {
	
	@Test
	public void parseXml()
	{
		given().
		when().get("http://restapi.adequateshop.com/api/Traveler").
		then().assertThat().statusCode(200).header("Content-Type","application/xml; charset=utf-8").
		body("TravelerinformationResponse.total_pages", equalTo("4118")).
		body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"));
		
		Response res = given().
				when().get("http://restapi.adequateshop.com/api/Traveler");
		String pages = res.xmlPath().get("TravelerinformationResponse.total_pages").toString();
	//	Assert.assertEquals(pages, "4118","pages no did not match");
	}
	
	@Test
	public void parseXmlUsingXmlPath()
	{
		given().
		when().get("http://restapi.adequateshop.com/api/Traveler").
		then().assertThat().statusCode(200).header("Content-Type","application/xml; charset=utf-8").
		log().body();
		
		Response res = given().
				when().get("http://restapi.adequateshop.com/api/Traveler");
		
		XmlPath xmlPath = new XmlPath(res.asString());
		List<String>travellersNames = xmlPath.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
		System.out.println("info travellers" + travellersNames.size());
		boolean status = false;
		for(String names : travellersNames)
		{
			if(names.equals("asdasd"))
			{
				status = true;
				break;
			}
		}
		Assert.assertTrue(status, "TravellerName present");
		
//		List<Map<String, String>> persons = path.getList("TravelerinformationResponse.travelers.Travelerinformation");
//		
//		 for (Map<String, String> person : persons) {
//	            System.out.println("Name: " + person.get("name"));
//	            System.out.println("Age: " + person.get("email"));
//	            System.out.println("address: " + person.get("adderes"));
//	        }
		
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		 resultMap.put("page", xmlPath.getInt("TravelerinformationResponse.page"));
	        resultMap.put("per_page", xmlPath.getInt("TravelerinformationResponse.per_page"));
	        resultMap.put("totalrecord", xmlPath.getInt("TravelerinformationResponse.totalrecord"));
	        resultMap.put("total_pages", xmlPath.getInt("TravelerinformationResponse.total_pages"));
	        Integer i = xmlPath.getInt("TravelerinformationResponse.travelers.Travelerinformation.id");
	        resultMap.put("id", i);
	        
	        Map<String, String> travelerInfoMap = new HashMap<String, String>();
	       
	        travelerInfoMap.put("name", xmlPath.getString("TravelerinformationResponse.travelers.Travelerinformation.name"));
	        travelerInfoMap.put("email", xmlPath.getString("TravelerinformationResponse.travelers.Travelerinformation.email"));
	        travelerInfoMap.put("adderes", xmlPath.getString("TravelerinformationResponse.travelers.Travelerinformation.adderes"));
	        travelerInfoMap.put("createdat", xmlPath.getString("TravelerinformationResponse.travelers.Travelerinformation.createdat"));
	        
	        for (Entry<String, Integer> values : resultMap.entrySet()) {
	        	 System.out.print(values.getKey());
	        	 System.out.println(values.getValue());
				
			}
		
		
	}
	

}
