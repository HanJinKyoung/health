package com.health.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.health.entity.MemberVO;

public interface MemberVORepository extends JpaRepository<MemberVO, String> {

}
