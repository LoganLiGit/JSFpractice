<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ad.model.*"%>
<%@ page import="com.store.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	AdService adSvc = new AdService();
	List<AdVO> list = adSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />

<html>
<head>
<title>�Ҧ����u��� - listAllAd.jsp</title>
</head>
<body bgcolor='white'>
	<b><font color=red>�����m�߱ĥ� EL ���g�k����:</font></b>
	<table border='1' cellpadding='5' cellspacing='0' width='800'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>�Ҧ����u��� - ListAllAd.jsp</h3> <a href="<%=request.getContextPath()%>/select_page.jsp"><img
					src="images/back1.gif" width="100" height="32" border="0">�^����</a>
			</td>
		</tr>
	</table>

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
	
		<%
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());
			String date = df.format(date_SQL);
		%>
				
	<table border='1' bordercolor='#CCCCFF' width='800'>
		<tr>
			<th>�s�i�s��</th>
			<th>���a�s��</th>
			<th>�s�i�Ϥ�</th>
			<th>�}�l���</th>
			<th>�������</th>
			<th>�ק�</th>
			<th>�R��</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="adVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr align='center' valign='middle'>
				<td>${adVO.ad_no}</td>
				<td>
					<c:forEach var="StoreVO" items="${storeSvc.all}">
	                    <c:if test="${adVO.store_no==StoreVO.store_no}">
		                    ${StoreVO.store_no}�i${StoreVO.store_name} �j
	                    </c:if>
	                </c:forEach>
                </td>
				<td><img width="120" height="100" src="<%=request.getContextPath()%>/ad/ReadImage.do?ad_no=${adVO.ad_no}" /></td>
				<td>${adVO.ad_date}</td>
				<td>${adVO.ad_date_ed}</td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ad/ad.do">
						<input type="submit" value="�ק�"> 
						<input type="hidden" name="ad_no" value="${adVO.ad_no}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ad/ad.do">
						<input type="submit" value="�R��"> 
						<input type="hidden" name="ad_no" value="${adVO.ad_no}"> 
						<input type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>
