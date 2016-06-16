<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Group: Home</title>
</head>
<body>
	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td><h3>IBM Group: Home</h3>
				<font color=red>( MVC )</font></td>
		</tr>
	</table>
	資料查詢
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
			<a href='<%=request.getContextPath()%>/group/listAllGroup.jsp'>List</a>
			all Groups.
		</li>
		<br>
		<li>
			<FORM METHOD="post" ACTION="group.do">
				<b>輸入揪團編號：</b>
				<input type="text" name="groupno">
				<input type="submit" value="送出">
				<input type="hidden" name="action" value="getOne_For_Display">
			</FORM>
		</li>
		<jsp:useBean id="groupSvc" scope="page" class="com.group.table.model.GroupTableService" />
		<li>
			<FORM METHOD="post" ACTION="group.do">
				<b>選擇揪團編號</b>
				<select size="1" name="group_no">
					<c:forEach var="groupVO" items="${groupSvc.all}">
						<option value="${groupVO.group_no}">${groupVO.group_no}
					</c:forEach>
				</select>
				<input type="submit" value="送出">
				<input type="hidden" name="action" value="getOne_For_Display">
			</FORM>
		</li>
		<li>
			<FORM METHOD="post" ACTION="group.do">
				<b>選擇店家編號</b>
				<select size="1" name="group_no">
					<c:forEach var="groupVO" items="${groupSvc.all}">
						<option value="${groupVO.group_no}">${groupVO.storeVO.store_no}
					</c:forEach>
				</select>
				<input type="submit" value="送出">
				<input type="hidden" name="action" value="getOne_For_Display">
			</FORM>
		</li>
		
		<li>
			<FORM METHOD="post" ACTION="group.do">
				<input type="hidden" name="mem_no" value="3">
				<input type="submit" value="送出">
				<input type="hidden" name="action" value="getGroup_For_Mem">
			</FORM>
		</li>
		
		
	</ul>
	<ul>
		<li>
			<a href='<%=request.getContextPath()%>/group/addGroup.jsp'>Add</a>
			a new Group.
		</li>
	</ul>
	<ul>
		<li>
			<a href='<%=request.getContextPath()%>/group/addGroupMem.jsp'>Add</a>
			a new GroupMem.
		</li>
	</ul>
	<%-- 萬用複合查詢-以下欄位-可隨意增減 --%>
	<ul>
		<li>
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group/group.do" name="form1">
				<b> <font color=blue>萬用複合查詢:</font>
				</b>
				<br>
				<b>輸入糾團編號:</b>
				<input type="text" name="group_no">
				<br>
				<b>輸入團長編號:</b>
				<input type="text" name="mem_no">
<!-- 				<br> -->
<!-- 				<b>選擇團長帳號:</b> -->
<!-- 				<input type="text" name="mem_account"> -->
<!-- 				<br> -->
				<b>選擇店家編號:</b>
				<input type="text" name="store_no">
				<b>選擇團編號:</b>
				<input type="text" name="group_name">
				<br>
					
				<input type="submit" value="送出">
				<input type="hidden" name="action" value="listGroups_ByCompositeQuery">
			</FORM>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group/group.do" name="form1">
				<b>選擇店家名稱:</b>
				<select size="1" name="store_name">
					<option value="">
						<c:forEach var="groupVO" items="${groupSvc.all}">
							<option value="${groupVO.storeVO.store_no}">${groupVO.storeVO.store_name}
						</c:forEach>
				</select>
				<br>
				<b>選擇店家城市:</b>
				<input type="text" name="store_city">
				<br>
				<b>選擇店家鄉鎮區:</b>
				<input type="text" name="store_district">
				
				<br>
				<b>選簡介搜尋:</b>
				<input type="text" name="group_intro">
				
				<br>
				<b>選擇店家類型:</b>
				<input type="text" name="store_type">
				<input type="submit" value="送出">
				<input type="hidden" name="action" value="listGroups_ByCompositeQuery_Store">
			</FORM>
		</li>
	</ul>
</body>
</html>