<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.reply.model.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.store.model.*"%>

<jsp:useBean id="articleSvc" scope="page" class="com.article.model.ArticleService" />
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="replySvc" scope="page" class="com.reply.model.ReplyService" />
<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />

<%
	ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
	ReplyVO replyVO = (ReplyVO) request.getAttribute("replyVO");
	StoreVO storeVO = (StoreVO) request.getAttribute("storeVO");
	
%>

<html>
<head>
<title>修改一篇食記~</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Coffee Break Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<!-- 連結關於javascript的程式區 開始 -->
<script type="application/x-javascript">

		addEventListener("load", function () {
			setTimeout(hideURLbar, 0);
		}, false);

		function hideURLbar() {
			window.scrollTo(0, 1);
		}
		
</script>

<script src="<%=request.getContextPath()%>/front/personal/js/jquery.min.js"></script>

<!---- start-smoth-scrolling---->
<script type="text/javascript" src="<%=request.getContextPath()%>/front/personal/js/move-top.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/front/personal/js/easing.js"></script>
<script src="<%=request.getContextPath()%>/front/personal/js/facebookchatboxUI.js"></script>
<script src="<%=request.getContextPath()%>/front/personal/js/bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/front/personal/js/magicsuggest-min.js"></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
<script src="<%=request.getContextPath()%>/front/personal/js/jquery.raty.js" type="text/javascript"></script>

<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1000);
		});
	});
</script>

<script type="text/javascript"><!-- 搜尋使用者bar -->
$(function() {
    var availableTags = [
<c:forEach var="memberVO" items="${memberSvc.all}">
"${memberVO.mem_name}",
</c:forEach>
"end"];
    $( "#tags" ).autocomplete({
      source: availableTags
    });
  });
</script>

<script type="text/javascript"><!-- 搜尋店家名稱bar -->
$(function() {
    var availableTags = [
<c:forEach var="storeVO" items="${storeSvc.all}">
"${storeVO.store_name}",
</c:forEach>
"end"];
    $( "#findStore" ).autocomplete({
      source: availableTags
    });
  });
</script>

 <script><!-- 今日朋友 -->
 $(document).ready(//  $(function(){......})
 	 function test(){
 	   $("#randomfriend").click(function (){
 		  
 		   $.ajax({
 				 type:"post",
 				 url:"<%=request.getContextPath()%>/friend/friend.do",
 				 data:{action:"getTodayFriend", mem_no:$('#mem_no').val()},
 				 dataType:"json",
 				 success:function (data){
 					alert('successsssssssssssssssssssssssssssssssssss');
 					 drawTable(data);
 			     },
 	             error:function(){alert("error")}
              })
 	   });
 	   
 	  $("#result").hide();
 	  $("#star").raty({
 		 path:"<%=request.getContextPath()%>/front/personal/images",
 		 hints: ['1', '2', '3', '4', '5'],
 		 starOff: 'star-off-big.png',
         starOn: 'star-on-big.png',
         size:24,
         target: '#result',
         targetKeep : true
 	  });
 	  
 	 var text = $('#result').text();
 	
        $('img').click(function () {
             if ($('#result').text() != text) {
            	 $('#raty').val($('#result').text());
                 return;
             }
        });
 })
 

function drawTable(oJson){

	 var num = oJson.length;

	 dindin = oJson[i]["mem_no"];
	 dindin = oJson[i]["mem_name"];
	 dindin = oJson[i]["mem_nickname"];
	 dindin = oJson[i]["mem_birthday"];
	 dindin = oJson[i]["mem_skill"];
	 dindin = oJson[i]["mem_hobby"];
	 
	 
	 
	 
	}

</script>

<script><!--評分星星 -->
$(".starbox").starbox({
    average: 0.5,
    changeable: 'once',
    autoUpdateAverage: true,
    ghosting: true
});
</script>



<!-- 連結關於javascript的程式區 結束 -->


<!-- 連結關於css的程式區 開始 -->

<link href="<%=request.getContextPath()%>/front/personal/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="<%=request.getContextPath()%>/front/personal/css/personalStyle.css" rel='stylesheet' type='text/css' />
<link href="<%=request.getContextPath()%>/front/personal/css/magicsuggest-min.css"/>
<link rel="stylesheet"href="<%=request.getContextPath()%>/front/personal/css/facebookchatboxUI.css"/>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link href="<%=request.getContextPath()%>/front/personal/css/jstarbox.css"/>

