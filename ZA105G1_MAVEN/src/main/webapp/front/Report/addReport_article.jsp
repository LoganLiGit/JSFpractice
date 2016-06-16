<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.report.model.*"%>
<%
ReportVO reportVO = (ReportVO) request.getAttribute("reportVO");
%>

<html>
<head>
<title>���u��Ʒs�W - addReport.jsp</title></head>
<div id="popupcalendar" class="text"></div>


<body>

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
		<td>���|�H:</td>
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
		<td>�Q���|�����O:</td>
		<td>
			<jsp:useBean id="articleSvc" scope="page" class="com.article.model.ArticleService" />
			<select size="1" name="article_no">
				<c:forEach var="articleVO" items="${articleSvc.all}">
					<option value="${articleVO.article_no}">${articleVO.article_no}
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
