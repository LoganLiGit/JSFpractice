<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--註冊JSTL	 --%>
<%@page import="java.util.*"%>
<%
	String url_MyOder = request.getContextPath()
			+ "/front/E_My_Ticket/ETicket_Order_List.jsp"; //訂單紀錄
	String url_MyTr = request.getContextPath()
			+ "/front/E_My_Order/Eorder_Gift_Record.jsp"; //儲值紀錄連結
	String url_MyTk = request.getContextPath()
			+ "/front/E_My_Ticket/ETicket_Ticket_List.jsp"; //我的團購劵連結
	String url_MyAddTr = request.getContextPath()
			+ "/front/E_My_Order/Eorder_Gift.jsp"; //儲值團購金連結
	String url_eshop = request.getContextPath()
			+ "/front/E_Shop/EShop.jsp"; //團購劵商城連結
	String url_logout = request.getContextPath()
			+ "/front/member/logout/logout.jsp"; //登出
	String url_login = request.getContextPath()
			+ "/front/member/login/login.jsp"; //登入
	String url_Re = request.getContextPath()
			+ "/front/member/register/register.jsp"; //註冊
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>抬頭</title>
<script src="<%=request.getContextPath()%>/front/member/JuicyTalk/js2/jquery.js"></script>
<script src="<%=request.getContextPath()%>/front/member/JuicyTalk/js2/jquery-migrate-1.1.1.js"></script>
<script src="<%=request.getContextPath()%>/front/member/JuicyTalk/js2/superfish.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/front/member/JuicyTalk/css/js/jquery.min.js"></script>
<link href="<%=request.getContextPath()%>/front/member/JuicyTalk/css/scsj.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/member/JuicyTalk/css/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/member/JuicyTalk/css2/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/member/JuicyTalk/css2/superfish2.css">
<script type="text/javascript">
	$(function() {
		$('.seatext').focus(function() {
			var keyval = $('.seatext').val();
			if (keyval == '輸入店家或揪團名稱地址') {
				$('.seatext').val('');
			}
		})
		$('.seatext').focusout(function() {
			var keyval = $('.seatext').val();
			if (keyval == '') {
				$('.seatext').val('輸入店家或揪團名稱地址');
			}
		})
		$('.mkey').click(function() {
			$('.keyul').show();
			$('.keyul li').click(function() {
				var keyval = $(this).text();
				$('.mkey').text(keyval);
				$('.keyul').hide();
			})
		})
		$(document).mouseup(function() {
			$('.keyul').hide();
		});
		$('.seabtn').click(function() {
			var mval = $('.mkey').text();
			//alert(mval);
			var keyval=$('.seatext').val();
			if (mval == '店家-名稱') {
			
				form1.action = "<%=request.getContextPath()%>/store/store.do";
				$('#action').val("listStores_ByCompositeQuery");
				form1.submit();
					
			}else if(mval == '店家-類型'){
			
				form1.action = "<%=request.getContextPath()%>/store/store.do";
				$('#action').val("listStores_ByCompositeQuery");
				$('.seatext').attr("name","store_type");
				form1.submit();
			} else if(mval == '揪團-團名'){
				
				form1.action = "<%=request.getContextPath()%>/group/group.do";
				$('#action').val("listGroups_ByCompositeQuery");
				$('.seatext').attr("name","group_name");
				form1.submit();
			}
			else{
				
				form1.action = "<%=request.getContextPath()%>/group/group.do";
				$('#action').val("listGroups_ByCompositeQuery_Store");
				form1.submit();
			}

		})
		
		$('#font_fbcm_add_tickettype').click(function() {
	
			add_tickettype.action="<%=request.getContextPath()%>/font/Fbcm.do"
				add_tickettype.submit();
						})
						
		$('#font_fbcm_nook_tickettype').click(function() {
	
			nook_tickettype.action="<%=request.getContextPath()%>/font/Fbcm.do"
				nook_tickettype.submit();
						})
						
		$('#font_fbcm_all_tickettype').click(function() {

			fbcm_all_tickettype.action="<%=request.getContextPath()%>/font/Fbcm.do"
				fbcm_all_tickettype.submit();
						})

		$('.keyul li').hover(function() {
			$(this).addClass('on');
		}, function() {
			$(this).removeClass('on');
		})
	})
