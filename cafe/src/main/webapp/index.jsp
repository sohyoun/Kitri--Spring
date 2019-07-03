<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kitri.cafe.member.model.MemberDto"%>
<%
response.sendRedirect(request.getContextPath() + "/boardadmin/boardmenu");

MemberDto memberDto = new MemberDto();
memberDto.setId("mongswell");
memberDto.setName("끼룩이");
memberDto.setEmail("kkiruk@naver.com");

session.setAttribute("userInfo", memberDto);
%>