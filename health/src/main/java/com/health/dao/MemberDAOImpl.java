package com.health.dao;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.health.entity.MemberVO;
import com.health.repository.MemberVORepository;

import lombok.extern.log4j.Log4j;

@Repository("memberDAO")
@Log4j
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	private MemberVORepository memberRepository;

	
	@Override
	public int insert(MemberVO vo) {
		//Member 추가
		int result = 0;
		try {
			memberRepository.save(vo);
			result = 1;
		} catch (Exception e) {
			result = 0;
		}
		
		return result;
	}

	@Override
	public int select(String id, String pw) {
		int result = 0;
		
		try {
			Optional<MemberVO> opt = memberRepository.findById(id);
			MemberVO vo =  opt.get();
			if (vo.getPassword().equals(pw)) {
				result = 1;
			}
		}catch (Exception e) {
			result = 0;
		}
		
		return result;
		
	}

	@Override
	public int selectOne(String id) {
		int result = 0;
		
		try {
			Optional<MemberVO> opt = memberRepository.findById(id);
			if (opt.isPresent()) {
				result = 1;
			}
		} catch (Exception e) {
			
		}
				
		return result;
	}
	
	public MemberVO selectMember(String id) {
		
		MemberVO vo = null;
		
		try {
			Optional<MemberVO> opt = memberRepository.findById(id);
			vo = opt.get();
		} catch (Exception e) {
			
		}
		
		return vo;
	}

}
