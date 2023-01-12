package com.testingtaskapi.testCases;

import org.apache.xmlbeans.impl.tool.XSTCTester.TestCase;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_TestingTask_Api extends TestCase{

	RequestSpecification httpRequest;
	Response response;
	
	String unixdatetime = "2016-01-01 02:03:22";

			

	@BeforeClass
	void createEmployee() throws InterruptedException
	{
				
		RestAssured.baseURI = "https://helloacm.com/api/unix-timestamp-converter";
		httpRequest = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		
		httpRequest.header("Content-Type", "application/json");
		httpRequest.header("Cache-Control", "no-cache");
		httpRequest.header("Postman-Token", "a74249b3-97f1-45c0-999c-66d7841bed8a");
		httpRequest.header("x-api-key", "62d64c84d8a1242ed4483cad7d316be0");

		httpRequest.body(requestParams.toJSONString());

		response = httpRequest.request(Method.GET, "/?cached&s=1451613802");
		
		Thread.sleep(5000);

	}
	
	@Test
	void checkResposeBody()
	{
		String responseBody = response.getBody().asString();
		System.out.println("responseBody is :" + responseBody);
		Assert.assertEquals(responseBody.contains(unixdatetime), true);

	}
		
	@Test
	void checkStatusCode()
	{
		int statusCode = response.getStatusCode(); // Gettng status code
		Assert.assertEquals(statusCode, 200);
	}
		
	@Test
	void checkstatusLine()
	{
		String statusLine = response.getStatusLine(); // get the  status Line
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}
	
	@Test
	void checkContentType()
	{
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json");
	}

	@Test
	void checkcontentEncoding()
	{
		String contentEncoding = response.header("Content-Encoding");
		Assert.assertEquals(contentEncoding, "gzip");

	}
	
	@AfterClass
	void tearDown()
	{

	}

}
