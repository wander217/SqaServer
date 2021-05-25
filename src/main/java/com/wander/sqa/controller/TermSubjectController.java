package com.wander.sqa.controller;

import com.wander.sqa.dto.group.TermSubjectStatDTO;
import com.wander.sqa.service.group.TermSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/termsubject")
public class TermSubjectController {
    @Autowired
    private TermSubjectService termSubjectService;

    @GetMapping(path = "/all")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public ResponseEntity<List<TermSubjectStatDTO>> getAll(){
        List<TermSubjectStatDTO> termSubjectDTOList = this.termSubjectService.getAll();
        return new ResponseEntity<>(termSubjectDTOList, HttpStatus.OK);
    }
}
