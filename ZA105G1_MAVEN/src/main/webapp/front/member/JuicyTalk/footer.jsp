<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.ann.model.*"%>
<%@ page import="com.qa.model.*"%>

<!DOCTYPE html> 
<html lang="en">
<head>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/front/member/JuicyTalk/css/style.css">
</head>
<body>
<!--============ FOOTER ============-->

<div class="footerwrapper">
	<footer class="container">
		<div class="customerreview">
       		
			<div class="review">
				<h4>- 使用規範</h4>
			</div> <!-- end of review-->
			<div class="clearfix"></div>
			<div class="line"></div>
			<div class="review">
				<p>Juicy Talk 係依照本服務條款之約定，提供儲值金兌換服務、Juicy Talk 店家優惠及Juicy Talk App 等相關服務。
				您必須同意本服務條款之內容並完成註冊程序，才能成為「Juicy Talk」的正式會員並使用「Juicy Talk」提供的各項服務。
				</p>
				
			</div> <!-- end of review-->
          

			<div class="review">
				<br>
				<h4>- Q&A</h4>
			</div> <!-- end of review-->
			<div class="clearfix"></div>
			<div class="line"></div>
 			<div class="review">
 			
	 			<%
				QaService qaSvc = new QaService();
				List<QaVO> list = qaSvc.getAll();
				pageContext.setAttribute("list",list);
				%>
				<%@ include file="page1.file" %>
				<c:forEach var="qaVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<p>
					${qaVO.qa_info}<br>
				</p>
				</c:forEach>
				<a href="<%=request.getContextPath()%>/front/qa/listAllQa.jsp"  target="_parent">詳細請按</a>
			</div> <!-- end of review-->
		</div>
		<div class="announcement">
        
			<div class="review">
				<h4>- 公告</h4>
			</div> <!-- end of review-->
			<div class="clearfix"></div>
			<div class="line"></div>
			<div class="review">
          
				<table height="350" width="600" border="1" >
					<%
					AnnService annSvc = new AnnService();
					List<AnnVO> ann = annSvc.getAll();
					pageContext.setAttribute("list",ann);
					%>
					<tr>
						<th scope="col">標題</th>
						<th scope="col">發表時間</th>
					</tr>
					<c:forEach var="annVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<tr>
							<td>${annVO.ann_info}</td>
							<td>${annVO.ann_date}</td>
						</tr>
					</c:forEach>
					<tr>
						<td id="more" colspan="3" ><a href="<%=request.getContextPath()%>/front/ann_front/announcement.jsp"  target="_parent">more</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
				</table>   
			</div> <!-- end of review-->
		</div>
	</footer> <!-- end of footer-->

</div> <!-- end of footer-->
<!--============ COPYRIGHTS ============-->

<div class="copyrightswrapper">
    <div id="copyrights" class="container">
    
 	   <p>Copyright 2016 All Rights Reserved</p>
    
    </div> <!-- end of copyrights-->
 <!-- end of website-->
</div>
</body>
</html>