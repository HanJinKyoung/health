package com.health.service;

import java.util.ArrayList;

import com.health.domain.HealthProVO;

public interface ProgramService {

	public boolean createProgram(HealthProVO pvo);	//프로그램 등록
	public HealthProVO search(int pid); 			//프로그램 조회(특정)
	public ArrayList<HealthProVO> getList(); 		//프로그램 조회(전체)
	public boolean modify(HealthProVO pvo);			//등록 프로그램 정보 수정
	public void removeProgram(int pid);				//프로그램 삭제
	
}
