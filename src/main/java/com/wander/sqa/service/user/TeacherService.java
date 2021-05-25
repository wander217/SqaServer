package com.wander.sqa.service.user;

import com.wander.sqa.dto.user.SubjectTeacherStatDTO;
import com.wander.sqa.dto.user.TeacherDTO;
import com.wander.sqa.dto.user.TeacherStatDTO;
import com.wander.sqa.exception.UsernameNotFoundException;

import java.util.List;

public interface TeacherService{
	TeacherDTO findByUsername(String u) throws UsernameNotFoundException;
	List<SubjectTeacherStatDTO> findRemember(long id);
	List<SubjectTeacherStatDTO> findForgot(long id);
	List<TeacherStatDTO> getAll();
}