</script>
</head>
<body>
	<div class="bodyClass">
		<%-- ==============================網頁開始================================= --%>
		<div class="main">
			<%-- ==============================header================================= --%>
			<div class="header">
				<div class="container_12">
					<div class="grid_12">
						<%-- ====Logo======= --%>
						<a href="<%=request.getContextPath()%>/index_store.jsp" target="_parent">
							<img class="imglogo" src="<%=request.getContextPath()%>/front/member/JuicyTalk/images2/logo3.png" style="width: 80px;">
						</a>
						<%-- ==============================下拉搜尋================================ --%>
						<div class="box_163css">
							<div class="key"></div>
						</div>
						<%-- ==============================header================================= --%>
						<%-- ====================選單================================= --%>
						<div class="menu_block">
							<div class="navv">
								<ul class="sf-menu">
									<li>
										<a href="<%=request.getContextPath()%>/index_store.jsp" target="_parent">美食社群</a>
									</li>
									<li class="">
										<FORM method="post" name=add_tickettype target="_parent" >
											<input type="hidden" name="action" value="font_fbcm_add_tickettype">		
											<input type="hidden" name="store_no" value="${storeVO.store_no}">
										</FORM>
										<a href="javascript:" id="font_fbcm_add_tickettype" type="button" target="_parent">申請團購券</a>
								
									</li>
									<li class=''>
										<FORM method="post" name=nook_tickettype target="_parent" >
											<input type="hidden" name="time" value="5">
											<input type="hidden" name="action" value="font_fbcm_nook_tickettype">
											<input type="hidden" name="store_no" value="${storeVO.store_no}">	
										</FORM>		
										<a href="javascript:" id="font_fbcm_nook_tickettype" type="button" target="_parent">審核中團購券</a>
									</li>
									<li>
										<FORM method="post" name=fbcm_all_tickettype target="_parent" >
											
											<input type="hidden" name="action" value="font_fbcm_all_tickettype">
											<input type="hidden" name="store_no" value="${storeVO.store_no}">	
										</FORM>		
										<a href="javascript:" id="font_fbcm_all_tickettype" type="button" target="_parent">查看團購券狀態</a>
									</li>
								</ul>
							</div>
							<div class="clear"></div>
						</div>
						<%-- ==============================選單結束================================= --%>
						<div class="clear"></div>
					</div>
				</div>
				<%--********登入登出動態*********** --%>
				<div class="yuyan eshopcolor">
					<ul>
						<c:choose>
							<c:when test="${ memberVO.mem_name!=null }">
								<a href="<%=request.getContextPath()%>/front/member/update/update.jsp" target="_parent" style="color: black;">${memberVO.mem_name}你好</a> 
								&nbsp;
								<a href="<%=request.getContextPath()%>/front/member/update/update.jsp" target="_parent" style="color: black;">修改資料</a> 
								&nbsp;
								<a href="<%=request.getContextPath()%>/front/member/logout/logout.jsp" target="_parent" style="color: black;" target="_parent">登出</a>
							</c:when>
							<c:when test="${ storeVO.store_name!=null }">
								<a href="<%=request.getContextPath()%>/front/store/update/update.jsp" target="_parent" style="color: black;">店家 ${storeVO.manager_name}你好</a> 
								&nbsp;
								<a href="<%=request.getContextPath()%>/front/store/update/update.jsp" target="_parent" style="color: black;">修改資料</a> 
								&nbsp;
								<a href="<%=request.getContextPath()%>/front/store/logout/logout.jsp" target="_parent" style="color: black;">登出</a>
							</c:when>
							<c:otherwise>
								<a href="<%=request.getContextPath()%>/front/member/login/login.jsp" id="login" style="color: black;" target="_parent">登入會員</a>
								&nbsp;
								<a href="<%=request.getContextPath()%>/front/store/login/login.jsp" id="login" style="color: black;" target="_parent">店家登入</a>
								&nbsp;
						        <a href="<%=request.getContextPath()%>/front/member/register/register.jsp" style="color: black;" target="_parent">註冊會員</a>
							</c:otherwise>
						</c:choose>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/member/login/login.jsp" id="login_from">
							<input type="hidden" name="url" value="<%=request.getServletPath()%>">
						</FORM>
					</ul>
				</div>
				<%--********登入傳送表單*********** --%>
				<script type="text/javascript">
					$(function() {
						$("#login").click(function() {
							$("#login_from").submit();
						})
					});
				</script>
				<%--********登入登出動態*********** --%>
			</div>
			<%-- ==============================header結束================================= --%>
			<%-- ==============================網頁結束================================= --%>
		</div>
		<%-- ==============================main結束================================= --%>
	</div>
	<%-- ==============div bodyClass結束======================= --%>
</body>
</html>