<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%
	List<ArticleVO> list2 = (List<ArticleVO>) session.getAttribute("articles"); //EmpServlet.java(Concroller), 存入req的empVO物件
	System.out.println("personal查到的食記筆數:" + list2.size());
	pageContext.setAttribute("list2", list2);
%>
<!DOCTYPE html>
<html>

<head>
<title>${memberVO.mem_name}的個人頁面</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


<meta name="keywords" content="Coffee Break Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
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

<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>

<!---- start-smoth-scrolling---->
<script type="text/javascript" src="<%=request.getContextPath()%>/front/personal/js/move-top.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/front/personal/js/easing.js"></script>
<script src="<%=request.getContextPath()%>/front/personal/js/facebookchatboxUI.js"></script>
<script src="<%=request.getContextPath()%>/front/personal/js/bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/front/personal/js/magicsuggest-min.js"></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<script src="<%=request.getContextPath()%>/front/personal/js/modernizr.custom.97074.js"></script>
	<script src="<%=request.getContextPath()%>/front/personal/js/jquery.chocolat.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/front/personal/css/chocolat.css" type="text/css" media="screen" charset="utf-8">
	<!--light-box-files -->
	<script type="text/javascript" charset="utf-8">
		$(function () {
			$('.gallery a').Chocolat();
		});
	</script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/front/personal/js/jquery.hoverdir.js"></script>

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


<!-- 連結關於javascript的程式區 結束 -->


<!-- 連結關於css的程式區 開始 -->

<link href="<%=request.getContextPath()%>/front/personal/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="<%=request.getContextPath()%>/front/personal/css/personalStyle.css" rel='stylesheet' type='text/css' />
<link href="<%=request.getContextPath()%>/front/personal/css/magicsuggest-min.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/personal/css/style.css">
<link rel="stylesheet"href="<%=request.getContextPath()%>/front/personal/css/facebookchatboxUI.css"/>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

<!-- 連結關於css的程式區 結束 -->
<style>
	.latestimgtest img {
		width:600px !important;
		height:337px !important;
		
	}

	span img {
		width:312px !important;
		height:265px !important;
		
	}
	
	.grid-might img {
		width:95px !important;
		height:71px !important;
		
	}
	
	.col-md{
		background-color:orange;
	}	
		
	
	
</style>
</head>

<body>
<div id="header" style="width: 100%; height: 225px;">
	<iFrame src="<%=request.getContextPath()%>/front/member/JuicyTalk/Header.jsp" scrolling="no"> </iFrame>
</div>
	

	<div class="header">
		
		<div class="container" style="padding:20px;">
			<div class="head">
<!-- 上方副功能導覽列 -->			
				<div class="navigation">
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
				
				
				<form id="articles" METHOD="post" ACTION="<%=request.getContextPath()%>/reply/reply.do">
							<div id="myranckedArticleImg">
							<c:forEach var="articleVO" items="${list2}" >
<!-- 下方所有食記開始 -->			
							<div class="a-1">
								<div class="col-md-6 abt-left" style="background-color:#FFB90F;padding:10px;margin-bottom:5px;margin:1px; width:340px; height:530px;">	
									<c:set var="content" value="${articleVO.article_content}" scope="page"/>
<!-- 抓食記的裡的圖片放到食記摘要 -->		<%
									String str = (String) pageContext.getAttribute("content");						
									int imgfront = str.indexOf("<img");
									int imgback = str.indexOf("/>");
									int textfront = 0;
									int textback = str.indexOf("<img");
									int imgend = imgback+2;
									int textend = textback-1;
									String img = null;
									String text = null;
									System.out.println("imgfront:"+imgfront);
									System.out.println("imgend:"+imgend);
									System.out.println("str.length():"+str.length());
									if((imgfront != -1) || (imgback != -1)) {
										img = str.substring(imgfront, imgend);
									}
	
									if(img==null){//如果沒有抓到任何圖片
										
										if(str.length()>20){
											text = str.substring(0, 20);//直接抓文摘
										}
										else{
											text = str.substring(0, str.length());//直接抓文摘
										}
										
										img="<img src="+"\"/ZA105G1/front/personal/images2/logo2.jpg\""+"/>";
									}
									else{//有抓到圖片
										if(imgfront!=3){//先放字在放圖片
											if(imgfront>50){
												text = str.substring(0, 30);
												System.out.println("先放字在放圖片");
												System.out.println("imgfront>50");
											}
											else{
												text = str.substring(0, imgfront);
												System.out.println("先放字在放圖片");
												System.out.println("imgfront<=50");
											}

										}
										else{//先放圖片在放字
											if(str.length()-imgend>50)
											{
												text = str.substring(imgend,imgend+30);
												System.out.println("先放圖片在放字1");
											}
											else{
												text = str.substring(imgend,str.length());
												System.out.println("先放圖片在放字2");
											}
											
										}
										
									}
										
									%>
									<div thisname2="myranckedArticleImg" no1="${articleVO.article_no}" no2="${articleVO.article_click}">
									<div style="text-align:center;"><span>${articleVO.article_title}</span></div>
									<span id="imgtest"><%=img %></span>
									</div>
									<div style="background-color:white;padding:10px;margin-top:10px;height:180px;">
										<div style="align:top;">
											<a href="../reply/reply.do?action=getSome_For_Display&article_no=${articleVO.article_no}&article_click=${articleVO.article_click}"><h6>Read More</h6></a>
										</div>
									<div id="latest_article_content" style="color:black; height:75px; margin:5px; font-size:18px; font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;"><%=text %></div>
									<div style="color:black; margin:5px; font-size:18px; font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;">點擊數:${articleVO.article_click}
										<a href="../reply/reply.do?action=getSome_For_Display&article_no=${articleVO.article_no}&article_click=${articleVO.article_click}"><h6>留言(${articleVO.article_replies})</h6></a>
									</div>
								
									</div>
									<div >
										<label style="color:black; margin:5px; font-size:18px; font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;">${articleVO.article_create.toString().substring(0,articleVO.article_create.toString().lastIndexOf(":"))}</label>
									</div>
									
								
								</div>
							</div>		
<!-- 所有食記結束 -->		
						</c:forEach>
						</div>
						<input type="hidden" name="article_no" id="article_no2">
						<input type="hidden" name="article_click" id="article_click2">
						<input type="hidden" name="action" value="getSome_For_Display">
						</form>



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
	
	<!--banner-end-->
	<!--gallery-starts-->
	<div class="gallery">
		<div class="container">
			
			

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
<!--============ footer ============-->
<div id="footer" style="width:100%; height: 455px;">
	<iFrame style="width:100%; height: 455px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/footer.jsp" scrolling="no"> </iFrame>
</div>
<!--============ footer  ============-->
</body>

</html>