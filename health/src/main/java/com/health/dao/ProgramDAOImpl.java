package com.health.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.health.entity.HealthProVO;
import com.health.repository.HealthProVORepository;

import lombok.extern.log4j.Log4j;


@Repository("programDAO")
@Log4j
public class ProgramDAOImpl implements ProgramDAO {

	
	@Autowired
	private HealthProVORepository programRepository;
	
	@Override
	public boolean insert(HealthProVO pvo) {
		boolean result = false;
		
		try {
			programRepository.save(pvo);
			result = true;
		} catch (Exception e) {
			result = false;
		}
		
		return result;
	}

	@Override
	public HealthProVO selectOne(int pid) {
		
		HealthProVO hvo = null;
		
		try {
			log.info("프로그램 아이디 : "+pid);
			Optional<HealthProVO> opt = programRepository.findById(pid);
			hvo = opt.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		log.info("프로그램 결과 확인 : "+hvo.getName());
		return hvo;
	}

	@Override
	public ArrayList<HealthProVO> selectAll() {
		
		List<HealthProVO> list =  programRepository.findAll();
		
		return (ArrayList<HealthProVO>)list;
	}
	
	
	@Override
	public boolean update(HealthProVO pvo) {
		
		boolean result = false;
		
		try {
			programRepository.save(pvo);
			result = true;
		} catch (Exception e) {
			result = false;
		}	
	
		return result;
	}

	@Override
	public boolean delete(int pid) {
		boolean result = false;
		
		try {
			programRepository.deleteById(pid);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

}
