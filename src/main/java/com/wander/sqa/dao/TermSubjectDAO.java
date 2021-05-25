package com.wander.sqa.dao;

import com.wander.sqa.entity.group.TermSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TermSubjectDAO extends JpaRepository<TermSubject,Long> {
    //Lấy môn học của kì mới cùng với sô nhóm đã đăng kí của giảng viên trong kì đó
    @Query("SELECT r.subjectGroup.termSubject ,COUNT (r)" +
            "FROM Registration r WHERE r.isEnable=true AND r.teacher.id=:tchId " +
            "AND r.subjectGroup.termSubject.term.id =:tId " +
            "GROUP BY r.subjectGroup.termSubject")
    public List<Object[]> getTermSubjectWithRegCountByTeacher(@Param("tchId")long tchId,@Param("tId")long tId);

    //Lấy môn học đã giao cùng của kì mới với số lượng giảng viên đăng kí đủ
    @Query("SELECT a.termSubject,COUNT(a) FROM AssignedSubject a " +
            "WHERE a.termSubject.term.id IN (SELECT MAX(t.id) FROM Term t) " +
            "AND a.numberOfGroup IN (SELECT COUNT(r) From Registration r " +
            "WHERE r.subjectGroup.termSubject.id=a.termSubject.id " +
            "AND r.teacher.id=a.teacher.id AND r.isEnable=true) GROUP BY a.termSubject")
    public List<Object[]> getTermSubjectWithRegCount();

    //Lấy môn học đã giao cùng tổng số giảng viên được giao
    @Query("SELECT a.termSubject,COUNT(a) FROM AssignedSubject a GROUP BY a.termSubject")
    public List<Object[]> getTermSubjectWithTeacherCount();
}
