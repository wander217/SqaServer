package com.wander.sqa.controller;

import com.wander.sqa.dto.group.TermSubjectDTO;
import com.wander.sqa.dto.user.SubjectTeacherStatDTO;
import com.wander.sqa.dto.user.TeacherStatDTO;
import com.wander.sqa.service.user.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "/teacher")
public class TeacherController {
	@Autowired
	private TeacherService teacherService;

	//Lấy danh sách đăng ký đủ theo môn
	@PostMapping(path = "/rfind")
	@PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
	public ResponseEntity<List<SubjectTeacherStatDTO>> findRemember(@RequestBody TermSubjectDTO t) {
		List<SubjectTeacherStatDTO> teacherDTOList = this.teacherService.findRemember(t.getId());
		return new ResponseEntity<>(teacherDTOList,HttpStatus.OK);
	}

	//Lấy danh sách đăng kí thiếu theo môn
	@PostMapping(path = "/ffind")
	@PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
	public ResponseEntity<List<SubjectTeacherStatDTO>> findForgot(@RequestBody TermSubjectDTO t) {
		List<SubjectTeacherStatDTO> teacherDTOList = this.teacherService.findForgot(t.getId());
		return new ResponseEntity<>(teacherDTOList,HttpStatus.OK);
	}

	//Lấy thống kê đăng kí theo giảng viên
	@GetMapping(path = "/all")
	@PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
	public ResponseEntity<List<TeacherStatDTO>> getAll(){
		List<TeacherStatDTO> teacherDTOList = this.teacherService.getAll();
		return new ResponseEntity<>(teacherDTOList,HttpStatus.OK);
	}
}