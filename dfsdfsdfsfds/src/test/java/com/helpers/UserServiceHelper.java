package com.helpers;

import static org.hamcrest.Matchers.is;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.constants.Endpoints;
import com.models.AddDataPojoTest;
import com.models.DeletePojoTest;
import com.models.LoginPojoTest;
import com.models.UserPojo;
import com.utility.PropertiesUtility;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class UserServiceHelper {

	public static String getBaseUri() {

		PropertiesUtility propertiesUtility = new PropertiesUtility();

		propertiesUtility.loadFile("applicationDataProperties");

		String uri = propertiesUtility.getPropertyValue("uri");

		return uri;

	}

	public static String getToken() {
		LoginPojoTest login = new LoginPojoTest();
		PropertiesUtility propertiesUtility = new PropertiesUtility();
		propertiesUtility.loadFile("applicationDataProperties");
		String userid = propertiesUtility.getPropertyValue("login.valid.userid");
		String password = propertiesUtility.getPropertyValue("login.empty.password");

		login.setUserName(userid);
		login.setPassword(password);

		Response res = RestAssured.given().contentType(ContentType.JSON).body(login)

				.when().post(Endpoints.LOGIN);
		res.then().statusCode(201);
		res.prettyPrint();

		String token = res.body().jsonPath().getString("[0].token");

		System.out.println("token : " + token);
		return token;

	}

	public static Response getUserData() {
		Header tokenHeader = new Header("token", getToken());
		Response res = RestAssured.given().header(tokenHeader).log().headers().when().get(Endpoints.GET_DATA);
		ArrayList<String> obj = res.body().jsonPath().get("findAll{it->it.userid=='JBFBUgrJ3VQaudaTXM0r'}.accountno");
		System.out.println(obj);
		assertEquals(obj.size(), 5);

		return res;

	}

	public static Response addUserData() {
		AddDataPojoTest addData = new AddDataPojoTest();
		PropertiesUtility propertiesUtility = new PropertiesUtility();
		propertiesUtility.loadFile("applicationDataProperties");
		String accno = propertiesUtility.getPropertyValue("add.accountno");
		String deptno = propertiesUtility.getPropertyValue("add.departmentno");
		String salary = propertiesUtility.getPropertyValue("add.salary");
		String pincode = propertiesUtility.getPropertyValue("add.pincode");
		addData.setAccountno(accno);
		addData.setDepartmentno(deptno);
		addData.setSalary(salary);
		addData.setPincode(pincode);
		Header tokenHeader = new Header("token", getToken());
		Response res = RestAssured.given().header(tokenHeader).contentType(ContentType.JSON).body(addData).when()
				.post(Endpoints.ADD_DATA);

		return res;
	}

	public static Response updateUserdata() {
		UserPojo updatedata = new UserPojo();
		PropertiesUtility propertiesUtility = new PropertiesUtility();
		propertiesUtility.loadFile("applicationDataProperties");
		String accno1 = propertiesUtility.getPropertyValue("update.accountno");
		String deptno1 = propertiesUtility.getPropertyValue("update.departmentno");
		String salary1 = propertiesUtility.getPropertyValue("update.salary");
		String pincode1 = propertiesUtility.getPropertyValue("update.pincode");
		String userid = propertiesUtility.getPropertyValue("update.userid");
		String id = propertiesUtility.getPropertyValue("update.id");
		updatedata.setAccountno(accno1);
		updatedata.setDepartmentno(deptno1);
		updatedata.setSalary(salary1);
		updatedata.setPincode(pincode1);
		updatedata.setUserid(userid);
		updatedata.setId(id);
		List<UserPojo> listofusers = gettingUserdata();
		for (UserPojo userToBeUpdated : listofusers) {
			if (userToBeUpdated.getAccountno().equals(accno1)) {
				updatedata = userToBeUpdated;
				updatedata.setDepartmentno(deptno1);
				break;

			}
		}

		Header tokenHeader = new Header("token", getToken());
		Response res = RestAssured.given().header(tokenHeader).contentType(ContentType.JSON).body(updatedata).when()
				.put(Endpoints.UPDATE_DATA);

		return res;

	}

	public static Response deleteUserdata() {

		PropertiesUtility propertiesUtility = new PropertiesUtility();
		propertiesUtility.loadFile("applicationDataProperties");
		String accno1 = propertiesUtility.getPropertyValue("update.accountno");

		String userid = propertiesUtility.getPropertyValue("update.userid");
		String id = propertiesUtility.getPropertyValue("update.id");
		List<UserPojo> listofusers = gettingUserdata();
		for (UserPojo user : listofusers) {
			if (user.getAccountno().equals(accno1)) {
				id = user.getId();
				userid = user.getUserid();
				break;

			}
		}
		DeletePojoTest deletedata = new DeletePojoTest();

		deletedata.setUserid(userid);
		deletedata.setId(id);

		Header tokenHeader = new Header("token", getToken());
		Response res = RestAssured.given().header(tokenHeader).contentType(ContentType.JSON).body(deletedata).when()
				.delete(Endpoints.DELETE_DATA);

		return res;
	}

	public void pretty(Response res) {
		res.prettyPrint();
	}

	public void statusSuccess(Response res, int code) {
		res.then().statusCode(code);
	}

	public void successValidation(Response res) {
		res.then().body("status", is("success"));

	}

	public void resSchemaValidator(Response res) {
		res.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getDataSchem.json"));

	}

	public static List<UserPojo> gettingUserdata() {

		Header tokenHeader = new Header("token", getToken());
		Response res = RestAssured.given().header(tokenHeader).contentType(ContentType.JSON).when()
				.get(Endpoints.GET_DATA);
		
		ArrayList<String> obj = res.body().jsonPath().get("findAll{it->it.userid=='JBFBUgrJ3VQaudaTXM0r'}.accountno");
		System.out.println(obj);
		assertEquals(obj.size(), 5);
	

		UserPojo[] userarr = res.as(UserPojo[].class);
		List<UserPojo> list = Arrays.asList(userarr);
		return list;

	}

}
