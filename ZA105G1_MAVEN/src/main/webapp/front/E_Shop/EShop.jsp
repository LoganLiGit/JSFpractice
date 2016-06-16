<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--註冊JSTL	 --%>
<%@page import="java.util.*"%>
<%@page import="com.TicketType.model.*"%>
<%@page import="com.store.model.StoreVO"%>
<%--import	 --%>
<%@page import="com.TicketShopCar.model.Ticket"%>
<%--購物車專用import	 --%>
<%
	List<TicketTypeVO> shoplist = new TicketTypeService().getShopAll();
	pageContext.setAttribute("shoplist",shoplist);
%>
<%--	取出資料庫所有上架的商品	 --%>
<%--	所有連結的路徑		不包含include --%>
<%
	String url_MyOder = request.getContextPath() + "/front/E_My_Ticket/ETicket_Order_List.jsp"; //訂單紀錄
	String url_MyTr = request.getContextPath() + "/front/E_My_Order/Eorder_Gift_Record.jsp"; //儲值紀錄連結
	String url_MyTk = request.getContextPath() + "/front/E_My_Ticket/ETicket_Ticket_List.jsp"; //我的團購劵連結
	String url_MyAddTr = request.getContextPath() + "/front/E_My_Order/Eorder_Gift.jsp"; //儲值團購金連結
	String url_eshop = request.getContextPath() +"/front/E_Shop/EShop.jsp";  //團購劵商城連結
	String url_logout = request.getContextPath() +"/front/member/logout/logout.jsp"; //登出
	String url_login = request.getContextPath() + "/front/member/login/login.jsp"; //登入
	String url_Re = request.getContextPath() + "/front/member/register/register.jsp"; //註冊
%>
<%--	所有連結的路徑		 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/style.css">
<title>團購券商城</title>
<%-- 導入的css js物件 --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/jscss/ador/lib/jquery/1.9.1/jquery.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/slider.css">
<script src="<%=request.getContextPath()%>/jscss/back/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/jscss/back/js/jquery-migrate-1.1.1.js"></script>
<script src="<%=request.getContextPath()%>/jscss/back/js/superfish.js"></script>
<script src="<%=request.getContextPath()%>/jscss/back/js/jquery.easing.1.3.js"></script>
<script src="<%=request.getContextPath()%>/jscss/back/js/sForm.js"></script>
<script src="<%=request.getContextPath()%>/jscss/back/js/jquery.carouFredSel-6.1.0-packed.js"></script>
<script src="<%=request.getContextPath()%>/jscss/back/js/tms-0.4.1.js"></script>
<script src="<%=request.getContextPath()%>/jscss/back/js/shop.js"></script>
<%-- 導入的css js物件結束 --%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/shop/shop_show.css">
<script src="<%=request.getContextPath()%>/jscss/back/js/shop/shop_show.js"></script>
<%-- 購物車導入JS CSS --%>
</head>
<body>
	<%-- ==============================網頁開始================================= --%>
	<div class="main">
		<!--============ header ============-->
		<div id="header" style="width: 100%; height: 225px;">
			<iFrame style="width: 100%; height: 225px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/Header.jsp" scrolling="no"> </iFrame>
		</div>
		<!--============ header  ============-->
		<%-- ==============================nav開始=================================  --%>
		<nav class="content page1">
		<div class="container_12">
			<jsp:include page="EShop_Select.jsp" flush="true" />
		</div>
		</nav>
		<%-- ==============================nav結束=================================  --%>
		<jsp:useBean id="StoreSvc" class="com.store.model.StoreService" />
		<%-- ==============================article開始=================================  --%>
		<%-- 購物商品選單 --%>
		<article class="content page1">
		<div class="container_11">
			<jsp:include page="EShop_Commodity.jsp" flush="true" />
		</div>
		</article>
		<%-- ==============================article結束=================================  --%>
		<%-- ==============================header結束================================= --%>
		<div class="content page1">
			<div class="container_12">
				<div class="grid_7"></div>
			</div>
		</div>
		<%-- ==============================header結束================================= --%>
	</div>
<!--============ footer ============-->
<div id="footer" style="width: 100%; height: 455px;">
	<iFrame style="width:100%; height: 455px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/footer.jsp" scrolling="no"> </iFrame>
</div>
<!--============ footer  ============-->
	<%-- ==============================購物車插鍵================================= --%>
	<%
		Vector<Ticket> buylist = (Vector<Ticket>)session.getAttribute("shoppingcart");
		if (buylist != null && (buylist.size() > 0 ))	{
	%>
	<div id="abgne_float_ad">
		<div id="cart">
			<div class="header">購物車</div>
			<div class="middle">
				<div><jsp:include page="EShop_Cart_show.jsp" flush="true" /></div>
			</div>
		</div>
	</div>
	<%
		}
	%>
	<%--錯誤處理 --%>
	<%-- ==============================錯誤處理================================= --%>
	<c:if test="${not empty errorMsgs}">
		<script type="text/javascript" type="text/javascript">
			$(window).load(function() {
				$("#BgDiv").css({
					display : "block",
					height : $(document).height()
				});
				var yscroll = document.documentElement.scrollTop;
				$("#DialogDiv").css("top", "5%");
				$("#DialogDiv").css("display", "block");
				$("#DialogDiv").css("z-index", "10");
				document.documentElement.scrollTop = 0;

			});
		</script>
		<%--錯誤就顯示DIV遮罩 --%>
		<div id="DialogDiv" style="display: none">
			<h2>
				錯誤訊息:
				<a href="javascript:;" id="btnClose" onclick="closeDiv('DialogDiv')">關閉</a>
			</h2>
			<div class="form">
				<font color='red'>錯誤: <c:forEach var="message" items="${errorMsgs}">
						<li>${message}</li>
					</c:forEach>
				</font>
			</div>
		</div>
	</c:if>
	<%-- ==============================網頁結束================================= --%>
	
	<script type="text/javascript">
		$(function() {
			$("#login").click(function() {
				$("#login_from").submit();
			})
		});
	</script>
</body>
</html>