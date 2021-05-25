package com.wander.sqa.dao;

import com.wander.sqa.entity.group.TermWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TermWeekDAO extends JpaRepository<TermWeek,Long> {
    @Query("SELECT tw FROM TermWeek tw WHERE tw.term.id in(SELECT MAX(t.id) FROM Term t)")
    public List<TermWeek> findAllTermWeekByLastTerm();
}
