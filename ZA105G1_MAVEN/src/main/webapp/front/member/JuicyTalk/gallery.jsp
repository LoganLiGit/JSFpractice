<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
	<title>肯德基的個人相簿</title>
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
	<!--start-smoth-scrolling-->
	<!--script-->
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
                <li> <a href="#slider">美食社群</a></li>
                <li><a href="#map">揪團</a></li>
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
						<li><a href="about.jsp">關於我</a></li>
						<li><a href="">我的食記</a></li>
						<li><a href="gallery.jsp" class="active">我的相簿</a></li>

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
	<!--gallery-starts-->
	<div class="gallery">
		<div class="container">
			<div class="gallery-top heading">
				<h3>我的相簿</h3>
			</div>
			<section>
				<ul id="da-thumbs" class="da-thumbs">
					<li>
						<a href="images2/ad1.jpg" rel="title" class="b-link-stripe b-animate-go  thickbox">
							<img src="images2/ad1.jpg" alt="" />
							<div>
								<h5>圖片標題1</h5>
								<span>圖片描述1</span>
							</div>
						</a>
					</li>
					<li>
						<a href="images2/ad2.jpg" rel="title" class="b-link-stripe b-animate-go  thickbox">
							<img src="images2/ad2.jpg" alt="" />
							<div>
								<h5>圖片標題2</h5>
								<span>圖片描述2</span>
							</div>
						</a>
					</li>
					<li>
						<a href="images2/ad3.jpg" rel="title" class="b-link-stripe b-animate-go  thickbox">
							<img src="images2/ad3.jpg" alt="" />
							<div>
								<h5>圖片標題3</h5>
								<span>圖片描述3</span>
							</div>
						</a>
					</li>
					<li>
						<a href="images2/ad5.jpg" rel="title" class="b-link-stripe b-animate-go  thickbox">
							<img src="images2/ad5.jpg" alt="" />
							<div>
								<h5>圖片標題4</h5>
								<span>圖片描述4</span>
							</div>
						</a>
					</li>
					<li>
						<a href="images2/ad6.jpg" rel="title" class="b-link-stripe b-animate-go  thickbox">
							<img src="images2/ad6.jpg" alt="" />
							<div>
								<h5>圖片標題5</h5>
								<span>圖片描述5</span>
							</div>
						</a>
					</li>
					<li>
						<a href="images2/01.jpg" rel="title" class="b-link-stripe b-animate-go  thickbox">
							<img src="images2/01.jpg" alt="" />
							<div>
								<h5>圖片標題6</h5>
								<span>圖片描述6</span>
							</div>
						</a>
					</li>
					<li>
						<a href="images2/02.jpg" rel="title" class="b-link-stripe b-animate-go  thickbox">
							<img src="images2/02.jpg" alt="" />
							<div>
								<h5>圖片標題7</h5>
								<span>圖片描述7</span>
							</div>
						</a>
					</li>
					<li>
						<a href="images2/03.jpg" rel="title" class="b-link-stripe b-animate-go  thickbox">
							<img src="images2/03.jpg" alt="" />
							<div>
								<h5>圖片標題8</h5>
								<span>圖片描述8</span>
							</div>
						</a>
					</li>
					<li>
						<a href="images2/04.jpg" rel="title" class="b-link-stripe b-animate-go  thickbox">
							<img src="images2/04.jpg" alt="" />
							<div>
								<h5>圖片標題9</h5>
								<span>圖片描述9</span>
							</div>
						</a>
					</li>
					<div class="clearfix"> </div>
				</ul>
			</section>

			<script type="text/javascript">
				$(function () {

					$(' #da-thumbs > li ').each(function () {
						$(this).hoverdir();
					});

				});
			</script>
		</div>
	</div>
	<!--gallery-end-->
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