<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
	<title>肯德基的個人頁面</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="Coffee Break Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
	<script type="application/x-javascript">
		addEventListener("load", function () {
			setTimeout(hideURLbar, 0);
		}, false);

		function hideURLbar() {
			window.scrollTo(0, 1);
		}
	</script>
	<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
	<link href="css/personalStyle.css" rel='stylesheet' type='text/css' />
	<script src="js/jquery.min.js"></script>
	<!---- start-smoth-scrolling---->
	<script type="text/javascript" src="js/move-top.js"></script>
	<script type="text/javascript" src="js/easing.js"></script>
	<script type="text/javascript">
		jQuery(document).ready(function ($) {
			$(".scroll").click(function (event) {
				event.preventDefault();
				$('html,body').animate({
					scrollTop: $(this.hash).offset().top
				}, 1000);
			});
		});
	</script>

	<script src="js/modernizr.custom.97074.js"></script>
	<script src="js/jquery.chocolat.js"></script>
	<link rel="stylesheet" href="css/chocolat.css" type="text/css" media="screen" charset="utf-8">
	<!--light-box-files -->
	<script type="text/javascript" charset="utf-8">
		$(function () {
			$('.gallery a').Chocolat();
		});
	</script>
	<script type="text/javascript" src="js/jquery.hoverdir.js"></script>
	<script type="text/javascript">
	$(function(){
		$("#login").click(function(){
			$("#login_from").submit();
		}
		)
	});
	</script>
</head>

<body>
	<!--header-top-starts-->
	<div id="slider" class="container">
		<div class="login">
		
		
<!--********登入登出動態*********** -->
		 <c:choose>
		    <c:when test="${ memberVO.mem_name!=null }">
				<a href="<%=request.getContextPath()%>/front/member/update/update.jsp"><button width="20" height="20" >${memberVO.mem_name}你好</button> </a> 
				<a href="<%=request.getContextPath()%>/front/member/logout/logout.jsp"><button width="20" height="20" >登出</button> </a>
		    </c:when>
		    <c:otherwise>
				<a id="login" ><button width="20" height="20" >登入會員</button></a>
		        <a href="<%=request.getContextPath()%>/front/member/register/register.jsp"><button width="20" height="20" >註冊會員</button> </a>
		    </c:otherwise>
		  </c:choose>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/member/login/login.jsp" id="login_from" >
		<input type="hidden" name="url" value="<%=request.getServletPath()%>">
	</FORM>
