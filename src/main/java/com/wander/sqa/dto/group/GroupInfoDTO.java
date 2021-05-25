package com.wander.sqa.dto.group;

import com.wander.sqa.dto.base.BaseDTO;

public class GroupInfoDTO extends BaseDTO{

	private String room;
	private String shift;
	private String learningWeek ;
	
	public GroupInfoDTO() {
		super();
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public String getLearningWeek() {
		return learningWeek;
	}

	public void setLearningWeek(String learningWeek) {
		this.learningWeek = learningWeek;
	}
}