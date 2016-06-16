<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");//MemberServlet.java (Concroller), 存入req的memberVO物件 (包括幫忙取出的memberVO, 也包括輸入資料錯誤時的memberVO物件)
%>
<html>
<head>
<title>員工資料修改 - update_emp_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>員工資料修改 - update_emp_input.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></td>
	</tr>
</table>

<h3>資料修改:</h3>
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

<FORM METHOD="post" ACTION="member.do" name="form1" enctype="multipart/form-data">
<table border="0">
	
	<tr>
		<td>會員帳號:</td>
		<td><input type="TEXT" name="mem_account" size="45" 
			value="<%= (memberVO==null)? "h22343525" : memberVO.getMem_account()%>" /></td>
	</tr>
	<tr>
		<td>密碼:</td>
		<td><input type="TEXT" name="mem_password" size="45"
			value="<%= (memberVO==null)? "p1234556" : memberVO.getMem_password()%>" /></td>
	</tr>
	<tr>
		<%java.sql.Date date_SQL2 = new java.sql.Date(System.currentTimeMillis());%>
		<td>註冊日期:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="mem_regist_date" value="<%= (memberVO==null)? date_SQL2 : memberVO.getMem_regist_date()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','mem_regist_date','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="開始日期"></a>
		</td>
	</tr>
	<tr>
		<td>姓名:</td>
		<td><input type="TEXT" name="mem_name" size="45"
			value="<%= (memberVO==null)? "大老二" : memberVO.getMem_name()%>" /></td>
	</tr>
		<tr>
		<td>暱稱:</td>
		<td><input type="TEXT" name="mem_nickname" size="45"
			value="<%= (memberVO==null)? "梅老二" : memberVO.getMem_nickname()%>" /></td>
	</tr>
	<tr>
		<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
		<td>生日:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="mem_birthday" value="<%= (memberVO==null)? date_SQL : memberVO.getMem_birthday()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','mem_birthday','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="開始日期"></a>
		</td>
	</tr>
	<tr>
		<td>我的照片</td>
		<td><input type="file" name="mem_photo"></td>
	</tr>
	
	<tr>
		<td>身分證號碼:</td>
		<td><input type="TEXT" name="mem_idcard" size="60"
			value="<%=(memberVO.getMem_idcard()==null)? "A123456789" : memberVO.getMem_idcard()%>"/></td>
	</tr>
	<tr>
		<td>性別:</td>
		<td><input type="TEXT" name="mem_sex" size="10"
			value="<%= (memberVO.getMem_sex()==null)? "100" : memberVO.getMem_sex()%>" /></td>
	</tr>
	<tr>
		<td>郵遞區號:</td>
		<td><input type="TEXT" name="mem_zipcode" size="60"
			value="<%= (memberVO.getMem_zipcode()==null)? "100" : memberVO.getMem_zipcode()%>" /></td>
	</tr>
	<tr>
		<td>城市:</td>
		<td><input type="TEXT" name="mem_city" size="60"
			value="<%= (memberVO.getMem_city()==null)? "台北市" : memberVO.getMem_city()%>" /></td>
	</tr>
	<tr>
		<td>區域:</td>
		<td><input type="TEXT" name="mem_district" size="60"
			value="<%= (memberVO.getMem_district()==null)? "信義區" : memberVO.getMem_district()%>" /></td>
	</tr>
	<tr>
		<td>地址:</td>
		<td><input type="TEXT" name="mem_address" size="60"
			value="<%= (memberVO.getMem_address()==null)? "XX路100號" : memberVO.getMem_address()%>" /></td>
	</tr>
	<tr>
		<td>家裡電話:</td>
		<td><input type="TEXT" name="mem_phone" size="60"
			value="<%= (memberVO.getMem_phone()==null)? "0123456789" : memberVO.getMem_phone()%>" /></td>
	</tr>
	<tr>
		<td>手機:</td>
		<td><input type="TEXT" name="mem_cellphone" size="60"
			value="<%= (memberVO.getMem_cellphone()==null)? "0987654321" : memberVO.getMem_cellphone()%>" /></td>
	</tr>
	<tr>
		<td>地址郵件:</td>
		<td><input type="TEXT" name="mem_email" size="60"
			value="<%= (memberVO.getMem_email()==null)? "dick@gmail.com" : memberVO.getMem_email()%>" /></td>
	</tr>
	<tr>
		<td>專長:</td>
		<td><input type="TEXT" name="mem_skill" size="60"
			value="<%= (memberVO.getMem_skill()==null)? "吃飯" : memberVO.getMem_skill()%>" /></td>
	</tr>
	<tr>
		<td>菜色喜好:</td>
		<td><input type="TEXT" name="mem_hobby" size="60"
			value="<%= (memberVO.getMem_hobby()==null)? "滷肉飯" : memberVO.getMem_hobby()%>" /></td>
	</tr>
	<tr>
		<td>感情狀態:</td>
		<td><input type="TEXT" name="mem_relationship" size="60"
			value="<%= (memberVO.getMem_relationship()==null)? "秘密>///<" : memberVO.getMem_relationship()%>" /></td>
	</tr>
	<tr>
		<td>帳號權限:</td>
		<td><input type="TEXT" name="mem_right" size="60"
			value="<%= (memberVO.getMem_right()==null)? "超級管理員" : memberVO.getMem_right()%>" /></td>
	</tr>
	<tr>
		<td>關於我:</td>
		<td><input type="TEXT" name="mem_intro" size="60"
			value="<%= (memberVO.getMem_intro()==null)? "我好帥" : memberVO.getMem_intro()%>" /></td>
	</tr>
	<tr>
		<td>會員等級:</td>
		<td><input type="TEXT" name="mem_level" size="60"
			value="<%= (memberVO.getMem_level()==null)? "VIP" : memberVO.getMem_level()%>" /></td>
	</tr>
	<tr>
		<td>會員狀態:</td>
		<td><input type="TEXT" name="mem_status" size="60"
			value="<%= (memberVO.getMem_status()==null)? "棒棒der" : memberVO.getMem_status()%>" /></td>
	</tr>
	<tr>
		<td>GCM註冊碼:</td>
		<td><input type="TEXT" name="mem_redid" size="60"
			value="<%= (memberVO.getMem_redid()==null)? "1111" : memberVO.getMem_redid()%>" /></td>
	</tr>
	<tr>
		<td>儲值金:</td>
		<td><input type="TEXT" name="mem_balance" size="60"
			value="<%= (memberVO.getMem_balance()==null)? "300" : memberVO.getMem_balance()%>" /></td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="mem_no" value="<%=memberVO.getMem_no()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>
