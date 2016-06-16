<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--註冊JSTL	 --%>
<%@ page import="java.util.*"%>
<%@ page import="com.TicketNo.model.*"%>
<%@ page import="com.TicketType.model.*" %>
<%@ page import="com.store.model.*" %>
<%@page import="com.member.model.*"%>
<%
	String url_eticket_do = request.getContextPath() + "/front/E_My_Ticket/Eticket_Servlet.do";
	List orderlist = null;
	List list2 = null;
	if(request.getAttribute("orderlist") != null){
		String mem_account = (String)session.getAttribute("mem_account");
		Integer mem_no = new MemberService().getOneAccount(mem_account).getMem_no();
		orderlist = (List)request.getAttribute("orderlist");
	 	pageContext.setAttribute("orderlist",orderlist);
	 	session.setAttribute("orderlist", orderlist);
	 	
	 	list2 = (List)request.getAttribute("list2");
	 	pageContext.setAttribute("list2",list2);
	 	session.setAttribute("list2", list2);
	 	pageContext.setAttribute("mem_no", mem_no);
	}
	else{
		if (session.getAttribute("orderlist")!= null){
			String mem_account = (String)session.getAttribute("mem_account");
			Integer mem_no = new MemberService().getOneAccount(mem_account).getMem_no();
			orderlist = (List)session.getAttribute("orderlist");
			pageContext.setAttribute("orderlist",orderlist);
			
			list2 = (List)session.getAttribute("list2");
			pageContext.setAttribute("list2",list2);
			pageContext.setAttribute("mem_no", mem_no);
		}
		else{
			System.out.print("亂亂登入唷!!");
		}
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>訂單詳情</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/jscss/ador/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/jscss/ador/lib/layer/1.9.3/layer.js"></script> 
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/myorderform/myorderform_list01.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/myorderform/myorderform_list02.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/myorderform/input01.css">

<body>
<jsp:useBean id="TicketSvc" scope="page" class="com.TicketNo.model.TicketNoService" />
<jsp:useBean id="TicketTypeSvc" scope="page" class="com.TicketType.model.TicketTypeService" />
<jsp:useBean id="StoreSvc" scope="page" class="com.store.model.StoreService" />	
</head>
<body>
		<!--============ header ============-->
		<div id="header" style="width:100%; height: 225px;">
			<iFrame style="width: 100%; height: 225px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/Header.jsp" scrolling="no"> </iFrame>
		</div>
		<!--============ header  ============-->
<div name="article1"  class="mc-content" >
	<h1 class="rd-smll">詳情列表</h1>
	<hr class="header_hr" />
	<h1 class="rd-smll">您的訂單編號為: ${orderlist[0].order_no}</h1>
	<div class="mc-order-states">
		<div id="mc-table">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rd-table" id="area">
				<thead>
					<tr class="rd-C-Hide">
						<th>團購劵編號</th>
						<th>團購劵圖片</th>
						<th>團購劵名稱</th>
						<th>店家名稱</th>
						<th>購買單價</th>
						<th>購買狀態</th>
						<th>購買數量</th>
						<th></th>
					</tr>
				</thead>
				<c:forEach var="listt" items="${list2}">
				<tbody>
					<tr class="mc-tableContentITEM">
							<td>${ listt }</td>
								<%-- 圖片辨識是否有圖 --%>
								<c:if test="${ TicketTypeSvc.getOneTicketType(listt).getTickets_image() != null }">
									<td>
										<img width="120" height="100" src=" <%= request.getContextPath() %>/DBGifReader11.do?name=${ listt }"/>
									</td>			
								</c:if>
								<c:if test="${ TicketTypeSvc.getOneTicketType(listt).getTickets_image() == null }">
									<td>
										<img width="120" height="100" src=" <%= request.getContextPath() %>/IMG/NoPic.jpg"/>
									</td>			
								</c:if>	
							<td>${TicketTypeSvc.getOneTicketType(listt).getTickets_type_name() }</td>
							<td>
								<c:forEach var="StoreVO" items="${StoreSvc.all}" > 					
									<c:if test="${ StoreVO.store_no == TicketTypeSvc.getOneTicketType(listt).getStore_no() }">${StoreVO.store_name}</c:if>
								</c:forEach>							
							</td>
							<%  int count= 0;%>
							<c:forEach var="ordervo" items="${orderlist}">
								<c:if test="${ listt == TicketSvc.getOneCashTr(ordervo.tickets_no).tickets_type_no }">
									<% count++; %>
									<c:if test="<%= count==1 %>">
										<td>${ ordervo.order_value }</td>
										<td>
											<c:if test="${ ordervo.order_status == 0}">
												訂單成立
											</c:if>
											<c:if test="${ ordervo.order_status == 1}">
												訂單不成立
											</c:if>
										</td>
									</c:if>
								</c:if>		

							</c:forEach>	
							<td><%= count %></td>	
							<td>
								<FORM METHOD="post" ACTION="<%=url_eticket_do%>" >
									<button>查看團購劵序號</button>
									<input type="hidden" name="action" value="myorderticket">	
									<input type="hidden" name="mem_no" value="${mem_no}">
									<input type="hidden" name="tickets_typeno" value="${listt}">
								</FORM>	
							</td>
							
					</tr>
				</tbody>
				</c:forEach>
			</table>
		</div>
	</div>
</div>

</body>
</html>