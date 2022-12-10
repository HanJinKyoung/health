package com.health.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.dao.HealthUseDAO;
import com.health.dao.ProgramDAO;
import com.health.dao.RegisterDAO;
import com.health.entity.HealthProVO;
import com.health.entity.HealthUseVO;
import com.health.entity.RegisterDTO;


import lombok.extern.log4j.Log4j;

@Log4j
@Service("registerService")
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private RegisterDAO rdao;
	
	@Autowired
	private HealthUseDAO hdao;
	
	@Autowired
	private ProgramDAO pdao;
	
	@Override
	@Transactional
	public boolean register(RegisterDTO rdto) {
		// 등록...  1. 프로그램 조회 ... 원수 확인... 
		//         2. 프로그램의 제한 인원 확인... 미달이면 등록 처리(O)... 등록 실패(X)
				// 회원권의 사용횟수 차감.... 
		boolean result = false;
		log.info("등록 처리 :"+rdto);
		
		int limitPerson = pdao.selectOne(rdto.getPId().getPid()).getTotalPerson();
		
		if(limitPerson > rdao.personCount(rdto.getPId())) {  // personCount()는 pid를 통해서 등록 사용자를 알아옴.
			if(!rdao.regCheck(rdto)) {
				result = rdao.insert(rdto);						// insert는 등록 처리... 
				hdao.updateUse(rdto.getHealthUseId().getHealthUseNo(),true);          //이용권 차감 처리...
			}
		}
		
		return result;
	}
	
	@Override
	@Transactional
	public boolean remove(int pid, int healthUseNo) {

		boolean result = false;
		
		try {
			hdao.updateUse(healthUseNo,false);
			rdao.delete(pid, healthUseNo);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}
	
	@Override
	public ArrayList<HealthProVO> getProgram(int HealthNo) {
		ArrayList<HealthProVO> pvo = new ArrayList<>();
		
		for (RegisterDTO rdto:rdao.select(HealthNo)) {
			log.info("레지스터에서 넘기는 값 : "+rdto.getPId().getPid());
			int npid = rdto.getPId().getPid();
			pvo.add(pdao.selectOne(npid));
		}
		return pvo;
	}
	
}
