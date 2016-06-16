<%@ page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group.table.model.*"%>
<%
	GroupTableVO groupVO = (GroupTableVO) request
			.getAttribute("groupVO");
%>
<html>
<head>
<title>揪團資料新增 - addGroup.jsp</title>
</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>

<div id="popupcalendar" class="text"></div>
<body>
	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>揪團資料新增 - addGroup.jsp</h3>
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
				<td>會員編號:</td>
				<td><input type="TEXT" name="mem_no" size="45" value="<%=(groupVO == null) ? "1" : groupVO.getMem_no()%>" /></td>
			</tr>
			<tr>
				<td>店家編號:</td>
				<td><input type="TEXT" name="store_no" size="45" value="<%=(groupVO == null) ? "5" : groupVO.getStoreVO()
					.getStore_no()%>" /></td>
			</tr>
			<tr>
				<td>揪團人數:</td>
				<td><input type="TEXT" name="group_num" size="45" value="<%=(groupVO == null) ? "10" : groupVO.getGroup_num()%>" /></td>
			</tr>
			<tr>
				<td>團名:</td>
				<td><input type="TEXT" name="group_name" size="45" value="<%=(groupVO == null) ? "安安" : groupVO.getGroup_name()%>" /></td>
			</tr>
			<tr>
				<td>簡介:</td>
				<td><input type="TEXT" name="group_intro" size="45" value="<%=(groupVO == null) ? "安安" : groupVO.getGroup_intro()%>" /></td>
			</tr>
			<tr>
				<%
					Timestamp date_SQL = new Timestamp(System.currentTimeMillis());
				%>
				<td>發起日期:</td>
				<td bgcolor="#CCCCFF"><input class="cal-TextBox" onFocus="this.blur()" size="9" readonly type="text" name="group_start_date"
						value="<%=(groupVO == null) ? date_SQL : groupVO
					.getGroup_start_date()%>"> <a class="so-BtnLink"
						href="javascript:calClick();return false;" onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
						onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
						onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','group_start_date','BTN_date');return false;">
						<img align="middle" border="0" name="BTN_date" src="images/btn_date_up.gif" width="22" height="17" alt="發起日期">
					</a></td>
			</tr>
			<tr>
				<td>吃飯時間:</td>
				<td bgcolor="#CCCCFF"><input class="cal-TextBox" onFocus="this.blur()" size="9" readonly type="text" name="group_eat_date"
						value="<%=(groupVO == null) ? date_SQL : groupVO
					.getGroup_eat_date()%>"> <a class="so-BtnLink"
						href="javascript:calClick();return false;" onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
						onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
						onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','group_eat_date','BTN_date');return false;">
						<img align="middle" border="0" name="BTN_date" src="images/btn_date_up.gif" width="22" height="17" alt="吃飯時間">
					</a></td>
			</tr>
			<tr>
				<td>截止日期:</td>
				<td bgcolor="#CCCCFF"><input class="cal-TextBox" onFocus="this.blur()" size="9" readonly type="text" name="group_stop_date"
						value="<%=(groupVO == null) ? date_SQL : groupVO
					.getGroup_stop_date()%>"> <a class="so-BtnLink"
						href="javascript:calClick();return false;" onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
						onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
						onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','group_stop_date','BTN_date');return false;">
						<img align="middle" border="0" name="BTN_date" src="images/btn_date_up.gif" width="22" height="17" alt="截止日期">
					</a></td>
			</tr>
		</table>
		<br>
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="送出">
	</FORM>
</body>
</html>