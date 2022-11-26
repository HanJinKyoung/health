package com.health.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.health.domain.HealthProVO;

import lombok.extern.log4j.Log4j;

@Repository("programDAO")
@Log4j
public class ProgramDAOImpl implements ProgramDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean insert(HealthProVO pvo) {
		boolean result = false;
		int sqlResult = 0;
		
		String sql = "insert into program(name, date, times, totalPerson)"
				+ " value(?,?,?,?)";
		sqlResult = jdbcTemplate.update(sql, new Object[] {
			pvo.getName()
			, pvo.getDate()
			, pvo.getTimes()
			, pvo.getTotalPerson()
		});
		
		if(sqlResult > 0) {result = true;}
		
		return result;
	}

	@Override
	public HealthProVO selectOne(int pid) {
		HealthProVO pvo = new HealthProVO();
		String sql = "select * from program where pid = ?";
		pvo = jdbcTemplate.queryForObject(sql, new Object[] {pid}, new RowMapper<HealthProVO>() {
			@Override
			public HealthProVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				HealthProVO vo = new HealthProVO();
				vo.setPid(rs.getInt("pid"));
				vo.setName(rs.getString("name"));
				vo.setDate(rs.getString("date"));
				vo.setTimes(rs.getInt("times"));
				vo.setTotalPerson(rs.getInt("totalPerson"));
				return vo;
			}
		});
		return pvo;
	}

	@Override
	public List<HealthProVO> selectAll() {
		List<HealthProVO> proList = new ArrayList<>();
		String sql = "select * from program";
		proList = jdbcTemplate.query(sql, new RowMapper<HealthProVO>() {
			@Override
			public HealthProVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				HealthProVO vo = new HealthProVO();
				vo.setPid(rs.getInt("pid"));
				vo.setName(rs.getString("name"));
				vo.setDate(rs.getString("date"));
				vo.setTimes(rs.getInt("times"));
				vo.setTotalPerson(rs.getInt("totalPerson"));
				return vo;
			}
		});
		return proList;
	}

	@Override
	public boolean update(HealthProVO pvo) {
		boolean result = false;
		int sqlResult = 0;
		
		String sql = "update program set name=?, date=?, times=?, totalPerson=? where pid=?";
		sqlResult = jdbcTemplate.update(sql, new Object[] {
			pvo.getName()
			, pvo.getDate()
			, pvo.getTimes()
			, pvo.getTotalPerson()
			, pvo.getPid()
		});
		
		if(sqlResult > 0) {result = true;}

		return result;
	}

	@Override
	public void delete(int pid) {
		String sql = "delete from program where pid=?";
		int result = jdbcTemplate.update(sql, pid);
		if(result>0) {
			log.info("program {{{{ "+ pid +" }}}}delete 성공");
		} else {
			log.info("program {{{{ "+ pid +" }}}}delete 실패");
		}
	}

}
