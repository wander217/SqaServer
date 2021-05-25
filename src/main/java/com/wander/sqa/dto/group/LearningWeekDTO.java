package com.wander.sqa.dto.group;

import com.wander.sqa.dto.base.BaseDTO;

public class LearningWeekDTO extends BaseDTO{

	private boolean isDesist;
	private TermWeekDTO termWeek;
	
	public LearningWeekDTO() {
		super();
	}

	public boolean isDesist() {
		return isDesist;
	}

	public void setDesist(boolean isDesist) {
		this.isDesist = isDesist;
	}

	public TermWeekDTO getTermWeek() {
		return termWeek;
	}

	public void setTermWeek(TermWeekDTO termWeek) {
		this.termWeek = termWeek;
	}
}