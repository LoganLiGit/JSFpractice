<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.store.pic.model.*"%>
<%
	Store_picVO store_picVO = (Store_picVO) request
			.getAttribute("store_picVO");
	Integer store_no = (Integer)request.getAttribute("store_no");
%>

<html>

<head>
<meta http-equiv="Content-Type">
<title>店家照片資料新增 - addStore_pic.jsp</title>
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
				<h3>員工資料新增 - addStore_pic.jsp</h3>
			</td>
			<td>
				<a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
			</td>
		</tr>
	</table>

	<h3>資料店家照片:</h3>
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
	<!-- enctype="multipart/form-data" -->
	<FORM METHOD="post" ACTION="store_pic.do" name="form1" enctype="multipart/form-data">
		<table border="0">


			<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />
			<tr>
				<td>
					店家編號:<font color=red><b>*</b></font>
				</td>
				<td>
					<%=store_no%>
				</td>
			</tr>

			<tr>
				<td>照片:</td>
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
		<input type="hidden" name="store_no" value="<%=store_no%>">
		<input type="submit" value="送出新增">

	</FORM>


	<img id="output1" width="300" height="200" />
	<img id="output2" width="300" height="200" />
	<img id="output3" width="300" height="200" />
</body>

</html>
