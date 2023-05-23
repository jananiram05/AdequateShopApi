package com.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddDataPojoTest {
	@JsonProperty("accountno")
	private String accountno;
	
	@JsonProperty("departmentno")
	private String departmentno;
	
	@JsonProperty("salary")
	private String salary;
	
	@JsonProperty("pincode")
	private String pincode;

	public String getAccountno() {
		return accountno;
	}

	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}

	public String getDepartmentno() {
		return departmentno;
	}

	public void setDepartmentno(String departmentno) {
		this.departmentno = departmentno;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	

}
