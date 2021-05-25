package com.wander.sqa.exception;

import com.wander.sqa.entity.group.SubjectGroup;

public class DuplicatedTimetableException extends Exception{
	private static final long serialVersionUID = 546497599215779816L;

	public DuplicatedTimetableException(SubjectGroup subjectGroup) {
        super("Nhóm đăng kí bị trùng lịch với nhóm "+subjectGroup.getCode()
                +" của môn "+subjectGroup.getTermSubject().getSubject().getName());
    }
}
