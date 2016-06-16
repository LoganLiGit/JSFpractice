<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.store.pic.model.*"%>
<%@ page import="com.store.model.*"%>
<%
	Store_picVO store_picVO = (Store_picVO) request.getAttribute("store_picVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<%
  StoreService storeSvc = new StoreService();
  StoreVO storeVO = storeSvc.getOneStore(store_picVO.getStoreVO().getStore_no());
%>
<html>
<head>
<title>店家照片資料 - listOneStore_pic.jsp</title>
</head>
<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='600'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>員工資料 - ListOneStore_pic.jsp</h3>
				<a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
			</td>
		</tr>
	</table>

	<table border='1' bordercolor='#CCCCFF' width='600'>
		<tr>

			<th>照片編號</th>
			<th>檔案名稱</th>
			<th>店家編號</th>
			<th>店家照片</th>
			<th>照片格式</th>
			<th>店家名稱</th>
		</tr>
		<tr align='center' valign='middle'>

			<td>${store_picVO.pic_no}</td>
			<td>${store_picVO.pic_name}</td>
			<td>${store_picVO.storeVO.store_no}</td>
			<td>
				<img width="120" height="100" src="<%=request.getContextPath()%>/store_pic/DBGifReader3.do?pic_no=${store_picVO.pic_no}" />
			</td>
			<td>${store_picVO.pic_format}</td>
			<td><%=store_picVO.getStoreVO().getStore_no()%>【<%=storeVO.getStore_name()%> - <%=storeVO.getManager_name()%>】</td>
		</tr>


	</table>

</body>
</html>