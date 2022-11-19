package com.health.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.health.domain.HealthProVO;

@Repository("programDAO")
public class ProgramDAOImpl implements ProgramDAO {
	
	ArrayList<HealthProVO> pDB = new ArrayList<>();
	
	private int id = 1;	// 프로그램 키값(pid)
	
	@Override
	public boolean insert(HealthProVO pvo) {
		boolean result = false;
		
		pvo.setPid(id);
		result = pDB.add(pvo);
		
		id += 1;
		
		return result;
	}

	@Override
	public HealthProVO selectOne(int pid) {
		for(HealthProVO pvo : pDB) {
			if(pvo.getPid() == pid) {
				return pvo;
			}
		}
		return null;
	}

	@Override
	public ArrayList<HealthProVO> selectAll() {
		return pDB;
	}

	@Override
	public boolean update(HealthProVO pvo) {
		boolean result = false;
		for(HealthProVO vo : pDB) {
			if(pvo.getPid() == pvo.getPid()) {
				vo.setDate(pvo.getDate());
				vo.setName(pvo.getName());
				vo.setTimes(pvo.getTimes());
				vo.setTotalPerson(pvo.getTotalPerson());
				
				result = true;
			}
		}
		return result;
	}

	@Override
	public void delete(int pid) {
		int num = 0; //index값...db아직 연결안해서.. 어쩔수 없음
		for(HealthProVO pvo : pDB) {
			if(pvo.getPid() == pid) {
				pDB.remove(num);
				break;
			}
			num += 1;
		}
	}

}
