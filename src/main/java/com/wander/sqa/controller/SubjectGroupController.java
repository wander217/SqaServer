package com.wander.sqa.controller;

import com.wander.sqa.dto.group.SubjectGroupDTO;
import com.wander.sqa.dto.group.TermSubjectDTO;
import com.wander.sqa.service.group.SubjectGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/subjectgroup")
public class SubjectGroupController {
	@Autowired
	private SubjectGroupService subjectGroupService;

	//Lấy nhóm môn học theo môn học
	@PostMapping(path = "/tsfind")
	@PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE','TEACHER')")
	public ResponseEntity<List<SubjectGroupDTO>> findByTermSubject(@RequestBody TermSubjectDTO t) {
		List<SubjectGroupDTO> subjectGroupDTOList = this.subjectGroupService.findByTermSubject(t);
		return new ResponseEntity<>(subjectGroupDTOList, HttpStatus.OK);
	}
}