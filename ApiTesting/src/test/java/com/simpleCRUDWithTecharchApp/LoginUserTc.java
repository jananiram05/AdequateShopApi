package com.simpleCRUDWithTecharchApp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.helpers.UserServiceHelper;

import io.restassured.RestAssured;

public class LoginUserTc extends UserServiceHelper {
	@BeforeMethod
	public void setupTest() {
		// RestAssured.baseURI="https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
		RestAssured.baseURI = getBaseUri();
	}
	@Test
	
	public void TCloginToApi() {
		String token = getToken();
		System.out.println("token========= " + token);
	}

}
