<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--註冊JSTL	 --%>
<%@page import="com.TicketShopCar.model.Ticket"%>
<%@ page import="java.util.*"  %>
<%
		String url_eshop = request.getContextPath() + "/front/E_Shop/EShop.jsp";
		String url_cart = request.getContextPath() + "/front/E_Shop/EShop_Cart.jsp";
		String url_shopping_do = request.getContextPath() + "/front/E_Shop/Eshop_Servlet.do";

%>
<%
		Vector<Ticket> buylist=(Vector<Ticket>)session.getAttribute("shoppingcart");
		if (buylist == null || buylist.size() == 0){
			response.sendRedirect(url_eshop);
		}
		String amount =  (String) session.getAttribute("amount");
		pageContext.setAttribute("buylist",buylist);
		pageContext.setAttribute("amount",amount);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>確認結帳</title>
<%-- 購物車 確認購物 專用CSS --%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/shop/Cart.css">
<%-- 導入的JQuery 物件 --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/jscss/ador/lib/jquery/1.9.1/jquery.min.js" ></script> 
</head>
<body style="margin:0;padding:0;">
		<!--============ header ============-->
		<div id="header" style="width: 100%; height: 225px;">
			<iFrame style="width: 100%; height: 225px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/Header.jsp" scrolling="no"> </iFrame>
		</div>
		<!--============ header  ============-->
<h1 class="title_01">結帳確認</h1>
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
                    	<th width="16%">小計</th>
                   		<th></th>
                    	<th></th>
					</tr>
            	</thead>
				<c:forEach var="ordervo" items="${buylist}">
            	<tbody>
            		<tr>
                		<td>
                    		<div align="center">
                        		<img width="140" height="105" id="tickets_type_no_image" src="<%=request.getContextPath() %>/DBGifReader11.do?name=${ordervo.tickets_type_no}"/>
                        	</div>
                    	</td>
                    	<td>
	                   		<div align="center">
	                      		<div class="50_" width="50%">
	                        		<div class="left_" >
	                            		<div align="left">
	                            			<%--團購劵名稱 --%>
	                            			<span class="style10">${ordervo.tickets_type_name}</span>
	                          	      	</div>
	                        		</div>
		                       	  	<div class="left_" >
		                            	<div align="left">
		                            		<%--團購劵編號 --%>
		                            		<span class="style12">${ordervo.tickets_type_no}</span>
		                          	    </div>
									</div>
									<div  class="left_" >
		                            	<div align="left">
		                            		<%--團購劵店家 --%>
		                            		<span class="style13">${ordervo.store_name}</span>
		                          	    </div>
									</div>
	                        	</div>
		                	</div>
                    	</td>
	                    <td>
		                    <div align="center">
								<div align="right">
		                        	<span class="style10" align="left" >NT:</span>
		                            <span class="style14" align="right">${ordervo.price}</span>
		                         </div>
		                    </div>	                    
	                    </td>
                    	<td></td>
                    	<td>
		                    <div align="center">
								<div align="right">
		                            <span class="style14" align="right">${ordervo.quantity}</span>
		                         </div>
		                    </div>
                    	</td>
	                    <td>
	                    	${ordervo.quantity*ordervo.price }
	                    </td>
	                    <td></td>
                    	<td></td>
                	</tr>            
            	</tbody>
            	</c:forEach>       
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
                    <td>共${buylist.size()}件商品</td>
                    <td>${amount}</td>
                    <td></td>
                    <td></td>
                </tr>
			</tbody>
			<tbody>
				<tr>
					<td><br></td>
				</tr>
			</tbody>
		</table>
		<table width="100%">
			<tbody>
            	<tr>
                    <td>
                    	<a class="ddd_blue" href="<%= url_eshop %>">繼續購物</a>
                    </td>
                    <td  colspan="3" ></td>
                    <td>
                    	<a class="ddd_blue" href="<%= url_cart %>">返回購物車</a>
                    </td>
                    <td  colspan="3" ></td>
                    <td>
				        <a class="ddd_blue" id="check_ok" >商品確認無誤</a>
                    </td>
                </tr>
            </tbody>
    	</table>
  		</div> 
  	</div>
</div>
<form id="checkoutForm" name="checkoutForm" action="<%= url_shopping_do %>" method="POST">
	<input type="hidden" name="action" value="checkoutok">
</form>
<%-- 按下送去後端做做結帳 --%>
<!--============ footer ============-->
<div id="footer" style="width: 100%; height: 455px;">
	<iFrame style="width:100%; height: 455px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/footer.jsp" scrolling="no"> </iFrame>
</div>
<!--============ footer  ============-->
<script>
$(function(){
	$("#check_ok").click(function(){
		$("#checkoutForm").submit();
	})
});
</script>
</body>
</html>