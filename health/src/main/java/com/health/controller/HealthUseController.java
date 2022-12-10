package com.health.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.health.entity.HealthUseVO;
import com.health.entity.MemberVO;
import com.health.service.HealthUseService;
import com.health.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/health/")
@Log4j
public class HealthUseController {
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private HealthUseService hservice;
	
	@GetMapping("/healthusebuy")
	public void buy() {
		
	}
	
	@RequestMapping("result")
	public void result() {

	}
	
	@PostMapping("/create")
	public String create(String id, String pw, String useRight, RedirectAttributes RA) {
		log.info("이용권 생성");
		log.info("아이디 : "+id+",  패스워드 : "+pw+",  사용권 종류: "+useRight);
		int result = service.login(id, pw);  //결제 처리를 위한 로그인...
		
		
		if(result == 1) {  // 1인 경우 ,결제 성공... 
						
			//사용권 등록 정보 생성
			HealthUseVO vo =new HealthUseVO();;
			if (useRight.equals("C")) {
				vo.setName("이용권"+useRight);
				vo.setStartDate("2022-12-01");
				vo.setEndDate("2022-12-31");
				vo.setUsingHealth(10);
				vo.setMemId(MemberVO.builder().id(id).build());
			}else if(useRight.equals("B")) {
				vo.setName("이용권"+useRight);
				vo.setStartDate("2022-10-01");
				vo.setEndDate("2022-12-31");
				vo.setUsingHealth(30);
				vo.setMemId(MemberVO.builder().id(id).build());
			}else if(useRight.equals("A")) {
				vo.setName("이용권"+useRight);
				vo.setStartDate("2022-07-01");
				vo.setEndDate("2022-12-31");
				vo.setUsingHealth(60);
				vo.setMemId(MemberVO.builder().id(id).build());
			}else if(useRight.equals("S")){
				vo.setName("이용권"+useRight);
				vo.setStartDate("2022-01-01");
				vo.setEndDate("2022-12-31");
				vo.setUsingHealth(120);
				vo.setMemId(MemberVO.builder().id(id).build());
			}
			log.info("선택한 이용권 : "+vo);
			if(hservice.create(vo)) log.info("이용권 생성 성공");
				
			
			// useRight를 이용하여 healthUseVO를 생성...
			
			RA.addFlashAttribute("msg","회원권 결제에 성공했습니다.");
		}else {  //결제 실패... 
			RA.addFlashAttribute("msg","회원권 결제에 실패했습니다.");			
		}
		
		return "redirect:/health/result";
	}
	

	
	

}
