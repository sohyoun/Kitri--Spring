package com.kitri.cafe.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kitri.cafe.board.model.ReboardDto;
import com.kitri.cafe.board.service.BoardService;
import com.kitri.cafe.board.service.ReboardService;
import com.kitri.cafe.common.service.CommonService;
import com.kitri.cafe.member.model.MemberDto;
import com.kitri.cafe.util.PageNavigation;

@Controller
@RequestMapping("/reboard")
public class ReboardController {
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private ReboardService reboardService;
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void write(@RequestParam Map<String,String> parameter, Model model) {
		model.addAttribute("parameter", parameter);
		//return "reboard/write";
		//view 이름을 작성 안하고 그냥 void write로 해도 넘어감 왜??
		//view 이름이 없으면 RequestMapping한 /reboard/write가 view이름으로 자동적으로 지정됨.
		//따라서 view 이름과 Mapping한 이름이 일치할때는 그냥 void로 해도됨.
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(ReboardDto reboardDto, 
				@RequestParam Map<String, String> parameter, 
				Model model, HttpSession session) {
		String path = "";
		
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		if(memberDto != null) {
			int seq = commonService.getNextSeq();
			reboardDto.setSeq(seq);
			reboardDto.setId(memberDto.getId());
			reboardDto.setName(memberDto.getName());
			reboardDto.setEmail(memberDto.getEmail());
			reboardDto.setRef(seq);
			
			seq = reboardService.writeArticle(reboardDto);
			
			if(seq != 0) {
				model.addAttribute("seq", seq);
				path = "reboard/writeok";
			} else {
				path = "reboard/writefail";
			}
		} else {
			path = "reboard/writefail";
		}
		model.addAttribute("parameter", parameter);
		return path;
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(@RequestParam("seq") int seq, @RequestParam Map<String, String> parameter,
					Model model, HttpSession session) {
		String path="";
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		if(memberDto != null) {
			
			ReboardDto reboardDto = reboardService.viewArticle(seq);
			
			model.addAttribute("article", reboardDto);
			model.addAttribute("parameter", parameter);
			path = "reboard/view";
		} else {
			path = "redirect:/index.jsp";
		}
		return path;
	}
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(@RequestParam Map<String,String> parameter, Model model, HttpServletRequest request) {
		List<ReboardDto> list = reboardService.listArticle(parameter);
		PageNavigation pageNavigation = commonService.getPageNavigation(parameter);
		pageNavigation.setRoot(request.getContextPath());
		pageNavigation.makeNavigator();
		
		model.addAttribute("parameter", parameter);
		model.addAttribute("articleList",list);
		model.addAttribute("navigator",pageNavigation);
		
	}
	
	@RequestMapping(value = "/reply", method = RequestMethod.GET)
	public String reply(@RequestParam("seq") int seq, @RequestParam Map<String, String> parameter,
			Model model, HttpSession session) {
		//원글의 정보 가져감
		String path="";
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		if(memberDto != null) {
			
			ReboardDto reboardDto = reboardService.getArticle(seq);
			
			model.addAttribute("article", reboardDto);
			model.addAttribute("parameter", parameter);
			path = "reboard/reply";
		} else {
			path = "redirect:/index.jsp";
		}
		return path;
	}
	
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public String reply(ReboardDto reboardDto, 
				@RequestParam Map<String, String> parameter, 
				Model model, HttpSession session) {
		String path = "";
		
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		if(memberDto != null) {
			int seq = commonService.getNextSeq();
			reboardDto.setSeq(seq);
			reboardDto.setId(memberDto.getId());
			reboardDto.setName(memberDto.getName());
			reboardDto.setEmail(memberDto.getEmail());
			
			
			seq = reboardService.replyArticle(reboardDto);
			
			if(seq != 0) {
				model.addAttribute("seq", seq);
				path = "reboard/writeok";
			} else {
				path = "reboard/writefail";
			}
		} else {
			path = "reboard/writefail";
		}
		model.addAttribute("parameter", parameter);
		return path;
	}
	
}
