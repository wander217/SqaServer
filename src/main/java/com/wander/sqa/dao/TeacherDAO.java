package com.wander.sqa.dao;

import com.wander.sqa.entity.user.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TeacherDAO extends JpaRepository<Teacher,Long> {

	@Query("SELECT t FROM Teacher t WHERE t.account.username=:u")
	public Optional<Teacher> findByUsername(@Param("u") String u);

	//Lấy danh sách giảng viên được đã đăng kí đủ
	@Query("SELECT a.teacher FROM AssignedSubject a " +
			"WHERE a.termSubject.id=:id " +
			"AND a.numberOfGroup IN (SELECT COUNT(r) FROM Registration r " +
			"WHERE r.isEnable= true AND r.teacher.id =a.teacher.id " +
			"AND r.subjectGroup.termSubject.id=a.termSubject.id)")
	List<Teacher> findRemember(long id);

	//Lấy danh sách giảng viên được đã đăng kí thiếu
	@Query("SELECT a.teacher FROM AssignedSubject a " +
			"WHERE a.termSubject.id=:id " +
			"AND a.numberOfGroup NOT IN (SELECT COUNT(r) FROM Registration r " +
			"WHERE r.isEnable= true AND r.teacher.id =a.teacher.id " +
			"AND r.subjectGroup.termSubject.id=a.termSubject.id)")
	List<Teacher> findForgot(long id);

	//Lấy tất cả giảng viên đã được giao cùng số lượng môn đã đăng kí đủ
	@Query("SELECT a.teacher,COUNT(a) FROM AssignedSubject a " +
			"WHERE a.termSubject.term.id in (SELECT MAX(t.id) FROM Term t) " +
			"AND a.numberOfGroup in (SELECT COUNT(r) FROM Registration r " +
			"WHERE r.teacher.id= a.teacher.id AND r.isEnable=true " +
			"AND r.subjectGroup.termSubject.id=a.termSubject.id) " +
			"GROUP BY a.teacher")
	public List<Object[]> findTeacherWithNumberOfFullRegGroup();

	//Lấy tất cả giảng viên cùng số môn được giao
	@Query("SELECT a.teacher,COUNT(a) FROM AssignedSubject a " +
			"WHERE a.termSubject.term.id in (SELECT MAX(t.id) FROM Term t) " +
			"GROUP BY a.teacher")
	public List<Object[]> findTeacherWithNumberOfGroup();
}