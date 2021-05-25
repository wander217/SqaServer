package com.wander.sqa.dao;

import com.wander.sqa.entity.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberDAO extends JpaRepository<Member,Long> {
	@Query("SELECT m FROM Member m WHERE m.account.username=:u")
	public Optional<Member> findByUsername(@Param("u") String u);
}