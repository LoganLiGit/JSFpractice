<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.store.pic.model.*"%>
<%@ page import="com.store.model.*"%>
<%
	Store_picVO store_picVO = (Store_picVO) request
			.getAttribute("store_picVO"); //EmpServlet.java (Concroller), �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>
<%
	StoreService storeSvc = new StoreService();
	StoreVO storeVO = storeSvc.getOneStore(store_picVO.getStoreVO().getStore_no());
%>
<html>
<head>
<meta http-equiv="Content-Type">
<title>���a��ƭק� - update_store_pic_input.jsp</title>
</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>
	<script>
		var store_pic1 = function(event) {
			var output = document.getElementById('output');
			output.src = URL.createObjectURL(event.target.files[0]);
		};
	</script>
	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>���a�Ӥ���ƭק� - update_store_pic_input.jsp</h3>
				<a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a>
			</td>
		</tr>
	</table>

	<h3>��ƭק�:</h3>
	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font color='red'> �Эץ��H�U���~:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>

	<FORM METHOD="post" ACTION="store_pic.do" name="form1" enctype="multipart/form-data">
		<table border="0">
			<tr>
				<td>
					�Ӥ��s��: <font color=red><b>*</b></font>
				</td>
				<td><%=store_picVO.getPic_no()%></td>
			</tr>
			<tr>
				<td>
					���a�s��: <font color=red><b>*</b></font>
				</td>
				<td><%=store_picVO.getStoreVO().getStore_no()%></td>
			</tr>
			<tr>
				<td>�Ӥ��W��:</td>
				<td>
					<input type="TEXT" name="pic_name" size="45" value="<%=(store_picVO == null) ? "�ڬO�Ӥ��W��" : store_picVO.getPic_name()%>" />
				</td>
			</tr>


			<tr>
				<td>
					���a�W��: <font color=red> <b>*</b></font>
				</td>
				<td><%=storeVO.getStore_name()%></td>
			</tr>
			<tr>
				<td>�Ӥ�:</td>
				<td>
					<input type="file" name="store_pic" accept="image/*" onchange="store_pic1(event)">
				</td>

			</tr>


		</table>
		<br>
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="store_no" value="<%=store_picVO.getStoreVO().getStore_no()%>">
		<input type="hidden" name="pic_no" value="<%=store_picVO.getPic_no()%>">
		<input type="hidden" name="requestURL" value="<%=request.getAttribute("requestURL")%>">
		<!--��e�X�ק諸�ӷ��������|,�qrequest���X��,�A�e��Controller�ǳ���椧��-->
		<input type="hidden" name="whichPage" value="<%=request.getAttribute("whichPage")%>">
		<!--�u�Ω�:istAllStore_pic.jsp-->
		<input type="submit" value="�e�X�ק�">
	</FORM>
	<img id="output" width="300" height="200" />
	<br>�e�X�ק諸�ӷ��������|:
	<br>
	<b> <font color=blue>request.getAttribute("requestURL"):</font> <%=request.getAttribute("requestURL")%><br> <font color=blue>request.getAttribute("whichPage"):</font> <%=request.getAttribute("whichPage")%> (���d�ҥثe�u�Ω�:istAllEmp.jsp))
	</b>
</body>
</html>
