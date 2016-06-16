<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--註冊JSTL	 --%>
<%@page import="java.util.*" %>
<%@page import="com.TicketType.model.*"%>
<%@page import="com.store.model.StoreVO"%>
<jsp:useBean id="StoreSvc" class="com.store.model.StoreService"/>
<jsp:useBean id="zipcodes" class="com.zipcodes.model.ZipcodesService"/>
<%
	List list = new ArrayList();
	if (request.getAttribute("store_district_list") != null){
		System.out.print("第一次進來2");
		list = (List)request.getAttribute("store_district_list");
		pageContext.setAttribute("shoplist",list);
	}
	else if (request.getAttribute("store_city_list") != null){
		System.out.print("第一次進來1");
		list = (List)request.getAttribute("store_city_list");
		pageContext.setAttribute("shoplist",list);
		//第一次 查詢城市 (台北市 執行這裡)
	}
	else{
		List<TicketTypeVO> shoplist = new TicketTypeService().getShopAll();
		pageContext.setAttribute("shoplist",shoplist);
		//第一次進來沒有做任何查詢的時候 走這裡
	}

	String url_shopping_do = request.getContextPath() + "/front/E_Shop/Eshop_Servlet.do";
	String url_shopping_do2 = request.getContextPath() + "/front/E_Shop/Eshop_Area_Servlet.do";

%>






<%--	先取得商店的M	 --%>
<%--import	 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>團購劵商品</title>
<%-- 導入的css js物件結束 --%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/shop/eshop_commodity.css">

</head>
<body>

<article name="article1" class="container_12">
	<div class="grid_11" id="div01">
		<div class="grid_10">
		<%-- 選單系統 --%>
		<a>選擇你想查看的區域:</a>
		<select name="CityNo" id="CityNo">	
			<option ${(store_city==null) ? 'selected':''} value="nonoa" >請選擇</option>
			<c:forEach var="zipcodevo" items="${zipcodes.all2}" >
				<option value="${zipcodevo.zipcodes_city }" ${(store_city==zipcodevo.zipcodes_city) ? 'selected':''} >${zipcodevo.zipcodes_city }</option>
			</c:forEach>
		</select>
		
		<select name="DistrictNo" id="DistrictNo">
			<option ${(store_district==null) ? 'selected':''} id="nonob" >請選擇</option>
			<c:forEach var="zipcodevo2" items="${store_city2}" >
				<option var="${zipcodevo2.zipcodes_district }" ${(store_district==zipcodevo2.zipcodes_district) ? 'selected':''} >${zipcodevo2.zipcodes_district }</option>
			</c:forEach>
		</select>
			
		</div>
		<Form method="POST" action="<%=url_shopping_do2%>" id="Shopping2_submit">
				<%-----------------------------------------商品選單------------------------------------------%>
				<c:forEach var="tickettypevo" items="${shoplist}">
					<div class="grid_2">
						<%-- 第一行 要顯示的 :  店家縣市 / 店家區鄉鎮 --%>
						<div class="tita1">							
							<c:forEach var="StoreVO" items="${StoreSvc.allforShop}" >
								<c:if test="${ StoreVO.store_no == tickettypevo.store_no}">
									<div class="reputation_foreshow_item_title" >
										<%-- 店家縣市 --%>
										<span class="reputation_foreshow_item_exchange">
											<a id="store_city" value="${StoreVO.store_city}">
												${StoreVO.store_city}
											</a>
										</span>
									</div>
									<div class="reputation_foreshow_item_title">
										<%-- 店家區鄉鎮 --%>
										<span class="reputation_foreshow_item_city">
											<a id="store_district" city="${StoreVO.store_city}" value="${StoreVO.store_district}">
												${StoreVO.store_district}
											</a>
										</span>
									</div>
								</c:if>
							</c:forEach>
									<%-- 團購劵圖片 --%>
									<div>
										<%--點進去察看詳情 --%>
										<img width="140" height="105" id="tickets_type_no_image" value="${ tickettypevo.tickets_type_no }" src="<%=request.getContextPath() %>/DBGifReader11.do?name=${ tickettypevo.tickets_type_no }"/>
									</div>
									<%-- 團購劵名稱 --%>
									<div class="h40">									
										<a href="#" class="nono2" id="tickets_type_no_name" value="${ tickettypevo.tickets_type_no }" >
										<%--點進去察看詳情 --%>
											${ tickettypevo.tickets_type_name }
										</a>
									</div>
									<%-- 團購劵剩餘數量 --%>
									<div class="h20">
										<a class="w1">剩餘數量 : </a>
										<a>${ tickettypevo.tickets_quantity }</a>
									</div>
									<%-- 團購劵價格 --%>
									<div class="h20">
										<a class="w1">價格: </a>
										<a>${ tickettypevo.tickets_price }</a>
									</div class="h20">
									<%-- 購買數量 --%>
									<div class="h20">
										<a class="w1">購買數量:</a>
										<a><input id="quantity${ tickettypevo.tickets_type_no }" type="text" name="quantity" size="3" value="1"></input></a>
									</div>
									<div>
										<input type="submit" id="addshop"
										tickets_type_no="${ tickettypevo.tickets_type_no }"
										tickets_type_name="${ tickettypevo.tickets_type_name }"
										tickets_lastqu="${ tickettypevo.tickets_quantity }"
										/>
									</div>			
						</div>
					</div>
				</c:forEach>
				<%-----------------------------------------商品選單------------------------------------------%>
		
		
			<input type="hidden" name="action" id="hidden_action" value="">
			<%-- 要送出做什?   ~~要送出的數值 用 JQuery來送 --%>	
			
			<input type="hidden" name="store_city" id="hidden_store_city" value="">
			<input type="hidden" name="store_district" id="hidden_store_district" value="">
		</Form>	
		<Form method="POST" action="<%=url_shopping_do%>" id="Shopping1_submit">
			<input type="hidden" name="tickets_type_no" id="hidden1_tickets_type_no" value="">
			<%-- 團購劵編號 ~~~~~要送出的數值 用 JQuery來送 --%>
			<input type="hidden" name="tickets_type_name" id="hidden1_tickets_type_name" value="">
			<%-- 團購劵名稱 ~~~~~要送出的數值 用 JQuery來送 --%>
			<input type="hidden" name="quantity" id="hidden1_quantity" value=""> 
			<%--購買數量 --%>
			<input type="hidden" name="lastqu" id="hidden1_lastqu" value="">
			<%-- 團購劵剩餘數量 ~~~~~要送出的數值 用 JQuery來送 --%>
			<%-- 要送出做什?   ~~要送出的數值 用 JQuery來送 --%>	
			<input type="hidden" name="action" id="hidden1_action" value="">
		</Form>
	</div>
