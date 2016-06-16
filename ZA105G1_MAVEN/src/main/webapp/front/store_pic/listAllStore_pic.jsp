<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.pic.model.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	Store_picService store_picSvc = new Store_picService();
	List<Store_picVO> list = store_picSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />

<html>
<head>
<title>所有店家資料 - listAllStore_pic.jsp</title>
</head>
<body bgcolor='white'>
	<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
	<table border='1' cellpadding='5' cellspacing='0' width='800'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>所有店家照片資料 - ListAllStore_pic.jsp</h3>
				<a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
			</td>
		</tr>
	</table>
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
	<table border='1' bordercolor='#CCCCFF' width='800'>
		<tr>
			<th>照片編號</th>
			<th>檔案名稱</th>
			<th>店家編號</th>
			<th>店家照片</th>
			<th>照片格式</th>
			<th>店家名稱</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%@ include file="page/page1.file"%>
		<c:forEach var="store_picVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr align='center' valign='middle' ${(store_picVO.pic_no==param.pic_no) ? 'bgcolor=#CCCCFF':''}>
				<td>${store_picVO.pic_no}</td>
				<td>${store_picVO.pic_name}</td>
				<td>${store_picVO.storeVO.store_no}</td>
				<td>
					<img width="120" height="100" src="<%=request.getContextPath()%>/store_pic/DBGifReader3.do?pic_no=${store_picVO.pic_no}" />
				</td>
				<td>${store_picVO.pic_format}</td>

				<td>
					<c:forEach var="storeVO" items="${storeSvc.all}">
						<c:if test="${store_picVO.storeVO.store_no==storeVO.store_no}">
	                    ${storeVO.store_no}【${storeVO.store_account} - ${storeVO.store_name}】
                    </c:if>
					</c:forEach>
				</td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store_pic/store_pic.do">
						<input type="submit" value="修改">
						<input type="hidden" name="pic_no" value="${store_picVO.pic_no}">
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<!--送出本網頁的路徑給Controller-->
						<input type="hidden" name="whichPage" value="<%=whichPage%>">
						<!--送出當前是第幾頁給Controller-->
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store_pic/store_pic.do">
						<input type="submit" value="刪除">
						<input type="hidden" name="pic_no" value="${store_picVO.pic_no}">
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<!--送出本網頁的路徑給Controller-->
						<input type="hidden" name="whichPage" value="<%=whichPage%>">
						<!--送出當前是第幾頁給Controller-->
						<input type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page/page2.file"%>
	<br>本網頁的路徑:<br><b> 
	<font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
	<font color=blue>request.getRequestURI():</font> <%=request.getRequestURI()%></b>
	
</body>
</html>