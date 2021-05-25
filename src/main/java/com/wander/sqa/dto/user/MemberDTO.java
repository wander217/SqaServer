package com.wander.sqa.dto.user;

import com.wander.sqa.dto.base.BaseDTO;

public class MemberDTO extends BaseDTO{
	
	private String email;
	private String phone;
	private AddressDTO address;
	private FullnameDTO fullname;
	private AccountDTO account;
	
	public MemberDTO() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public FullnameDTO getFullname() {
		return fullname;
	}

	public void setFullname(FullnameDTO fullname) {
		this.fullname = fullname;
	}

	public AccountDTO getAccount() {
		return account;
	}

	public void setAccount(AccountDTO account) {
		this.account = account;
	}
}