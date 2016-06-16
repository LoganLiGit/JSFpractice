<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.qa.model.*"%>
<%@ page import="com.ad.model.*"%>
<%@ page import="com.store.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    QaService qaSvc = new QaService();
    List<QaVO> list = qaSvc.getAll();
    pageContext.setAttribute("list",list);
    
	AdService adSvc = new AdService();
	List<AdVO> list1 = adSvc.getAll();
	pageContext.setAttribute("list1", list1);
%>

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
       	  		<%@ include file="page1.file" %> 
       	  	</div>
			<div class="qaTitle">
				<h1>Q&amp;A</h1>
			</div>
			
		<div class="qaLine">
			<table class="ann_qa">
				<c:forEach var="qaVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr valign='middle'>
						<td>Q:${qaVO.qa_info}</td>
						<td rowspan="2">${qaVO.qa_date}</td>
					</tr>
					<tr id="qa">
						<td>A:${qaVO.qa_content}</td>
					</tr>
				</c:forEach>
			</table>
			<div class="ann_page">
				<%@ include file="page2.file" %>
			</div>
		</div>
       	  		
	</div>
       
</div>
</div> <!-- end of book online wrapper-->




	
</body>
</html>
