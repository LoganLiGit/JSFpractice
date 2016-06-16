<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<%--註冊JSTL	 --%>
<%
	String url_eticket_do = request.getContextPath() + "/front/E_My_Ticket/Eticket_Servlet.do";
	List myOrderTicketlist = new ArrayList();
	if (request.getAttribute("MyOrderTicketlist")!=null){
		myOrderTicketlist = (List)request.getAttribute("MyOrderTicketlist");
		pageContext.setAttribute("myOrderTicketlist",myOrderTicketlist);
	}
%>
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
	<h1 class="rd-smll">團購券序號列表(查詢單一狀態)</h1>
	<hr class="header_hr" />
	<div class="mc-order-states">
		<div id="mc-table" >
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rd-table" id="area">
            	<thead>
                	<tr class="rd-C-Hide">
						<th>團購券種類編號</th>
						<th>團購券序號</th>
						<th>使用狀態</th>
                        <th></th>
                	</tr>
				</thead>
				<c:forEach var="aa" items="${myOrderTicketlist}">
					<tbody>
						<td><a>${aa.tickets_type_no}</a></td>
						<td><a>${aa.tickets_no}</a></td>
						<td>						
							<c:if test="${aa.tickets_no_status == 0}"><a>未賣出</a></c:if>
							<c:if test="${aa.tickets_no_status == 1}"><a>尚未使用</a></c:if>
							<c:if test="${aa.tickets_no_status == 2}"><a>已使用</a></c:if>
							<c:if test="${aa.tickets_no_status == 3}"><a>失效</a></c:if>
						</td>
					</tbody>
				</c:forEach>
			</table>
		</div>
	</div>
</div>

</body>
</html>