package com.wander.sqa.service.registration;

import com.wander.sqa.dto.group.TermSubjectDTO;
import com.wander.sqa.dto.registration.RegistrationDTO;
import com.wander.sqa.dto.registration.RegistrationRequestDTO;
import com.wander.sqa.dto.user.TeacherDTO;
import com.wander.sqa.exception.*;

import java.util.List;

public interface RegistrationService{
	//Tìm kiếm tất cả những đăng kí còn hiệu lực theo môn học
	List<RegistrationDTO> findAllEnableByTermSubject(TermSubjectDTO ts);
	//Thực hiện đăng kí môn học
	void doRegistration(RegistrationRequestDTO rs)
            throws OverLimitGroupException, DuplicatedTimetableException,
			AssignmentException, OverLimitRegistrationException,
			TermNotFoundException, OutOfRegistrationTimeException;
	//Thực hiện tìm kiếm tất cả đăng kí theo giảng viên
	//Để show lịch sử đăng kí
	List<RegistrationDTO> findAllByTeacher(TeacherDTO t);
}