<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--註冊JSTL	 --%>
<%@ page import="com.TicketType.model.*" %>
<%@ page import="com.store.model.*"%>
<%-- 如果強制用網頁強登 沒有店家編號 直接轉導到登入區域 --%>
<%	
	try {
		Integer store_no = (Integer)session.getAttribute("session_store_no");
		pageContext.setAttribute("store_no",store_no);	
	}
	catch(Exception e){
		RequestDispatcher reqd = request.getRequestDispatcher("/login/storelogin.jsp");
		reqd.forward(request,response);
	}

%>
<%-- 取得控制器幫你用好的.並在此 --%>
<%-- 如果強制用網頁強登 沒有店家編號 直接轉導到登入區域 --%>
<%	
	StoreVO storevo = (StoreVO) session.getAttribute("storeVO");
	TicketTypeVO tickettypevo = (TicketTypeVO) session.getAttribute("ticketTypeVO");
	pageContext.setAttribute("storevo",storevo);	
	pageContext.setAttribute("tickettypevo",tickettypevo);	
%>
<%-- 取得控制器幫你用好的.並在此 --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>申請團購劵</title>
<!-- 需要的JQuery的匯入 -->
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/myorderform/myorderform_list01.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/myorderform/myorderform_list02.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/myorderform/input01.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/myorderform/input02.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/myorderform/input03.css">
</head>
<body>

<!--============ header ============-->
<div id="header" style="width:100%; height: 225px;">
	<iFrame style="width:100%; height: 225px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/Header_store.jsp" scrolling="no"> </iFrame>
</div>
<!--============ header ============-->
	<%-- 錯誤列表 --%>
	<c:if test="${not empty errorMsgs }">
	<%--if test--> 條件式 布林值	 --%>
		<forn color="red">請修正以下錯誤:
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li>${message}</li>
			</c:forEach>
		</ul>
		</forn>
	</c:if>
	
	
	<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
	
	<form method="POST" action="<%=request.getContextPath()%>/font/Fbcm.do" enctype="multipart/form-data">

<div class="container regBox" id="inputtr">
	<div class="company_inner div_center" style="width: 700px">
		<h2 class="align_c font_normal blue">團購券申請表單</h2>
			<p>請依序填寫下方內容，以完善您的申請資料！</p>
			<ul class="apLogin">
				<li>
					<%-- 店家名稱: --%>
					<label>店家名稱:</label>
					<div class="col"><p class="text_normal margin_l10">
						<%-- 第一次進來: --%>
						<c:if test="<%=(tickettypevo==null)%>" >					
							<%= (tickettypevo==null)? storevo.getStore_name() : "" %>
							<input type="hidden" name="store_no" value="<%= storevo.getStore_no() %>">
						</c:if>
						<%-- 第二次進來(有錯誤): --%>
						<c:if test="<%=(tickettypevo!=null)%>" >
							<jsp:useBean id="StoreSvc" scope="page" class="com.store.model.StoreService" />	
							<%  StoreSvc.getOneStore(tickettypevo.getStore_no()).getStore_name(); %>
						</c:if>
					</div>
				</li>
				<%-- 店家編號: --%>
				<li>
					<label>店家編號:</label>
					<div class="col"><p class="text_normal margin_l10">
						<%-- (tickettypevo==null)? pageContext.getAttribute("store_no") :tickettypevo.getStore_no() --%>
						<%--<input type="hidden" name="store_no" value="<%= (tickettypevo==null)? pageContext.getAttribute("store_no") :tickettypevo.getStore_no() %>">--%>
						
						<%= (tickettypevo==null)? storevo.getStore_no() :tickettypevo.getStore_no() %>
						<input type="hidden" name="store_no" value="<%= (tickettypevo==null)? storevo.getStore_no() :tickettypevo.getStore_no() %>">
					</div>
				</li>
				<%-- 團購劵名稱: --%>	
				<li>
					<label>團購劵名稱:</label>
					<div class="col">
						<input type="TEXT" name="tickets_type_name" size="45" value="<%= (tickettypevo==null)? "" :tickettypevo.getTickets_type_name() %>" />
					</div>
				</li>
				<%-- 上架時間: --%>		
				<li>
					<label>上架時間:</label>
					<div class="col">
				    	<input type="text"  id="from" name="upper_date" value="<%= (tickettypevo==null)? date_SQL :tickettypevo.getUpper_date()%>">
					</div>
                </li>
				<%-- 下架時間: --%>
				<li>
					<label>下架時間:</label>
					<div class="col">
						<input type="text"  id="to" name="lower_date" value="<%= (tickettypevo==null)? date_SQL :tickettypevo.getLower_date()%>">
					</div>
				</li>	
				<%-- 團購劵總數量: --%>
				<li>
					<label>團購劵總數量:</label>
					<div class="col">
						<input type="TEXT" name="tickets_total" size="45" value="<%= (tickettypevo==null)? "" :tickettypevo.getTickets_total() %>" />
					</div>
				</li>	
				<%-- 剩餘數量: 
				<tr>
					<td>剩餘數量:</td>
					<td><input type="TEXT" name="tickets_quantity" size="45" value="<%= (tickettypevo==null)? "" :tickettypevo.getTickets_quantity() %>" /></td>
				</tr>--%>
				<%-- 團購劵單價格: --%>
				<li>
					<label>團購劵單價格:</label>
					<div class="col">
						<input type="TEXT" name="tickets_price" size="45" value="<%= (tickettypevo==null)? "" :tickettypevo.getTickets_price() %>" />
					</div>
				</li>	
				<%-- 團購劵說明: --%>
				<li>
					<label>團購劵說明:</label>
					<div class="col">
						<input type="TEXT" name="tickets_ex" size="45" value="<%= (tickettypevo==null)? "" :tickettypevo.getTickets_ex() %>" />
					</div>
 				</li>	
				<%-- 團購劵圖片: --%>
				<li>
					<label>團購劵圖片:</label>
					<div class="col">
						<input type="file" name="tickets_image" onchange="previewImage(this,1)" /> 
					</div>
				</li>	
				<%-- 團購劵圖片預覽地方: --%>
				<li style="height:200px; width:200px;">
					<div class="col">
						<img id="imghead1" style="margin-left:auto; margin-right:auto;">
					</div>
				</li>
			</ul>
		<div class="next_btn">
			<input type="hidden" name="action" value="font_insert_tickettype" >
			<input class="btn blue_button btn_Submit" type="submit" value="送出新增">
		</div>	
	</div>
</div>
</form>	

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
</body>
</html>