<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--註冊JSTL	 --%>
<%@page import="java.util.*" %>
<%@page import="com.member.model.*"%>
<%@page import="com.TicketOr.model.*"%>
<%@page import="com.TicketNo.model.*"%>
<%@page import="com.OrderDe.model.OrderDeService"%>
<%
	String url_eticket_do = request.getContextPath() + "/front/E_My_Ticket/Eticket_Servlet.do";;
	if (request.getParameter("MyOrderTicketlist")==null){
		Integer mem_no =0;
		List<TicketOrVO> list = null;
		String mem_account = (String)session.getAttribute("mem_account");
		if (mem_account == null ){
			
		}else{
			mem_no = new MemberService().getOneAccount(mem_account).getMem_no();
			list = new TicketOrService().getUserdate(mem_no);
			pageContext.setAttribute("list", list);
					
			HashSet set = new HashSet();				
			TicketOrService ticketorservic = new TicketOrService();
			OrderDeService orderdeservicee = new OrderDeService();
			TicketNoService ticketnoservice = new TicketNoService();

			for (int i=0;i<list.size();i++ ){
				Integer order_no = list.get(i).getOrder_no();
				List list2 = orderdeservicee.get_Order(order_no);
				for (int j=0;j<list2.size();j++){
					String tickets_no = orderdeservicee.get_Order(order_no).get(j).getTickets_no();
					TicketNoVO ticketvo = ticketnoservice.getOneCashTr(tickets_no);
					set.add(ticketvo.getTickets_type_no());
				}
			}
			List listtickets_typeno = new ArrayList();
			Iterator objs = set.iterator();
			while(objs.hasNext()){
				listtickets_typeno.add(objs.next());
			}
			pageContext.setAttribute("listtickets_typeno", listtickets_typeno);
			pageContext.setAttribute("mem_no",mem_no);
		}
	}
%>
<jsp:useBean id="OrderDeSvc" scope="page" class="com.OrderDe.model.OrderDeService" />	
<jsp:useBean id="TicketNoSvc" scope="page" class="com.TicketNo.model.TicketNoService" />	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的團購劵</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/jscss/ador/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/jscss/ador/lib/layer/1.9.3/layer.js"></script> 
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/myorderform/myorderform_list01.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/myorderform/myorderform_list02.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/myorderform/input01.css">

</head>
<body>
		<!--============ header ============-->
		<div id="header" style="width:100%; height: 225px;">
			<iFrame style="width: 100%; height: 225px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/Header.jsp" scrolling="no"> </iFrame>
		</div>
		<!--============ header  ============-->
<div name="article1"  class="mc-content" >
	<h1 class="rd-smll">團購券序號列表</h1>
	<hr class="header_hr" />
	<div class="mc-order-states">
		<div id="mc-table" >
			<FORM METHOD="post" ACTION="<%=url_eticket_do%>" >
			只顯示
			<select name="tickets_typeno" class="select" id="select_tickets_typeno">
				<c:forEach var="tickets_typenoSvc" items="${listtickets_typeno}">				
					<option value="${tickets_typenoSvc}">${tickets_typenoSvc}</option>
				</c:forEach>
			</select>
			<select name="state" class="select" id="select_tickets_typeno">
					<option value="1">尚未使用</option>
					<option value="2">已使用</option>
					<option value="3">失效</option>
			</select>
			<input type="hidden" name="action" value="myorderticket">	
			<input type="hidden" name="mem_no" value="${mem_no}">	
			
			<input type="submit" />
			</FORM>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rd-table" id="area">
            	<thead>
                	<tr class="rd-C-Hide">
						<th>團購券種類編號</th>
						<th>團購券序號</th>
						<th>使用狀態</th>
                        <th></th>
                	</tr>
				</thead>
				<c:forEach var="ticketorvo" items="${list}">
					<c:forEach var="orderdevo" items="${OrderDeSvc.get_Order(ticketorvo.order_no)}">
					
					
						<c:if test="${TicketNoSvc.getOneCashTr(orderdevo.tickets_no).tickets_no_status == 1}"><%--只顯示尚未使用的 --%>
							<tbody>
								<tr id="tr_tt" class="mc-tableContentITEM" tickets_type_no="${TicketNoSvc.getOneCashTr(orderdevo.tickets_no).tickets_type_no}" tickets_no="${TicketNoSvc.getOneCashTr(orderdevo.tickets_no).tickets_no}">
									<td><a>${TicketNoSvc.getOneCashTr(orderdevo.tickets_no).tickets_type_no}</a></td>
									<td><a>${TicketNoSvc.getOneCashTr(orderdevo.tickets_no).tickets_no}</a></td>
									<td>						
										<c:if test="${TicketNoSvc.getOneCashTr(orderdevo.tickets_no).tickets_no_status == 0}"><a>未賣出</a></c:if>
										<c:if test="${TicketNoSvc.getOneCashTr(orderdevo.tickets_no).tickets_no_status == 1}"><a>尚未使用</a></c:if>
										<c:if test="${TicketNoSvc.getOneCashTr(orderdevo.tickets_no).tickets_no_status == 2}"><a>已使用</a></c:if>
										<c:if test="${TicketNoSvc.getOneCashTr(orderdevo.tickets_no).tickets_no_status == 3}"><a>失效</a></c:if>
									</td>
								</tr>
							</tbody>
						</c:if>
					</c:forEach>
				</c:forEach>
			</table>
		</div>
	</div>
</div>

</body>
</html>