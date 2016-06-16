<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.store.model.*"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>Juicy Talk</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="<%=request.getContextPath()%>/front/member/JuicyTalk/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/front/member/JuicyTalk/js/jquery.glide.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/member/JuicyTalk/css/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/member/JuicyTalk/css/animate.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/front/member/JuicyTalk/js/MyJQ.js"></script>
<script src="<%=request.getContextPath()%>/front/member/JuicyTalk/js/jquery.localScroll.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/front/member/JuicyTalk/js/jquery.scrollTo.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/front/member/JuicyTalk/js/wow.min.js" type="text/javascript"></script>
<!-- scroll function -->
<script type="text/javascript">
	$(document).ready(function() {
		$('#navigations').localScroll({
			duration : 800
		});
	});
</script>
<script src="<%=request.getContextPath()%>/front/member/JuicyTalk/js/wow.min.js"></script>
<script>
	new WOW().init();
</script>
<script type="text/javascript">
	$(function() {
		$("#login").click(function() {
			$("#login_from").submit();
		})
	});
</script>
</head>
<!--============ header ============-->
<div id="header" style="width: 1900px; height: 225px;">
	<iFrame src="<%=request.getContextPath()%>/front/member/JuicyTalk/Header_store.jsp" scrolling="no"> </iFrame>
</div>
<!--============ header  ============-->
<body>
	<div class="sliderwrapper">
		<div id="slider" class="container">
			<div class="slider">
				<ul class="slides">
					<li class="slide">
						<h5 class="wow fadeInDown" data-wow-delay="0.8s">Juicy Talk</h5>
						<p class="wow fadeInUp" data-wow-delay="0.8s">
							一個為了讓大家增進交流與享用美食的網站
							<br>
							也有提供商家的服務功能
							<br>
							讓餐廳提高知名度
						</p>
						<img src="<%=request.getContextPath()%>/front/member/JuicyTalk/images2/logo1.png" width="317" height="256" class="wow fadeInRight"
							data-wow-delay="0.8s" alt="slide1img">
					</li>
					<li class="slide">
						<h5 class="wow fadeInDown" data-wow-delay="0.8s">名字由來</h5>
						<p class="wow fadeInUp" data-wow-delay="0.8s">
							網站的名字結合了Juicy和Talk
							<br>
							Juicy是取自於食物的多汁，代表的是食物的甘甜
							<br>
							Talk是說話的口語，借代交流的意義
						</p>
						<img src="<%=request.getContextPath()%>/front/member/JuicyTalk/images2/juta.png" width="317" height="256" class="wow fadeInRight"
							data-wow-delay="0.8s" alt="slideimg2">
					</li>
					<li class="slide">
						<h5 class="wow fadeInDown" data-wow-delay="0.8s">最新活動</h5>
						<p class="wow fadeInUp" data-wow-delay="0.8s">通過下載最新的APP可以有機會抽到IPHONE 6S</p>
						<img src="<%=request.getContextPath()%>/front/member/JuicyTalk/images2/hero.png" width="400" height="256" class="wow fadeInRight"
							data-wow-delay="0.8s" alt="slideimg2">
					</li>
				</ul>
			</div>
		</div>
		<!-- End of Slider-->
	</div>
	<!-- end of sliderwrapper-->
	<!--============ Best Dishes ============-->
	<div class="bestdisheswrapper">
		<div id="bestdishes" class="container">
			<h2 class="wow fadeInUp" data-wow-delay="0.3s">合作店家</h2>
			<div class="slider">
				<ul class="slides">

					<c:forEach var="storeVO1" items="${storeSvc.all}" varStatus="s" step="3">
						<li class="slide">
							<c:forEach var="storeVO2" items="${storeSvc.all}" begin="${s.index}" end="${s.index+2}">
								<c:if test="${s.index%3==0}">
									<div class="item">
										<img src="<%=request.getContextPath()%>/store_pic/DBGifReader2.do?store_no=${storeVO2.store_no}" width="226" height="225" alt="sliderimg" class="wow flipInX"
											data-wow-delay=".8s">
										<h3>${storeVO2.store_name}</h3>
									</div>
								</c:if>
								<c:if test="${s.index%3!=0}">
									<!-- end of item-->
									<div class="item${s.count%3}">
										<img src="<%=request.getContextPath()%>/store_pic/DBGifReader2.do?store_no=${storeVO2.store_no}" width="226" height="225" alt="sliderimg" class="wow flipInX"
											data-wow-delay=".8s">
										<h3>${storeVO2.store_name}</h3>
									</div>
								</c:if>
							</c:forEach>
						</li>
					</c:forEach>
				</ul>
			</div>
			<!-- end of slider-->
		</div>
		<!-- end of besth dishes-->
	</div>
	<!-- end of bestdishes wrapper-->
	<!--============ BOOK ONLINE ============-->
	<div class="bookonlinewrapper">
		<div class="container" id="bookonline">
			<h3 class="wow fadeInUp" data-wow-delay="0.3s">店家廣告</h3>
			<div id="adlist">
				<table>
					<tr>
						<td><input type="image" src="images2/ad2.jpg" class="image wow zoomIn">
							<h4>怡人園精緻餐敘館</h4>
							<p>怡人園商號成立於西元1972年，怡人園第一代曾老爺爺、老奶奶用創業基金...</p></td>
						<!-- end of item-->
						<td><input type="image" src="images2/ad3.jpg" class="image wow zoomIn">
							<h4>鯨吞燒 串燒酒場</h4>
							<p>鯨吞燒於2007年創立，喜愛日式料理精神的創辦人，在台北工作十年 後決...</p></td>
						<!-- end of item-->
						<td><input type="image" src="images2/ad5.jpg" class="image wow zoomIn">
							<h4>Move Deluxe 燄</h4>
							<p>【Move Deluxe燄】在精緻、經典與創意之間完美拿捏，屢屢呈現豐...</p></td>
						<!-- end of item-->
						<td><input type="image" src="images2/ad6.jpg" class="image wow zoomIn">
							<h4>新百王客家餐廳</h4>
							<p>於68年成立惟新海霸王餐廳，創店緣由，因外公爵的要開餐廳來腹務更多在地...</p></td>
						<!-- end of item-->
					</tr>
				</table>
			</div>
		</div>
	</div>



<!--============ footer ============-->
<div id="footer" style="width: 100%; height: 455px;">
	<iFrame style="width:100%; height: 455px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/footer.jsp" scrolling="no"> </iFrame>
</div>
<!--============ footer  ============-->

	<script type="text/javascript">
		$('.sliderwrapper .slider').glide({
			autoplay : 7000,
			animationDuration : 3000,
			arrows : true,

		});
	</script>
	<script type="text/javascript">
		$('.bestdisheswrapper .slider').glide({
			autoplay : false,
			animationDuration : 700,
			arrows : true,
			navigation : false,

		});
	</script>
</body>
</html>