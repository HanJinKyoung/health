package com.health.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.health.domain.HealthUseVO;
import com.health.service.HealthUseService;
import com.health.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/health/")
@Log4j
public class HealthUseController {

	@Autowired
	private HealthUseService hService;
	
	@Autowired
	private MemberService mService;
	
	@GetMapping("/healthusebuy")
	public void buy() {
		  
	}
	
	@PostMapping("/create")
	public String create(String id, String pw, String useRight, RedirectAttributes ra) {
		log.info("이용권 생성");
		log.info("아이디 : "+id+", 패스워드 : "+pw+", 이용권 : "+useRight);
		int result = mService.login(id, pw); // 결제처리를 위한 로그인
		
		int HealthNo = 1;
		
		if(result == 1) {
			ra.addFlashAttribute("msg", "회원권 결제에 성공했습니다.");
			
			//사용권 등록 정보 생성
			HealthUseVO vo = null;
			if(useRight.equals("C")) {
				vo = new HealthUseVO(HealthNo, "이용권-"+useRight, "2022-11-01", "2022-11-30", 10, id);
			} else if(useRight.equals("B")) {
				vo = new HealthUseVO(HealthNo, "이용권-"+useRight, "2022-10-01", "2022-12-31", 30, id);
			} else if(useRight.equals("A")) {
				vo = new HealthUseVO(HealthNo, "이용권-"+useRight, "2022-07-01", "2022-12-31", 60, id);
			} else if(useRight.equals("S")) {
				vo = new HealthUseVO(HealthNo, "이용권-"+useRight, "2022-01-01", "2022-12-31", 120, id);
			}
			
			log.info("선택한 이용권 : "+vo);
			
			if(hService.create(vo)) log.info("이용권 생성 성공");
			
			HealthNo += 1;
		} else {
			ra.addFlashAttribute("msg", "회원권 결제에 실패했습니다.");
		}
		return "redirect:/health/result";
	}
	
	@RequestMapping("/result")
	public void result() {
		
	}
}
