package com.wander.sqa.controller;

import com.wander.sqa.dto.group.TermSubjectDTO;
import com.wander.sqa.dto.registration.RegistrationDTO;
import com.wander.sqa.dto.registration.RegistrationRequestDTO;
import com.wander.sqa.dto.user.TeacherDTO;
import com.wander.sqa.exception.*;
import com.wander.sqa.service.registration.RegistrationService;
import com.wander.sqa.service.user.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/registration")
public class RegistrationController{
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private TeacherService teacherService;

	//Lấy danh sách đã đăng kí theo môn được chọn
	@PostMapping(path = "/tsfind")
	@PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE','TEACHER')")
	public ResponseEntity<List<RegistrationDTO>> findAllEnableByTermSubject(@RequestBody TermSubjectDTO ts) {
		List<RegistrationDTO> registrationDTOList = this.registrationService.findAllEnableByTermSubject(ts);
		return new ResponseEntity<>(registrationDTOList, HttpStatus.OK);
	}

	//Admin đăng kí cho giảng viên
	@PostMapping(path = "/regadmin")
	@PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
	public ResponseEntity<String> adminRegistration(@RequestBody RegistrationRequestDTO rs)
			throws DuplicatedTimetableException, OverLimitGroupException,
			AssignmentException, OverLimitRegistrationException,
			OutOfRegistrationTimeException, TermNotFoundException {
		this.registrationService.doRegistration(rs);
		return new ResponseEntity<>("Đăng kí thành công",HttpStatus.OK);
	}

	//Giảng viên tự đăng kí
	//Cần xác thực thêm id bằng cách lấy lên lại từ tên đăng nhập
	@PostMapping(path = "/regteacher")
	@PreAuthorize("hasRole('TEACHER')")
	public ResponseEntity<String> teacherRegistration(Authentication authentication,@RequestBody RegistrationRequestDTO rs)
			throws UsernameNotFoundException, DuplicatedTimetableException, OverLimitGroupException,
			AssignmentException, OverLimitRegistrationException,
			OutOfRegistrationTimeException, TermNotFoundException {
		TeacherDTO teacherDTO = this.teacherService.findByUsername(authentication.getName());
		rs.setTeacherId(teacherDTO.getId());
		this.registrationService.doRegistration(rs);
		return new ResponseEntity<>("Đăng kí thành công",HttpStatus.OK);
	}

	//Lấy lịch sử đăng kí của giảng viên
	@PostMapping(path = "/tchfind")
	@PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
	public ResponseEntity<List<RegistrationDTO>> findAllByTeacher(@RequestBody TeacherDTO t){
		List<RegistrationDTO> registrationDTOList = this.registrationService.findAllByTeacher(t);
		return new ResponseEntity<>(registrationDTOList, HttpStatus.OK);
	}

	//Xử lý ngoại lệ
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

	//Đăng kí môn không được giao
	@ExceptionHandler(AssignmentException.class)
	public ResponseEntity<String> handleAssignmentException(){
		return new ResponseEntity<>("Không được phép đăng kí môn học này!",HttpStatus.BAD_REQUEST);
	}

	//Đăng kí quá giới hạn được giao
	@ExceptionHandler(OverLimitRegistrationException.class)
	public ResponseEntity<String> handleOverLimitRegistrationException(OverLimitRegistrationException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}

	//Đăng kí quá giới hạn của nhóm
	@ExceptionHandler(OverLimitGroupException.class)
	public ResponseEntity<String> handleOverLimitGroupException(OverLimitGroupException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}

	//Đăng kí bị trùng lịch với đăng kí trước đó
	@ExceptionHandler(DuplicatedTimetableException.class)
	public ResponseEntity<String> handleDuplicatedTimetableException(DuplicatedTimetableException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
}