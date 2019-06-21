package com.kitri.admin.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kitri.admin.model.service.AdminService;
import com.kitri.member.controller.MemberController;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private AdminService adminService;
	
	
	@RequestMapping("/mvmemberlist.kitri")
	public String memberlist() {
		return "admin/member/memberlist";
	}
	
	@RequestMapping("/getmemberlist.kitri")
	@ResponseBody //이걸 안하면 string이 주소값으로 들어감!
	public String getMemberList(@RequestParam Map<String, String> map) {
		String json = adminService.getMemberList(map);
		return json;
	}

}
