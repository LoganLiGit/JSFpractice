<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.store.model.*"%>
<%
	StoreVO storeVO = (StoreVO) request.getAttribute("storeVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<title>店家資料修改 - update_store_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>店家資料修改 - update_store_input.jsp</h3>
		<a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></td>
	</tr>
</table>

<h3>資料修改:</h3>
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

<FORM METHOD="post" ACTION="store.do" name="form1">
<table border="0">
	<tr>
		<td>店家編號:<font color=red><b>*</b></font></td>
		<td><%=storeVO.getStore_no()%></td>
	</tr>
	<tr>
		<td>店家帳號:</td>
		<td><input type="TEXT" name="store_account" size="45" value="<%=storeVO.getStore_account()%>" /></td>
	</tr>
	<tr>
		<td>店家密碼:</td>
		<td><input type="TEXT" name="store_password" size="45"	value="<%=storeVO.getStore_password()%>" /></td>
	</tr>
		<tr>
		<td>店家名稱:</td>
		<td><input type="TEXT" name="store_name" size="45"	value="<%=storeVO.getStore_name()%>" /></td>
	</tr>
	<tr>
		<td>註冊日期:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="store_regist_date" value="<%=storeVO.getStore_regist_date()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','store_regist_date','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="開始日期"></a>
		</td>
	</tr>
	<tr>
		<td>聯絡人姓名:</td>
		<td><input type="TEXT" name="manager_name" size="45"	value="<%=storeVO.getManager_name()%>" /></td>
	</tr>
	

<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="store_no" value="<%=storeVO.getStore_no()%>">
<input type="hidden" name="store_longitude" value="<%=storeVO.getStore_longitude()%>">
<input type="hidden" name="store_latitude" value="<%=storeVO.getStore_latitude()%>">
<input type="hidden" name="store_state" value="<%=storeVO.getStore_state()%>">
<input type="hidden" name="store_zipcode" value="<%=storeVO.getStore_zipcode()%>">
<input type="hidden" name="store_city" value="<%=storeVO.getStore_city()%>">
<input type="hidden" name="store_district" value="<%=storeVO.getStore_district()%>">
<input type="hidden" name="store_address" value="<%=storeVO.getStore_address()%>">
<input type="hidden" name="store_phone" value="<%=storeVO.getStore_phone()%>">
<input type="hidden" name="store_type" value="<%=storeVO.getStore_type()%>">
<input type="hidden" name="store_score" value="<%=storeVO.getStore_score()%>">
<input type="hidden" name="store_balance" value="<%=storeVO.getStore_balance()%>">
<input type="hidden" name="store_cell_registcode" value="<%=storeVO.getStore_cell_registcode()%>">
<input type="hidden" name="store_violation" value="<%=storeVO.getStore_violation()%>">
<input type="hidden" name="manager_name" value="<%=storeVO.getManager_name()%>">
<input type="hidden" name="manager_gender" value="<%=storeVO.getManager_gender()%>">
<input type="hidden" name="manager_email" value="<%=storeVO.getManager_email()%>">
<input type="hidden" name="manager_id" value="<%=storeVO.getManager_id()%>">
<input type="hidden" name="manager_cellphone" value="<%=storeVO.getManager_cellphone()%>">
<input type="hidden" name="manager_credit_num" value="<%=storeVO.getManager_credit_num()%>">
<input type="hidden" name="manager_credit_expyear" value="<%=storeVO.getManager_credit_expyear()%>">
<input type="hidden" name="manager_credit_expmonth" value="<%=storeVO.getManager_credit_expmonth()%>">
<input type="hidden" name="manager_credit_secure_num" value="<%=storeVO.getManager_credit_secure_num()%>">
<input type="hidden" name="tickts_limits" value="<%=storeVO.getTickts_limits()%>">

<input type="hidden" name="store_longitude" value="${storeVO.store_longitude}">
<input type="hidden" name="store_latitude" value="${storeVO.store_latitude}">
<input type="hidden" name="store_introduction" value="${storeVO.store_introduction}">
<input type="hidden" name="clicks" value="${storeVO.clicks}">
<input type="hidden" name="store_articles" value="${storeVO.store_articles}">
<input type="hidden" name="store_scopenum" value="${storeVO.store_scopenum}">
<input type="hidden" name="store_pocketnum" value="${storeVO.store_pocketnum}">


<input type="hidden" name="requestURL" value="<%=request.getAttribute("requestURL")%>"><!--原送出修改的來源網頁路徑,從request取出後,再送給Controller準備轉交之用-->
<input type="hidden" name="whichPage" value="<%=request.getAttribute("whichPage")%>">  <!--只用於:istAllEmp.jsp-->
<input type="submit" value="送出修改"></FORM>
<br>送出修改的來源網頁路徑:<br><b>
   <font color=blue>request.getAttribute("requestURL"):</font> <%= request.getAttribute("requestURL")%><br>
   <font color=blue>request.getAttribute("whichPage"):</font> <%= request.getAttribute("whichPage")%> (此範例目前只用於:istAllEmp.jsp))</b>
</body>
</html>
