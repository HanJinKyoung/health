package com.health.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.health.domain.HealthUseVO;

import lombok.extern.log4j.Log4j;

@Repository("healthUseDAO")
@Log4j
public class HealthUseDAOImpl implements HealthUseDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int insert(HealthUseVO vo) {
		int result = 0; 
		
		String sql = "insert into usinghealth(name, startDate, endDate, usingHealth, memid)"
				+ " values(?,?,?,?,?)";
		
		result = jdbcTemplate.update(sql, new Object[] {
			vo.getName()
			, vo.getStartDate()
			, vo.getEndDate()
			, vo.getUsingHealth()
			, vo.getMemid()
		});
		
		log.info("usinghealth insert결과 >>>>>>>>>>>>>> "+result);
		return result;
	}

	@Override
	public int updateUse(int healthUseNo) {
		int result = 0;
		
		String sql = "update usinghealth set usingHealth=usingHealth-1 where healthUseNo=?";
		
		result = jdbcTemplate.update(sql, healthUseNo);
		
		log.info("useinghealth update결과 >>>>>>>>>>>>> "+result);
		return result;
	}

	@Override
	public boolean delete(int healthUseNo) {
		boolean result = false;
		int sqlResult = 0;

		String sql = "delete from usinghealth where healthUseNo=?";
		sqlResult = jdbcTemplate.update(sql, healthUseNo);
		
		if(sqlResult > 0) {result = true;}
		
		log.info("useinghealth delete결과 >>>>>>>>>>>>> "+result);
		return result;
	}

	@Override
	public HealthUseVO selectOne(String userId) {
		HealthUseVO hvo = new HealthUseVO();
		
		String sql = "select * from usinghealth where memid = ?";
		hvo = jdbcTemplate.queryForObject(sql, new Object[] {userId}, new RowMapper<HealthUseVO>() {
			
			@Override
			public HealthUseVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				HealthUseVO vo = new HealthUseVO();
				vo.setHealthUseNo(rs.getInt("healthUseNo"));
				vo.setName(rs.getString("name"));
				vo.setStartDate(rs.getString("startDate"));
				vo.setEndDate(rs.getString("endDate"));
				vo.setUsingHealth(rs.getInt("usingHealth"));
				vo.setMemid(rs.getString("memid"));
				return vo;
			}
		});
		return hvo;
	}

}
