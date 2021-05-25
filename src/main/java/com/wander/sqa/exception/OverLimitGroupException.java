package com.wander.sqa.exception;

import com.wander.sqa.entity.group.SubjectGroup;

public class OverLimitGroupException extends Exception{
	private static final long serialVersionUID = -7396102151833744779L;

	public OverLimitGroupException(SubjectGroup subjectGroup) {
        super("Nhóm "+subjectGroup.getCode()+" chỉ được tối đa "
                +subjectGroup.getNumberOfTeacher()+" giảng viên đăng kí!");
    }
}
