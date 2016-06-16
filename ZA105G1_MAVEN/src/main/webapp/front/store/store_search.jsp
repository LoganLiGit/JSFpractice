<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.store.pic.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="listStores_ByCompositeQuery" scope="request" type="java.util.List" />
<html>
<head>
<title>${storeVO.store_name}</title>
<!--webfont-->
<link
	href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900,200italic,300italic,400italic,600italic,700italic,900italic'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Lobster+Two:400,400italic,700,700italic' rel='stylesheet' type='text/css'>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<%=request.getContextPath()%>/front/store/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/store/css/style.css">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Custom Theme files -->
<link href="<%=request.getContextPath()%>/front/store/css/style2.css" rel="stylesheet" type="text/css" media="all" />
<!-- Custom Theme files -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- scroll function -->
<script type="text/javascript">
	$(document).ready(function() {
		$('#navigations').localScroll({
			duration : 800
		});
	});
</script>
<script src="<%=request.getContextPath()%>/front/store/js/wow.min.js"></script>
<link href="<%=request.getContextPath()%>/front/store/css/animate.css" rel='stylesheet' type='text/css' />
<script>
	new WOW().init();
</script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1300);
		});
	});
</script>
</head>
<body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/front/store/js/bootstrap.min.js"></script>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font color='red'> 請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>
	
		<!--============ header ============-->

		<div id="header" style="width: 100%; height: 225px;">
			<iFrame src="<%=request.getContextPath()%>/front/member/JuicyTalk/Header.jsp" scrolling="no"> </iFrame>
		</div>

	<!--============ header  ============-->
	
	<!-- end of headerwrapper-->
	<div class="wrap">
		<div class="Popular-Restaurants-grids">
			<c:forEach var="storeVO" items="${listStores_ByCompositeQuery}" varStatus="s">
				<c:if test="${s.index%2 ==0}" var="condition" scope="page">
					<div class="Popular-Restaurants-grid wow fadeInRight" data-wow-delay="0.4s">
				</c:if>
				<c:if test="${s.index%2 !=0}" var="condition" scope="page">
					<div class="Popular-Restaurants-grid wow fadeInLeft" data-wow-delay="0.4s">
				</c:if>
				<div class="col-md-3 restaurent-logo">
					<img class="img-responsive" src="<%=request.getContextPath()%>/store_pic/DBGifReader2.do?store_no=${storeVO.store_no}" />
				</div>
				<div class="col-md-2 restaurent-title">
					<div class="logo-title">
						<h4>
							<a href="#">${storeVO.store_name}</a>
						</h4>
					</div>
					<div class="rating">
						<span class="glyphicon glyphicon-phone-alt">電話：${storeVO.store_phone}</span>
						<span class="glyphicon glyphicon-home">地址：(${storeVO.store_zipcode})${storeVO.store_city}${storeVO.store_district}${storeVO.store_address}</span>
						<span class="glyphicon glyphicon-cutlery">類型：${storeVO.store_type}</span>
						<span class="glyphicon glyphicon-heart">分數：<c:if test="${storeVO.store_scopenum==0}" var="condition" scope="page">暫無人評分</c:if><c:if test="${storeVO.store_scopenum>0}" var="condition" scope="page"><fmt:formatNumber value="${storeVO.store_score/storeVO.store_scopenum}" pattern="#0.0#"/></c:if>
						</span>
					</div>
				</div>
				<div class="col-md-7 buy">
					<a href="#"> </a>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do">
						<input type="submit" value="Enter">
						<input type="hidden" name="action" value="store_search">
						<input type="hidden" name="store_no" value="${storeVO.store_no}">
					</FORM>
				</div>
				<div class="clearfix"></div>
		</div>
		</c:forEach>
	</div>
	</div>
	<div class="clear"></div>

	
<!--============ footer ============-->
<div id="footer" style="width: 100%; height: 455px;">
	<iFrame style="width:100%; height: 455px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/footer.jsp" scrolling="no"> </iFrame>
</div>
<!--============ footer  ============-->
</body>
</html>