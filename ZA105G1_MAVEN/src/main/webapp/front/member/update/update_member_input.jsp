<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");//MemberServlet.java (Concroller), �s�Jreq��memberVO���� (�]�A�������X��memberVO, �]�]�A��J��ƿ��~�ɪ�memberVO����)
%>
<html>
<head>
<title>���u��ƭק� - update_emp_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>���u��ƭק� - update_emp_input.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></td>
	</tr>
</table>

<h3>��ƭק�:</h3>
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

<FORM METHOD="post" ACTION="member.do" name="form1" enctype="multipart/form-data">
<table border="0">
	
	<tr>
		<td>�|���b��:</td>
		<td><input type="TEXT" name="mem_account" size="45" 
			value="<%= (memberVO==null)? "h22343525" : memberVO.getMem_account()%>" /></td>
	</tr>
	<tr>
		<td>�K�X:</td>
		<td><input type="TEXT" name="mem_password" size="45"
			value="<%= (memberVO==null)? "p1234556" : memberVO.getMem_password()%>" /></td>
	</tr>
	<tr>
		<%java.sql.Date date_SQL2 = new java.sql.Date(System.currentTimeMillis());%>
		<td>���U���:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="mem_regist_date" value="<%= (memberVO==null)? date_SQL2 : memberVO.getMem_regist_date()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','mem_regist_date','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="�}�l���"></a>
		</td>
	</tr>
	<tr>
		<td>�m�W:</td>
		<td><input type="TEXT" name="mem_name" size="45"
			value="<%= (memberVO==null)? "�j�ѤG" : memberVO.getMem_name()%>" /></td>
	</tr>
		<tr>
		<td>�ʺ�:</td>
		<td><input type="TEXT" name="mem_nickname" size="45"
			value="<%= (memberVO==null)? "���ѤG" : memberVO.getMem_nickname()%>" /></td>
	</tr>
	<tr>
		<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
		<td>�ͤ�:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="mem_birthday" value="<%= (memberVO==null)? date_SQL : memberVO.getMem_birthday()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','mem_birthday','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="�}�l���"></a>
		</td>
	</tr>
	<tr>
		<td>�ڪ��Ӥ�</td>
		<td><input type="file" name="mem_photo"></td>
	</tr>
	
	<tr>
		<td>�����Ҹ��X:</td>
		<td><input type="TEXT" name="mem_idcard" size="60"
			value="<%=(memberVO.getMem_idcard()==null)? "A123456789" : memberVO.getMem_idcard()%>"/></td>
	</tr>
	<tr>
		<td>�ʧO:</td>
		<td><input type="TEXT" name="mem_sex" size="10"
			value="<%= (memberVO.getMem_sex()==null)? "100" : memberVO.getMem_sex()%>" /></td>
	</tr>
	<tr>
		<td>�l���ϸ�:</td>
		<td><input type="TEXT" name="mem_zipcode" size="60"
			value="<%= (memberVO.getMem_zipcode()==null)? "100" : memberVO.getMem_zipcode()%>" /></td>
	</tr>
	<tr>
		<td>����:</td>
		<td><input type="TEXT" name="mem_city" size="60"
			value="<%= (memberVO.getMem_city()==null)? "�x�_��" : memberVO.getMem_city()%>" /></td>
	</tr>
	<tr>
		<td>�ϰ�:</td>
		<td><input type="TEXT" name="mem_district" size="60"
			value="<%= (memberVO.getMem_district()==null)? "�H�q��" : memberVO.getMem_district()%>" /></td>
	</tr>
	<tr>
		<td>�a�}:</td>
		<td><input type="TEXT" name="mem_address" size="60"
			value="<%= (memberVO.getMem_address()==null)? "XX��100��" : memberVO.getMem_address()%>" /></td>
	</tr>
	<tr>
		<td>�a�̹q��:</td>
		<td><input type="TEXT" name="mem_phone" size="60"
			value="<%= (memberVO.getMem_phone()==null)? "0123456789" : memberVO.getMem_phone()%>" /></td>
	</tr>
	<tr>
		<td>���:</td>
		<td><input type="TEXT" name="mem_cellphone" size="60"
			value="<%= (memberVO.getMem_cellphone()==null)? "0987654321" : memberVO.getMem_cellphone()%>" /></td>
	</tr>
	<tr>
		<td>�a�}�l��:</td>
		<td><input type="TEXT" name="mem_email" size="60"
			value="<%= (memberVO.getMem_email()==null)? "dick@gmail.com" : memberVO.getMem_email()%>" /></td>
	</tr>
	<tr>
		<td>�M��:</td>
		<td><input type="TEXT" name="mem_skill" size="60"
			value="<%= (memberVO.getMem_skill()==null)? "�Y��" : memberVO.getMem_skill()%>" /></td>
	</tr>
	<tr>
		<td>���ߦn:</td>
		<td><input type="TEXT" name="mem_hobby" size="60"
			value="<%= (memberVO.getMem_hobby()==null)? "���׶�" : memberVO.getMem_hobby()%>" /></td>
	</tr>
	<tr>
		<td>�P�����A:</td>
		<td><input type="TEXT" name="mem_relationship" size="60"
			value="<%= (memberVO.getMem_relationship()==null)? "���K>///<" : memberVO.getMem_relationship()%>" /></td>
	</tr>
	<tr>
		<td>�b���v��:</td>
		<td><input type="TEXT" name="mem_right" size="60"
			value="<%= (memberVO.getMem_right()==null)? "�W�ź޲z��" : memberVO.getMem_right()%>" /></td>
	</tr>
	<tr>
		<td>�����:</td>
		<td><input type="TEXT" name="mem_intro" size="60"
			value="<%= (memberVO.getMem_intro()==null)? "�ڦn��" : memberVO.getMem_intro()%>" /></td>
	</tr>
	<tr>
		<td>�|������:</td>
		<td><input type="TEXT" name="mem_level" size="60"
			value="<%= (memberVO.getMem_level()==null)? "VIP" : memberVO.getMem_level()%>" /></td>
	</tr>
	<tr>
		<td>�|�����A:</td>
		<td><input type="TEXT" name="mem_status" size="60"
			value="<%= (memberVO.getMem_status()==null)? "�δ�der" : memberVO.getMem_status()%>" /></td>
	</tr>
	<tr>
		<td>GCM���U�X:</td>
		<td><input type="TEXT" name="mem_redid" size="60"
			value="<%= (memberVO.getMem_redid()==null)? "1111" : memberVO.getMem_redid()%>" /></td>
	</tr>
	<tr>
		<td>�x�Ȫ�:</td>
		<td><input type="TEXT" name="mem_balance" size="60"
			value="<%= (memberVO.getMem_balance()==null)? "300" : memberVO.getMem_balance()%>" /></td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="mem_no" value="<%=memberVO.getMem_no()%>">
<input type="submit" value="�e�X�ק�"></FORM>

</body>
</html>
