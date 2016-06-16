<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--註冊JSTL	 --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登入</title>
</head>
<body bgcolor='white' >
	<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />

	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td><h3>Login</h3> <font color=red>( MVC )</font></td>
		</tr>
	</table>
	<p>Login</p>
	<h3>登入:</h3>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>
	<ul>
		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/font/Fbcm.do">
				<b>選擇店家編號:</b> 
				<select size="1" name="store_no">
					<c:forEach var="storeVO" items="${storeSvc.all}">
						<option value="${storeVO.store_no}">${storeVO.store_no}
					</c:forEach>
				</select> 
				<input type="submit" value="送出"> 
				<input type="hidden" name="action" value="fbcm_login">
			</FORM>
		</li>	
	</ul>
</body>
</html>