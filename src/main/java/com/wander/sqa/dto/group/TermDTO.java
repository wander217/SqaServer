package com.wander.sqa.dto.group;

import com.wander.sqa.dto.base.BaseDTO;

import java.sql.Date;
import java.sql.Timestamp;

public class TermDTO extends BaseDTO{

	private Date startDate;
	private Date endDate;
	private Timestamp startRegTime;
	private Timestamp endRegTime;
	
	public TermDTO() {
		super();
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Timestamp getStartRegTime() {
		return startRegTime;
	}

	public void setStartRegTime(Timestamp startRegTime) {
		this.startRegTime = startRegTime;
	}

	public Timestamp getEndRegTime() {
		return endRegTime;
	}

	public void setEndRegTime(Timestamp endRegTime) {
		this.endRegTime = endRegTime;
	}
}