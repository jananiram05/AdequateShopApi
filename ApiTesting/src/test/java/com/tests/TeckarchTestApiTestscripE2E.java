package com.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.helpers.UserServiceHelper;
import com.models.UserDataResponsePojo;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@Test
public class TeckarchTestApiTestscripE2E extends UserServiceHelper {
	@BeforeClass
	public void setupTest() {
		RestAssured.baseURI = getBaseUri();
	}

	@Test(priority = 0)
	public void TCloginToApi() {

		String token = getToken();
		System.out.println("token========= " + token);
	}

	@Test(priority = 1)
	public void TCgetUserData() {

		Response res = getUserData();
		resSchemaValidator(res);
		
		UserDataResponsePojo[] data=res.as(UserDataResponsePojo[].class);
		System.out.println("number of records "+ data.length);
		statusSuccess(res, 200);
		//pretty(res);
		

	}

	@Test(priority = 2)
	public void TCaddUserData() {

		Response adduser = addUserData();
		statusSuccess(adduser, 201);
		pretty(adduser);
		successValidation(adduser);

	}

	@Test(priority = 3)
	public void TCupdateUserData() {

		Response updateuser = updateUserdata();
		statusSuccess(updateuser, 200);
		pretty(updateuser);
		successValidation(updateuser);

	}

	@Test(priority = 4)
	public void deleteUserData() {

		Response deleteuser = deleteUserdata();
		statusSuccess(deleteuser, 200);
		pretty(deleteuser);
		successValidation(deleteuser);

	}

}
