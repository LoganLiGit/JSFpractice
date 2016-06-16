<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--註冊JSTL	 --%>
<%@ page import="java.util.*"  %>
<%@page import="com.sun.jmx.remote.util.OrderClassLoaders"%>
<%@page import="com.TicketShopCar.model.Ticket"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>浮動購物車</title>
</head>
<body>
<%
	String url_shopping_do = request.getContextPath() + "/front/E_Shop/Eshop_Servlet.do";
	String url_cart = request.getContextPath() + "/front/E_Shop/EShop_Cart.jsp";
	
	Vector<Ticket> buylist = (Vector<Ticket>)session.getAttribute("shoppingcart");
	if (buylist != null && (buylist.size() > 0 ))	{
%>

<font size="+3">目前您購物車的內容如下：</font><p>
<table>
	<tr>
		<th>名稱</th>
		<th>數量</th>
		<th>價格</th>
	</tr>
	<%
		for (int index=0;index < buylist.size(); index++){
			Ticket order = buylist.get(index);
	%>
	<tr>
		<td><a><%=order.getTickets_type_name() %></a></td>
		<td><a><%=order.getQuantity() %></a></td>
		<td><a><%=order.getPrice() %></a></td>
		<td>
			<div align="center">
				<form name=delleteForm action="<%= url_shopping_do %>" method="POST" >
					<input type="hidden" name="action" value="DELETE">
					<input type="hidden" name="del" value="<%=index %>">
					<input type="hidden" name="url" value="eshop">
					<input type="submit" value="刪除">
				</form>
			</div>
		</td>
	</tr>
	<% } %>
</table>

<table>
	<tr>
		<form action="<%= url_cart %>">
			<input  type="submit" value="查看購物車" >	
		</form>
	</tr>
	<tr>
		<form name="checkoutForm" action="<%= url_shopping_do %>" method="POST">
		    <input type="hidden" name="action" value="checkout">
		    <input type="submit" value="付款結帳">
		</form>
	</tr>
</table>
<%}%>


</body>
</html>