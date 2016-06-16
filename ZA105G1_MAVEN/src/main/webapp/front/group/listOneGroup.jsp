<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.group.table.model.*"%>   
<%
	GroupTableVO groupVO = (GroupTableVO) request.getAttribute("groupVO");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>���θ�� - listOneGroup.jsp</title>
</head>

<body>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>���θ�� - listOneGroup.jsp</h3>
		<a href="select_page.jsp">�^����</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
			<th>揪團編號</th>
			<th>會員編號</th>
			<th>店家編號</th>
			<th>揪團人數</th>
			<th>簡介</th>
			<th>圖片</th>
			<th>揪團時間</th>
			<th>發起時間</th>
			<th>截止時間</th>
			<th>揪團狀態</th>
			<th>修改</th>
			<th>刪除</th>
	</tr>
	
	<tr align='center' valign='middle'>
			<td>${groupVO.group_no}</td>
			<td>${groupVO.mem_no}</td>
			<td>${groupVO.storeVO.store_no}</td>
			<td>${groupVO.group_num}</td>
			<td>${groupVO.group_intro}</td>
			<td>${groupVO.group_photo}</td>
			<td>${groupVO.group_eat_date}</td>
			<td>${groupVO.group_start_date}</td>
			<td>${groupVO.group_stop_date}</td>
			<td>${groupVO.group_status}</td>
	</tr>
</table>	


</body>
</html>