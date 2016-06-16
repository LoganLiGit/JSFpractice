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
	<!--start-smoth-scrolling-->
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
	<!--============ Navigation ============-->

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
	<!-- end of headerwrapper-->

	<!--header-top-end-->
	<!--start-header-->
	<div class="header">
		<div class="container">
			<div class="head">


				<div class="navigation">
					<span class="menu"></span>
					<ul class="navig">
						<li><a href="index.jsp">首頁</a></li>
						<li><a href="personal.jsp" class="active">個人頁面</a></li>
						<li><a href="about.jsp">關於我</a></li>
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
	<!--about-starts-->
	<div class="about">
		<div class="container">
			<div class="about-main">
				<div class="col-md-8 about-left">
					<div class="about-one">
						<p>最新食記</p>
						<h3>高雄新興~野饌日式炭火燒肉~</h3>
					</div>
					<div class="about-two">
						<a href="single.jsp"><img src="images2/cm20140109.jpg" alt="" /></a>
						<p>美食家 <a href="about.jsp">肯德基</a> on 10 feb, 2016 <a href="#">留言(2)</a></p>
						<p>由於最近聚會太多 這張野饌口碑券被排到昨天才使用 約了朋友就直接在店門口碰面了 很久很久之前有吃過野饌 如果不是因為口碑券可能也不會再走入 高雄實在太多燒肉店 競爭激烈 通常我都在北高雄家附近吃 今天就來嘗嘗野饌看跟以前有沒有什麼改變.</p>
						<p>除了新鮮現撈海鮮，【野饌日式炭火燒肉】嚴選肉品，提供單點燒肉等級品質，無論是高檔「肋眼厚切牛」、「骰子牛」、「松阪豬」等等皆不惜重本通通吃到飽。為了保持最新鮮肉汁與口感，【野饌日式炭火燒肉】肉品一律現點現切，各類牛羊豬雞樣樣新鮮美味，不怕饕客吃，只怕饕客沒胃裝！【野饌日式炭火燒肉】超人氣「起司牛奶鍋」亦是不可錯過的好滋味，濃濃起司香光聞就讓人食欲大開，放入各類食材吸飽湯底精華與起司香氣，每口都只能用過癮兩字形容。.</p>
						<div class="about-btn">
							<a href="single.jsp">Read More</a>
						</div>
						<ul>
							<li>
								<p>Share : </p>
							</li>
							<li><a href="#"><span class="fb"> </span></a></li>

						</ul>
					</div>
					<div class="about-tre">
						<div class="a-1">
							<div class="col-md-6 abt-left">

								<a href="single.jsp"><img src="images2/ad2.jpg" alt="" /></a>
								<h6>Read More</h6>
								<h3><a href="single.jsp">家蒂諾溫莎花園鐵板燒</a></h3>
								<p>莎堡是外觀看起來非常豪華的建案 而新家蒂諾開在這兒一定也要非常豪氣也行 聽說耗資近8000萬打造 真的是富麗堂皇呢! 一樓一進門就有一棵顏色很美的大聖誕樹.
								</p>
								<label>May 29, 2015</label>
							</div>
							<div class="col-md-6 abt-left">

								<a href="single.jsp"><img src="images2/ad3.jpg" alt="" /></a>
								<h6>Read More</h6>
								<h3><a href="single.jsp">高雄左營~大八大飯店</a></h3>

								<p>坐落於高雄市中心裕誠路與博愛路交叉路口，擁有優越的地理位置與各式精緻美食，更增加了多元菜式，以最高規格的環境與服務，讓每一位顧客都能感到賓至如歸。.</p>
								<label>May 29, 2015</label>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="a-1">
							<div class="col-md-6 abt-left">

								<a href="single.jsp"><img src="images2/ad5.jpg" alt="" /></a>
								<h6>Read More</h6>
								<h3><a href="single.jsp">全鮨壽司</a></h3>
								<p>但林立於此的攤位加上實體店面少說也五十家吧!或許還不只,舉凡說~鹽酥雞~炭烤~滷味~雞排~蚵仔煎!比比皆是!但隱身其中的一家日本味十足的壽司店引人注目!不看他幾眼的好像對不起自己似的.</p>
								<label>May 29, 2015</label>
							</div>
							<div class="col-md-6 abt-left">

								<a href="single.jsp"><img src="images2/ad1.jpg" alt="" /></a>
								<h6>Read More</h6>
								<h3><a href="single.jsp">Tasty Coffee</a></h3>
								<p>今天來到了位於後火車站與高醫附近的美食 戰區吉林街夜市上!(熱河街也算是戰區之一) 整條放眼望去眼看過去滿處都是小吃店家， 這裡也是學生時期常來的地方!好多店家都有 這當時的懷念記憶.</p>
								<label>May 29, 2015</label>
							</div>
							<div class="clearfix"></div>
						</div>

					</div>
				</div>
				<div class="col-md-4 about-right heading">
					<div class="abt-1">
						<h3>關於我</h3>
						<div class="abt-one">
							<img src="images2/KFC.jpg" alt="" />
							<p>肯德基是專賣炸雞的快餐連鎖店，總部設於美國肯塔基州路易維爾市。是全球第二大的餐飲連鎖企業.</p>
							<div class="a-btn">
								<a href="about.jsp">關於更多</a></br>
								<a href="">加入關注</a></br>
							</div>
						</div>
					</div>
					<div class="abt-2">
						<h3>你可能也喜歡的食記</h3>
						<div class="might-grid">
							<div class="grid-might">
								<a href="single.jsp"><img src="images2/ma.jpg" class="img-responsive" alt=""> </a>
							</div>
							<div class="might-top">
								<h4><a href="single.jsp">龍蝦料理三爭霸</a></h4>
								<p>今天的晚餐我特別找了可以看夜景 還可以有現場演唱的漢來飯店龍蝦酒殿 看看桌上的玫瑰花跟燭光是不是超有氣氛的呢.</p>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="might-grid">
							<div class="grid-might">
								<a href="single.jsp"><img src="images2/right1.jpg" class="img-responsive" alt=""> </a>
							</div>
							<div class="might-top">
								<h4><a href="single.jsp">飛斯上菜現撈海膽</a></h4>
								<p>除了新鮮現撈海鮮，【野饌日式炭火燒肉】嚴選肉品，提供單點燒肉等級品質,等等皆不惜重本通通吃到飽.</p>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="might-grid">
							<div class="grid-might">
								<a href="single.jsp"><img src="images2/left1.jpg" class="img-responsive" alt=""> </a>
							</div>
							<div class="might-top">
								<h4><a href="single.jsp">賞海景，嗑龍蝦。龍蝦酒殿</a></h4>
								<p>桌上的紅玫瑰紅的看似燦爛，卻其實一點也不美麗， 花瓣邊緣已近枯萎，比起這般細節，新國際西餐廳比這更用心!!.
								</p>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
					<div class="abt-2">
						<h3>最新收藏店家</h3>
						<div class="might-grid">
							<div class="grid-might">
								<a href="single.jsp"><img src="images2/ma.jpg" class="img-responsive" alt=""> </a>
							</div>
							<div class="might-top">
								<h4><a href="">龍蝦料理三爭霸之一</a></h4>
								<p>地區：高雄市</p>
								<p>分類：海鮮料理</p>
							</div>
							<div class="clearfix"></div>

						</div>
						<div class="might-grid">
							<div class="grid-might">
								<a href="single.jsp"><img src="images2/ma.jpg" class="img-responsive" alt=""> </a>
							</div>
							<div class="might-top">
								<h4><a href="">龍蝦料理三爭霸之三</a></h4>
								<p>地區：台北市</p>
								<p>分類：海鮮料理</p>
							</div>
							<div class="clearfix"></div>

						</div>
						<div class="might-grid">
							<div class="grid-might">
								<a href=""><img src="images2/ma.jpg" class="img-responsive" alt=""> </a>
							</div>
							<div class="might-top">
								<h4><a href="single.jsp">龍蝦料理三爭霸之二</a></h4>
								<p>地區：台南市</p>
								<p>分類：海鮮料理</p>
							</div>
							<div class="clearfix"></div>

						</div>
					</div>
					<div class="abt-2">
						<h3>誰來關注你</h3>
						<div class="flower">
							<a href=""><img src="images2/t-4.jpg" class="img-flower" alt=""> </a>
							<a href=""><img src="images2/t-2.jpg" class="img-flower" alt=""> </a>
							<a href=""><img src="images2/t-3.jpg" class="img-flower" alt=""> </a>
						</div>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--about-end-->
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
				<p></p>
			</div>
		</div>
	</div>
	<!--footer-end-->
</body>

</html>