<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--註冊JSTL	 --%>
<%@page import="java.util.*" %>
<%@page import="com.member.model.*"%>
<%@page import="com.TicketOr.model.*"%>
<%
	String url_eticket_do = request.getContextPath() + "/front/E_My_Ticket/Eticket_Servlet.do";
	List<TicketOrVO> list = null;
	String mem_account = (String)session.getAttribute("mem_account");
	if (mem_account == null ){		
	}else{
		Integer mem_no = new MemberService().getOneAccount(mem_account).getMem_no();
		//Integer mem_no = new MemberService().getOneAccount(mem_account).getMem_no(); 
		//利用MemberService的這個getOneAccount方法取得裡面的getMem_no()  (會員編號)
		list = new TicketOrService().getUserdate(mem_no);
		//List<TicketOrVO> list = new TicketOrService().getUserdate(mem_no);
		//利用TicketOrService的這個getUserdate(mem_no);方法取得裡面的list  (此會員編號的所有訂單資訊)
		pageContext.setAttribute("list", list);
	}
%>
<%-- 放進當前頁面 任意取用 --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的訂單總覽</title>
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
	<h1 class="rd-smll">訂單列表</h1>
	<hr class="header_hr" />
	<div class="mc-order-states">
		<div id="mc-table">
			<%-- <%@ include file="/Ador/fbcm/ador_fbcm_select__tickettype_page/page1.file" %>--%>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rd-table" id="area">
            	<thead>
                	<tr class="rd-C-Hide">
						<th>訂單編號</th>
						<th>訂單日期</th>
						<th>會員編號</th>
						<th>訂單金額</th>
						<th>訂單狀態</th>
						<th>查看詳情</th>
                        <th></th>
                	</tr>
				</thead>
				<FORM id="batch_submit" METHOD="post" ACTION="<%=url_eticket_do%>">
				<%-- <c:forEach var="ticketorvo" items="${list}"  begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">--%>
				<c:forEach var="ticketorvo" items="${list}">
				<tbody>
					<tr class="mc-tableContentITEM">
						<td><a href="#" id="a_${ ticketorvo.order_no }" thisname="a_out" >${ ticketorvo.order_no }</a></td>
						<td><a>${ ticketorvo.order_date }</a></td>
						<td><a>${ ticketorvo.mem_no }</a></td>
						<td><a>${ ticketorvo.order_money }</a></td>
						<td>
							<a>
								<c:if test="${ ticketorvo.order_status == 0 }">訂單已取消</c:if>
								<c:if test="${ ticketorvo.order_status == 1 }">訂單已成立</c:if>							
							</a>
						</td>
						<td>								
							<button id="btn_${ ticketorvo.order_no }" thisname="lowout" class="btn btn-primary radius">查看</button>
						</td>
					</tr>
				</tbody>
				</c:forEach>
					<input type="hidden" id="order_no"  name="order_no" value="">					
					<input type="hidden" id="action" name="action" value="">
				</FORM>
			</table>
			<%--<%@ include file="/Ador/fbcm/ador_fbcm_select__tickettype_page/page2.file" %>--%>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(function(){
		$("#area").find("a").click(function(){
			if($(this).attr("thisname")=='a_out'){	
				var pk_value=$(this).attr("id").replace("a_","");
				$("#order_no").val(pk_value);
				$("#action").val("select_order_no");
				$("#batch_submit").submit();
			}else{
				return false;
			}
		})
		
		$("#area").find("button").click(function(){			
			if ($(this).attr("thisname")=='lowout'){
				var pk_value=$(this).attr("id").replace("btn_","");
				$("#order_no").val(pk_value);
				$("#action").val("select_order_no");
				$("#batch_submit").submit();
			}
		})
	
	});
</script>
</body>
</html>