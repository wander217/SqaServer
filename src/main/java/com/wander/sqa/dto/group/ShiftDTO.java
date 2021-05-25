package com.wander.sqa.dto.group;

import com.wander.sqa.dto.base.BaseDTO;

import java.sql.Time;

public class ShiftDTO extends BaseDTO{

	private String name;
	private Time startTime;
	private Time endTime;
	
	public ShiftDTO() {
		super();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
}