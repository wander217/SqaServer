package com.wander.sqa.dao;

import com.wander.sqa.entity.group.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface TermDAO extends JpaRepository<Term,Long> {
    @Query("SELECT t FROM Term t WHERE t.id IN (SELECT MAX(t1.id) FROM Term t1)")
    public Optional<Term> getLastTerm();
}
