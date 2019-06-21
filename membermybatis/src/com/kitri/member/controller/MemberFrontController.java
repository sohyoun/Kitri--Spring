package com.kitri.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.service.MemberServiceImpl;
import com.kitri.util.MoveUrl;
import com.kitri.util.SiteConstance;

@WebServlet("/user")
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String path="/index.jsp"; //내가 이동할 경로(default는 index.jsp)
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String act = request.getParameter("act");

		// act.equals("mvjoin") >> nullpointerexception나기 때문에 좋지 않음. act가 null
		// 이걸 쓰고 싶으면 if(act!=null)을 위에서 해줘야함.
		// 따라서 mvjoin은 null일수없기 때문에 밑의 방법이 더 좋다.
		if ("mvjoin".equals(act)) {
			MoveUrl.redirect(request, response, "/user/member/member.jsp");
		} else if ("mvlogin".equals(act)) {
			MoveUrl.redirect(request, response, "/user/login/login.jsp");
		} else if ("idcheck".equals(act)) {	//idcheck도 controller에 넣는것이 원칙적 but 에이젝스여서 안넣음
			String sid = request.getParameter("sid");
//			System.out.println("검색아이디 : " + sid);
			String resultXML = MemberServiceImpl.getMemberService().idCheck(sid);

			response.setContentType("text/xml;charset=UTF-8");// text문자열로 보내지만 받을때 xml로 인식해라
			PrintWriter out = response.getWriter();
			out.print(resultXML);

		} else if ("zipsearch".equals(act)) { //zipsearch도 controller에 넣는것이 원칙적 but 에이젝스여서 안넣음
			String doro = request.getParameter("doro");
//			System.out.println("검색 도로명 : "+doro);
			String resultXML = MemberServiceImpl.getMemberService().zipSearch(doro);
//			System.out.println(resultXML);
			response.setContentType("text/xml;charset=UTF-8");// text문자열로 보내지만 받을때 xml로 인식해라
			PrintWriter out = response.getWriter();
			out.print(resultXML);
		} else if ("register".equals(act)) {
			path = MemberController.getMemberController().register(request, response);
//			MoveUrl.redirect(request, response, path); //path경로로 이동. //send redirect는 보내기 전 request와 response를 clear하고 가져감
																		//따라서 열심히 request에 담았는데 clear되기 때문에 에러남
																		//>>forward를 사용!(내용을 그대로 가져감)
			MoveUrl.forward(request, response, path);
		} else if ("login".equals(act)) {
			path = MemberController.getMemberController().login(request, response);
			MoveUrl.forward(request, response, path);
		} else if ("logout".equals(act)) {
			path = MemberController.getMemberController().logout(request, response);
			MoveUrl.redirect(request, response, path);
		} else if ("mvmodify".equals(act)) {
			path = MemberController.getMemberController().modify(request, response);
			MoveUrl.redirect(request, response, path);
		} else if ("deletemember".equals(act)) {
			path = MemberController.getMemberController().delete(request, response);
			MoveUrl.redirect(request, response, path);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(SiteConstance.ENCODE);
		doGet(request, response);
	}


}
