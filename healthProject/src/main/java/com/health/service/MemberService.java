package com.health.service;

import com.health.domain.MemberVO;

public interface MemberService {

	public int join(MemberVO vo);			// 회원 가입
	public int login(String id, String pw);	// 로그인
	public int idcheck(String id);			// 아이디확인
}
 