package com.spring.basic.member.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.basic.member.Member;
import com.spring.basic.member.service.MemberService;

@Controller
public class MemberController {

//	MemberService service = new MemberService();
//	@Autowired
	@Resource(name="memService")
	MemberService service;
	
	@ModelAttribute("serverTime")
	public String getServerTime(Locale locale) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		return dateFormat.format(date);
	}
	
	// 파라미터를 받아 그대로 사용하는 방법
//	@RequestMapping(value="/memJoin", method=RequestMethod.POST)
//	public String memJoin(Model model, HttpServletRequest request) {
//		String memId = request.getParameter("memId");
//		String memPw = request.getParameter("memPw");
//		String memMail = request.getParameter("memMail");
//		String memPhone1 = request.getParameter("memPhone1");
//		String memPhone2 = request.getParameter("memPhone2");
//		String memPhone3 = request.getParameter("memPhone3");
//		
//		service.memberRegister(memId, memPw, memMail, memPhone1, memPhone2, memPhone3);
//		
//		model.addAttribute("memId", memId);
//		model.addAttribute("memPw", memPw);
//		model.addAttribute("memMail", memMail);
//		model.addAttribute("memPhone", memPhone1 + " - " + memPhone2 + " - " + memPhone3);
//		
//		return "memJoinOk";
//	}
	
	// 커맨드 객체를 사용하는 방법
	@RequestMapping(value = "/memJoin", method = RequestMethod.POST)
	public String memJoin(Member member) {
		
		service.memberRegister(member);
		
		return "memJoinOk";
	}
	
	@RequestMapping(value = "/memLogin", method = RequestMethod.POST)
	public String memLogin(Member member) {
		
		service.memberSearch(member);
		
		return "memLoginOk";
	}
	
	@RequestMapping(value = "/memRemove", method = RequestMethod.POST)
	public String memRemove(@ModelAttribute("mem") Member member) {
		
		service.memberRemove(member);
		
		return "memRemoveOk";
	}
	
	/*
	@RequestMapping(value = "/memModify", method = RequestMethod.POST)
	public String memModify(Model model, Member member) {
		
		Member[] members = service.memberModify(member);
		
		model.addAttribute("memBef", members[0]);
		model.addAttribute("memAft", members[1]);
		
		return "memModifyOk";
	}
	*/
	
	@RequestMapping(value = "/memModify", method = RequestMethod.POST)
	public ModelAndView memModify(Member member) {
		
		Member[] members = service.memberModify(member);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("memBef", members[0]);
		mav.addObject("memAft", members[1]);
		
		mav.setViewName("memModifyOk");
		
		return mav;
	}
}