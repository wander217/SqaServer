package com.wander.sqa.dto.registration;

import com.wander.sqa.dto.base.BaseDTO;

import java.sql.Timestamp;

public class RegistrationDTO extends BaseDTO{
	private Timestamp regTime;
	private boolean isEnable;
	private long subjectGroupId;
	private String subjectGroupCode;
	private String subjectName;
	private long teacherId;
	
	public RegistrationDTO() {
		super();
	}

	public Timestamp getRegTime() {
		return regTime;
	}

	public void setRegTime(Timestamp regTime) {
		this.regTime = regTime;
	}

	public boolean isEnable() {
		return isEnable;
	}

	public void setEnable(boolean enable) {
		isEnable = enable;
	}

	public long getSubjectGroupId() {
		return subjectGroupId;
	}

	public void setSubjectGroupId(long subjectGroupId) {
		this.subjectGroupId = subjectGroupId;
	}

	public String getSubjectGroupCode() {
		return subjectGroupCode;
	}

	public void setSubjectGroupCode(String subjectGroupCode) {
		this.subjectGroupCode = subjectGroupCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(long teacherId) {
		this.teacherId = teacherId;
	}
}