<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>

<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	StoreService storeSvc = new StoreService();
	List<StoreVO> list = storeSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="store_picSvc" scope="page" class="com.store.pic.model.Store_picService" />
<html>
<head>
<title>�Ҧ����a��� - listAllStore.jsp</title>
</head>
<body bgcolor='white'>
	<b><font color=red>�����m�߱ĥ� EL ���g�k����:</font></b>
	<table border='1' cellpadding='5' cellspacing='0' width='800'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>�Ҧ����a��� - ListAllStore.jsp</h3>
				<a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a>
			</td>
		</tr>
	</table>
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
	<table border='1' bordercolor='#CCCCFF' width='1500'>
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
			<th>�ק�</th>
			<th>�R��</th>
			<th>�s�W</th>
		</tr>
		<%@ include file="page/page1.file"%>
		<c:forEach var="storeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr align='center' valign='middle' ${(storeVO.store_no==param.store_no) ? 'bgcolor=#CCCCFF':''}>
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
						<input type="hidden" name="action" value="listStore_pics_ByStore_no_B">
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<!--�e�X�����������|��Controller-->

						<input type="hidden" name="whichPage" value="<%=whichPage%>">
						<!--�e�X��e�O�ĴX����Controller-->
					</FORM>
				</td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do">
						<input type="submit" value="�ק�" />
						<input type="hidden" name="store_no" value="${storeVO.store_no}">

						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<!--�e�X�����������|��Controller-->

						<input type="hidden" name="whichPage" value="<%=whichPage%>">
						<!--�e�X��e�O�ĴX����Controller-->

						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do">
						<input type="submit" value="�R��">
						<input type="hidden" name="store_no" value="${storeVO.store_no}">

						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<!--�e�X�����������|��Controller-->

						<input type="hidden" name="whichPage" value="<%=whichPage%>">
						<!--�e�X��e�O�ĴX����Controller-->

						<input type="hidden" name="action" value="delete">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store_pic/store_pic.do">
						<input type="submit" value="�s�W�Ӥ�">
						<input type="hidden" name="store_no" value="${storeVO.store_no}">
						<input type="hidden" name="action" value="getOne_For_Insert">
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<!--�e�X�����������|��Controller-->

						<input type="hidden" name="whichPage" value="<%=whichPage%>">
						<!--�e�X��e�O�ĴX����Controller-->
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page/page2.file"%>

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