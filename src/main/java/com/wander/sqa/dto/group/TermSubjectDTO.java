package com.wander.sqa.dto.group;

import com.wander.sqa.dto.base.BaseDTO;

public class TermSubjectDTO extends BaseDTO{
	private int credit;
	private SubjectDTO subject;
	private TermDTO term;
	
	public TermSubjectDTO() {
		super();
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public SubjectDTO getSubject() {
		return subject;
	}

	public void setSubject(SubjectDTO subject) {
		this.subject = subject;
	}

	public TermDTO getTerm() {
		return term;
	}

	public void setTerm(TermDTO term) {
		this.term = term;
	}
}