package com.health.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.dao.MemberDAO;
import com.health.entity.MemberVO;


@Service("MemberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO dao;

	@Override
	public int join(MemberVO vo) {
		int result = 0;
		result = dao.insert(vo);
		
		return result;
	}

	@Override
	public int login(String id, String pw) {
		int result = 0;
		result = dao.select(id, pw);
		return result;
	}

	@Override
	public int idCheck(String id) {
		return dao.selectOne(id);
	}

	@Override
	public MemberVO getMember(String id) {

		
		return null;
	}
}
