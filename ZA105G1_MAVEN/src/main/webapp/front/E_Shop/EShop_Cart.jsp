<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--註冊JSTL	 --%>
<%@	page import="com.store.model.StoreService"%>
<%@	page import="com.TicketType.model.TicketTypeVO"%>
<%@	page import="com.TicketType.model.TicketTypeService"%>
<%@	page import="java.util.*"  %>
<%@	page import="com.sun.jmx.remote.util.OrderClassLoaders"%>
<%@	page import="com.TicketShopCar.model.Ticket"%>
<%
	String url_eshop = request.getContextPath() + "/front/E_Shop/EShop.jsp";
	String url_shopping_do = request.getContextPath() + "/front/E_Shop/Eshop_Servlet.do";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>購物車</title>
<%-- 購物車 確認購物 專用CSS --%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/shop/Cart.css">
<%-- 導入的JQuery 物件 --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/jscss/ador/lib/jquery/1.9.1/jquery.min.js"></script>
</head>
<body style="margin:0;padding:0;">
<%
	Vector<Ticket> buylist = (Vector<Ticket>)session.getAttribute("shoppingcart");
	Integer total = 0;
	if (buylist == null || buylist.size() == 0){
		response.sendRedirect(url_eshop);
	}
	if (buylist != null && (buylist.size() > 0 ))	{
%>
		<!--============ header ============-->
		<div id="header" style="width: 100%; height: 225px;">
			<iFrame style="width: 100%; height: 225px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/Header.jsp" scrolling="no"> </iFrame>
		</div>
		<!--============ header  ============-->
<h1 class="title_01">購物車</h1>
<hr class="header_hr_ar" >
<div class="div01">　
	<div class="div02">
		<div class="div03">
			<div align="center">
				<span class="style9">
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font color='red'>修改狀態:
							<c:forEach var="message" items="${errorMsgs}">
								${message}
							</c:forEach>
						</font>
					</c:if>				
				</span>			
			</div>
  		</div>
    	<div>
    		<table width="100%">
        		<thead>
            		<tr>
                		<th width="19%"></th>
                		<th width="40%">商品名稱</th>
                    	<th width="10%">單價</th>
                    	<th width="5%"></th>
                    	<th width="10%">數量</th>
                    	<th width="10%">小計</th>
                   		<th width="5%">刪除</th>
                    	<th width="1%"></th>
					</tr>
            	</thead>
				<%
				
					for (int index=0;index < buylist.size(); index++){
						
						Ticket order = buylist.get(index);
				%>
            	<tbody>
            		<tr>
                		<td>
                    		<div align="center">
                        		<img width="140" height="105" id="tickets_type_no_image" src="<%=request.getContextPath() %>/DBGifReader11.do?name=<%=order.getTickets_type_no() %>"/>
                        	</div>
                    	</td>
                    	<td>
	                   		<div align="center">
	                      		<div class="50_" width="50%">
	                        		<div class="left_" >
	                            		<div align="left">
	                            			<%--團購劵名稱 --%>
	                            			<span class="style10"><%=order.getTickets_type_name() %></span>
	                          	      	</div>
	                        		</div>
		                       	  	<div class="left_" >
		                            	<div align="left">
		                            		<%--團購劵編號 --%>
		                            		<span class="style12"><%=order.getTickets_type_no() %>
		                            		</span>
		                          	    </div>
									</div>
									<div  class="left_" >
		                            	<div align="left">
		                            		<%--團購劵店家 --%>
		                            		<span class="style13"><%= order.getStore_name() %></span>
		                          	    </div>
									</div>
	                        	</div>
		                	</div>
                    	</td>
	                    <td>
		                    <div align="center">
								<div align="right">
		                        	<span class="style10" align="left" >NT:</span>
		                            <span class="style14" align="right"><%=order.getPrice() %></span>
		                         </div>
		                    </div>
	                    </td>
                    	<td></td>
                    	<td><%-- 修改數量 --%>
	                    	<div align="center">
			                	<input type="text" name="time" size="1" value="<%=order.getQuantity() %>" id="time_no_<%=index %>" >
			                	<a id="update_button_time" class="ddd_ora update_hidden_time" no="<%=index %>" >修改</a>
	                   		</div>                    
                    	</td>
                    	<%	Integer thismoney = order.getPrice() * order.getQuantity(); 
                    		total = total + thismoney;
                    	%>
	                    <td>
		                    <div align="center">
		                   		<span><%=thismoney %></span>
		                    </div>
	                    </td>
	                    <td>
		                    <div align="center">
								<a id="delete_button" class="ddd_red delete_hidden" no="<%=index %>">刪除</a>
		                    </div>
	                    </td>
                    	<td></td>
                	</tr>            
            	</tbody>
            	<% } %>        
        	</table>    
    	</div>
    	<div class="div04">
    	<br>
    	<table width="100%">
        	<thead>
            	<tr>
                	<th width="19%"></th>
                	<th width="40%"></th>
                    <th width="10%"></th>
                    <th width="15%"></th>
                    <th width="10%">
	                    <div align="center">
	                   	<span></span>
	                    </div>                   
                    </th>
                    <th width="5%"></th>
                    <th width="1%"></th>
                </tr>
            </thead>
            <tbody>
            	<tr>
                	<td></td>
                    <td></td>
                    <td></td>
                    <td colspan="2">
                    <hr>                   
                    </td>
                    <td></td>
                    <td></td>
                </tr>
            	<tr>
                	<td></td>
                    <td></td>
                    <td></td>
                    <td>共<%=buylist.size() %>件商品</td>
                    <td><%=total %></td>
                    <td></td>
                    <td></td>
                </tr>
                
			</tbody>
			<tbody><tr><br></tr></tbody>
			<tbody>
            	<tr>
                    <td colspan="2" >
                    	<a id="go_e_shop" class="ddd_green" href="<%= url_eshop %>" method="POST"  >繼續購物</a>
                    </td>
                    <td colspan="4">
                    	<a id="go_checkout" class="ddd_green" >付款結帳</a>
                    </td>
                </tr>
            </tbody>
    	</table>
  		</div> 
  	</div>
</div>
<%}%>
<%-- 按下確定結帳 到後端坐錯誤辨識  V--%>
<form id="go_check_form" action="<%= url_shopping_do %>" method="POST">
	<input type="hidden" name="action" value="checkout">
