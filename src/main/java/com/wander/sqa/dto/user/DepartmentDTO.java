package com.wander.sqa.dto.user;

import com.wander.sqa.dto.base.BaseDTO;

public class DepartmentDTO extends BaseDTO{
	private String name;
	
	public DepartmentDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}