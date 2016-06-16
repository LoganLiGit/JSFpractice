<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ann.model.*"%>
<%
AnnVO annVO = (AnnVO) request.getAttribute("annVO"); //AnnServlet.java(Concroller), 存入req的annVO物件
%>
<html>
<head>
<title>員工資料 - listOneAnn.jsp</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <script src="<%=request.getContextPath()%>/front/member/JuicyTalk/js/jquery.js"></script> 
	<script src="<%=request.getContextPath()%>/front/member/JuicyTalk/js/jquery.glide.js"></script>
    
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front/member/JuicyTalk/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front/member/JuicyTalk/css/animate.css">
    <script type="text/javascript" src="js/MyJQ.js"></script>
    
    <script src="<%=request.getContextPath()%>/front/member/JuicyTalk/js/jquery.localScroll.min.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/front/member/JuicyTalk/js/jquery.scrollTo.min.js" type="text/javascript"></script> 
    <script src="<%=request.getContextPath()%>/front/member/JuicyTalk/js/wow.min.js" type="text/javascript"></script> 
    
    
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front/member/JuicyTalk/css/annAndQA.css">
    
<!-- scroll function -->
<script type="text/javascript">
$(document).ready(function() {
   $('#navigations').localScroll({duration:800});
});
</script>


<script src="js/wow.min.js"></script>
<script>
new WOW().init();
</script>

</head>
<body>
<!--============ header ============-->
<div id="header" style="width:100%; height: 225px;">
	<iFrame style="width:100%; height: 225px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/Header.jsp" scrolling="no"> </iFrame>
</div>
<!--============ header ============-->
	


<div class="ann_qa">
	<table class="ann_qa" width='600'>
		<tr>
			<th><%=annVO.getAnn_info()%></th>
		</tr>
		
		<tr><td><%=annVO.getAnn_content()%></td></tr>
		<tr><td class="ann_date"><%=annVO.getAnn_date()%></td></tr>
	</table>
</div>




</body>
</html>