<!-- 連結關於css的程式區 結束 -->



<!--start-smoth-scrolling-->
</head>
<body>
<div id="header" style="width:100%; height: 225px;">
	<iFrame style="width:100%; height: 225px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/Header.jsp" scrolling="no"> </iFrame>
</div>
		
		<div class="container" style="padding:20px;">
			<div class="head">
<!-- 上方副功能導覽列 -->			
				<div class="navigation" style="font-weight:bold;">
					<span class="menu"></span>
					<ul class="navig">
						
						
						<li style="border-right:solid 5px;">
<%-- 						<li><form METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do"> --%>
<!-- 								<input type="submit" value="關於我" >  -->
<%-- 								<input type="hidden" name="mem_no" value="${memberVO2.mem_no}">  --%>
<!-- 								<input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 							</form> -->
							<a href="../member/member.do?action=getOne_For_Display&mem_no=${memberVO2.mem_no}"
								style="font-size:36px; font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;">關於我</a>
						</li>
						<li style="border-right:solid 5px;">
<%-- 							<form METHOD="post" ACTION="<%=request.getContextPath()%>/personal/personal.do"> --%>
<!-- 								<input type="submit" value="所有食記" class="btn-info btn-lg">  -->
<%-- 								<input type="hidden" name="mem_no" value="${memberVO2.mem_no}">  --%>
<!-- 								<input type="hidden" name="action" value="get_SomeOne_All_Article">	 -->
<!-- 							</form> -->
							<a href="../personal/personal.do?action=get_SomeOne_All_Article&mem_no=${memberVO2.mem_no}"
							style="font-size:36px;font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;">所有食記</a>
						</li>
						<li style="border-right:solid 5px;">
<%-- 							<form METHOD="post" ACTION="<%=request.getContextPath()%>/personal/personal.do"> --%>
<!-- 								<input type="submit" value="相簿" class="btn-info btn-lg">  -->
<%-- 								<input type="hidden" name="mem_no" value="${memberVO2.mem_no}">  --%>
<!-- 								<input type="hidden" name="action" value="get_Album"> -->
<!-- 							</form> -->
							<a href="../personal/personal.do?action=get_Album&mem_no=${memberVO2.mem_no}"
							style="font-size:36px;font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;">相簿</a>
						</li>
						<c:if test="${memberVO.mem_no==memberVO2.mem_no}">
						<li style="border-right:solid 5px;">
<%-- 							<form METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do"> --%>
<!-- 								<input type="submit" value="發表食記" class="btn-info btn-lg"> -->
<!-- 								<input type="hidden" name="action" value="myArticle"> -->
<!-- 							</form> -->
							<a href="../article/article.do?action=myArticle"
							style="font-size:36px;font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;">發表食記</a>
						</li>
						</c:if>
						<li style="border-right:solid 5px;">
<%-- 							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/friend/friend.do"> --%>
<!-- 								<input type="submit" value="我的朋友" class="btn-info btn-lg"> -->
<%-- 								<input type="hidden" name="mem_no" value="${memberVO2.mem_no}" > --%>
<!-- 								<input type="hidden" name="action" value="getSome_For_Display"> -->
<!-- 							</FORM> -->
							<a href="../friend/friend.do?action=getSome_For_Display&mem_no=${memberVO2.mem_no}"
							style="font-size:36px;font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;">我的朋友</a>
						</li>

					</ul>
				</div>

