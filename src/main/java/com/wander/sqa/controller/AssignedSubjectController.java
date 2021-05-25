package com.wander.sqa.controller;

import com.wander.sqa.dto.registration.AssignedSubjectDTO;
import com.wander.sqa.dto.user.TeacherDTO;
import com.wander.sqa.exception.OutOfRegistrationTimeException;
import com.wander.sqa.exception.TermNotFoundException;
import com.wander.sqa.service.registration.AssignedSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/assignedsubject")
public class AssignedSubjectController {
	@Autowired
	private AssignedSubjectService assignedSubjectService;

	//Lấy danh sách các môn đã giao theo giảng viên
	@PostMapping(path = "/tchfind")
	@PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE','TEACHER')")
	public ResponseEntity<List<AssignedSubjectDTO>> findByTeacher(@RequestBody TeacherDTO tch)
			throws OutOfRegistrationTimeException, TermNotFoundException {
		List<AssignedSubjectDTO> assignedSubjectDTOList = this
				.assignedSubjectService.findByTeacher(tch);
		return new ResponseEntity<>(assignedSubjectDTOList, HttpStatus.OK);
	}

	//Chưa có kì mới
	@ExceptionHandler(TermNotFoundException.class)
	public ResponseEntity<String> handleTermNotFoundException(){
		return new ResponseEntity<>("Chưa có kì mới!",HttpStatus.BAD_REQUEST);
	}

	//Đăng kí quá giới hạn thời gian đăng kí
	@ExceptionHandler(OutOfRegistrationTimeException.class)
	public ResponseEntity<String> handleOutOfRegistrationTimeException(OutOfRegistrationTimeException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
}