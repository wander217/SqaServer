package com.wander.sqa.dao;

import com.wander.sqa.entity.group.SubjectGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectGroupDAO extends JpaRepository<SubjectGroup,Long> {
	@Query("SELECT s FROM SubjectGroup s WHERE s.termSubject.id=:id")
	List<SubjectGroup> findByTermSubject(@Param("id") long id);
}