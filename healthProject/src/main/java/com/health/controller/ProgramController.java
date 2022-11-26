package com.health.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.health.domain.HealthProVO;
import com.health.service.ProgramService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/program/")
@Log4j
public class ProgramController {

	@Autowired
	private ProgramService pService;
	
	//프로그램 등록 화면 처리
	@GetMapping("/create")
	public String creatProgram() {
		return "program/create";
	}
	
	//프로그램 등록처리
	@PostMapping("/createForm")
	public String createPro(HealthProVO vo, RedirectAttributes ra) {
		boolean rs = false;
		rs = pService.createProgram(vo);
		if(rs) {
			//등록 성공
			log.info("프로그램 등록::::::::::성공");
			return "redirect:/program/pList";
		}else {
			//등록 실패
			log.info("프로그램 등록::::::::::실패");
			ra.addFlashAttribute("msg", "등록에 실패하였습니다");
			return "redirect:/program/create";
		}
	}
	
	//프로그램 리스트
	@RequestMapping("/pList")
	public void getList(Model model) {
		List<HealthProVO> list = pService.getList();
		model.addAttribute("programList", list);
	}
	
	//프로그램 수정
	
	//프로그램 삭제
	@RequestMapping("/programDelete")
	public String deleteProgram(@RequestParam("num") String num) {
		int pid = Integer.parseInt(num);
		pService.removeProgram(pid);
		return "redirect:/program/pList";
	}
}
