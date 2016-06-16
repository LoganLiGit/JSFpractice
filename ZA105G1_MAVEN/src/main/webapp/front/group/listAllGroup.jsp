<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group.table.model.*"%>
<%@ page import="java.util.*"%>
<%
	GroupTableService groupSvc = new GroupTableService();
	List<GroupTableVO> list = groupSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<html>
<head>
<title>所有揪團資料 - listAllGroup.jsp</title>
</head>
<body>
	<a href="select_page.jsp">回首頁</a>
	<c:if test="${not empty errorMsgs}">
		<font color='red'> 請修正以下錯誤:
			<ul>
				<c:forEach var="errMsg" items="${errorMsgs}">
					<li>${errMsg}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>
	<table border='1' bordercolor='#CCCCFF' width='800'>
		<tr>
			<th>揪團編號</th>
			<th>會員編號</th>
			<th>店家編號</th>
			<th>揪團人數</th>
			<th>團名</th>
			<th>簡介</th>
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
		<%@ include file="page/page1.file"%>
		<c:forEach var="groupVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr align='center' valign='middle'>
				<td>${groupVO.group_no}</td>
				<td>${groupVO.mem_no}</td>
				<td>${groupVO.storeVO.store_no}</td>
				<td>${groupVO.group_num}</td>
				<td>${groupVO.group_name}</td>
				<td>${groupVO.group_intro}</td>
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
						<input type="hidden" name="whichPage" value="<%=whichPage%>">
						<!--送出當前是第幾頁給Controller-->
					</FORM>
				</td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group/group.do">
						<input type="submit" value="修改">
						<input type="hidden" name="group_no" value="${groupVO.group_no}">
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<!--送出本網頁的路徑給Controller-->
						<input type="hidden" name="whichPage" value="<%=whichPage%>">
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
						<input type="hidden" name="whichPage" value="<%=whichPage%>">
						<!--送出當前是第幾頁給Controller-->
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page/page2.file"%>
	<%
		if (request.getAttribute("listGroupMems_ByGroup_no") != null) {
	%>
	<jsp:include page="listGroupMems_ByGroup_no.jsp" />
	<%
		}
	%>
</body>
</html>