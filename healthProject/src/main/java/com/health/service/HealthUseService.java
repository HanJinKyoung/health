package com.health.service;

import com.health.domain.HealthUseVO;

public interface HealthUseService {

	public boolean create(HealthUseVO hvo);		// 이용권 생성
	public int use(int healthUseNo);			// 이용권 번호를 이용하여 회차 차감
	public boolean deprecated(int healthUseNo); // 이용권 소멸 
	 
}
