<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.store.model.*"%>
<%
	StoreVO storeVO = (StoreVO) request.getAttribute("storeVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>
<html>
<head>
<title>���u��� - listOneStore.jsp</title>
</head>
<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='600'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>���u��� - ListOneStore.jsp</h3>
				<a href="<%=request.getContextPath()%>/select_page.jsp">
					<img src="images/back1.gif" width="100" height="32" border="0">
					�^����
				</a>
			</td>
		</tr>
	</table>

	<table border='1' bordercolor='#CCCCFF' width='600'>
		<tr>
			<th>���a�s��</th>
			<th>���a�b��</th>
			<th>���a�K�X</th>
			<th>���a���A</th>
			<th>���a�W��</th>
			<th>���U���</th>
			<th>���a�l���ϸ�</th>
			<th>���a����</th>
			<th>���a�ϰ�</th>
			<th>���a�a�}</th>
			<th>���a�q��</th>
			<th>���a����</th>
			<th>���a����</th>
			<th>�b���x�Ȫ�</th>
			<th>������U�X</th>
			<th>�H�W����</th>
			<th>�p���H�m�W</th>
			<th>�p���H�ʧO</th>
			<th>�p���H�q�l�l��</th>
			<th>�p���H�����Ҧr��</th>
			<th>�p���H���</th>
			<th>�H�Υd���X</th>
			<th>�H�Υd����~</th>
			<th>�H�Υd�����</th>
			<th>�H�Υd�w���X</th>
			<th>���ʨ��v��</th>
			<th>�a�}�g��</th>
			<th>�a�}�n��</th>
			<th>���a²��</th>
			<th>�s������</th>
			<th>���a�峹��</th>
			<th>�����H��</th>
			<th>���äH��</th>
			<th>�Ӥ�</th>

		</tr>
		<tr align='center' valign='middle'>
			<td>${storeVO.store_no}</td>
			<td>${storeVO.store_account}</td>
			<td>${storeVO.store_password}</td>
			<td>${storeVO.store_state}</td>
			<td>${storeVO.store_name}</td>
			<td>${storeVO.store_regist_date}</td>
			<td>${storeVO.store_zipcode}</td>
			<td>${storeVO.store_city}</td>
			<td>${storeVO.store_district}</td>
			<td>${storeVO.store_address}</td>
			<td>${storeVO.store_phone}</td>
			<td>${storeVO.store_type}</td>
			<td>${storeVO.store_score}</td>
			<td>${storeVO.store_balance}</td>
			<td>${storeVO.store_cell_registcode}</td>
			<td>${storeVO.store_violation}</td>
			<td>${storeVO.manager_name}</td>
			<td>${storeVO.manager_gender}</td>
			<td>${storeVO.manager_email}</td>
			<td>${storeVO.manager_id}</td>
			<td>${storeVO.manager_cellphone}</td>
			<td>${storeVO.manager_credit_num}</td>

			<td>${storeVO.manager_credit_expyear}</td>
			<td>${storeVO.manager_credit_expmonth}</td>
			<td>${storeVO.manager_credit_secure_num}</td>
			<td>${storeVO.tickts_limits}</td>
			<td>${storeVO.store_longitude}</td>
			<td>${storeVO.store_latitude}</td>
			<td>${storeVO.store_introduction}</td>
				<td>${storeVO.clicks}</td>
				<td>${storeVO.store_articles}</td>
				<td>${storeVO.store_scopenum}</td>
					<td>${storeVO.store_pocketnum}</td>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do">
					<input type="hidden" name="store_no" value="${storeVO.store_no}">
					<input type="submit" value="�Ӥ�">
					<input type="hidden" name="action" value="listStore_pics_ByStore_no_A">
				</FORM>
			</td>
		</tr>
	</table>


	<%
		if (request.getAttribute("listStore_pics_ByStore_no") != null) {
	%>
	<jsp:include page="listStore_pics_ByStore_no.jsp" />
	<%
		}
	%>

	<font color=blue>request.getServletPath():</font><%=request.getServletPath()%><br>

	<font color=blue>request.getRequestURI(): </font><%=request.getRequestURI()%>

</body>
</html>