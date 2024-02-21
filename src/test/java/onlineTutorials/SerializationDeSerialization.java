package onlineTutorials;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class SerializationDeSerialization {
	
	//pojo--->serialisation--->json--->deserilazation-->pojo
	@Test
	public void convertPojoToJson() throws JsonProcessingException
	{
		Students stPojo= new Students();
		stPojo.setName("anita");
		stPojo.setLocation("bengaluru");
		stPojo.setPhone("8123919234");
		String [] courses = {"java","selenium"};
		stPojo.setCourses(courses);
		// convert java object to json object(serialization)
		ObjectMapper objMap = new ObjectMapper();
		String jsonData = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(stPojo);
		System.out.println(jsonData);
		
	}
	
	
	//json--->deserilazation-->pojo
		@Test
		public void convertJsonToPojo() throws JsonProcessingException
		{
			String jsonData ="{\r\n"
					+ "  \"name\" : \"anita\",\r\n"
					+ "  \"location\" : \"bengaluru\",\r\n"
					+ "  \"phone\" : \"8123919234\",\r\n"
					+ "  \"courses\" : [ \"java\", \"selenium\" ]\r\n"
					+ "}";
			
			ObjectMapper obj =  new ObjectMapper();
			Students stPojo = obj.readValue(jsonData, Students.class);
			System.out.println(Arrays.toString(stPojo.getCourses()));
			System.out.println(stPojo.getLocation());
			
		}

}
