package com.health.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.dao.MemberDAO;
import com.health.domain.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO dao;
	
	@Override
	public int join(MemberVO vo) {
		int result = dao.insert(vo);
		return result;
	}

	@Override
	public int login(String id, String pw) {
		int result = dao.selectLogin(id, pw);
		return result;
	}

	@Override
	public int idcheck(String id) {
		int result = dao.selectCheck(id);
		return result;
	}

}
