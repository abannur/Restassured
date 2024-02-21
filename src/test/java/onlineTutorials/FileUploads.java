package onlineTutorials;

import java.io.File;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class FileUploads {

	@Test
	public void testSingleFileUpload() {
		File file = new File("C:\\Auto\\Test1.txt");
		given().
		 	multiPart("file", file).
		when().
			post("api").
		then().
			assertThat().statusCode(200)
			.body("fileName", equalTo("Test1.txt")).log().all();
	}

	@Test
	public void testMultiFileUpload() {
		File file1 = new File("C:\\Auto\\Test1.txt");
		File file2 = new File("C:\\Auto\\Test2.txt");
		File[] filesArray = {file1,file2};
		given().
		 	//multiPart("files", file1).
		 //	multiPart("files", file2).
			multiPart("files", filesArray).
		 	contentType("multipart/form-data").
		when().
			post("api url").
		then().
			assertThat().statusCode(200)
			.body("[0].fileName", equalTo("Test1.txt")).
			body("[1].fileName", equalTo("Test2.txt")).
			log().all();
	}
	
	@Test
	public void testFileDownload() {
		given().
		when().
			get("url").
			then().
			assertThat().statusCode(200).body("values",null);
		
	}

}
