package com.health.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.health.domain.MemberVO;
import com.health.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/member/")
@Log4j
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	//회원가입
	@RequestMapping("/join")
	public void join() { 
		
	}
	
	//회원가입 처리
	@PostMapping("/joinForm")
	public String joinForm(MemberVO vo, RedirectAttributes ra) {
		log.info(vo);
		int result = 0;
		result = service.join(vo);
		log.info("Controller - 조인 실행결과 0:실패, 1:성공===="+result);
		
		if(result == 1) {
			ra.addFlashAttribute("msg", "회원가입에 성공하였습니다.");
		} else {
			ra.addFlashAttribute("msg", "회원가입에 실패하였습니다.");
		}
		
		return "redirect:/member/join_result";
	}
	
	//join 결과
	@RequestMapping("/join_result")
	public void joinResult() {
		
	}
	
	//로그인 화면
	@RequestMapping("/login")
	public String login() {
		return "member/login";
	}
	
	//로그인 처리
	@PostMapping("/loginForm")
	public String loginForm(MemberVO vo, HttpSession session, RedirectAttributes ra) {
		int result = 0;
		result = service.login(vo.getId(), vo.getPassword());
		
		if(result == 1) {
			session.setAttribute("user_id", vo.getId());
			return "redirect:/";
		} else {
			ra.addFlashAttribute("msg", "없는 ID거나 잘못된 패스워드입니다.");
			return "redirect:/member/login";
		}
		
	}
	
	//마이페이지
	@GetMapping("/mypage")
	public String mypage(HttpSession session) {
		String id = (String)session.getAttribute("user_id");
		//user_id값을 이용하여 회원정보 가져오기
		
		return "member/mypage";
	}
	
	//로그아웃 처리
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
