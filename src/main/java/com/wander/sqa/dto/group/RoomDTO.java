package com.wander.sqa.dto.group;

import com.wander.sqa.dto.base.BaseDTO;

public class RoomDTO extends BaseDTO{

	private String name;
	private BuildingDTO building;
	
	public RoomDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BuildingDTO getBuilding() {
		return building;
	}

	public void setBuilding(BuildingDTO building) {
		this.building = building;
	}
}