</article>
<style>
#addshop {
    margin-left: 2px;
    width: 110px;
    height: 22px;
    border: 0;
    cursor: pointer;
    text-indent: -9999px;
    background: url(<%= request.getContextPath() %>/jscss/1.jpg) no-repeat;
}
</style>
<%-- 假如按下<a>選單上的 (台北市 東區 這類型的 執行) --%>
<script type="text/javascript">
$(function(){
	$("#div01 Form div span").find("a").click(function(){
		if($(this).attr("id")=='store_city'){
			var store_city = $(this).attr("value");
			
			$("#hidden_action").val('store_city');
			$("#hidden_store_city").val(store_city);
			$("#Shopping2_submit").submit();			
		}
		<%-- 當按下<a>台北市的時候做這個	--%>
		if($(this).attr("id")=='store_district'){			
			var store_district = $(this).attr("value");
			var store_city = $(this).attr("city");
			
			$("#hidden_action").val('store_district');
			$("#hidden_store_city").val(store_city);
			$("#hidden_store_district").val(store_district);
			$("#Shopping2_submit").submit();
		}
		<%-- 當按下<a>東區的時候做這個	--%>		
	})
});
</script>
<%-- 假如按下<a>選單上的 (圖片 文字 與 加入購物車) --%>
<script type="text/javascript">
$(function(){
	$("#div01 Form div").find("a").click(function(){
		if($(this).attr("id")=='tickets_type_no_name'){
			var tickets_type_no_name = $(this).attr("value");
			$("#hidden_tickets_type_no").val(tickets_type_no_name);
			$("#hidden_action").val('select_tickettype');			
			$("#Shopping2_submit").submit();
		}
	})
	<%-- 假如按下<a>選單上的 (文字) --%>
	$("#div01 Form div").find("img").click(function(){
		if($(this).attr("id")=='tickets_type_no_image'){
			var store_district = $(this).attr("value");
			$("#hidden_tickets_type_no").val(store_district);
			$("#hidden_action").val('select_tickettype');			
			$("#Shopping2_submit").submit();
		}
	})
	<%-- 假如按下<a>選單上的 (圖片) --%>
	$("#div01 div").find("input").click(function(){
		
		if($(this).attr("id")=='addshop'){
			var tickets_type_no = $(this).attr("tickets_type_no");
			var tickets_type_name = $(this).attr("tickets_type_name");
			var store_district = $(this).attr("value");			
			var quantity =$("#quantity"+tickets_type_no).val();  
			var tickets_lastqu = $(this).attr("tickets_lastqu");
			$("#hidden1_lastqu").val(tickets_lastqu);
			$("#hidden1_tickets_type_no").val(tickets_type_no); //團購劵編號
			$("#hidden1_tickets_type_name").val(tickets_type_name);	 //團購劵名稱
			$("#hidden1_quantity").val(quantity);	//團購劵購買數量
			$("#hidden1_action").val('ADD');	
			$("#Shopping1_submit").submit();
		}
		return false;
	})
	<%-- 假如按下選單上的 (加入購物車) --%>
});
</script>
 <%-- 下拉選單的修改 自動做 --%>
<script type="text/javascript">
$(function(){
	
	$("#CityNo").change(function(){
		var store_city = $(this).attr("value");
		$("#hidden_action").val('store_city');
		$("#hidden_store_city").val(store_city);
		
		var store_city2 = "nonoa";
		if (store_city != store_city2){
			$("#Shopping2_submit").submit();
		}		
	})
	<%-- 如果選擇城市 (台北市 )的時候 做這個 --%>
	$("#DistrictNo").change(function(){
		var store_district = $(this).attr("value");
		$("#hidden_action").val('store_district');
		$("#hidden_store_district").val(store_district);
			
		var store_city2 = $("#CityNo").attr("value");
		$("#hidden_store_city").val(store_city2);
			
		var store_district2 = "nonob";
		if (store_district != store_district2){
			$("#Shopping2_submit").submit();
		}
	})
	<%-- 如果選擇鄉  (東區 )的時候 做這個 --%>
});
</script>

</body>
</html>