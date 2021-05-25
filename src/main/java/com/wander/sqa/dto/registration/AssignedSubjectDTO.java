package com.wander.sqa.dto.registration;

import com.wander.sqa.dto.base.BaseDTO;

public class AssignedSubjectDTO extends BaseDTO{

	private long id;
	private String name;
	private int numberOfGroup;
	private long numberOfRegister;

	public AssignedSubjectDTO() {
		super();
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfGroup() {
		return numberOfGroup;
	}

	public void setNumberOfGroup(int numberOfGroup) {
		this.numberOfGroup = numberOfGroup;
	}

	public long getNumberOfRegister() {
		return numberOfRegister;
	}

	public void setNumberOfRegister(long numberOfRegister) {
		this.numberOfRegister = numberOfRegister;
	}
}