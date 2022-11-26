package com.health.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.health.domain.HealthProVO;
import com.health.domain.HealthUseVO;
import com.health.domain.RegisterDTO;
import com.health.service.HealthUseService;
import com.health.service.ProgramService;
import com.health.service.RegisterService;
import com.mysql.cj.Session;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/register/")
@Log4j
public class RegisterController { //health이용권을 이용한 프로그램 등록 신청
	
	@Autowired
	private ProgramService pservice;
	
	@Autowired
	private HealthUseService hservice;
	
	@Autowired
	private RegisterService rservice;
	
	@RequestMapping("/regist")
	public String regist(Model model) {
		// 수업 신청 화면
		List<HealthProVO> programList = pservice.getList();
		model.addAttribute("programList", programList);
		return "register/regList";
	}

	@RequestMapping("/rlist")
	public String rlist(Model model, HttpSession session) {
		// 수업 신청 확인 화면
		String id = (String)session.getAttribute("user_id");
		int healthUseNo = hservice.userInfo(id).getHealthUseNo();
		ArrayList<HealthProVO> programList = new ArrayList<>();
		programList = rservice.getProgram(healthUseNo);
		model.addAttribute("programList", programList);
		return "register/list";
	}
	
	@RequestMapping("/regCreate")
	public String createRegister(@RequestParam("pid") int pid, HttpSession session, RedirectAttributes ra) {
		
		String userId = (String)session.getAttribute("user_id");
		HealthUseVO hvo = hservice.userInfo(userId);
		int healthUseNo = hvo.getHealthUseNo();

		RegisterDTO rdto = new RegisterDTO();
		rdto.setHealthUseNo(healthUseNo);
		rdto.setPid(pid);
		boolean result = rservice.register(rdto);
		if(result) {
			ra.addFlashAttribute("msg","수업이 등록되었습니다");
			return "redirect:/register/rlist";
		}else {
			ra.addFlashAttribute("msg","인원이 초과되었습니다");
			return "redirect:/register/regist";
		}
		
	}
	
	@RequestMapping("/regDelete")
	public String deleteRegister(@RequestParam("pid") int pid, HttpSession session, RedirectAttributes ra) {
		int result = 0;
		result = rservice.remove(pid);
		if(result>0) {
			ra.addFlashAttribute("msg","수업이 삭제되었습니다");
		}else {
			ra.addFlashAttribute("msg","삭제 실패되었습니다");
		}
		return "redirect:/register/rlist";
	}
}
