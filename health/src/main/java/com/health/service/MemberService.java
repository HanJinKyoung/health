package com.health.service;

import com.health.entity.MemberVO;

public interface MemberService {
	
	public int join(MemberVO vo) ; //회원 가입
	public int login(String id, String pw); //로그인
	public int idCheck(String id); //아이디 확인
	public MemberVO getMember(String id);
	//회원 정보 수정... 
	//회원 탈퇴... 
}
