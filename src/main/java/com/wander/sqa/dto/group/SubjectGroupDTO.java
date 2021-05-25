package com.wander.sqa.dto.group;

import com.wander.sqa.dto.base.BaseDTO;

import java.util.ArrayList;
import java.util.List;

public class SubjectGroupDTO extends BaseDTO{

	private String learningDay;
	private int numberOfTeacher;
	private String code;
	private List<GroupInfoDTO> groupInfo =  new ArrayList<>();
	
	public SubjectGroupDTO() {
		super();
	}

	public String getLearningDay() {
		return learningDay;
	}

	public void setLearningDay(String learningDay) {
		this.learningDay = learningDay;
	}

	public int getNumberOfTeacher() {
		return numberOfTeacher;
	}

	public void setNumberOfTeacher(int numberOfTeacher) {
		this.numberOfTeacher = numberOfTeacher;
	}

	public List<GroupInfoDTO> getGroupInfo() {
		return groupInfo;
	}

	public void setGroupInfo(List<GroupInfoDTO> groupInfo) {
		this.groupInfo = groupInfo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}