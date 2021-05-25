package com.wander.sqa.dao;


import com.wander.sqa.entity.registration.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegistrationDAO extends JpaRepository<Registration,Long> {
	//Tìm kiếm cho việc hiển thị ra những đăng ki của nhóm
	@Query("SELECT r FROM Registration r " +
			"WHERE r.isEnable=true " +
			"AND r.subjectGroup.termSubject.id=:tsId")
	List<Registration> findAllEnableByTermSubject(@Param("tsId")long tsId);

	//Tìm kiếm cho việc hiển thị lịch sử đăng kí
	@Query("SELECT r FROM Registration r " +
			"WHERE r.teacher.id=:tchId " +
			"AND r.subjectGroup.termSubject.term.id " +
			"IN(SELECT MAX(t.id) FROM Term t)")
	List<Registration> findAllByTeacher(@Param("tchId")long tchId);

	//Tìm kiếm đăng kí còn hiệu lực cho giảng viên
	@Query("SELECT r FROM Registration r " +
			"WHERE r.teacher.id=:tchId " +
			"AND r.isEnable=true " +
			"AND r.subjectGroup.termSubject.term.id=:tId")
	List<Registration> findAllEnableByTeacher(@Param("tchId")long tchId,@Param("tId")long tId);

	//Tìm tất cả đăng kí của nhóm môn học có hiệu lực
	@Query("SELECT r FROM Registration r WHERE r.subjectGroup.id=:sgId AND r.isEnable=true ")
	List<Registration> findAllBySubjectGroup(@Param("sgId")long sgId);

	//Cập nhật lại các đăng kí theo kì
	@Query("SELECT r FROM Registration r WHERE r.subjectGroup.termSubject.term.id=:tId AND r.isEnable=true ")
	List<Registration> findAllEnableByTerm(@Param("tId")long tId);
}