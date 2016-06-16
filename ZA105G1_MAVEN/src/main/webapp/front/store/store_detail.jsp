<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.store.pic.model.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	// 從揪團來的
	String store_no =request.getParameter("store_no");
	Boolean from = false;
	if(store_no!=null){
		StoreService storeSvc = new StoreService();
		Set<Store_picVO> store_picVO1 = (Set<Store_picVO>) storeSvc.getStore_picsByStore_no(Integer.parseInt(store_no));
	
		StoreVO storeVO = storeSvc.getOneStore(Integer.parseInt(store_no));
		pageContext.setAttribute("storeVO", storeVO);
		pageContext.setAttribute("store_picVO1", store_picVO1);
		
	}else{
		// servlet來的
		StoreVO storeVO = (StoreVO) request.getAttribute("storeVO");
		Set<Store_picVO> store_picVO  = (Set<Store_picVO>) request.getAttribute("store_picVO");


	}
	
	ArticleService articleSvc = new ArticleService();
	List<ArticleVO> ArticleVO =articleSvc.ListArticleByStore_no(Integer.parseInt(store_no));
	pageContext.setAttribute("ArticleVO", ArticleVO);
%>
<html>
<head>
<title>${storeVO.store_name}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<%=request.getContextPath()%>/front/store/css/bootstrap.min.detail.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/front/store/css/style.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/front/store/css/star-rating.css" media="all" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/store/css/sweet-alert.css">
<script src="<%=request.getContextPath()%>/front/store/js/sweet-alert.js"></script>
<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/front/store/js/star-rating.js" type="text/javascript"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/front/store/js/bootstrap.min.js"></script>
<script>
	new gethide().init();
</script>
<script>
	$(document).ready(function gethide() {
		$(".form-group").hide();
	});
	$(document).ready(function() {
		$("#scope-btn").click(function() {
			$(".form-group").toggle(888);
		});
	});
	$(document).ready(function() {
		$('#navigations').localScroll({
			duration : 800
		});
	});