<!--********登入登出動態*********** -->

		</div>
	</div>
	<div class="headerwrapper">

		<div id="header" class="container">

			<div class="logo">
				<a href="#"><img src="images2/logo2.jpg" alt="logo" width="90" height="90"></a>
			</div>
			<!--end of Logo-->
			<nav>
				 <ul id="navigations">
                <li><a href="index.jsp">HOME</a></li>
                <li><a href="test.jsp">test.jsp</a></li>
                <li><a href="test2.jsp">test2.jsp</a></li>
                <li><a href="personal.jsp">個人頁面</a></li>
                <li><a href="#contactus">團購券商城</a></li>
            </ul>
			</nav>
		</div>
		<!--end of header-->
	</div>
	<!--header-top-end-->
	<!--start-header-->
	<div class="header">
		<div class="container">
			<div class="head">


				<div class="navigation">
					<span class="menu"></span>
					<ul class="navig">
						<li><a href="index.jsp">首頁</a></li>
						<li><a href="personal.jsp">個人頁面</a></li>
						<li><a href="about.jsp" class="active">關於我</a></li>
						<li><a href="">我的食記</a></li>
						<li><a href="gallery.jsp">我的相簿</a></li>

					</ul>
				</div>


				<div class="header-right">
					<div class="search-bar">
						<input type="text" value="Search" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search';}">
						<input type="submit" value="">
					</div>
					<ul>
						<li><a href="#"><span class="fb"> </span></a></li>
					</ul>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!-- script-for-menu -->
	<!-- script-for-menu -->
	<script>
		$("span.menu").click(function () {
			$(" ul.navig").slideToggle("slow", function () {});
		});
	</script>
	<!-- script-for-menu -->
	<!--banner-starts-->
	<div class="banner">
		<div class="container">
			<div class="banner-top">

			</div>
		</div>
	</div>
	<!--banner-end-->
	<!--welcome-starts-->
	<div class="welcome">
		<div class="container">
			<div class="welcome-top heading">
				<h3>歡迎大家</h3>
				<div class="welcome-bottom">
					<img src="images2/KFC2.jpg" alt="" />
					<p>肯德基是專賣炸雞的快餐連鎖店，總部設於美國肯塔基州路易維爾市。是全球第二大的餐飲連鎖企業，僅次於麥當勞，截至2013年12月，肯德基在118個國家和地區擁有1萬8000個分店。 肯德基由哈蘭德·桑德斯創辦，桑德斯在大蕭條時期開始在位於肯塔基州科爾賓市的路邊餐廳賣炸雞。桑德斯了解到餐廳特許經營的概念的潛力，1952年，第一家「肯德基炸雞」的特許經營餐廳在猶他州開門營業，與漢堡在市場上競爭。桑德斯以「桑德斯上校」的名字聞名，成為美國文化歷史的著名人物，肯德基廣泛以桑德斯作為廣告形象。1964年，他將公司賣給一批投資者，主要有約翰·布朗和傑克·梅西。 肯德基是第一家向國際擴張的連鎖餐飲業之一，1960年代中旬，肯德基於英國、墨西哥及牙買開設分店。70年代至80年代期間，肯德基國內外業績喜憂參半，這是因為肯德基企業所有權交換給別的企業，而這些企業對餐廳管理沒有經驗。1970年代初，肯德基賣給了烈酒經銷公司休伯萊恩，由R. J.雷諾茲菸草公司接管，後來又將肯德基轉賣給百事公司。1987年在中國開設的第一家西式餐飲連鎖店。肯德基自此在中國迅速擴張，如今中國是肯德基利潤最高的市場。百事公司分拆餐廳部門，立泰康全球餐飲公司，後改名為百勝餐飲集團。 肯德基的產品是炸雞塊，以桑德斯的11種草藥和香料的配方調味,配方的成分是商業機密。自1990年代初以來，肯德基不斷擴展菜單，提供其他雞肉產品如雞柳包，沙拉,薯條、涼拌捲心菜，甜品和飲料，飲料由百事公司供應。.
					</p>
				</div>
			</div>
		</div>
	</div>
	<!--welcome-end-->
	<!--	個人資料-->
	<div class="team">
		<div class="container">
			<div class="team-top heading">
				<h3>個人資料</h3>
				 
				
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	</div>


	<!--team-starts-->
	<div class="team">
		<div class="container">
			<div class="team-top heading">
				<h3>我的好友</h3>
			</div>
			<div class="team-bottom">
				<div class="col-md-3 team-left">
					<img src="images/t-2.jpg" alt="" />
					<h4>李曉華</h4>
				</div>
				<div class="col-md-3 team-left">
					<img src="images/t-2.jpg" alt="" />
					<h4>李曉華</h4>
				</div>
				<div class="col-md-3 team-left">
					<img src="images/t-2.jpg" alt="" />
					<h4>李曉華</h4>
				</div>
				<div class="col-md-3 team-left">
					<img src="images/t-2.jpg" alt="" />
					<h4>李曉華</h4>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--team-end-->
	<!--slide-starts-->
	<div class="slide">
		<div class="container">
			<div class="fle-xsel">
				<ul id="flexiselDemo3">
					<li>
						<a href="#">
							<div class="banner-1">
								<img src="images2/01.jpg" class="img-responsive" alt="">
							</div>
						</a>
					</li>
					<li>
						<a href="#">
							<div class="banner-1">
								<img src="images2/02.jpg" class="img-responsive" alt="">
							</div>
						</a>
					</li>
					<li>
						<a href="#">
							<div class="banner-1">
								<img src="images2/ad1.jpg" class="img-responsive" alt="">
							</div>
						</a>
					</li>
					<li>
						<a href="#">
							<div class="banner-1">
								<img src="images2/ad2.jpg" class="img-responsive" alt="">
							</div>
						</a>
					</li>
					<li>
						<a href="#">
							<div class="banner-1">
								<img src="images2/ad3.jpg" class="img-responsive" alt="">
							</div>
						</a>
					</li>
					<li>
						<a href="#">
							<div class="banner-1">
								<img src="images2/ad5.jpg" class="img-responsive" alt="">
							</div>
						</a>
					</li>
				</ul>

				<script type="text/javascript">
					$(window).load(function () {

						$("#flexiselDemo3").flexisel({
							visibleItems: 5,
							animationSpeed: 1000,
							autoPlay: true,
							autoPlaySpeed: 3000,
							pauseOnHover: true,
							enableResponsiveBreakpoints: true,
							responsiveBreakpoints: {
								portrait: {
									changePoint: 480,
									visibleItems: 2
								},
								landscape: {
									changePoint: 640,
									visibleItems: 3
								},
								tablet: {
									changePoint: 768,
									visibleItems: 3
								}
							}
						});

					});
				</script>
				<script type="text/javascript" src="js/jquery.flexisel.js"></script>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
	<!--slide-end-->
	<!--footer-starts-->
	<div class="footer">
		<div class="container">
			<div class="footer-text">
				
			</div>
		</div>
	</div>
	<!--footer-end-->
</body>

</html>