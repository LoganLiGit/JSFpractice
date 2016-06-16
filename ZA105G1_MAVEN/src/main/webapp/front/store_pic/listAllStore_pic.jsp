<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.pic.model.*"%>

<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	Store_picService store_picSvc = new Store_picService();
	List<Store_picVO> list = store_picSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />

<html>
<head>
<title>�Ҧ����a��� - listAllStore_pic.jsp</title>
</head>
<body bgcolor='white'>
	<b><font color=red>�����m�߱ĥ� EL ���g�k����:</font></b>
	<table border='1' cellpadding='5' cellspacing='0' width='800'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>�Ҧ����a�Ӥ���� - ListAllStore_pic.jsp</h3>
				<a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a>
			</td>
		</tr>
	</table>
	<%-- ���~���C --%>
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
			<th>�Ӥ��s��</th>
			<th>�ɮצW��</th>
			<th>���a�s��</th>
			<th>���a�Ӥ�</th>
			<th>�Ӥ��榡</th>
			<th>���a�W��</th>
			<th>�ק�</th>
			<th>�R��</th>
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
	                    ${storeVO.store_no}�i${storeVO.store_account} - ${storeVO.store_name}�j
                    </c:if>
					</c:forEach>
				</td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store_pic/store_pic.do">
						<input type="submit" value="�ק�">
						<input type="hidden" name="pic_no" value="${store_picVO.pic_no}">
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<!--�e�X�����������|��Controller-->
						<input type="hidden" name="whichPage" value="<%=whichPage%>">
						<!--�e�X���e�O�ĴX����Controller-->
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store_pic/store_pic.do">
						<input type="submit" value="�R��">
						<input type="hidden" name="pic_no" value="${store_picVO.pic_no}">
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<!--�e�X�����������|��Controller-->
						<input type="hidden" name="whichPage" value="<%=whichPage%>">
						<!--�e�X���e�O�ĴX����Controller-->
						<input type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page/page2.file"%>
	<br>�����������|:<br><b> 
	<font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
	<font color=blue>request.getRequestURI():</font> <%=request.getRequestURI()%></b>
	
</body>
</html>