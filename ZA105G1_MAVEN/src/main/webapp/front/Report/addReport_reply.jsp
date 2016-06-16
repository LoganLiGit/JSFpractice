<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.report.model.*"%>
<%
ReportVO reportVO = (ReportVO) request.getAttribute("reportVO");
%>

<html>
<head>
<title>員工資料新增 - addReport.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<script type="text/javascript">

</script>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>員工資料新增 - addReport.jsp</h3>
		</td>
		<td>
		   <a href="select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>資料員工:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back/Report/Back_Report_Servlet.do" name="form1">
<table border="0">

	<tr>
		<td>檢舉人編號:</td>
		<td>
			<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
			<select size="1" name="mem_no">
				<c:forEach var="memberVO" items="${memberSvc.all}">
					<option value="${memberVO.mem_no}">${memberVO.mem_name}
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>被檢舉的留言:</td>
		<td>
			<jsp:useBean id="replySvc" scope="page" class="com.reply.model.ReplyService" />
			<select size="1" name="reply_no">
				<c:forEach var="replyVO" items="${replySvc.all}">
					<option value="${replyVO.reply_no}">${replyVO.reply_no}
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>檢舉內容:</td>
		<td>
			<input type="TEXT" name="report_content" size="45" value="<%= (reportVO==null)? "" : reportVO.getReport_content()%>" />
		</td>
	</tr>
	<tr>
		<td>檢舉狀態:</td>
		<td>
			<input type="TEXT" name="report_status" size="45" value="<%= (reportVO==null)? "0" : reportVO.getReport_status()%>" />
		</td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
</FORM>
</body>

</html>
