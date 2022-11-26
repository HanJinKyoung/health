package com.health.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.health.domain.RegisterDTO;

import lombok.extern.log4j.Log4j;

@Repository("registerDAO")
@Log4j
public class RegisterDAOImpl implements RegisterDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int personCount(int pid) {
		int result = 0;
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+pid);
		String sql = "select * from register where pid = ?";
		List<RegisterDTO> rList = jdbcTemplate.query(sql, new Object[] {pid}, new RowMapper<RegisterDTO>(){
			public RegisterDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				RegisterDTO rdto = new RegisterDTO();
				rdto.setId(rs.getInt("id"));
				rdto.setHealthUseNo(rs.getInt("healthUseNo"));
				rdto.setPid(rs.getInt("pid"));
				return rdto;
			}
		});
		if(rList != null) {
			result = rList.size(); 
		}else {
			result = 0;
		}
		
		System.out.println("done");
		return result;
	}
	
	@Override
	public int insert(RegisterDTO rdto) {
		int result = 0;
		String sql = "insert into register(healthUseNo, pid) values(?,?)";
		result = jdbcTemplate.update(sql, new Object[] {rdto.getHealthUseNo(), rdto.getPid()});
		return result;
	}

	@Override
	public int delete(int pid) {
		int result = 0;
		String sql = "delete from register where pid = ?";
		result = jdbcTemplate.update(sql, pid);
		return result;
	}

	@Override
	public ArrayList<RegisterDTO> selectList(int HealthUseNo) {
		String sql = "select * from register where healthUseNo = ?";
		List<RegisterDTO> pList = jdbcTemplate.query(sql, new Object[] {HealthUseNo}, new RowMapper<RegisterDTO>() {
			@Override
			public RegisterDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				RegisterDTO rdto = new RegisterDTO();
				rdto.setId(rs.getInt("id"));
				rdto.setHealthUseNo(rs.getInt("healthUseNo"));
				rdto.setPid(rs.getInt("pid"));
				return rdto;
			};
		});
		return (ArrayList<RegisterDTO>)pList;
	}

}
