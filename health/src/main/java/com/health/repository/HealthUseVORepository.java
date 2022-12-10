package com.health.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.health.entity.HealthUseVO;
import com.health.entity.MemberVO;

public interface HealthUseVORepository extends JpaRepository<HealthUseVO, Integer> {
	
	Optional<HealthUseVO> findByMemId(MemberVO memId);

}
