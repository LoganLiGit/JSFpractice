<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group.table.model.*"%>
<%@ page import="com.group.mem.model.*"%>
<%
	GroupMemVO groupMemVO = (GroupMemVO) request
			.getAttribute("groupVO");
%>
<html>
<head>
<title>揪團團員資料新增 - addGroupMem.jsp</title>
</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>
<body>
	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>揪團團員資料新增 - addGroupMem.jsp</h3>
			</td>
			<td><a href="select_page.jsp">回首頁</a></td>
		</tr>
	</table>
	<h3>揪團:</h3>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>
	<FORM METHOD="post" ACTION="group.do" name="form1">
		<table border="0">
			<tr>
				<td>揪團編號:</td>
				<td><input type="TEXT" name="mem_no" size="45" value="<%=(groupMemVO == null) ? "1" : groupMemVO.getMem_no()%>" /></td>
			</tr>
			<tr>
				<td>會員編號:</td>
				<td><input type="TEXT" name="group_no" size="45" value="<%=(groupMemVO == null) ? "2" : groupMemVO.getGroup_no()%>" /></td>
			</tr>
			
		</table>
		<br>
		<input type="hidden" name="action" value="insertMem">
		<input type="submit" value="送出">
	</FORM>
</body>
</html>