package com.wander.sqa.dto.user;

import com.wander.sqa.dto.base.BaseDTO;

public class RoleDTO extends BaseDTO{

	private String name;

	public RoleDTO() {
		super();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}