<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--註冊JSTL	 --%>
<%@ page import="com.TicketType.model.*" %>
<%@ page import="com.store.model.*"%>
<!-- 驗證區域 -->
<%-- 如果強制用網頁強登 沒有店家編號 直接轉導到登入區域 --%>
<%
	if (session.getAttribute("storeno") == null ){
		RequestDispatcher reqd = request.getRequestDispatcher("/login/storelogin.jsp");
		reqd.forward(request,response);
	}
	else{
		Integer store_no = (Integer)session.getAttribute("session_store_no");
		pageContext.setAttribute("store_no",store_no);
	}
%>

<%
	TicketTypeVO tickettypevo = (TicketTypeVO) request.getAttribute("tickettypevO");
	pageContext.setAttribute("tickettypevo",tickettypevo);
%>
<%-- 取得getOne_For_Update放入的tickettypevO資料 放入tickettypevo --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改團購劵</title>

<!-- 需要的JQuery的匯入 -->
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<link href="<%=request.getContextPath()%>/Font/fbcm/css/styles.css" rel="stylesheet">

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
<!-- 以上為圖片預覽的JS -->
<script>
		var ad_image = function(event) {
			var output = document.getElementById('output');
			output.src = URL.createObjectURL(event.target.files[0]);
		};
		
		$(function() {
		    $( "#from" ).datepicker({
		      defaultDate: "+1w",
		      changeMonth: true,
		      numberOfMonths: 3,
		      onClose: function( selectedDate ) {
		        $( "#to" ).datepicker( "option", "minDate", selectedDate );
		      }
		    });
		    $( "#to" ).datepicker({
		      defaultDate: "+1w",
		      changeMonth: true,
		      numberOfMonths: 3,
		      onClose: function( selectedDate ) {
		        $( "#from" ).datepicker( "option", "maxDate", selectedDate );
		      }
		    });
		  });
</script>
<!-- 以上為上下時間的JQuery -->

</head>
<body>
	<!--============ header ============-->
<div id="header" style="width:100%; height: 225px;">
	<iFrame style="width:100%; height: 225px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/Header_store.jsp" scrolling="no"> </iFrame>
</div>
<!--============ header ============-->
<div class="forfont">
	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
			<h3>新增交易資料 - fbnt_fbcm_update</h3>
			</td>
			<td>
					<form method="POST" action="<%=request.getContextPath()%>/font/Fbcm.do">
						<input type="hidden" name="action" value="back">
						<button type="submit">
							店家頁面管理系統
						</button>					
					</form>
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
<FORM method="post" action="<%=request.getContextPath()%>/font/Fbcm.do" name="form1" enctype="multipart/form-data">	

	<table border="0">
		<%-- 店家編號: --%>
		<tr>
			<td>店家編號:</td>
			<td><%= tickettypevo.getStore_no() %></td>
			<td>
				<input type="hidden" name="store_no" value="<%= tickettypevo.getStore_no() %>" />
			</td>
		</tr>
		<%-- 店家名稱: --%>
		<tr>
			<td>店家名稱:</td>
			<td>
				<jsp:useBean id="StoreSvc" scope="page" class="com.store.model.StoreService" />				
				<c:forEach var="StoreVO" items="${StoreSvc.all}" > 					
					<c:if test="${ StoreVO.store_no == tickettypevo.store_no}">
						${StoreVO.store_name} 
						
					</c:if>
				</c:forEach>
				(
				${tickettypevo.store_no} 
				)
			</td>
			
		</tr>
		<%-- 團購券種類編號: --%>
		<tr>
			<td>團購券種類編號:</td>
			<%-- <td><input type="TEXT" name="tickets_type_no" size="45" value="<%= tickettypevo.getTickets_type_no() %>" /></td>--%>
			<td><%= tickettypevo.getTickets_type_no() %></td>
			<td>
				<input type="hidden" name="tickets_type_no" value="<%= tickettypevo.getTickets_type_no() %>" />
			</td>
		</tr>
		<%-- 團購劵名稱: --%>	
		<tr>
			<td>團購劵名稱:</td>
			<td>
				<input type="TEXT" name="tickets_type_name" size="45" value="<%= tickettypevo.getTickets_type_name() %>" />
			</td>
		</tr>
		
		<%-- 上架時間: --%>
		<tr>
			<td>上架時間:</td>
			<td bgcolor="#CCCCFF">
				<input type="text"  id="from" name="upper_date" value="<%= tickettypevo.getUpper_date()%>" />
			</td>
		</tr>
		<%-- 下架時間: --%>
		<tr>
			<td>下架時間:</td>
			<td bgcolor="#CCCCFF">
				<input type="text"  id="to" name="lower_date" value="<%= tickettypevo.getLower_date()%>" />
			</td>
		</tr>
		<%-- 團購劵總數量: --%>
		<tr>
			<td>團購劵總數量:</td>
			<td>
				<input type="TEXT" name="tickets_total" size="45" value="<%= tickettypevo.getTickets_total() %>" />
			</td>
		</tr>
		<tr>
			<td>團購劵單價格:</td>
			<td>
				<input type="TEXT" name="tickets_price" size="45" value="<%= tickettypevo.getTickets_price() %>" />
			</td>
		</tr>
		<%-- 團購劵說明: --%>
		<tr>
			<td>團購劵說明:</td>
			<td>
				<input type="TEXT" name="tickets_ex" size="45" value="<%= tickettypevo.getTickets_ex() %>" />
			</td>
		</tr>
		<%-- 團購劵圖片: --%>
		<tr>
			<td>團購劵圖片:</td>
			<td>
				<input type="file" name="tickets_image" onchange="previewImage(this,1)" />
			</td>
		</tr>
		<%-- 團購劵圖片預覽地方: --%>
		<tr>
			<td id="preview">
				<img id="imghead1">
			</td>
		</tr>		
	</table>
	<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
	<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">  <!--用於:istAllEmp.jsp 與 複合查詢 listEmps_ByCompositeQuery.jsp-->
	<input type="hidden" name="action" value="font_fbcm_update" >
	<input type="submit" value="送出新增">	
</FORM>



</div>


</body>
</html>