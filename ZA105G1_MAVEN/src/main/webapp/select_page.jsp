<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>IBM Store: Home</title>
</head>
<body bgcolor='white'>
	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>IBM Store: Home</h3>
				<font color=red>( MVC )</font>
			</td>
		</tr>
	</table>
	<p>This is the Home page for IBM Store: Home</p>
	<h3>資料查詢:</h3>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>
			請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>

	<ul>
		<li>
			<a href='<%=request.getContextPath()%>/store/listAllStore.jsp'>List</a>
			all Store.
		</li>
		<br>
		<li>
			<a href='<%=request.getContextPath()%>/store_pic/listAllStore_pic.jsp'>List</a>
			all Store_pic.
		</li>
		<br>
		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do">
				<b>輸入店家編號 (如1,2,3):</b>
				<input type="text" name="store_no">
				<input type="submit" value="送出">
				<input type="hidden" name="action" value="getOne_For_Display">
			</FORM>
		</li>

		<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />

		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do">
				<b>選擇店家編號:</b>
				<select size="1" name="store_no">
					<c:forEach var="storeVO" items="${storeSvc.all}">
						<option value="${storeVO.store_no}">${storeVO.store_no}
					</c:forEach>
				</select>
				<input type="submit" value="送出">
				<input type="hidden" name="action" value="store_detail">
			</FORM>
		</li>
		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do">
				<b>選擇店家帳號:</b>
				<select size="1" name="store_no">
					<c:forEach var="storeVO" items="${storeSvc.all}">
						<option value="${storeVO.store_no}">${storeVO.store_account}
					</c:forEach>
				</select>
				<input type="submit" value="送出">
				<input type="hidden" name="action" value="getOne_For_Display">
			</FORM>
		</li>
		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do">
				<b>選擇店家名稱(包含照片):</b>
				<select size="1" name="store_no">
					<c:forEach var="storeVO" items="${storeSvc.all}">
						<option value="${storeVO.store_no}">${storeVO.store_name}
					</c:forEach>
				</select>
				<input type="submit" value="送出">
				<input type="hidden" name="action" value="getOne_For_Display">
			</FORM>
		</li>

		<jsp:useBean id="store_picSvc" scope="page" class="com.store.pic.model.Store_picService" />
		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store_pic/store_pic.do">
				<b>
					<font color=orange>選擇照片:</font>
				</b>
				<select size="1" name="pic_no">
					<c:forEach var="store_picVO" items="${store_picSvc.all}">
						<option value="${store_picVO.pic_no}">${store_picVO.pic_no}--${store_picVO.pic_name}
					</c:forEach>
				</select>
				<input type="submit" value="送出">
				<input type="hidden" name="action" value="getOne_For_Display">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store_pic/store_pic.do">
				<b>
					<font color=orange>選擇照片編號:</font>
				</b>
				<select size="1" name="pic_no">
					<c:forEach var="store_picVO" items="${store_picSvc.all}">
						<option value="${store_picVO.pic_no}">${store_picVO.pic_no}
					</c:forEach>
				</select>
				<input type="submit" value="送出">
				<input type="hidden" name="action" value="getOne_For_Display">
			</FORM>
		</li>

	</ul>
	<%-- 萬用複合查詢-以下欄位-可隨意增減 --%>
	<ul>
		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do" name="form1">
				<b>
					<font color=blue>萬用複合查詢:</font>
				</b>
				<br>
				<b>輸入店家名稱:</b>
				<input type="text" name="store_name">
				<br>
				<b>輸入店家電話:</b>
				<input type="text" name="store_phone">
				<br>
				<b>選擇店家城市:</b>
				<input type="text" name="store_city">
				<br>
				<b>選擇店家鄉鎮區:</b>
				<input type="text" name="store_district">
				<br>
				<b>選擇店家類型:</b>
				<input type="text" name="store_type">
				
				<input type="submit" value="送出">
				<input type="hidden" name="action" value="listStores_ByCompositeQuery">
			</FORM>
		</li>
	</ul>

	<h3>店家管理</h3>
	<ul>
		<li>
			<a href='<%=request.getContextPath()%>/store/addStore.jsp'>Add</a>
			a new Store.
		</li>
	</ul>

</body>
</html>