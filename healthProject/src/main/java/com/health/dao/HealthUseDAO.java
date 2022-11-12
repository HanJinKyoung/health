package com.health.dao;

import com.health.domain.HealthUseVO;

public interface HealthUseDAO {

	public boolean insert(HealthUseVO vo);	// 이용권 생성
	public int updateUse(int healthUseNo); 	// 이용권 고유번호를 이용한 횟수 차감
	public boolean delete(int healthUseNo); // 이용권 고유번호를 이용한 삭제  
	
}
