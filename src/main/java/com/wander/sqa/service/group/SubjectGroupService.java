package com.wander.sqa.service.group;

import com.wander.sqa.dto.group.SubjectGroupDTO;
import com.wander.sqa.dto.group.TermSubjectDTO;

import java.util.List;

public interface SubjectGroupService{
	List<SubjectGroupDTO> findByTermSubject(TermSubjectDTO t);
}