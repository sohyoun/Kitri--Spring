<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templete/header.jsp" %>
<font size="13" color="red">
	아이디 또는 비밀번호를 다시 확인하세요.<br>
	등록되지 않은 아이디이거나, 아이디 또는 비밀번호를 잘못 입력하셨습니다.
	</font>
	<a href="${root}/user?act=mvlogin">로그인</a>
<%@ include file="/WEB-INF/views/templete/footer.jsp" %>