<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.store.model.*"%>
<%
	StoreVO storeVO = (StoreVO) request.getAttribute("storeVO");
%>

<html>
<head>
<meta http-equiv="Content-Type">
<title>���a��Ʒs�W - addStore.jsp</title>
</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>
	<script>
		var store_pic1 = function(event) {
			var output = document.getElementById('output1');
			output.src = URL.createObjectURL(event.target.files[0]);
			
		};
		var store_pic3 = function(event) {
			var output = document.getElementById('output3');
			output.src = URL.createObjectURL(event.target.files[0]);
		};
		var store_pic2 = function(event) {
			var output = document.getElementById('output2');
			output.src = URL.createObjectURL(event.target.files[0]);
		};
	</script>
	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>���a��Ʒs�W - addStore.jsp</h3>
			</td>
			<td>
				<a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">�^����</a>
			</td>
		</tr>
	</table>

	<h3>��Ʃ��a:</h3>
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

	<FORM METHOD="post" ACTION="store.do" name="form1" enctype="multipart/form-data">
		<table border="0">

			<tr>
				<td>���a�b��:</td>
				<td>
					<input type="TEXT" name="store_account" size="45" value="<%=(storeVO == null) ? "a123456" : storeVO
					.getStore_account()%>" />
				</td>
			</tr>
			<tr>
				<td>���a�K�X:</td>
				<td>
					<input type="TEXT" name="store_password" size="45" value="<%=(storeVO == null) ? "a123456" : storeVO
					.getStore_password()%>" />
				</td>
			</tr>
			<tr>
				<td>���a�W��:</td>
				<td>
					<input type="TEXT" name="store_name" size="45" value="<%=(storeVO == null) ? "�Ѯ�n�N��x�l" : storeVO.getStore_name()%>" />
				</td>
			</tr>
			<tr>
				<%
					java.sql.Date date_SQL = new java.sql.Date(
							System.currentTimeMillis());
				%>
				<td>���U���:</td>
				<td bgcolor="#CCCCFF">
					<input class="cal-TextBox" onFocus="this.blur()" size="9" readonly type="text" name="store_regist_date" value="<%=(storeVO == null) ? date_SQL : storeVO
					.getStore_regist_date()%>">
					<a class="so-BtnLink" href="javascript:calClick();return false;" onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);" onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);" onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','store_regist_date','BTN_date');return false;"> <img align="middle" border="0" name="BTN_date" src="images/btn_date_up.gif" width="22" height="17" alt="�}�l���">
					</a>
				</td>
			</tr>
			<tr>
				<td>�p���H�m�W:</td>
				<td>
					<input type="TEXT" name="manager_name" size="45" value="<%=(storeVO == null) ? "�n�w�w" : storeVO.getManager_name()%>" />
				</td>
			</tr>

			<%-- 			<jsp:useBean id="deptSvc" scope="page" --%>
			<%-- 				class="com.dept.model.DeptService" /> --%>
			<!-- 			<tr> -->
			<!-- 				<td>����:<font color=red><b>*</b></font></td> -->
			<!-- 				<td><select size="1" name="deptno"> -->
			<%-- 						<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
			<%-- 							<option value="${deptVO.deptno}" --%>
			<%-- 								${(empVO.deptno==deptVO.deptno)? 'selected':'' }>${deptVO.dname} --%>
			<%-- 						</c:forEach> --%>
			<!-- 				</select></td> -->
			<!-- 			</tr> -->

			<tr>
				<td>�Ӥ�:</td>
				<td>
					<input type="file" name="store_pic[]" accept="image/*" onchange="store_pic1(event)">
					</br>
					<input type="file" name="store_pic[]" accept="image/*" onchange="store_pic2(event)">
					</br>
					<input type="file" name="store_pic[]" accept="image/*" onchange="store_pic3(event)">
				</td>

			</tr>
		</table>
		<br>
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="�e�X�s�W">
	</FORM>

	<img id="output1" width="300" height="200" />
	<img id="output2" width="300" height="200" />
	<img id="output3" width="300" height="200" />
</body>

</html>
</body>
</html>