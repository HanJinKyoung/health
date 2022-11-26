package com.health.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.health.dao.HealthUseDAO;
import com.health.dao.ProgramDAO;
import com.health.dao.RegisterDAO;
import com.health.domain.HealthProVO;
import com.health.domain.RegisterDTO;

import lombok.extern.log4j.Log4j;

@Service("registerService")
@Log4j
@Transactional
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private RegisterDAO rdao;
	
	@Autowired
	private ProgramDAO pdao;
	
	@Autowired
	private HealthUseDAO hdao;
	
	@Override
	public boolean register(RegisterDTO rdto) {
		boolean result = false;
		int limitPerson = pdao.selectOne(rdto.getPid()).getTotalPerson();
		if(limitPerson > rdao.personCount(rdto.getPid())) {
			
			result = this.createRegister(rdto) > 0? true : false;
			if(result) {
				//회원권 사용횟수 차감
				hdao.updateUse(rdto.getHealthUseNo());
			}
		}
		return result;
	}
	
	@Override
	public int createRegister(RegisterDTO rdto) {
		return rdao.insert(rdto);
	}

	@Override
	public int remove(int pid) {
		return rdao.delete(pid);
	}

	@Override
	public ArrayList<HealthProVO> getProgram(int HealthUseNo) {
		
		ArrayList<RegisterDTO> rList = rdao.selectList(HealthUseNo);
		ArrayList<HealthProVO> programList = new ArrayList<HealthProVO>();
		for(RegisterDTO rdto : rList) {
			int pid = rdto.getPid();
			HealthProVO pvo= pdao.selectOne(pid);
			programList.add(pvo);
		}
		return programList;
	}

}
