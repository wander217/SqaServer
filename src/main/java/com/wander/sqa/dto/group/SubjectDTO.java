package com.wander.sqa.dto.group;

import com.wander.sqa.dto.base.BaseDTO;

public class SubjectDTO extends BaseDTO{

	private String name;
	
	public SubjectDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}