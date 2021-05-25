package com.wander.sqa.dao;


import com.wander.sqa.entity.registration.AssignedSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssignedSubjectDAO extends JpaRepository<AssignedSubject,Long> {

	@Query("SELECT a FROM AssignedSubject a " +
			"WHERE a.teacher.id=:tchId " +
			"AND a.termSubject.term.id=:tId")
	List<AssignedSubject> findByTeacher(@Param("tchId") long tchId,@Param("tId")long tId);

	//Tìm kiêm phân công theo nhóm môn học và giảng viên
	@Query("SELECT a FROM AssignedSubject a" +
			" WHERE a.teacher.id=:tchId AND a.termSubject.id" +
			" IN (SELECT s.termSubject.id FROM SubjectGroup s WHERE s.id=:sgId)")
	AssignedSubject findBySubjectGroupAndTeacher(@Param("sgId") long sgId,@Param("tchId") long tchId);
}