package com.kitri.member.controller;

import javax.servlet.http.*;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.MemberDto;
import com.kitri.member.model.service.MemberServiceImpl;

public class MemberController {

	private static MemberController memberController;

	static {
		memberController = new MemberController();
	}

	public MemberController() {

	}

	public static MemberController getMemberController() {
		return memberController;
	}

	public String register(HttpServletRequest request, HttpServletResponse response) {
		String path = "/index.jsp";
		MemberDetailDto memberDetailDto = new MemberDetailDto();
		memberDetailDto.setName(request.getParameter("name"));
		memberDetailDto.setId(request.getParameter("id"));
		memberDetailDto.setPass(request.getParameter("pass"));
		memberDetailDto.setEmailid(request.getParameter("emailid"));
		memberDetailDto.setEmaildomain(request.getParameter("emaildomain"));
		memberDetailDto.setTel1(request.getParameter("tel1"));
		memberDetailDto.setTel2(request.getParameter("tel2"));
		memberDetailDto.setTel3(request.getParameter("tel3"));
		memberDetailDto.setZipcode(request.getParameter("zipcode"));
		memberDetailDto.setAddress(request.getParameter("address"));
		memberDetailDto.setAddressDetail(request.getParameter("address_detail"));

		int cnt = MemberServiceImpl.getMemberService().registerMember(memberDetailDto);
//		System.out.println("cnt===="+cnt); //2가 나와야함

		// cnt에 따라 registerok와 registerfail로 감
		if (cnt != 0) {
			// memberDetailDto는 registerok에만 필요함 > request에 담음
			request.setAttribute("userInfo", memberDetailDto);
			path = "/user/member/registerok.jsp"; // 갈꺼다 라는 지정 //실제 가는것은 frontcontroller에서
		} else {
			path = "/user/member/registerfail.jsp";
		}
		return path;
	}

	public String login(HttpServletRequest request, HttpServletResponse response) {
		String path = "/index.jsp";
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

		MemberDto memberDto = MemberServiceImpl.getMemberService().loginMember(id, pass);

		if (memberDto != null) {
			//////////////////// cookie////////////////////////////////////////////
			String idsv = request.getParameter("idsave");
			if ("idsave".equals(idsv)) {
				Cookie cookie = new Cookie("kid_inf", id);
				cookie.setDomain("localhost");
				cookie.setPath(request.getContextPath());
				cookie.setMaxAge(60 * 60 * 24 * 365 * 50);// 50년
				response.addCookie(cookie);
			} else { // 체크풀었다면
				Cookie cookie[] = request.getCookies();
				if(cookie != null) {
					for(Cookie c : cookie) {
						if("kid_inf".equals(c.getName())){
							c.setDomain("localhost");
							c.setPath(request.getContextPath());
							c.setMaxAge(0);// 50년
							response.addCookie(c);
							break;
						}
					}
				}
			}
			/////////////////// session////////////////////////////////////////////
			HttpSession session = request.getSession();// session에 담아서 로그인이 되게 만듬
			session.setAttribute("userInfo", memberDto);
			//////////////////////////////////////////////////////////////////////
			path = "/user/login/loginok.jsp";
		} else {
			path = "/user/login/loginfail.jsp";
		}

		return path;
	}

	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
//		session.setAttribute("userInfo", null); >> 별로 좋지 않은 방법
//		session.removeAttribute("userInfo");	>> 지우는 방법
		session.invalidate(); // >> 이 안의 모든 데이터를 지움.
		return "/user/login/login.jsp";
	}
	
	public String modify(HttpServletRequest request, HttpServletResponse response) {
		return "/index.jsp";
	}
	
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = session.getAttribute("userInfo").toString();
		System.out.println(id);
//		MemberServiceImpl.getMemberService().deleteMember(id);
		return "/index.jsp";
	}

}
