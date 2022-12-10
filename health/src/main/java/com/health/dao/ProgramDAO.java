package com.health.dao;

import java.util.ArrayList;

import com.health.entity.HealthProVO;

public interface ProgramDAO {
	
	public boolean insert(HealthProVO pvo);   		//프로그램 등록
	public HealthProVO selectOne(int pid);   	// 등록 프로그램 조회
	public ArrayList<HealthProVO> selectAll(); 
	public boolean update(HealthProVO vo);				// 등록 프로그램 정보 수정
	public boolean delete(int programId);							//등록 프로그램 삭제

}
