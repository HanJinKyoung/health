package com.health.service;

import java.util.ArrayList;

import com.health.domain.HealthProVO;
import com.health.domain.RegisterDTO;

public interface RegisterService {

	public boolean register(RegisterDTO rdto);				// 조회(제한인원)
	public int createRegister(RegisterDTO rdto);			// 등록
	public int remove(int pid);								// 등록 취소
	public ArrayList<HealthProVO> getProgram(int HealthNo);	// 회원 이용권으로 정보조회

}
