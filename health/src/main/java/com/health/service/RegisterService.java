package com.health.service;

import java.util.ArrayList;

import com.health.entity.HealthProVO;
import com.health.entity.RegisterDTO;


public interface RegisterService {

	public boolean register(RegisterDTO rdto);  //등록
	public boolean remove(int pid, int healthUseNo);				//등록 취소... 
	public ArrayList<HealthProVO> getProgram(int HealthNo);   //회원이용권을 이용한 조회
	
}
