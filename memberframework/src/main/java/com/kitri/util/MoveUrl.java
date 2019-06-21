package com.kitri.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MoveUrl {
	
	public static void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path); // redirect : 안에 url을 지정. url은 어디든 갈 수 있다.
	}
	
	public static void forward(HttpServletRequest request, HttpServletResponse response, String path) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path); // forward : 안에 path를 지정. 내 프로젝트 안에서만 이동 가능.
		dispatcher.forward(request, response);
	}
}
