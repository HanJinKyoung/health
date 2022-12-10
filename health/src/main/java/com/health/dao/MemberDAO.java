package com.health.dao;

import com.health.entity.MemberVO;

public interface MemberDAO {
	
	public int insert(MemberVO vo);
	public int select(String id, String pw);
	public int selectOne(String id);
	public MemberVO selectMember(String id);

}
