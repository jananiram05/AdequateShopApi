package com.university;

import java.io.File;
import java.util.ArrayList;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class UniversityTc {
	@Test
	public void readingData() {
		String curDir = System.getProperty("user.dir");
		File file = new File(curDir + "/src/test/resources/university.json");
		JsonPath data = new JsonPath(file);
		
		//1.find value of username
		ArrayList<String> username=data.get("username");
		System.out.println(" value of username: "+username);
		//2.find values of all sessionid
		ArrayList<String>allStudentid=data.get("students.id");
		System.out.println("values of all studentid: "+allStudentid);
		//3.find  last value of sessionid
		int lastStudentid=data.get("[1].students[1].id");
		System.out.println("last value of sessionid: "+lastStudentid);
		//4.find username of 1st university
		String userNameFirstUniv=data.get("[0].username");
		System.out.println("find username of 1st university: "+userNameFirstUniv);
		//5.find name of 2nd university
		String userName2ndUniv=data.get("[1].name");
		System.out.println("find name of 2nd university: "+userNameFirstUniv);
		//6.find number of universites in the list
		ArrayList<String>univList=data.get("size");
		System.out.println(" number of universites in the list: "+univList);
		//7.find all marks of second student of 1st university
		ArrayList<Integer>ss1stuniv=data.get("[1].students[1].marks");
		System.out.println("find all marks of second student of 1st university: "+ss1stuniv);
		//8.find the second state(in the address) value of first student of 1st university

		ArrayList<String>data1=data.get("students[0].adresss[1].state");
		System.out.println("second state(in the address) value of first student of 1st university: "+data1);
		
		//9.find the second contact value of second student of 2nd university

		String contactValue = data.get("[1].students[1].contact[1]");
		System.out.println("second contact value of second student of 2nd university: "+contactValue);
		
		//10.find all cities of second student of 2nd university
		
		ArrayList<String>data3=data.get("[1].students[1].adresss.city");
		System.out.println("all cities of second student of 2nd university: "+data3);
		
		//11.find names of all students
		
		ArrayList<String>data4=data.get("students.name");
		System.out.println("find names of all students: "+data4);
		
		//12.find contacts of all students

		ArrayList<Integer>data5=data.get("students.contact");
		System.out.println("contacts of all students: "+data5);
		
		//13.find adresses of first student of 1st university

		ArrayList<String>data6=data.get("[0].students[0].adresss");
		System.out.println("adresses of first student of 1st university: "+data6);
		
		//14.find number of adresses of second student of 2nd university
		int data7 = data.get("[1].students[1].adresss.size()");
		System.out.println("number of adresses of second student of 2nd university: "+data7);
		//15.find the number of student records of 2nd university
		int data8=data.get("[1].students.size()");
		System.out.println(" the number of student records of 2nd university: "+data8);
		
		//16.find the number of contacts of second student of 1st university
		int data9=data.get("[0].students[1].contact.size()");
		System.out.println("the number of contacts of second student of 1st university: "+data9);

		
		







		




	}

}
