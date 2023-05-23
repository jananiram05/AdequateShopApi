package com.tests;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
		// 1. Find the total number of user records existing in the response(print to
		// console)
		System.out.println("total number of records: " + res.body().jsonPath().get("size()"));
		// 2. Find the total number of records existing for the given user id
		// laCGA21I8UVpjNbEFcP4
		ArrayList<String> listOfaccounts = res.body().jsonPath()
				.get("findAll{it->it.userid=='laCGA21I8UVpjNbEFcP4'}.accountno");
		System.out.println("ids are: " + listOfaccounts.size());
		// 3. Find the sum of all salary(note: salary is in string) of the given user id
		// laCGA21I8UVpjNbEFcP4
		List<String> salaries = res.body().jsonPath().getList("findAll{it->it.userid=='laCGA21I8UVpjNbEFcP4'}.salary");
		System.out.println("salaries list which for particular id " + salaries);

		int sum = 0;
		for (String salary : salaries) {
			sum += Integer.parseInt(salary);
			// sum =sum+ salary;
		}
		System.out.println("Sum of salaries: " + sum);

		// System.out.println(salary);
		// 4. Find the all records for the given dept no 9
		// ArrayList<String> records =
		// res.body().jsonPath().get("findAll{it->it.departmentno == '9'}");
		// System.out.println("the all records for the given dept no 9: " + records);
		// 5. validate all the id for the given user id lzQHg4ywe0MI87vM7fpF as
		// {sZo9DIeowhov16XdJ1k8,xcUd3LpoVlEqhYsJ4Iea,gpq2G8QLEFmf611v9NpB.KplwvzQFWJd97VeB5atu,bIrDlvlxI1sAAcgon5SR}
		ArrayList<String> id = res.body().jsonPath().get("findAll{it->it.userid=='lzQHg4ywe0MI87vM7fpF'}.id");
		System.out.println(id);
		// 6. Find the avg salary for the given dept no 3 (note: salary is in string)
		List<Object> salaryStrings = res.body().jsonPath()
				.getList("findAll{it->!(it.salary=='') && (it.departmentno == '3')}.salary");
		
		salaryStrings.stream().forEach(x -> System.out.println(x));
		
		  BigInteger sum1 = BigInteger.ZERO; 
		  for (Object salaryString : salaryStrings)
		  { 
			    
			  if (salaryString instanceof String) {
				  
			  //int salary = Integer.parseInt(salaryString);
				String s=  (String)salaryString;
				s =s.replace(",","");
			   BigInteger salary = new		  BigInteger(s);
			   sum1 = sum1.add(salary);
			  }
			  
			  if (salaryString instanceof Integer) {
				  
				  //int salary = Integer.parseInt(salaryString); 
				  BigInteger   salary =   BigInteger.valueOf((Integer)salaryString);
				  sum1 = sum1.add(salary);
				  }
		   } BigInteger size =
		  BigInteger.valueOf(salaryStrings.size());
		  
		  BigInteger average = sum1.divide(size);
		  System.out.println("avg of salaries: " + average);
		  
		  

		UserDataResponsePojo[] data = res.as(UserDataResponsePojo[].class);
		System.out.println("number of records " + data.length);
		statusSuccess(res, 200);
		// pretty(res);

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
