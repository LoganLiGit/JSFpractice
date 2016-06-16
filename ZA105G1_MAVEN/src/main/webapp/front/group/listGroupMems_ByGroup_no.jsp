<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>
<jsp:useBean id="groupSvc" scope="page" class="com.group.table.model.GroupTableService" />
<html>
<head>
<title>團員資料 - listGroupMems_ByGroup_no.jsp</title>
</head>
<body bgcolor='white'>
	<b><font color=red>�����m�߱ĥ� EL ���g�k����:</font></b>
	<table border='1' cellpadding='5' cellspacing='0' width='800'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>�Ҧ��έ���� - listGroupMems_ByGroup_no.jsp</h3> <a href="<%=request.getContextPath()%>/select_page.jsp">
					<img src="images/back1.gif" width="100" height="32" border="0">
					�^����
				</a>
			</td>
		</tr>
	</table>
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
	<table border='1' bordercolor='#CCCCFF' width='800'>
		<tr>
			<th>團長編號</th>
			<th>店家名稱</th>
			<th>團編號</th>
			<th>團員編號</th>
			<th>測試</th>
			
			<th>刪除</th>
		</tr>
		<c:forEach var="groupMemVO" items="${listGroupMems_ByGroup_no}">
			<tr align='center' valign='middle' ${(groupMemVO.group_no==param.group_no) ? 'bgcolor=#CCCCFF':''}>
				<td>${groupMemVO.groupTableVO.mem_no}</td>
				<td>${groupMemVO.groupTableVO.storeVO.store_name}</td>
				<td>${groupMemVO.group_no}</td>
				<td>${groupMemVO.mem_no}</td>
				<td><c:forEach var="groupTableVO" items="${groupSvc.all}">
						<c:if test="${groupMemVO.group_no==groupTableVO.group_no}">
	                    ${groupTableVO.group_no}�i<font color=orange>${groupTableVO.group_num}</font>  - ${groupTableVO.group_intro}�j
                    </c:if>
					</c:forEach></td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group/group.do">
						<input type="submit" value="刪除">
						<input type="hidden" name="group_no" value="${groupMemVO.group_no}">
						<input type="hidden" name="mem_no" value="${groupMemVO.mem_no}">
						<!--送出本網頁的路徑給Controller-->
						<input type="hidden" name="action" value="deleteOneMem">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br>

	<br>
	<font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
	<font color=blue>request.getRequestURI():</font> <%=request.getRequestURI()%></b>
</body>
</html>