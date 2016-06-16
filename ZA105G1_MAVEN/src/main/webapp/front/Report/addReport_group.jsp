<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.report.model.*"%>
<%
ReportVO reportVO = (ReportVO) request.getAttribute("reportVO");
%>

<html>
<head>
<title>���u��Ʒs�W - addReport.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<script type="text/javascript">

</script>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>���u��Ʒs�W - addReport.jsp</h3>
		</td>
		<td>
		   <a href="select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">�^����</a>
	    </td>
	</tr>
</table>

<h3>��ƭ��u:</h3>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back/Report/Back_Report_Servlet.do" name="form1" id="form1">
<table border="0">

	<tr>
		<td>���|�H�s��:</td>
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
		<td>�Q���|������:</td>
		<td>
			<jsp:useBean id="groupVO" scope="page" class="com.group.table.model.GroupTableService" />
			<select size="1" name="group_no">
				<c:forEach var="groupVO" items="${groupVO.all}">
					<option value="${groupVO.group_no}">${groupVO.group_no}
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>���|���e:</td>
		<td>
			<input type="TEXT" name="report_content" size="45" value="<%= (reportVO==null)? "�uԣ" : reportVO.getReport_content()%>" />
		</td>
	</tr>
	<tr>
		<td>���|���A:</td>
		<td>
			<input type="TEXT" name="report_status" size="45" value="<%= (reportVO==null)? "0" : reportVO.getReport_status()%>" />
		</td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
</body>

</html>
