package com.wander.sqa.exception;

import com.wander.sqa.entity.registration.AssignedSubject;

public class OverLimitRegistrationException extends Exception{
	private static final long serialVersionUID = -726930022617851460L;

	public OverLimitRegistrationException(AssignedSubject assignedSubject) {
        super("Bạn chỉ được phép đăng kí tối đa "+assignedSubject.getNumberOfGroup()+" nhóm cho môn học này!");
    }
}
