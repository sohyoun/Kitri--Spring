package com.kitri.member.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.MemberDto;
import com.kitri.member.model.service.MemberService;


@Controller
@RequestMapping("/user")
@SessionAttributes("userInfo")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
//	@Qualifier("Impl1") >> MemberService를 implements하는 class가 많으면 Qualifier로 어떤 memberservice를 말하는 것인지 명시해줘야함.
	private MemberService memberService;
	
	@RequestMapping(value = "/register.kitri", method = RequestMethod.GET)
	public String register() {
		return "user/member/member";
	}
	
	@RequestMapping(value = "/register.kitri", method = RequestMethod.POST)
	public String register(MemberDetailDto memberDetailDto, Model model) { //memberdetaildto를 안에 넣어주면 알아서 들어감.
		//단 이름이 같아야한다.
		int cnt = memberService.registerMember(memberDetailDto);
		if(cnt != 0) {
			model.addAttribute("registerInfo", memberDetailDto);
			//뭐를 가지고 가야하는 때는 ModelAndView를 해야한다.
			//model은 map 형태이기 때문에 그냥 map으로 해도 됨.
			return "user/member/registerok";
		}
		return "user/member/memberfail";
	}
	
	@RequestMapping(value = "/idcheck.kitri", method = RequestMethod.GET)
	public @ResponseBody String idCheck(@RequestParam(name= "checkid", defaultValue = "") String id) { 
		//넘어오는 data랑 변수의 이름하고 같으면 처리 안해줘도 됨.
		//하지만 달라지면 안넘어오기 때문에 RequestParam으로 명시해주는 것이 제일 좋음.
		logger.info("검색 아이디 : " + id);
		String json = memberService.idCheck(id);
		return json;
		//따라서 저 String은 ResponseBody라고 해야한다.
	}
	
	@RequestMapping("/zipsearch.kitri")
	@ResponseBody
	public String zipSearch(@RequestParam("doro") String doro) {
//		logger.info("검색 도로명 : " + doro);
		String json = memberService.zipSearch(doro);
		return json;
	}
	
	@RequestMapping(value = "/login.kitri", method = RequestMethod.GET)
	public String login() {
		return "user/login/login";
	}
	
//	@RequestMapping(value = "/login.kitri", method = RequestMethod.POST)
//	public String login(@RequestParam("id") String id, @RequestParam("pass") String pass, HttpSession session) {
//		MemberDto memberDto = memberService.loginMember(id, pass);
//		if(memberDto != null) {
//			session.setAttribute("userInfo", memberDto);
//			return "user/login/loginok";
//		} else {
//			return "user/login/loginfail";
//		}
//	}
	
	@RequestMapping(value = "/login.kitri", method = RequestMethod.POST)
	public String login(@RequestParam Map<String, String> map, Model model) {
		MemberDto memberDto = memberService.loginMember(map);
		if(memberDto != null) {
			model.addAttribute("userInfo", memberDto);
			return "user/login/loginok";
		} else {
			return "user/login/loginfail";
		}
	}
//	데이터 가져갈 경우에는 모델엔 뷰 해서 모델 만들고 addObject> setView 해주고 
//	단순 페이지 이동은 view의 이름만 설정하는 String으로 지정
	
//	@RequestMapping("/logout.kitri")
//	public String logout(HttpSession session) {
////		MemberDto memberDto = (MemberDto)session.getAttribute("userInfo"); 
//		session.removeAttribute("userInfo"); //세션에서 로그인 정보 지움
//		return "redirect:/index.jsp";
//	}
	
	@RequestMapping("/logout.kitri")
	public String logout(@ModelAttribute("userInfo") MemberDto memberDto, SessionStatus sessionStatus) {
			sessionStatus.setComplete(); //세션에 있는 모든 정보 지움
			return "redirect:/index.jsp";			
		
	}

}
