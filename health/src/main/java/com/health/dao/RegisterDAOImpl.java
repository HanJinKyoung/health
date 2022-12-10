package com.health.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.health.entity.HealthProVO;
import com.health.entity.HealthUseVO;
import com.health.entity.RegisterDTO;
import com.health.repository.RegisterDTORepository;

import lombok.extern.log4j.Log4j;


@Repository("registerDAO")
@Log4j
public class RegisterDAOImpl implements RegisterDAO {

	@Autowired
	private RegisterDTORepository registerRepository;
	
	@Override
	public boolean insert(RegisterDTO rdto) {
		
		boolean result = false; 
		
		try {
			registerRepository.save(rdto);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	@Override
	public int personCount(HealthProVO pid) {
		
		Long result = 0L;
		
		result = registerRepository.countBypId(pid);
		
		return result.intValue();
	}

	@Override
	public boolean delete(int pid, int healthUseNo) {
		boolean result = false;
		
		try {
			Optional<RegisterDTO> opt =  registerRepository.findByPidAndHealthUseNo(pid, healthUseNo);
			int rid = opt.get().getId();
			registerRepository.deleteById(rid);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	@Override
	public ArrayList<RegisterDTO> select(int healthUseNo) {
		log.info("이용권 번호 :"+healthUseNo);
		List<RegisterDTO> list = registerRepository.findByhealthUseNo(healthUseNo);
		return (ArrayList<RegisterDTO>)list;
	}
	
	@Override
	public boolean regCheck(RegisterDTO rdto) {
		boolean result = false; 
		try {
			log.info("DAO에 넘어간 값 : "+rdto);
			int pid = rdto.getPId().getPid();
			int healthUseNo = rdto.getHealthUseId().getHealthUseNo();
			Optional<RegisterDTO> opt =  registerRepository.findByPidAndHealthUseNo(pid,healthUseNo);
			
			log.info("결과 : "+opt.get().getId());
			if(opt.isPresent()) {;
				result = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

}
