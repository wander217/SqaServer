package com.wander.sqa.exception;

import com.wander.sqa.entity.registration.AssignedSubject;

public class OverLimitRegistrationException extends Exception{
    public OverLimitRegistrationException(AssignedSubject assignedSubject) {
        super("Bạn chỉ được phép đăng kí tối đa "+assignedSubject.getNumberOfGroup()+" nhóm cho môn học này!");
    }
}
