package com.wander.sqa.dto.group;

import com.wander.sqa.dto.base.BaseDTO;

import java.sql.Date;

public class TermWeekDTO extends BaseDTO{
	private Date startDate;
	private Date endDate;
	private TermDTO term;
	
	public TermWeekDTO() {
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

	public TermDTO getTerm() {
		return term;
	}

	public void setTerm(TermDTO term) {
		this.term = term;
	}
}