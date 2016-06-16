<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>


<jsp:useBean id="listGroups_ByCompositeQuery" scope="request" type="java.util.List" />

<html>
<head>
<title>複合查詢 - listGroups_ByCompositeQuery.jsp</title>
</head>
<body bgcolor='white'>
	<b>
		<font color=blue>
			☆萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位
			<br>
			☆此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能:
		</font>
	</b>
	<table border='1' cellpadding='5' cellspacing='0' width='800'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>
					<font color=red>複合查詢</font>
					店家 - listGroups_ByCompositeQuery.jsp
				</h3>
				<a href="<%=request.getContextPath()%>/group/select_page.jsp">
					<img src="images/back1.gif" width="100" height="32" border="0">
					回首頁
				</a>
			</td>
		</tr>
	</table>


	<table border='1' bordercolor='#CCCCFF' width='800'>
		<tr>
			<th>揪團編號</th>
			<th>會員編號</th>
			<th>店家編號</th>
			<th>揪團人數</th>
			<th>簡介</th>
			<th>城市</th>
			<th>類型</th>
			<th>圖片</th>
			<th>圖片外觀</th>
			<th>揪團時間</th>
			<th>發起時間</th>
			<th>截止時間</th>
			<th>揪團狀態</th>
			<th>團員名單</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>

		<c:forEach var="groupVO" items="${listGroups_ByCompositeQuery}">
			<tr align='center' valign='middle' ${(groupVO.group_no==param.group_no) ? 'bgcolor=#CCCCFF':''}>
				<td>${groupVO.group_no}</td>
				<td>${groupVO.mem_no}</td>
				<td>${groupVO.storeVO.store_no}</td>
				<td>${groupVO.group_num}</td>
				<td>${groupVO.group_intro}</td>
				<td>${groupVO.storeVO.store_city}</td>
				<td>${groupVO.storeVO.store_type}</td>
				<td>${groupVO.storeVO.store_pics.iterator().next().store_pic}</td>
				<td>${groupVO.storeVO.store_pics.iterator().next().pic_name}</td>
				<td>${groupVO.group_eat_date}</td>
				<td>${groupVO.group_start_date}</td>
				<td>${groupVO.group_stop_date}</td>
				<td>${groupVO.group_status}</td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group/group.do">
						<input type="hidden" name="group_no" value="${groupVO.group_no}">
						<input type="submit" value="名單">
						<input type="hidden" name="action" value="listGroupMems_ByGroup_no">
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<!--送出本網頁的路徑給Controller-->
				
						<!--送出當前是第幾頁給Controller-->
					</FORM>
				</td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group/group.do">
						<input type="submit" value="修改">
						<input type="hidden" name="group_no" value="${groupVO.group_no}">
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<!--送出本網頁的路徑給Controller-->
		
						<!--送出當前是第幾頁給Controller-->
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group/group.do">
						<input type="submit" value="刪除">
						<input type="hidden" name="group_no" value="${groupVO.group_no}">
						<input type="hidden" name="action" value="delete">
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<!--送出本網頁的路徑給Controller-->
					
						<!--送出當前是第幾頁給Controller-->
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>

	<%
		if (request.getAttribute("listGroupMems_ByGroup_no") != null) {
	%>
	<jsp:include page="listGroupMems_ByGroup_no.jsp" />
	<%
		}
	%>

	<font color=blue>request.getServletPath():</font><%=request.getServletPath()%><br>

	<font color=blue>request.getRequestURI(): </font><%=request.getRequestURI()%>
</body>
</html>
