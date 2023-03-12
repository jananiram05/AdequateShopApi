package com.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginPojoTest {
	@JsonProperty("username")
	private String username;
	@JsonProperty("password")
	private String password;
	public String getUserName() {
		return username;
	}
	public void setUserName(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