<!-- 搜尋使用者bar -->
				<div class="header-right">
					<div class="search-bar">		
						 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/personal/personal.do">
							<input type="submit" value="">
							<input id="tags"  name="mem_name" value="Search" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search';}">
							<input type="hidden" name="login_mem_no" value="${memberVO.mem_no}">						
							<input type="hidden" name="action" value="getPersonal_Display">
						</FORM> 
					</div>
				</div>
			</div>
		</div>
	<!-- script-for-menu -->

	<script>
		$("span.menu").click(function() {
			$(" ul.navig").slideToggle("slow", function() {
			});
		});
	</script>
	<!-- script-for-menu -->

	<!--start-single-->
	<div class="starbox">
		
		</div>
	<div class="single">
		<div class="container">
			<div class="single-top">

				<div class="comment-bottom heading">

		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do">

		<input type="hidden" name="article_no" size="45" value="<%= (articleVO==null)? "1" : articleVO.getArticle_no()%>" />
		
		<input type="hidden" name="article_status" size="45" value="<%= (articleVO==null)? "1" : articleVO.getArticle_status()%>" />
		
		<input type="hidden" name="store_no" size="45" value="<%= (articleVO==null)? "1" : articleVO.getStore_no()%>" />

		
		食記評分:<input type="hidden" id="raty" name="article_score" size="45" value="<%= (articleVO==null)? "0" : articleVO.getArticle_score()%>" />
		<div id="star"></div>
		<div id="result">
		</div>
		
		<input type="hidden" name="article_create" size="45" value="${articleVO.article_create}" readonly/>

		店家名稱:<input type="TEXT" id="findStore"  name="store_name" size="45" value="${articleVO.store_name}" readonly style="font-size:22px; font-family: Microsoft YaHei; color:black;"/ >
	
		
		<input type="hidden" name="mem_no" size="45" value="${memberVO.mem_no}" readonly/>
		
		<input type="hidden" name="article_click" size="45" value="<%= (articleVO==null)? "0" : articleVO.getArticle_click()%>" />
		
		<input type="hidden" name="article_replies" size="45" value="<%= (articleVO==null)? "0" : articleVO.getArticle_replies()%>" />
		
		食記標題:<input type="TEXT" name="article_title" size="45" value="<%= (articleVO==null)? "" : articleVO.getArticle_title()%>" style="font-size:22px; font-family: Microsoft YaHei; color:black;"/>
		
		食記內容:<textarea class="ckeditor" id="content" name="article_content"><%= articleVO.getArticle_content()%></textarea>

<br>
<input type="hidden" name="action" value="update">
<input type="submit" value="完成修改"></FORM>
				</div>

			</div>
		</div>
	</div>
	<div class="slide">
		<div class="container">
			<div class="fle-xsel">
				<ul id="flexiselDemo3">
					<li><a href="#">
							<div class="banner-1">
								<img src="<%=request.getContextPath()%>/front/personal/images2/01.jpg" class="img-responsive" alt="">
							</div>
					</a></li>
					<li><a href="#">
							<div class="banner-1">
								<img src="<%=request.getContextPath()%>/front/personal/images2/02.jpg" class="img-responsive" alt="">
							</div>
					</a></li>
					<li><a href="#">
							<div class="banner-1">
								<img src="<%=request.getContextPath()%>/front/personal/images2/ad1.jpg" class="img-responsive" alt="">
							</div>
					</a></li>
					<li><a href="#">
							<div class="banner-1">
								<img src="<%=request.getContextPath()%>/front/personal/images2/ad2.jpg" class="img-responsive" alt="">
							</div>
					</a></li>
					<li><a href="#">
							<div class="banner-1">
								<img src="<%=request.getContextPath()%>/front/personal/images2/ad3.jpg" class="img-responsive" alt="">
							</div>
					</a></li>
					<li><a href="#">
							<div class="banner-1">
								<img src="<%=request.getContextPath()%>/front/personal/images2/ad5.jpg" class="img-responsive" alt="">
							</div>
					</a></li>
				</ul>

				<script type="text/javascript">
					$(window).load(function() {

						$("#flexiselDemo3").flexisel({
							visibleItems : 5,
							animationSpeed : 1000,
							autoPlay : true,
							autoPlaySpeed : 3000,
							pauseOnHover : true,
							enableResponsiveBreakpoints : true,
							responsiveBreakpoints : {
								portrait : {
									changePoint : 480,
									visibleItems : 2
								},
								landscape : {
									changePoint : 640,
									visibleItems : 3
								},
								tablet : {
									changePoint : 768,
									visibleItems : 3
								}
							}
						});

					});
				</script>
				<script type="text/javascript" src="<%=request.getContextPath()%>/front/personal/js/jquery.flexisel.js"></script>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--end-single-->
<!--============ footer ============-->
<div id="footer" style="width: 100%; height: 455px;">
	<iFrame style="width:100%; height: 455px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/footer.jsp" scrolling="no"> </iFrame>
</div>
<!--============ footer  ============-->
</html>