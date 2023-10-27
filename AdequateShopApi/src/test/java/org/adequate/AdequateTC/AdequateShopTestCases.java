package org.adequate.AdequateTC;

import static org.testng.Assert.assertEquals;

import static org.testng.Assert.assertTrue;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.adequate.Apihelper.ApiHelper;
import org.adequate.requestPOJO.RegisterPojo;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

@Test
@Listeners(org.adequate.util.TestEventListnersutility.class)

public class AdequateShopTestCases extends ApiHelper {

	@BeforeTest
	public void setup() {
		// RestAssured.baseURI="http://restapi.adequateshop.com/api/authaccount";
		// RequestSpecification reqSpec=RestAssured.given();
		RestAssured.baseURI = getBaseUri();

	}

	// @Test (priority = 1)
	public void registerUser() {
		// faker=new Faker();

		RegisterPojo registerpojo = new RegisterPojo();

		registerpojo.name = "jannai";
		registerpojo.email = "jan@gmail.qa";
		registerpojo.password = "janani";

		Response res = RestAssured.given().contentType(ContentType.JSON).body(registerpojo).when()
				.post("/registration");
		// return res;
		res.prettyPrint();
		res.then().statusCode(200);

	}

	@Test

	public void ValidLoginWithValidCredentials() {

		String token = getToken();
		assertEquals(token.contains("\"message\": \"success\""), true); // Check if "message" is "success"

		System.out.println("Response Body: " + token);

		// loginSchemaValidator(res);

	}

	@Test
	public void InValidLoginWithInValidCredentials() {
		String responseBody = invalidLogin();
		assertTrue(responseBody.contains("name"));
		assertTrue(responseBody.contains("email"));
		assertTrue(responseBody.contains("location"));

		System.out.println("Response Body: " + responseBody);

	}

	@Test
	public void TCgetUserData() {

		Response res = getUserData();
		// resSchemaValidator(res);

		int r = res.body().jsonPath().get("totalrecord");

		System.out.println("total number of records: " + r);
		Assert.assertEquals(res.getStatusCode(), HttpStatus.SC_OK,
				"Get all users data  working for valid credentials.");

		String responseBody = res.asString();
		assertTrue(responseBody.contains("name"));
		assertTrue(responseBody.contains("email"));
		assertTrue(responseBody.contains("location"));

		System.out.println("Response Body: " + responseBody);

		/*
		 * GetExamplePojo[] respdata = res.as(GetExamplePojo[].class); for
		 * (GetExamplePojo data : respdata) { // For example, you can assert properties
		 * of each element Assert.assertNotNull(data.getTotalPages());
		 */
	}

	@Test
	public void addUserData() {

		Response adduser = addUser();
		Assert.assertEquals(adduser.getStatusCode(), HttpStatus.SC_CREATED,
				"\"Add data functionality is not working as expected.\"");
		pretty(adduser);
		String responseBody = adduser.asString();
		assertTrue(responseBody.contains("name"));
		assertTrue(responseBody.contains("email"));
		assertTrue(responseBody.contains("location"));

		System.out.println("Response Body: " + responseBody);

	}

	@Test
	public void deleteUser() {
		Response deleteuser = deleteUserById();
		Assert.assertEquals(deleteuser.getStatusCode(), HttpStatus.SC_METHOD_NOT_ALLOWED,
				"Delete data functionality is not working as expected.");
		pretty(deleteuser);
		// successValidation(deleteuser);

	}

	@Test
	public void updateUser() {
		Response updateuser = updateUserById();
		Assert.assertEquals(updateuser.getStatusCode(), HttpStatus.SC_METHOD_NOT_ALLOWED,
				"update  data functionality is not working as expected .");
		pretty(updateuser);
		// successValidation(updateuser);

	}

