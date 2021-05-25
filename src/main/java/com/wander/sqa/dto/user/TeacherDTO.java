package com.wander.sqa.dto.user;

public class TeacherDTO extends MemberDTO {

	DepartmentDTO departmentDTO;
	private String tchCode;
	private DepartmentDTO department;

	public String getTchCode() {
		return this.tchCode;
	}

	public void setTchCode(String tchCode) {
		this.tchCode = tchCode;
	}

	public DepartmentDTO getDepartment() {
		return this.department;
	}

	public void setDepartment(DepartmentDTO department) {
		this.department = department;
	}

}