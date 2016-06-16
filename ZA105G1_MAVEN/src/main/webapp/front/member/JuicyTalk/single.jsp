<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>高雄新興~野饌日式炭火燒肉~</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Coffee Break Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="css/personalStyle.css" rel='stylesheet' type='text/css' />
<script src="js/jquery.min.js"></script>
<!---- start-smoth-scrolling---->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event){		
					event.preventDefault();
					$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
				});
			});
		</script>
		<script type="text/javascript">
			$(function(){
				$("#login").click(function(){
					$("#login_from").submit();
				}
				)
			});
		</script>
<!--start-smoth-scrolling-->
</head>
<body>
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

	<!--start-single-->
	<div class="single">
		<div class="container">
				<div class="single-top">
						
					<div class=" single-grid">
						<h4>高雄新興~野饌日式炭火燒肉~</h4>				
							<ul class="blog-ic">
								<li><a href="about.jsp"><span> <i  class="glyphicon glyphicon-user"> </i>肯德基</span> </a> </li>
		  						 <li><span><i class="glyphicon glyphicon-time"> </i>10 feb, 2016</span></li>		  						 	
		  						 <li><span><i class="glyphicon glyphicon-eye-open"> </i>點擊數:15</span></li>
		  					</ul>	
<a href="#"><img class="img-responsive" src="images2/cm20140109.jpg" alt=" "></a>	  						
						<p>由於最近聚會太多 這張野饌口碑券被排到昨天才使用 約了朋友就直接在店門口碰面了 很久很久之前有吃過野饌 如果不是因為口碑券可能也不會再走入 高雄實在太多燒肉店 競爭激烈 通常我都在北高雄家附近吃 今天就來嘗嘗野饌看跟以前有沒有什麼改變.</p>
						<p>除了新鮮現撈海鮮，【野饌日式炭火燒肉】嚴選肉品，提供單點燒肉等級品質，無論是高檔「肋眼厚切牛」、「骰子牛」、「松阪豬」等等皆不惜重本通通吃到飽。為了保持最新鮮肉汁與口感，【野饌日式炭火燒肉】肉品一律現點現切，各類牛羊豬雞樣樣新鮮美味，不怕饕客吃，只怕饕客沒胃裝！【野饌日式炭火燒肉】超人氣「起司牛奶鍋」亦是不可錯過的好滋味，濃濃起司香光聞就讓人食欲大開，放入各類食材吸飽湯底精華與起司香氣，每口都只能用過癮兩字形容。.</p>
						<p>由於最近聚會太多 這張野饌口碑券被排到昨天才使用 約了朋友就直接在店門口碰面了 很久很久之前有吃過野饌 如果不是因為口碑券可能也不會再走入 高雄實在太多燒肉店 競爭激烈 通常我都在北高雄家附近吃 今天就來嘗嘗野饌看跟以前有沒有什麼改變.</p>
						<a href="single.jsp"><img src="images2/ad3.jpg" alt="" /></a>
						<p>除了新鮮現撈海鮮，【野饌日式炭火燒肉】嚴選肉品，提供單點燒肉等級品質，無論是高檔「肋眼厚切牛」、「骰子牛」、「松阪豬」等等皆不惜重本通通吃到飽。為了保持最新鮮肉汁與口感，【野饌日式炭火燒肉】肉品一律現點現切，各類牛羊豬雞樣樣新鮮美味，不怕饕客吃，只怕饕客沒胃裝！【野饌日式炭火燒肉】超人氣「起司牛奶鍋」亦是不可錯過的好滋味，濃濃起司香光聞就讓人食欲大開，放入各類食材吸飽湯底精華與起司香氣，每口都只能用過癮兩字形容。.</p>
<p>由於最近聚會太多 這張野饌口碑券被排到昨天才使用 約了朋友就直接在店門口碰面了 很久很久之前有吃過野饌 如果不是因為口碑券可能也不會再走入 高雄實在太多燒肉店 競爭激烈 通常我都在北高雄家附近吃 今天就來嘗嘗野饌看跟以前有沒有什麼改變.</p>
						<p>除了新鮮現撈海鮮，【野饌日式炭火燒肉】嚴選肉品，提供單點燒肉等級品質，無論是高檔「肋眼厚切牛」、「骰子牛」、「松阪豬」等等皆不惜重本通通吃到飽。為了保持最新鮮肉汁與口感，【野饌日式炭火燒肉】肉品一律現點現切，各類牛羊豬雞樣樣新鮮美味，不怕饕客吃，只怕饕客沒胃裝！【野饌日式炭火燒肉】超人氣「起司牛奶鍋」亦是不可錯過的好滋味，濃濃起司香光聞就讓人食欲大開，放入各類食材吸飽湯底精華與起司香氣，每口都只能用過癮兩字形容。.</p>
						<p>由於最近聚會太多 這張野饌口碑券被排到昨天才使用 約了朋友就直接在店門口碰面了 很久很久之前有吃過野饌 如果不是因為口碑券可能也不會再走入 高雄實在太多燒肉店 競爭激烈 通常我都在北高雄家附近吃 今天就來嘗嘗野饌看跟以前有沒有什麼改變.</p>
						<p>除了新鮮現撈海鮮，【野饌日式炭火燒肉】嚴選肉品，提供單點燒肉等級品質，無論是高檔「肋眼厚切牛」、「骰子牛」、「松阪豬」等等皆不惜重本通通吃到飽。為了保持最新鮮肉汁與口感，【野饌日式炭火燒肉】肉品一律現點現切，各類牛羊豬雞樣樣新鮮美味，不怕饕客吃，只怕饕客沒胃裝！【野饌日式炭火燒肉】超人氣「起司牛奶鍋」亦是不可錯過的好滋味，濃濃起司香光聞就讓人食欲大開，放入各類食材吸飽湯底精華與起司香氣，每口都只能用過癮兩字形容。.</p>
					</div>
					<div class="comments heading">
						<h3>留言</h3>
						<div class="media">
					      	<div class="media-body">
						        <h4 class="media-heading">李曉華</h4>
						        <p>有一天，郝棒棒,郝美麗,郝帥氣三人去游泳，結果三人都溺水了，但是只有郝帥氣會游泳，先把郝美麗救了起來，然而救上岸的郝美麗就對郝帥氣說：.</p>
					      	</div>
					      <div class="media-right">
					        <a href="#">
								<img src="images/t-2.jpg" alt="" width=200px> </a>
					      </div>
					 </div>
					  <div class="media">
					      <div class="media-left">
					        <a href="#">
					        	<img src="images/t-2.jpg" alt="" width=200px>
					        </a>
					      </div>
					      <div class="media-body">
					        <h4 class="media-heading">李曉華</h4>
					        <p>阿不救郝棒棒.</p>
					      </div>
					    </div>
    				</div>
    				<div class="comment-bottom heading">
    					<h3>我要留言</h3>
    					<form>	
						<input type="text" value="Name" onfocus="this.value='';" onblur="if (this.value == '') {this.value ='Name';}">
						<input type="text" value="Subject" onfocus="this.value='';" onblur="if (this.value == '') {this.value ='Subject';}">
						<textarea cols="77" rows="6" value=" " onfocus="this.value='';" onblur="if (this.value == '') {this.value = 'Message';}">Message</textarea>
							<input type="submit" value="Send">
					</form>
    				</div>
				</div>	
			</div>					
	</div>
	<!--end-single-->
	<!--footer-starts-->
	<div class="footer">
		<div class="container">
			<div class="footer-text">
				<p> </p>
			</div>
		</div>
	</div>
	<!--footer-end-->
</body>
</html>