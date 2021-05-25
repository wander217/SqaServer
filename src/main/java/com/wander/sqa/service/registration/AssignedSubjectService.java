package com.wander.sqa.service.registration;

import com.wander.sqa.dto.registration.AssignedSubjectDTO;
import com.wander.sqa.dto.user.TeacherDTO;
import com.wander.sqa.exception.OutOfRegistrationTimeException;
import com.wander.sqa.exception.TermNotFoundException;

import java.util.List;

public interface AssignedSubjectService{
	List<AssignedSubjectDTO> findByTeacher(TeacherDTO tch) throws TermNotFoundException, OutOfRegistrationTimeException;
}