package com.wander.sqa.dto.user;

import com.wander.sqa.dto.base.BaseDTO;

public class AccountDTO extends BaseDTO{
	private String username;
	private String password;
	private RoleDTO role;
	
	public AccountDTO() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RoleDTO getRole() {
		return role;
	}

	public void setRole(RoleDTO role) {
		this.role = role;
	}
}