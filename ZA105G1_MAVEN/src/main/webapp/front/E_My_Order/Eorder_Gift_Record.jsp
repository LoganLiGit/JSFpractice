<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--註冊JSTL	 --%>
<%@page import="java.util.*" %>
<%@page import="com.member.model.*"%>
<%@page import="com.CashTr.model.*"%>
<%
	Integer mem_no = null;
	List<CashTrVO> list = null;
	String mem_account = (String)session.getAttribute("mem_account");
	if (mem_account == null ){
		
	}else{
		mem_no = new MemberService().getOneAccount(mem_account).getMem_no();
		CashTrService cashtrservice = new CashTrService();
		list = cashtrservice.getusertrandaction(mem_no);
		pageContext.setAttribute("list", list);
		System.out.print(list.size());
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>儲值金交易紀錄</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/jscss/ador/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/jscss/ador/lib/layer/1.9.3/layer.js"></script> 
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/myorderform/myorderform_list01.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/myorderform/input01.css">


<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/myorderform/myorderform_list02.css">
</head>

<body>
		<!--============ header ============-->
		<div id="header" style="width:100%; height: 225px;">
			<iFrame style="width: 100%; height: 225px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/Header.jsp" scrolling="no"> </iFrame>
		</div>
		<!--============ header  ============-->
<div name="article1"  class="mc-content" >
	<h1 class="rd-smll">儲值紀錄列表</h1>
	<hr class="header_hr" />
	<div class="mc-order-states">
		<div id="mc-table">
			<%-- <%@ include file="/Ador/fbcm/ador_fbcm_select__tickettype_page/page1.file" %>--%>
			<table width="90%" border="0" cellspacing="0" cellpadding="0" class="rd-table" id="area">
            	<thead>
                	<tr class="rd-C-Hide">
						<th>交易日期</th>
						<th>交易編號</th>
						<th>儲值編號</th>
						<th>儲值金額</th>
                	</tr>
				</thead>
				<%-- <c:forEach var="ticketorvo" items="${list}"  begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">--%>
				<c:forEach var="cashtrvo" items="${list}">
				<tbody>
					<tr class="mc-tableContentITEM">
						<td><a>${cashtrvo.trandaction_date }</a></td>
						<td><a>${cashtrvo.trandaction_no }</a></td>
						<td><a>${cashtrvo.mem_no }</a></td>
						<td><a>${cashtrvo.trandaction_money }</a></td>
				</tbody>
				</c:forEach>
			</table>
			<%--<%@ include file="/Ador/fbcm/ador_fbcm_select__tickettype_page/page2.file" %>--%>
		</div>
	</div>
</div>

</body>
</html>