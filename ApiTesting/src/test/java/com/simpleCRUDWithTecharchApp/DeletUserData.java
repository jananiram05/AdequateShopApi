package com.simpleCRUDWithTecharchApp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.helpers.UserServiceHelper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeletUserData extends UserServiceHelper {

	@BeforeMethod
	public void setupTest() {
		// RestAssured.baseURI="https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
		RestAssured.baseURI = getBaseUri();
	}
	@Test(priority = 4)
	public void deleteUserData() {
		Response deleteuser = deleteUserdata();
		statusSuccess(deleteuser, 200);
		pretty(deleteuser);
		successValidation(deleteuser);

	}
	
}
