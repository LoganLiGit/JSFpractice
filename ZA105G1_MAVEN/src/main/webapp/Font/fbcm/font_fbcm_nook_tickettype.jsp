<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.TicketType.model.*"%>
<% int rowsPerPage = 5;  //每頁的筆數     %>
<%--
	註冊JSTL 與引入 java.util.List 
 --%>
 
<!-- 驗證區域 -->
<%-- 如果強制用網頁強登 沒有店家編號 直接轉導到登入區域 --%>
<%
	if (session.getAttribute("storeno") == null ){
		RequestDispatcher reqd = request.getRequestDispatcher("/login/storelogin.jsp");
		reqd.forward(request,response);
	}
	else{
		Integer store_no = (Integer)session.getAttribute("storeno");
		pageContext.setAttribute("store_no",store_no);
	}
%>

 <%
 	List list = (List)request.getAttribute("list");
 	pageContext.setAttribute("list",list);
 	request.setAttribute("list",list);
 	
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>團購券尚未審核總覽</title>
<%-- 效果部分--%>
<link href="<%=request.getContextPath()%>/jscss/ador/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/jscss/ador/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/jscss/ador/skin/default/skin.css" rel="stylesheet" type="text/css" id="skin" />
<link href="<%=request.getContextPath()%>/jscss/ador/lib/Hui-iconfont/iconfont.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/jscss/ador/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/jscss/ador/css/nono.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jscss/ador/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/jscss/ador/lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/jscss/ador/js/H-ui.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/jscss/ador/js/H-ui.admin.js"></script> 
</head>
<body>
<!--============ header ============-->
<div id="header" style="width:100%; height: 225px;">
	<iFrame style="width:100%; height: 225px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/Header_store.jsp" scrolling="no"> </iFrame>
</div>
<!--============ header ============-->
<header class="header-top breadcrumb">
	<i class="Hui-iconfont">&#xe625;</i> 
	<a>首頁</a> 
	<i class="Hui-iconfont">&#xe63d;</i>
	<a>團購券尚未審核總覽</a> 

</header>
<div class="pd-20">
	<div class="text-c">
		<div class="mt-20">
			<%@ include file="/Font/fbcm/page/page1A.file" %>  
			<table class="table table-border table-bordered table-bg table-sort">
				<thead>
					<tr class="text-c">
						<th>團購券種類編號</th>
						<th>團購券名稱</th>
						<th>上架時間</th>
						<th>下架時間</th>
						<th>團購劵總數量</th>
						<th>剩餘數量</th>
						<th>團購劵單價格</th>
						<th>團購劵狀態</th>
						<th>店家編號</th>
						<th>團購劵說明</th>
						<th>團購劵圖片</th>
						<th>修改</th>
						<th>刪除</th>
					</tr>
				</thead>
				
				<c:forEach var="tickettypevo" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<tbody>
					<tr align='center' valign='middle' ${(tickettypevo.tickets_type_no==param.tickets_type_no) ? 'bgcolor=#CCCCFF':''} ><!--將修改的那一筆加入對比色而已-->
			
						<td>${ tickettypevo.tickets_type_no }</td>
						<td>${ tickettypevo.tickets_type_name }</td>
						<td>${ tickettypevo.upper_date }</td>
						<td>${ tickettypevo.lower_date }</td>
						<td>${ tickettypevo.tickets_total }</td>
						<td>${ tickettypevo.tickets_quantity }</td>
						<td>${ tickettypevo.tickets_price }</td>
						<td>
							<c:if test="${ tickettypevo.tickets_state == 0}">
								未審核 
							</c:if>
							<c:if test="${ tickettypevo.tickets_state == 1}">
								已審核 
							</c:if>	
							<c:if test="${ tickettypevo.tickets_state == 2}">
								上架中
							</c:if>
							<c:if test="${ tickettypevo.tickets_state == 3}">
								下架中
							</c:if>	
							<c:if test="${ tickettypevo.tickets_state > 3}">
								下架中
							</c:if>		
						</td>
						<%-- 店家編號 名稱--%>
						<td>
									
							<c:forEach var="StoreVO" items="${StoreSvc.all}" > 					
								<c:if test="${ StoreVO.store_no == tickettypevo.store_no}">
									${StoreVO.store_name} 
									
								</c:if>
							</c:forEach>(			
							${ tickettypevo.store_no }
							)
						</td>
						<td>${ tickettypevo.tickets_ex }</td>	
						<!-- 圖片辨識是否有圖 -->
						<c:if test="${ tickettypevo.tickets_image != null }">
							<td>
								<img width="120" height="100" src=" <%=request.getContextPath() %>/DBGifReader11.do?name=${ tickettypevo.tickets_type_no } "/>
							</td>			
						</c:if>
						<c:if test="${ tickettypevo.tickets_image == null }">
							<td>
								<img width="120" height="100" src=" <%= request.getContextPath() %>/IMG/NoPic.jpg"/>
							</td>			
						</c:if>
							
			<%-- 			<td>
							<img width="120" height="100" src=" <%= request.getContextPath() %>/DBGifReader.do?name=${ tickettypevo.tickets_type_no }"/>
						</td> --%>
						<td>
						  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/font/Fbcm.do">
						  <%-- 修改按鍵 路徑到 傳送到控制器 --%>
						     <input type="submit" value="修改">
						     <input type="hidden" name="tickets_type_no" value="${ tickettypevo.tickets_type_no }">
						     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
						     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
						     <input type="hidden" name="store_no" value="${store_no}">
						     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
						</td>
						<td>
						  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/font/Fbcm.do">
						  <%-- 刪除按鍵 路徑到 傳送到控制器 --%>
						    <input type="submit" value="刪除">
						    <input type="hidden" name="tickets_type_no" value="${ tickettypevo.tickets_type_no }">
						    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
						    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
						    <input type="hidden" name="store_no" value="${store_no}">
						    <input type="hidden" name="action"value="delete"></FORM>
						</td>
					</tr>
				</tbody>
				</c:forEach>
			</table>			
			<%@ include file="/Font/fbcm/page/page2.file" %>
			</div>
		</div>
	</div>
</body>


</body>
</html>