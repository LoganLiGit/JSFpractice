<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--註冊JSTL	 --%>
<%@ page import="com.TicketType.model.*" %>
<%
	TicketTypeVO tickettypevo = (TicketTypeVO) request.getAttribute("tickettypevO");
%>
<%-- 取得getOne_For_Update放入的tickettypevO資料 放入tickettypevo --%>
<html>
<head>
<script type="text/javascript">
function previewImage(file,i)
{
      var img = document.getElementById('imghead'+i);
      var reader = new FileReader();
      reader.onload = function(e){
			img.src = e.target.result;
			img.width = 260;
			img.height = 190;

	  }
      reader.readAsDataURL(file.files[0]);
      // readAsText() 方法，並且以所要讀取的 file 物件為參數傳入。
}
</script>

<title>IBM Emp: Home</title>

</head>

<body bgcolor='white'>

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

<FORM method="post" action="<%=request.getContextPath()%>/ticket_type/tickettype.do" name="form1" enctype="multipart/form-data">


<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>update_ticket_type_input.jsp</h3>
		</td>
		<td>
		   <a href="select_TicketType_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>
<table border="0">
	<%-- 團購券種類編號: --%>
	<tr>
		<td>團購券種類編號:</td>
		<%-- <td><input type="TEXT" name="tickets_type_no" size="45" value="<%= tickettypevo.getTickets_type_no() %>" /></td>--%>
		<td><%= tickettypevo.getTickets_type_no() %></td>
	</tr>
	<%-- 上架時間: --%>
	<tr>
		<td>上架時間:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="upper_date" value="<%= tickettypevo.getUpper_date()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','trandaction_date','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="上架時間"></a>
		</td>
	</tr>
	<%-- 下架時間: --%>
	<tr>
		<td>下架時間:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="lower_date" value="<%= tickettypevo.getLower_date()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','trandaction_date','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="下架時間"></a>
		</td>
	</tr>
	<%-- 團購劵總數量: --%>
	<tr>
		<td>團購劵總數量:</td>
		<td><input type="TEXT" name="tickets_total" size="45" value="<%= tickettypevo.getTickets_total() %>" /></td>
	</tr>
	<%-- 剩餘數量: --%>
	<tr>
		<td>剩餘數量:</td>
		<td><input type="TEXT" name="tickets_quantity" size="45" value="<%= tickettypevo.getTickets_quantity() %>" /></td>
	</tr>
	<%-- 團購劵單價格: --%>
	<tr>
		<td>團購劵單價格:</td>
		<td><input type="TEXT" name="tickets_price" size="45" value="<%= tickettypevo.getTickets_price() %>" /></td>
	</tr>
	<%-- 團購劵狀態: --%>
	<tr>
		<td>團購劵狀態:</td>
		<td><input type="TEXT" name="tickets_state" size="45" value="<%= tickettypevo.getTickets_state()  %>" /></td>
	</tr>
	<%-- 店家編號: --%>
	<tr>
		<td>店家編號:</td>
		<td><input type="TEXT" name="store_no" size="45" value="<%= tickettypevo.getStore_no() %>" /></td>
	</tr>
	<%-- 團購劵說明: --%>
	<tr>
		<td>團購劵說明:</td>
		<td><input type="TEXT" name="tickets_ex" size="45" value="<%= tickettypevo.getTickets_ex() %>" /></td>
	</tr>
	<%-- 團購劵圖片: --%>
	<tr>
		<td>團購劵圖片:</td>
		<td><input type="file" name="tickets_image" onchange="previewImage(this,1)" /> </td>
	</tr>
	<tr>
		<td id="preview">
			<img id="imghead1">
		</td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update" >
<input type="hidden" name="tickets_type_no" value="<%= tickettypevo.getTickets_type_no() %>">
<input type="submit" value="送出修改">
</FORM>

</body>
</html>