</form>
<%-- 按下刪除 到後端做刪除本商品      V --%>
<form id="go_delete" action="<%= url_shopping_do %>" method="POST" >
	<input type="hidden" name="action" value="DELETE">
	<input type="hidden" id="hedden_submit_delete" name="del" value="">
</form>
<%-- 按下去到後端去修改數量              V --%>
<form id="go_update_time" name=delleteForm action="<%= url_shopping_do %>" method="POST" >
	<input type="hidden" id="hidden_submit_update" name="updata_time" value="">
	<input type="hidden" id="hidden_time_update" name="time" value="">
	<input type="hidden" name="action" value="UPDATDTIME">
</form>


<%-- 按下轉去結帳 --%>
<script type="text/javascript">
$(function(){
	$("#go_checkout").click(function(){
		$("#go_check_form").submit();
	})
});
</script>
<%-- 按下送去後端做刪除動作 --%>
<script>
$(function(){
	$(".delete_hidden").click(function(){
		var no = $(this).attr("no"); //取得第幾個
		$("#hedden_submit_delete").val(no);
		$("#go_delete").submit();
	})
});
</script>
<%-- 按下送去後端做修改動作 --%>
<script>
$(function(){
	$(".update_hidden_time").click(function(){
		var no = $(this).attr("no"); //取得第幾個
		var time = $("#time_no_" + no ).val();//取得數量
		//alert(time);
		$("#hidden_submit_update").val(no);
		$("#hidden_time_update").val(time);
		$("#go_update_time").submit();
	})
});
</script>
<!--============ footer ============-->
<div id="footer" style="width: 100%; height: 455px;">
	<iFrame style="width:100%; height: 455px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/footer.jsp" scrolling="no"> </iFrame>
</div>
<!--============ footer  ============-->

</body>
</html>