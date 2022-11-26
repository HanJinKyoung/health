package com.health.dao;

import java.util.ArrayList;

import com.health.domain.RegisterDTO;

public interface RegisterDAO {
	public int personCount(int pid);
	public int insert(RegisterDTO rdto);						// 등록
	public int delete(int pid);									// 등록 취소
	public ArrayList<RegisterDTO> selectList(int HealthUseNo);	// 회원 이용권으로 정보조회
}
