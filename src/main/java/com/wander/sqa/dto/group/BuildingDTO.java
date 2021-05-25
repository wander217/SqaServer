package com.wander.sqa.dto.group;

import com.wander.sqa.dto.base.BaseDTO;

public class BuildingDTO extends BaseDTO{
	
	private String name;
	
	public BuildingDTO() {
		super();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}