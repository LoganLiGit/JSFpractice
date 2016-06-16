<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<% 	
	MemberService memberSvc = new MemberService();
	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
	//memberSvc.updateMemberOnline(memberVO.getMem_no(), 0);//更新上線狀態
	pageContext.setAttribute("memberVO", memberVO);
	if(!session.isNew()){
		memberSvc.updateMemberOnline(memberVO.getMem_no(), 0);//更新上線狀態
	}
	session.invalidate();
	
%>

<c:redirect url="index.jsp"></c:redirect>
</body>
</html>