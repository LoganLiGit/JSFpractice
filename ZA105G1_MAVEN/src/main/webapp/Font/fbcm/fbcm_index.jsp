<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.store.model.*"%>
<%--註冊JSTL	 --%>

<!-- 驗證區域 -->
<%-- 如果強制用網頁強登 沒有店家編號 直接轉導到登入區域 --%>
<%
	if (session.getAttribute("storeno") == null ){
		RequestDispatcher reqd = request.getRequestDispatcher("/login/storelogin.jsp");
		reqd.forward(request,response);
	}
	else{
		Integer store_no = (Integer)session.getAttribute("storeno");
		pageContext.setAttribute("store_no", store_no);
	}
%>
<%
	StoreVO storevo = (StoreVO) request.getAttribute("storeVO");
	pageContext.setAttribute("storevo", storevo);
%>
<jsp:useBean id="storeSrv" class="com.store.model.StoreService"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td><h3>店家頁面管理系統</h3> <font color=red>( MVC )</font></td>
		</tr>
	</table>
	<p>front_Buycoupon_management</p>
	<h3>店家頁面管理系統入:</h3>
	
	<!-- 錯誤修正 -->
	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>
	
	
<%-- 	<p><%= storevo.getStore_name() %> 你好</p> --%>
	<p>${storevo.store_name } 你好</p>
	<p>${store_no} 你好</p>
	<ul>
		<li>
			<FORM method="post" action="<%=request.getContextPath()%>/font/Fbcm.do">
				<input type="submit" value="申請團購劵">
				<input type="hidden" name="action" value="font_fbcm_add_tickettype">				
				<input type="hidden" name="store_no" value="${store_no}">			
			</FORM>			
		</li>
		<li>
			<FORM method="post" action="<%=request.getContextPath()%>/font/Fbcm.do">
				<input type="submit" value="查詢審核中團購劵">
				<input type="hidden" name="time" value="5">
				<input type="hidden" name="action" value="font_fbcm_nook_tickettype">
				<input type="hidden" name="store_no" value="${store_no}">			
			</FORM>		
		</li>
		<li>
			<FORM method="post" action="<%=request.getContextPath()%>/font/Fbcm.do">
				<input type="submit" value="查看團購劵狀態">
				<input type="hidden" name="action" value="font_fbcm_all_tickettype">
				<input type="hidden" name="store_no" value="${store_no}">			
			</FORM>	
		</li>
	</ul>

</body>
</html>