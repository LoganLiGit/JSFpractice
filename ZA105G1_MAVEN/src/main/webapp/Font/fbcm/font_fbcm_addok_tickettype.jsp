<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--註冊JSTL	 --%>
<%@ page import="com.TicketType.model.*" %>
<%@ page import="com.store.model.*"%>
<%-- 如果強制用網頁強登 沒有店家編號 直接轉導到登入區域 --%>

<!-- 驗證區域 -->
<%-- 如果強制用網頁強登 沒有店家編號 直接轉導到登入區域 --%>
<%
	if (session.getAttribute("storeno") == null ){
		RequestDispatcher reqd = request.getRequestDispatcher("/login/storelogin.jsp");
		reqd.forward(request,response);
	}
%>
<%
	TicketTypeVO tickettypevo = (TicketTypeVO) session.getAttribute("tickettypevo2");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增成功</title>
<!-- 需要的JQuery的匯入 -->
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/myorderform/myorderform_list01.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/myorderform/myorderform_list02.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/myorderform/input01.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/myorderform/input02.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/myorderform/input03.css">
</head>
<body>
<!--============ header ============-->
<div id="header" style="width:100%; height: 225px;">
	<iFrame style="width:100%; height: 225px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/Header_store.jsp" scrolling="no"> </iFrame>
</div>
<!--============ header ============-->
<div class="container regBox" id="inputtr">
	<div class="company_inner div_center" style="width: 700px">
		<h2 class="align_c font_normal blue">新增成功團購券</h2>
			<p>此新增團購劵需要審核唷!!</p>
			<ul class="apLogin">
				<li>
					<label>團購券種類編號:</label>
					<div class="col"><%= tickettypevo.getTickets_type_no() %></div>
				</li>
				<li>
					<label>團購券名稱:</label>
					<div class="col"><%= tickettypevo.getTickets_type_name() %></div>
				</li>
				<li>
					<label>團購劵總數量:</label>
					<div class="col"><%= tickettypevo.getTickets_total() %></div>
				</li>
				<li>
					<label>店家編號:</label>
					<div class="col"><%= tickettypevo.getStore_no() %></div>
				</li>
				<li>
					<label>上架時間:</label>
					<div class="col"><%= tickettypevo.getUpper_date() %></div>
				</li>
				<li>
					<label>下架時間:</label>
					<div class="col"><%= tickettypevo.getLower_date() %></div>
				</li>
				<li>
					<label>團購券種類編號:</label>
					<div class="col"><%= tickettypevo.getTickets_type_no() %></div>
				</li>
			</ul>
		<div class="next_btn">
			<input class="btn blue_button btn_Submit" type="submit" value="查看所有團購劵">
		</div>	
	</div>
</div>
</body>
</html>