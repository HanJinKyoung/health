package com.health.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		int result = mService.login(id, pw);
		if(result == 1) {
			ra.addFlashAttribute("msg", "회원권 결제에 성공했습니다.");
		} else {
			ra.addFlashAttribute("msg", "회원권 결제에 실패했습니다.");
		}
		return "redirect:/health/result";
	}
	
}
