package com.health.dao;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.health.entity.HealthUseVO;
import com.health.entity.MemberVO;
import com.health.repository.HealthUseVORepository;

import lombok.extern.log4j.Log4j;

@Repository("healthUseDAO")
@Log4j
public class HealthUseDAOImpl implements HealthUseDAO {
	
	@Autowired
	private HealthUseVORepository healthUseRepository;

	@Override
	public boolean insert(HealthUseVO hvo) {
		boolean result = false;
		
		try {
			healthUseRepository.save(hvo);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	@Override
	public int updateUse(int healthUseNo,boolean plusMinus) {
		int result = 0;
		try {
			Optional<HealthUseVO> opt = healthUseRepository.findById(healthUseNo);
			
			if(plusMinus) {
				HealthUseVO hvo = opt.get();
				hvo.setUsingHealth(hvo.getUsingHealth()-1);
				healthUseRepository.save(hvo);
				result = 1;
			}else {
				HealthUseVO hvo = opt.get();
				hvo.setUsingHealth(hvo.getUsingHealth()+1);
				healthUseRepository.save(hvo);
				result = 1;
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	@Override
	public boolean delete(int healthUseNo) {
		boolean result = false;
		
		try {
			healthUseRepository.deleteById(healthUseNo);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	@Override
	public HealthUseVO selectUser(String userId) {
		
		HealthUseVO hvo = null;
		
		Optional<HealthUseVO> opt =  healthUseRepository
				.findByMemId(MemberVO.builder().id(userId).build());
		hvo = opt.get();
		
		return hvo;
	}

}
