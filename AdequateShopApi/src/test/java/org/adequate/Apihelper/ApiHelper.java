package org.adequate.Apihelper;

import static org.hamcrest.Matchers.is;

import java.util.Random;

import org.adequate.constants.Endpoints;
import org.adequate.requestPOJO.AddDataPojo;
import org.adequate.requestPOJO.LoginPojo;
import org.adequate.util.PropertiesUtility;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.Logger;

public class ApiHelper {
	static RequestSpecification reqSpec;
	static String token = "";
	static Random random = new Random();
	protected static Logger logger;

	public void statusSuccess(Response res, int code) {
		res.then().statusCode(code);
	}

	public void resSchemaValidator(Response res) {
		res.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getDataSchema.json"));

	}

	public static void loginSchemaValidator(Response res) {
		res.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("loginSchema.json"));

	}

	public static String getBaseUri() {

		PropertiesUtility propertiesUtility = new PropertiesUtility();

		propertiesUtility.loadFile("applicationDataProperties");

		String uri = propertiesUtility.getPropertyValue("uri");

		return uri;

	}

	public static String getToken() {
		LoginPojo login = new LoginPojo();
		// logger.info("addid data");
		// logger.info("getting token");

		PropertiesUtility propertiesUtility = new PropertiesUtility();
		propertiesUtility.loadFile("applicationDataProperties");
		String userid = propertiesUtility.getPropertyValue("login.valid.email");
		String password = propertiesUtility.getPropertyValue("login.valid.password");
		// logger.info("adding below data");

		login.setEmail(userid);
		login.setPassword(password);

		Response res = RestAssured.given().contentType(ContentType.JSON).body(login).log().all()

				.when().post(Endpoints.LOGIN);
		loginSchemaValidator(res);
		res.then().statusCode(200);

		// res.then().statusCode(HttpStatus.C);

		res.prettyPrint();

		token = res.body().jsonPath().getString("data.Token");

		System.out.println("token : " + token);
		return token;

	}

	public static String invalidLogin() {
		LoginPojo login = new LoginPojo();

		PropertiesUtility propertiesUtility = new PropertiesUtility();
		propertiesUtility.loadFile("applicationDataProperties");
		String userid = propertiesUtility.getPropertyValue("login.Invalid.email");
		String password = propertiesUtility.getPropertyValue("login.Invalid.password");

		login.setEmail(userid);
		login.setPassword(password);
		logger.info("adding below data");

		Response res = RestAssured.given().contentType(ContentType.JSON).body(login).log().all()

				.when().post(Endpoints.LOGIN);
		res.then().statusCode(400);
		// res.then().statusCode(HttpStatus.C);

		res.prettyPrint();
		return userid;

	}

	public static Response getUserData() {
		// Header tokenHeader = new Header("token", getToken());
		// token = getToken();
		String tokenHeader = getToken();

		Response res = RestAssured.given().header("Authorization", "Bearer " + tokenHeader).log().all().when()
				.get(Endpoints.GET_DATA);
		// Response res =
		// RestAssured.given().header(tokenHeader).log().headers().when().get(Endpoints.GET_DATA);
		res.then().statusCode(200);
		res.prettyPrint();

		return res;

	}

	public static Response addUser() {
		AddDataPojo addData = new AddDataPojo();
		PropertiesUtility propertiesUtility = new PropertiesUtility();
		propertiesUtility.loadFile("applicationDataProperties");
		String name = propertiesUtility.getPropertyValue("add.name");
		String email = propertiesUtility.getPropertyValue("add.email");
		String location = propertiesUtility.getPropertyValue("add.location");

		String fname = name + random.nextInt(200);
		String fakeEmail = email + random.nextInt(200);
		String fakeloc = location + random.nextInt(200);

		

		addData.setName(fname);
		addData.setEmail(fakeEmail);
		addData.setLocation(fakeloc);
		System.out.println(token);
		String tokenHeader = getToken();
		Response res = RestAssured.given().header("Authorization", "Bearer " + tokenHeader)
				.header("Content-Type", "application/Json").body(addData).log().all().when().post(Endpoints.ADD_USER);
		res.prettyPrint();
		res.then().statusCode(201);
		System.out.println(token);

		return res;

	}

	public static Response deleteUserById() {

		String tokenHeader = getToken();

		Response res = RestAssured.given().header("Authorization", "Bearer " + tokenHeader).log().all().when()
				.delete(Endpoints.DELETE_USER);
		// Response res =
		// RestAssured.given().header(tokenHeader).log().headers().when().get(Endpoints.GET_DATA);
		res.then().statusCode(405);
		res.prettyPrint();
		return res;

	}

	public static Response updateUserById() {

		String tokenHeader = getToken();

		Response res = RestAssured.given().header("Authorization", "Bearer " + tokenHeader).log().all().when()
				.put(Endpoints.UPDATE_USER);
		// Response res =
		// RestAssured.given().header(tokenHeader).log().headers().when().get(Endpoints.GET_DATA);
		res.then().statusCode(405);
		res.prettyPrint();
		return res;

	}

	public void pretty(Response res) {
		res.prettyPrint();
	}

	public void successValidation(Response res) {
		res.then().body("status", is("success"));

	}
	

}
