package com.health.repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.health.dao.HealthUseDAO;
import com.health.dao.MemberDAO;
import com.health.dao.ProgramDAO;
import com.health.dao.RegisterDAO;
import com.health.entity.HealthProVO;
import com.health.entity.HealthUseVO;
import com.health.entity.MemberVO;
import com.health.entity.RegisterDTO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
public class RepositoryTest {
	
	@Autowired
	private HealthUseDAO healthUseDAO;
	
	@Autowired
	private ProgramDAO programDAO;
	
	@Autowired
	private RegisterDAO registerDAO;
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private HealthUseVORepository healthUseVORepository;
	
	@Autowired
	private RegisterDTORepository registerDTORepository;
	
	@Test
	public void testDependency() {
		log.info("주입 여부(DAO):"+healthUseDAO);
		log.info("주입 여부(DAO):"+programDAO);
		log.info("주입 여부(DAO):"+registerDAO);
		log.info("주입 여부(DAO):"+memberDAO);
	}
	
	@Test
	@Commit
	public void addMember() {
		
		IntStream.rangeClosed(1, 9).forEach(i -> {
			
			MemberVO mvo = new MemberVO("test"+i, "test"+i, "testuser"+i, "010-1111-000"+i, "man", 
					"2022-12-20", "test"+i+"@test.com", null);
			
			memberDAO.insert(mvo);
		});
		
	}
	
	@Test
	public void addHealthUse() {
		IntStream.rangeClosed(1, 9).forEach(i -> {
			HealthUseVO hvo = new HealthUseVO();
			hvo.setName("이용권"+i);
			hvo.setStartDate("2022-12-01");
			hvo.setEndDate("2022-12-31");
			hvo.setUsingHealth(i);
			hvo.setMemId(MemberVO.builder().id("test"+i).build());
				
			
			healthUseDAO.insert(hvo);
				
		});
	}
	
	@Test
	public void addProgram() {
		IntStream.rangeClosed(1, 5).forEach(i -> {
			programDAO.insert(HealthProVO.builder()
					.name("프로그램"+i)
					.date("2022-12-1"+i)
					.times(i)
					.totalPerson(5+i).build());
		});
	}
	
	@Test
	public void testinsert() {
		
		RegisterDTO registerDTO = new RegisterDTO();
		
		registerDTO.setHealthUseId(HealthUseVO.builder().healthUseNo(2).build());
		registerDTO.setPId(HealthProVO.builder().pid(3).build());
		
		log.info("처리 결과 : "+registerDAO.insert(registerDTO));
		
		
	}
	
	@Test
	public void testPersonCount() {
		
		log.info("결과 출력 : "+ healthUseVORepository.findByMemId(MemberVO.builder().id("test1").build()));
		
	}
	
	@Test
	public void testSelect() {
		ArrayList<RegisterDTO> list = registerDAO.select(2);
		for(RegisterDTO rdto: list) {
			log.info("id값 : "+rdto.getId());
			log.info("이용권 id : "+rdto.getHealthUseId());
			log.info("프로그램 id : "+rdto.getPId());
		}
	}
	
	//registerDTO
	@Test
	public void testRegisterDTOInsert() {
		
		RegisterDTO rdto = new RegisterDTO();
		rdto.setHealthUseId(HealthUseVO.builder().healthUseNo(6).build());
		rdto.setPId(HealthProVO.builder().pid(11).build());
		log.info(registerDAO.insert(rdto));
		
		//
		log.info(registerDTORepository.countBypId(HealthProVO.builder().pid(3).build()));
	}
	
	@Test
	@Commit
	public void testRegDelete() {
//		HealthProVO hvo = HealthProVO.builder().pid(11).build();
//		HealthUseVO uvo = HealthUseVO.builder().healthUseNo(6).build();
//		RegisterDTO rdto = new RegisterDTO();
//		rdto.setPId(hvo);
//		rdto.setHealthUseId(uvo);
		log.info("삭제결과 : "+registerDAO.delete(11, 6));
		
	}
	
	@Test
	public void testSelectReg() {
		log.info(registerDAO.select(6));
	}
	
	@Test
	public void regCheckTest() {
		HealthProVO hvo = HealthProVO.builder().pid(11).build();
		HealthUseVO uvo = HealthUseVO.builder().healthUseNo(6).build();
		RegisterDTO rdto = new RegisterDTO();
		rdto.setPId(hvo);
		rdto.setHealthUseId(uvo);
		log.info("등록 확인 결과 : "+registerDAO.regCheck(rdto));
		
		int pid = 11;
		int healthUseNo = 6;
		Optional<RegisterDTO> opt =  registerDTORepository.findByPidAndHealthUseNo(pid,healthUseNo);
		
		log.info("결과 : "+opt.get().getId());
	}
	
	@Test
	public void testSelectOne() {
		log.info(programDAO.selectOne(13));
	}
	
	
	

}
