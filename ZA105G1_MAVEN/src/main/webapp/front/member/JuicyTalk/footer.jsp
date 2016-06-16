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
				<h4>- �ϥγW�d</h4>
			</div> <!-- end of review-->
			<div class="clearfix"></div>
			<div class="line"></div>
			<div class="review">
				<p>Juicy Talk �Y�̷ӥ��A�ȱ��ڤ����w�A�����x�Ȫ��I���A�ȡBJuicy Talk ���a�u�f��Juicy Talk App �������A�ȡC
				�z�����P�N���A�ȱ��ڤ����e�ç������U�{�ǡA�~�ন���uJuicy Talk�v�������|���èϥΡuJuicy Talk�v���Ѫ��U���A�ȡC
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
				<a href="<%=request.getContextPath()%>/front/qa/listAllQa.jsp"  target="_parent">�ԲӽЫ�</a>
			</div> <!-- end of review-->
		</div>
		<div class="announcement">
        
			<div class="review">
				<h4>- ���i</h4>
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
						<th scope="col">���D</th>
						<th scope="col">�o��ɶ�</th>
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