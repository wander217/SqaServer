package com.wander.sqa.dto.user;

import com.wander.sqa.dto.base.BaseDTO;

public class FullnameDTO extends BaseDTO{
	private String firstname;
	private String lastname;
	private String middlename;
	
	public FullnameDTO() {
		super();
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
}