	@Test
	public void WriteInExceltotalRecordPercity() {
		Response response = getUserData();
		String jsonResponse = response.asString();

		// Parse the JSONresponse
		JsonPath jsonPath = new JsonPath(jsonResponse);

		// Extract all records and store them in a list
		List<Map<String, Object>> allRecords = extractAllRecords(jsonPath);

		// Create an Excel workbook and open a sheet

		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Total Records per City");

		// Create headers for the columns
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("City");
		headerRow.createCell(1).setCellValue("ID");
		headerRow.createCell(2).setCellValue("Name");
		headerRow.createCell(3).setCellValue("Email");

		// Populate the data in the sheet
		int rowNum = 1;
		for (Map<String, Object> record : allRecords) {
			Row dataRow = sheet.createRow(rowNum++);
			dataRow.createCell(0).setCellValue(record.get("location").toString()); // City
			dataRow.createCell(1).setCellValue(record.get("id").toString()); // ID
			dataRow.createCell(2).setCellValue(record.get("name").toString()); // Name
			dataRow.createCell(3).setCellValue(record.get("email").toString()); // Email
		}

		// Save the Excel file
		try (FileOutputStream outputStream = new FileOutputStream("TotalRecordsPerCity1.xlsx")) {
			workbook.write(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Excel file created successfully.");
	}

	private static List<Map<String, Object>> extractAllRecords(JsonPath jsonPath) {
		List<Map<String, Object>> allRecords = jsonPath.getList("data");
		return allRecords;

	}

	@Test

	public void storeDataInExcel() {
		Response response = getUserData();

		if (response.getStatusCode() != 200) {
			System.err.println("API request failed with status code: " + response.getStatusCode());
			return;
		}

		// Extract the JSON response
		String jsonResponse = response.asString();

		// Parse the JSON response
		JsonPath jsonPath = new JsonPath(jsonResponse);

		// Extract all records
		List<Map<String, Object>> allRecords = eeeextractAllRecords(jsonPath);

		// Create an Excel workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a sheet for total records per city
		createTotalRecordsSheet(workbook, allRecords);

		// Create a sheet for emails per city
		createEmailsPerCitySheet(workbook, allRecords);

		// Save the Excel file
		try (FileOutputStream outputStream = new FileOutputStream("DataPerCity.xlsx")) {
			workbook.write(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Excel file created successfully.");
	}

	// Method to extract all records
	private static List<Map<String, Object>> eeeextractAllRecords(JsonPath jsonPath) {
		List<Map<String, Object>> allRecords = jsonPath.getList("data");
		return allRecords;
	}

	// Create a sheet to store total records per city
	private static void createTotalRecordsSheet(XSSFWorkbook workbook, List<Map<String, Object>> allRecords) {
		XSSFSheet sheet = workbook.createSheet("Total Records per City");

		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("City");
		headerRow.createCell(1).setCellValue("Total Records");

		int rowNum = 1;
		for (Map<String, Object> record : allRecords) {
			String location = record.get("location") != null ? record.get("location").toString() : "";
			String totalRecord = record.get("totalrecord") != null ? record.get("totalrecord").toString() : "";

			Row dataRow = sheet.createRow(rowNum++);
			dataRow.createCell(0).setCellValue(location);
			dataRow.createCell(1).setCellValue(totalRecord.isEmpty() ? 0 : Integer.parseInt(totalRecord));
		}
	}

	// Create a sheet to store emails per city
	private static void createEmailsPerCitySheet(XSSFWorkbook workbook, List<Map<String, Object>> allRecords) {
		XSSFSheet sheet = workbook.createSheet("Emails per City");

		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("City");
		headerRow.createCell(1).setCellValue("Name");
		headerRow.createCell(2).setCellValue("Email");

		int rowNum = 1;
		for (Map<String, Object> record : allRecords) {
			String location = record.get("location") != null ? record.get("location").toString() : "";
			String name = record.get("name") != null ? record.get("name").toString() : "";
			String email = record.get("email") != null ? record.get("email").toString() : "";

			Row dataRow = sheet.createRow(rowNum++);
			dataRow.createCell(0).setCellValue(location);
			dataRow.createCell(1).setCellValue(name);
			dataRow.createCell(2).setCellValue(email);
		}
	}
}
