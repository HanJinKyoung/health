package com.health.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.dao.HealthUseDAO;
import com.health.domain.HealthUseVO;

import lombok.extern.log4j.Log4j; 

@Service("healthUseService")
@Log4j
public class HealthUseServiceImpl implements HealthUseService {

	@Autowired
	private HealthUseDAO hdao;
	
	@Override
	public boolean create(HealthUseVO hvo) {
		return hdao.insert(hvo);
	}

	@Override
	public int use(int healthUseNo) {
		return hdao.updateUse(healthUseNo);
	}

	@Override
	public boolean deprecated(int healthUseNo) {
		return hdao.delete(healthUseNo);
	}

}