</script>
<style>
span img {
	width: 200px !important;
	height: 200px !important;
}
</style>
</head>
<body>
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
	<!--============ Navigation ============-->
	<!--============ header ============-->
	<div id="header" style="width: 100%; height: 225px;">
		<iFrame src="<%=request.getContextPath()%>/front/member/JuicyTalk/Header.jsp" scrolling="no"> </iFrame>
	</div>
	<!--============ header  ============-->
	<!-- end of headerwrapper-->
	<div class="wrap">
		<div class="center">
			<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
					<c:forEach var="store_picVO" items="${store_picVO1}" varStatus="s" begin="1">
						<li data-target="#carousel-example-generic" data-slide-to="${s.count}"></li>
					</c:forEach>
				</ol>
				<!-- Wrapper for slides -->
				<div class="carousel-inner" role="listbox">
					<c:forEach var="store_picVO" items="${store_picVO1}" varStatus="s">
						<c:if test="${s.index<1}" var="condition" scope="page">
							<div class="item active">
								<img width="800" height="500" src="<%=request.getContextPath()%>/store_pic/DBGifReader5.do?pic_no=${store_picVO.pic_no}" alt="123">
								<div class="carousel-caption">${store_picVO.pic_name}</div>
							</div>
						</c:if>
						<c:if test="${s.index>=1}" var="condition" scope="page">
							<div class="item">
								<img width="800" height="500" src="<%=request.getContextPath()%>/store_pic/DBGifReader5.do?pic_no=${store_picVO.pic_no}" alt="123">
								<div class="carousel-caption">${store_picVO.pic_name}</div>
							</div>
						</c:if>
					</c:forEach>
				</div>
				<!-- Controls -->
				<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
					<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a>
				<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
					<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>
			<div class="store_detail">
				<div class="text">
					<ul>
						<li>店家地址： (${storeVO.store_zipcode})${storeVO.store_city}${storeVO.store_district}${storeVO.store_address}</li>
						<li>營業時間： 週一至週日， 11:30~14:00、17:00~21:00</li>
						<li>店家電話： ${storeVO.store_phone}</li>
						<li>店家類型： ${storeVO.store_type}</li>
						<li>評分人數： ${storeVO.store_scopenum}</li>
						<li>
							店家分數：
							<c:if test="${storeVO.store_scopenum==0}" var="condition" scope="page">暫無人評分</c:if>
							<c:if test="${storeVO.store_scopenum>0}" var="condition" scope="page">
								<fmt:formatNumber value="${storeVO.store_score/storeVO.store_scopenum}" pattern="#0.0#" />
							</c:if>
						</li>
						<li>收藏人數： ${storeVO.store_pocketnum}</li>
					</ul>
				</div>
				<div class="aboutStore">
					<h2 class="store_itr">店家介紹</h2>
					<h3>${storeVO.store_introduction}</h3>
				</div>
				<div class="storebutton">
					<div class="btn-group btn-group-lg" role="group" aria-label="...">
						<form class="btn-group btn-group-lg" METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do">	
							<button type="submit" class="btn btn-default">發表分享文</button>
							<input type="hidden" name="store_no" value="${storeVO.store_no}">				
							<input type="hidden" name="action" value="myArticle2">
						</form>
						<form class="btn-group btn-group-lg" METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do">
							<button type="submit" class="btn btn-default">我要揪團</button>
							<input type="hidden" name="action" value="go_group">
							<input type="hidden" name="store_no" value="${storeVO.store_no}">
							<input type="hidden" name="mem_no" value="${memberVO.mem_no}">
						</form>
						<button type="button" id="scope-btn" class="btn btn-default">我要評分</button>
						<button type="submit" id="pocket" class="btn btn-default">加入收藏</button>
						<input type="hidden" name="action" value="store_pocket">
						<input type="hidden" name="store_no" id="arti_no" value="${storeVO.store_no}">
						<input type="hidden" name="mem_no" id="arti_no2" value="${memberVO.mem_no}">
						<script>
							document.querySelector('#pocket').onclick = function() {
								 $.ajax({
									   type:"POST",
									   url:'<%=request.getContextPath()%>/store/store.do',
									   data:{action:'store_pocket',store_no:'${storeVO.store_no}',mem_no:'${memberVO.mem_no}'}, //傳arti_no過去
									   dataType:"text",
									   success:function (data){
										   swal("Good job!", "成功將店家收入口袋名單!", "success");
									    },
									    error:function(){alert("error")}
									    }) 

								
							};
						</script>
					</div>
				</div>
				<div class="form-group">
					<input id="input-2ba" name="store_scope" value="0" type="number" class="rating" min="0" max="5" step="0.5" data-stars=5 data-symbol="&#xe005;"
						data-default-caption="{rating} hearts" data-star-captions="{}">
					<button type="submit" id="store_scope-submit" class="btn btn-primary">確認</button>
				</div>
				<input type="hidden" name="action" value="store_scope">
				<input type="hidden" name="store_no" id="arti_no3" value="${storeVO.store_no}">
				<script>
						document.querySelector('#store_scope-submit').onclick = function() {
							 $.ajax({
								   type:"POST",
								   url:'<%=request.getContextPath()%>/store/store.do',
								   data:{'action':'store_scope','store_no':$('#arti_no3').val(),'store_scope':$('#input-2ba').val()}, //傳arti_no過去
								   dataType:"text",
								   success:function (data){
									   swal("Good job!", "成功評分!", "success");
								    },
								  	  error:function(){alert("error")}
								    }) 

						};
			</script>
			</div>
			<script>
				jQuery(document).ready(function() {
					$(".rating-kv").rating();
				});
			</script>
			
		</div>
		<div style="clear: both;"></div>
		<div class="share">
			<h2>會員分享文</h2>
		</div>
		<div class="mes">
			<c:forEach var="ArticleVO" items="${ArticleVO}" varStatus="s" begin="0" end="2">
				<%
					MemberService MemberSvc = new MemberService();
						pageContext.setAttribute("MemberSvc", MemberSvc);
				%>
				<div class="mes_1">
					<img src="<%=request.getContextPath()%>/DBGifReader3?mem_no=${ArticleVO.mem_no}">
					<p>
						${MemberSvc.getOneMember(ArticleVO.mem_no).mem_name}
						<br>
						評分：${ArticleVO.article_score}
						<br>
						性別：${MemberSvc.getOneMember(ArticleVO.mem_no).mem_sex}
					</p>
				</div>
				<div class="mes_2">
					<a href="../reply/reply.do?action=getSome_For_Display&article_no=${ArticleVO.article_no}&article_click=${ArticleVO.article_click}"
						class="Articletitle">${ArticleVO.article_title}></a>
					<p class="issue">發表於${ArticleVO.article_create.toString().substring(0,ArticleVO.article_create.toString().lastIndexOf(":"))}
					<p class="response">文章點擊數：${ArticleVO.article_click}&nbsp;&nbsp;&nbsp;&nbsp;文章點擊數：${ArticleVO.article_replies}
					<p>
						<c:set var="content" value="${ArticleVO.article_content}" scope="page" />
						<!-- 抓食記的裡的圖片放到食記摘要 -->
						<%
							String str = (String) pageContext.getAttribute("content");
								int imgfront = str.indexOf("<img");
								int imgback = str.indexOf("style");
								int textfront = 0;
								int textback = str.indexOf("<img");
								int imgend = imgback;
								int textend = textback - 1;
								String img = null;
								String text = null;
								System.out.println("imgfront:" + imgfront);
								System.out.println("imgend:" + imgend);
								System.out.println("str.length():" + str.length());
								if ((imgfront != -1) || (imgback != -1)) {
									img = str.substring(imgfront, imgend);
								}

								if (img == null) {//如果沒有抓到任何圖片

									if (str.length() > 50) {
										text = str.substring(0, 50);//直接抓文摘
									} else {
										text = str.substring(0, str.length());//直接抓文摘
									}

									img = "<img src="
											+ "\"/ZA105G1/front/personal/images2/logo2.jpg\""
											+ "/>";
								} else {//有抓到圖片
									if (imgfront != 3) {//先放字在放圖片
										if (imgfront > 50) {
											text = str.substring(0, 50) + "....";
											System.out.println("先放字在放圖片");
											System.out.println("imgfront>50");
										} else {
											text = str.substring(0, imgfront);
											System.out.println("先放字在放圖片");
											System.out.println("imgfront<=50");
										}

									} else {//先放圖片在放字

										if (str.length() - imgend > 100) {
											text = str.substring(imgend + 86, imgend + 136)
													+ "....";
											System.out.println("先放圖片在放字1");
										} else {
											text = str.substring(imgend + 36, str.length());
											System.out.println("先放圖片在放字2");
										}

										//else{
										//	text="我沒有輸入內容";
										//	}

									}

								}
						%>
					
					<p><%=text%>
				</div>
				<div class="mes_3">
					<span class="latestimgtest" id="latestimgtest"><%=img%></span>
				</div>
				<div class="clear"></div>
				<hr color="gray">
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