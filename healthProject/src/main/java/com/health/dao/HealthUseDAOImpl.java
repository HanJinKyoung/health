package com.health.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.health.domain.HealthUseVO;

@Repository("healthUseDAO")
public class HealthUseDAOImpl implements HealthUseDAO {
	
	ArrayList<HealthUseVO> HDB = new ArrayList<>();
	
	@Override
	public boolean insert(HealthUseVO vo) {
		boolean result = false; 
		
		result = HDB.add(vo);
		
		return result;
	}

	@Override
	public int updateUse(int healthUseNo) {
		int result = 0;
		
		for(HealthUseVO hvo : HDB) {
			if(hvo.getHealthUseNo() == healthUseNo) {
				int udtCnt = hvo.getUsingHealth() - 1;
				hvo.setUsingHealth(udtCnt);
				result = 1;
			}
		}
		
		return result;
	}

	@Override
	public boolean delete(int healthUseNo) {
		boolean result = false;
		
		for(HealthUseVO hvo : HDB) {
			if(hvo.getHealthUseNo() == healthUseNo) {
				result = HDB.remove(hvo);
			}
		}
		
		return result;
	}

}
