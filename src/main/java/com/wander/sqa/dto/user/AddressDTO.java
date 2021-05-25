package com.wander.sqa.dto.user;

import com.wander.sqa.dto.base.BaseDTO;

public class AddressDTO extends BaseDTO{
	private String street;
	private String district;
	private String city;
	private String description;

	public AddressDTO() {
		super();
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}