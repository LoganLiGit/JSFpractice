<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ann.model.*"%>
<%@ page import="com.ad.model.*"%>
<%
	AnnService annSvc = new AnnService();
	List<AnnVO> list = annSvc.getAll();
	pageContext.setAttribute("list",list);
    
	AdService adSvc = new AdService();
	List<AdVO> list1 = adSvc.getAll();
	pageContext.setAttribute("list1", list1);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Juicy Talk</title>
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script src="<%=request.getContextPath()%>/front/member/JuicyTalk/js/jquery.js"></script> 
	<script src="<%=request.getContextPath()%>/front/member/JuicyTalk/js/jquery.glide.js"></script>
    
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front/member/JuicyTalk/css/style3.css">
    
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front/member/JuicyTalk/css/annAndQA.css">
    <!-- 廣告用 -->
    <script src="<%=request.getContextPath()%>/front/ad/js/ad.js" type="text/javascript"></script> 
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front/ad/js/ad.css">
    
<!-- scroll function -->


</head>
<body>

<!--============ header ============-->
<div id="header" style="width:100%; height: 225px;">
	<iFrame style="width:100%; height: 225px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/Header.jsp" scrolling="no"> </iFrame>
</div>
<!--============ header ============-->
	
	
<div class="bookonlinewrapper">
	<div class="container1" id="bookonline">
	
		<div id="abgne-block-20110317" style="float:left; margin-left:100px" >
			<div class="abgne-player">
				<ul class="abgne-list">
					<c:forEach var="adVO" items="${list1}">
						<li>
							<a target="_blank" href="#">
								<img src="<%=request.getContextPath()%>/ad/ReadImage.do?ad_no=${adVO.ad_no}" />
							</a>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="abgne-control">
				<ul class="arrows"> 
					<li class="prev"></li> 
					<li class="next"></li> 
				</ul>
			</div>
		</div>
		<div style="float:left; padding-left:60px; padding-top:50px;">
			<div class="ann_page">
				<h1>系統公告</h1>
				<br>
				<table class="ann_qa" width="800">
					<tr class="ann_qa">
						<th>公告標題</th>
						<th>公告內容</th>
						<th>公告日期</th>
					</tr>
					<%@ include file="annPage1.file" %> 
					<c:forEach var="annVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<tr align='center' valign='middle'>
							<td>${annVO.ann_info}</td>
							<td><a href="<%=request.getContextPath()%>/ann/ann.do?ann_no=${annVO.ann_no}&action=getOne_For_Display">${(annVO.ann_content).substring(0,10)}......</a></td>
							<td>${annVO.ann_date}</td>
						</tr>
					</c:forEach>
				</table>
				<div class="ann_page">
					<%@ include file="annPage2.file" %>
				</div>
			</div>
		</div>
	</div>
</div>
	

</body>
</html>