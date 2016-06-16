<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.store.model.*"%>
<%
	StoreVO storeVO = (StoreVO) request.getAttribute("storeVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<html>
<head>
<title>員工資料 - listOneStore.jsp</title>
</head>
<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='600'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>員工資料 - ListOneStore.jsp</h3>
				<a href="<%=request.getContextPath()%>/select_page.jsp">
					<img src="images/back1.gif" width="100" height="32" border="0">
					回首頁
				</a>
			</td>
		</tr>
	</table>

	<table border='1' bordercolor='#CCCCFF' width='600'>
		<tr>
			<th>店家編號</th>
			<th>店家帳號</th>
			<th>店家密碼</th>
			<th>店家狀態</th>
			<th>店家名稱</th>
			<th>註冊日期</th>
			<th>店家郵遞區號</th>
			<th>店家城市</th>
			<th>店家區域</th>
			<th>店家地址</th>
			<th>店家電話</th>
			<th>店家類型</th>
			<th>店家評分</th>
			<th>帳戶儲值金</th>
			<th>手機註冊碼</th>
			<th>違規次數</th>
			<th>聯絡人姓名</th>
			<th>聯絡人性別</th>
			<th>聯絡人電子郵件</th>
			<th>聯絡人身份證字號</th>
			<th>聯絡人手機</th>
			<th>信用卡號碼</th>
			<th>信用卡到期年</th>
			<th>信用卡到期日</th>
			<th>信用卡安全碼</th>
			<th>團購券權限</th>
			<th>地址經度</th>
			<th>地址緯度</th>
			<th>店家簡介</th>
			<th>瀏覽次數</th>
			<th>店家文章數</th>
			<th>評分人數</th>
			<th>收藏人數</th>
			<th>照片</th>

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
					<input type="submit" value="照片">
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