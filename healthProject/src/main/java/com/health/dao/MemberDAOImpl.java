package com.health.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.health.domain.MemberVO;

import lombok.extern.log4j.Log4j;

@Repository("memberDAO")
@Log4j
public class MemberDAOImpl implements MemberDAO{

	//private ArrayList<MemberVO> DB = new ArrayList<>();
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int insert(MemberVO vo) {
		log.info("DAO vo======="+vo);
		int result = 0;
		
		String sql = "insert into member(id, password, name, phone, email, birthday, gender)"
				+ " values(?,?,?,?,?,?,?)";
		
		result = jdbcTemplate.update(sql, new Object[] {
				vo.getId()
				, vo.getPassword()
				, vo.getName()
				, vo.getPhone()
				, vo.getEmail()
				, vo.getBirthday()
				, vo.getGender()
		});
		
		log.info("insert결과>>>>>>>>>>>"+result);
		return result;
	}

	@Override
	public int selectLogin(String id, String pw) {
		int result = 0;
		
		String sql = "select * from member where id=? and password=?";
		
		/*
		 * query() 파라미터 의미
		 * sql : sql쿼리를 의미. ?를 사용하는 preparedStatement용 쿼리를 사용함
		 * new Object[] {값1, 값2} : sql ?에 들어갈 값을 나열_바인딩
		 * new RowMapper<type>() : 조회 결과 ResultSet으로 부터 데이터를 읽어와 Type데이터를 생성
		 *  >> 익명클래스 사용
		 *  >> mapRow() 메서드 : ResultSet에서 값을 처리한 후 return
		 */
		List<MemberVO> list = jdbcTemplate.query(sql, new Object[] {id,pw}, new RowMapper<MemberVO>() {
			@Override
			public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setPhone(rs.getString("phone"));
				vo.setEmail(rs.getString("email"));
				vo.setBirthday(rs.getString("birthday"));
				vo.setGender(rs.getString("gender"));
				return vo;
			}
		});
		
		result = list.size();
		log.info("로그인 결과>>>>>>>>>>>>" + result);

		return result;
	}

	@Override
	public int selectCheck(String id) {
		int result = 0;
		String sql = "select count(*) from member where id = ?";
		result = jdbcTemplate.queryForObject(sql, Integer.class, id);
		return result;
	}

}
