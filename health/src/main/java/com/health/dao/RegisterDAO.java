package com.health.dao;

import java.util.ArrayList;

import com.health.entity.HealthProVO;

import com.health.entity.RegisterDTO;


public interface RegisterDAO {
	
	public boolean insert(RegisterDTO rdto);
	public int personCount(HealthProVO pid); 
	public boolean delete(int pid, int HealthUseNo);
	public ArrayList<RegisterDTO> select(int HealthUseNo);
	public boolean regCheck(RegisterDTO rdto);
}
