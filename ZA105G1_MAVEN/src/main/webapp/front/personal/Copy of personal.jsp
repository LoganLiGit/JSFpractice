<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.friend.model.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.store.model.*"%>
<%
	FriendService friendSvc = new FriendService();

	List<FriendVO> list = (List<FriendVO>) session.getAttribute("friends"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
	System.out.println("listSomeFriend�d�쪺�B�͵���:" + list.size());
	pageContext.setAttribute("list", list);
	
	List<FriendVO> list3 = (List<FriendVO>) session.getAttribute("userfriends"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
	System.out.println("listSomeFriend�d�쪺�B�͵���:" + list.size());
	pageContext.setAttribute("list3", list3);
%>
	
<jsp:useBean id="articleSvc" scope="page" class="com.article.model.ArticleService" />
<%
	//ArticleService articleSvc = new ArticleService();
	List<ArticleVO> list2 = (List<ArticleVO>) session.getAttribute("articles"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
	System.out.println("personal�d�쪺���O����:" + list2.size());
	pageContext.setAttribute("list2", list2);
	List<ArticleVO> allarticles = (List<ArticleVO>) session.getAttribute("allarticles");
	pageContext.setAttribute("allarticles", allarticles);
	List<ArticleVO> rankedArticles = (List<ArticleVO>) session.getAttribute("rankedArticles");
	pageContext.setAttribute("rankedArticles", rankedArticles);
	
	
	
	
	List<StoreVO> keepStores = (List<StoreVO>) session.getAttribute("keepStores");
	pageContext.setAttribute("keepStores", keepStores);

%>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />

<html>

<head>
<title>${memberVO.mem_name}���ӤH����</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


<meta name="keywords" content="Coffee Break Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />

<!-- �s������javascript���{���� �}�l -->
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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/move-top.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/easing.js"></script>
<script src="<%=request.getContextPath()%>/js/facebookchatboxUI.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/js/magicsuggest-min.js"></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

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

<script>
$(function(){
	$("#latestimgtest").find("img").click(function(){
		$("#latestArticle").submit();
	})	
	
	$("#imgtest").find("img").click(function(){
		$("#articles").submit();
	})	
	
	$("#ranckedArticleImg").find("img").click(function(){
		$("#rackedArticle").submit();
	})	
});




</script>





<script type="text/javascript"><!-- �j�M�ϥΪ�bar -->
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

 <script><!-- ����B�� -->
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

<!-- �s������javascript���{���� ���� -->


<!-- �s������css���{���� �}�l -->

<link href="<%=request.getContextPath()%>/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="<%=request.getContextPath()%>/css/personalStyle.css" rel='stylesheet' type='text/css' />
<link href="<%=request.getContextPath()%>/css/magicsuggest-min.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
<link rel="stylesheet"href="<%=request.getContextPath()%>/css/facebookchatboxUI.css"/>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

<!-- �s������css���{���� ���� -->
<style>
	.latestimgtest img {
		width:600px !important;
		height:337px !important;
	}
</style>

<style>
	span img {
		width:312px !important;
		height:265px !important;
	}
	
	.grid-might img {
		width:95px !important;
		height:71px !important;
	}
</style>
</head>

<body>
<div id="statusOutput" class="statusOutput"></div>
<!--============ header ============-->
<div id="header" style="width:100%; height: 225px;">
	<iFrame src="<%=request.getContextPath()%>/front/member/JuicyTalk/Header.jsp" scrolling="no"> </iFrame>
</div>
<!--============ header  ============-->

	<div class="header">
		<div class="container">
			<div class="head">
<!-- �W��ƥ\������C -->			
				<div class="navigation">
					<span class="menu"></span>
					<ul class="navig">
						<li><a href="<%=request.getContextPath()%>/index.jsp">����</a></li>
						<li><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/personal/personal.do">
								<input type="submit" value="�ӤH����">
								<input type="hidden" name="mem_no" value="${memberVO2.mem_no}">
								<input type="hidden" name="action" value="getPersonal_Display">
							</FORM>
						</li>
						<li><form METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do">
								<input type="submit" value="�����"> 
								<input type="hidden" name="mem_no" value="${memberVO2.mem_no}"> 
								<input type="hidden" name="action" value="getOne_For_Display">
							</form>
						</li>
						<li><a href="">�ڪ����O</a></li>
						<li><a href="gallery.jsp">�ڪ���ï</a></li>
						<li>${memberVO2.mem_name}���ӤH����</li>
					</ul>
				</div>

<!-- �j�M�ϥΪ�bar -->
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
	<!--banner-starts-->
	<div class="banner">
		<div class="container">
			<div class="banner-top"></div>
		</div>
	</div>
	<!--banner-end-->

	<!-- chatbox-starts-->
<!-- �b�ӤH������ܬY�H���B�ͭ� (�k������)-->	
	<div class="chat-sidebar">
	memberVO.mem_no:${memberVO.mem_no}
	memberVO2.mem_no:${memberVO2.mem_no}
	
		<c:forEach var="friendVO" items="${list3}">
			<c:if test="${friendVO.friend_status==1}">
				<c:forEach var="memberVO" items="${memberSvc.all}">
					<c:if test="${friendVO.friend_no==memberVO.mem_no}">
						<div class="sidebar-name" id="sidebar-name" onclick="connect(${memberVO.mem_no})">
						<a href="javascript:register_popup('${memberVO.mem_name}', '${memberVO.mem_name}');">
							<img width="100" src="<%=request.getContextPath()%>/DBGifReader13?mem_no=${memberVO.mem_no}">
							<span>${memberVO.mem_name}</span>
						</a>	
						</div>	
					</c:if>
				</c:forEach>
			</c:if>
		</c:forEach>
	</div>
	<!-- chatbox-end-->

	<!--about-starts-->
	<div class="about">
		<div class="container">
			<div class="about-main">
				<div class="col-md-8 about-left">
					
						
<!-- ���g���O -->			<c:forEach var="articleVO" items="${list2}" >
<!-- �̷s���O -->			<c:if test="${articleVO.article_create.toString().substring(0,articleVO.article_create.toString().lastIndexOf(":"))==latestArticle.article_create.toString().substring(0,latestArticle.article_create.toString().lastIndexOf(":"))}">
						${list2.size()}
							<div class="about-one">
								<label>${latestArticle.article_create.toString().substring(0,latestArticle.article_create.toString().lastIndexOf(":"))}</label>
								<p>�̷s���O</p>
								<h3>${articleVO.article_title}</h3>
							</div>
							
							<div class="about-two"><!-- �̷s���O�����e -->
								<c:set var="content" value="${articleVO.article_content}" scope="page"/>
<!-- �쭹�O���̪��Ϥ���쭹�O�K�n -->		<%
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
	
									if(img==null){//�p�G�S��������Ϥ�
										
										if(str.length()>50){
											text = str.substring(0, 50);//�������K
										}
										else{
											text = str.substring(0, str.length());//�������K
										}
										
										System.out.println("�S�Ϥ�");
									}
									else{//�����Ϥ�
										if(imgfront!=3){//����r�b��Ϥ�
											if(imgfront>50){
												text = str.substring(0, 50)+"....";
												System.out.println("����r�b��Ϥ�");
												System.out.println("imgfront>50");
											}
											else{
												text = str.substring(0, imgfront)+"....";
												System.out.println("����r�b��Ϥ�");
												System.out.println("imgfront<=50");
											}

										}
										else{//����Ϥ��b��r
											if(str.length()-imgend>50)
											{
												text = str.substring(imgend,imgend+60)+"....";
												System.out.println("����Ϥ��b��r1");
											}
											else{
												text = str.substring(imgend,str.length())+"....";
												System.out.println("����Ϥ��b��r2");
											}
											
										}
										
									}
										
									%>
								<span class="latestimgtest" id="latestimgtest"><%=img %></span>
								<p>
									�����a <a href="about.html">�ּw��</a> on 10 feb, 2016 <a href="#">�d��(2)</a>�I����:${articleVO.article_click}
								</p>
								<p><%=text %></p>
								<div class="about-btn">
									<form id="latestArticle" METHOD="post" ACTION="<%=request.getContextPath()%>/reply/reply.do">
										<input type="hidden" name="article_no" value="${articleVO.article_no}">
										<input type="hidden" name="article_click" value="${articleVO.article_click}">
										<input type="hidden" name="action" value="getSome_For_Display">
									</form>
									
								</div>
								<ul>
									<li>
										<p>Share :</p>
									</li>
									<li><a href="#"><span class="fb"> </span></a></li>
								</ul>
							</div>						
						</c:if>
						
						
						<c:if test="${articleVO.article_create.toString().substring(0,articleVO.article_create.toString().lastIndexOf(":"))!=latestArticle.article_create.toString().substring(0,latestArticle.article_create.toString().lastIndexOf(":"))}">
							<div class="a-1"><!-- �U��|�g���O -->	
								<div class="col-md-6 abt-left">	
									<c:set var="content" value="${articleVO.article_content}" scope="page"/>
<!-- �쭹�O���̪��Ϥ���쭹�O�K�n -->		<%
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
	
									if(img==null){//�p�G�S��������Ϥ�
										
										if(str.length()>50){
											text = str.substring(0, 50);//�������K
										}
										else{
											text = str.substring(0, str.length());//�������K
										}
										
										System.out.println("�S�Ϥ�");
									}
									else{//�����Ϥ�
										if(imgfront!=3){//����r�b��Ϥ�
											if(imgfront>50){
												text = str.substring(0, 50)+"....";
												System.out.println("����r�b��Ϥ�");
												System.out.println("imgfront>50");
											}
											else{
												text = str.substring(0, imgfront)+"....";
												System.out.println("����r�b��Ϥ�");
												System.out.println("imgfront<=50");
											}

										}
										else{//����Ϥ��b��r
											if(str.length()-imgend>50)
											{
												text = str.substring(imgend,imgend+60)+"....";
												System.out.println("����Ϥ��b��r1");
											}
											else{
												text = str.substring(imgend,str.length())+"....";
												System.out.println("����Ϥ��b��r2");
											}
											
										}
										
									}
										
									%>
									<span id="imgtest"><%=img %></span>
									
									
									
									<h6>Read More</h6>	
									<h3>
										${articleVO.article_title}</a>
										
									</h3>
									<span><%=text %></span>
									<form id="articles" METHOD="post" ACTION="<%=request.getContextPath()%>/reply/reply.do">
											<input type="hidden" name="article_no" value="${articleVO.article_no}">
											<input type="hidden" name="article_click" value="${articleVO.article_click}">
											<input type="hidden" name="action" value="getSome_For_Display">
									</form>
									

									<label>${articleVO.article_create.toString().substring(0,articleVO.article_create.toString().lastIndexOf(":"))}</label>
									
								
								</div>
							</div>	
							</c:if>
						</c:forEach>
							<div class="clearfix"></div>
					</div>
				</div>
				
<!-- ����B�ͫ��s -->				
				<div class="col-md-4 about-right heading">
					<!-- ���sĲ�o modal -->
					<div>
  						<form METHOD="post">
  							<input type="button" id="randomfriend" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal" value="����n��">
							<input type="hidden" id="mem_no" name="mem_no" value="${memberVO.mem_no}">
						</form>
						
					</div>
					
					<div class="container">
					<!-- Modal -->
  					<div class="modal fade" id="myModal" role="dialog">
   					<div class="modal-dialog">
    
     				 <!-- Modal content-->
      				<div class="modal-content">
        				<div class="modal-header">
          					<button type="button" class="close" data-dismiss="modal">&times;</button>
         					 <h4 class="modal-title">����B��</h4>
        				</div>
        				<div class="modal-body" id="modal-body">
          					<table id="rspTable">

          					</table>
        				</div>
        				<div class="modal-footer">
          					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        				</div>
     				</div>
      
    				</div>
  					</div>
			</div>
			
<!-- ���H��T -->			
					<div class="abt-1">
						<h3>${memberVO2.mem_name}</h3>
						<div class="abt-one">
							<img width="200"src="<%=request.getContextPath()%>/DBGifReader13?mem_no=${memberVO2.mem_no}">

							<p><input type="file" name="mem_photo" value="���Ӥ�"></p>
							<c:if test="${memberVO.mem_no!=memberVO2.mem_no}">
							<div class="a-btn">
								<a href="">�[�J���`</a></br>
							</div>
							</c:if>
						</div>
					</div>
					
<!-- ���O�ƦW -->					
				   <div class="abt-2">
						<h3>�A�i��]���w�����O</h3>	
<!-- ���򭹰O�ƦW-->						
						<c:forEach var="articleVO" items="${rankedArticles}" >
							<c:set var="content" value="${articleVO.article_content}" scope="page"/>
<!-- �쭹�O���̪��Ϥ�.�K�n��쭹�O�K�n -->	<%
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
	
									if(img==null){//�p�G�S��������Ϥ�
										
										if(str.length()>50){
											text = str.substring(0, 50);//�������K
										}
										else{
											text = str.substring(0, str.length());//�������K
										}
										
										System.out.println("�S�Ϥ�");
									}
									else{//�����Ϥ�
										if(imgfront!=3){//����r�b��Ϥ�
											if(imgfront>50){
												text = str.substring(0, 50)+"....";
												System.out.println("����r�b��Ϥ�");
												System.out.println("imgfront>50");
											}
											else{
												text = str.substring(0, imgfront)+"....";
												System.out.println("����r�b��Ϥ�");
												System.out.println("imgfront<=50");
											}

										}
										else{//����Ϥ��b��r
											if(str.length()-imgend>50)
											{
												text = str.substring(imgend,imgend+60)+"....";
												System.out.println("����Ϥ��b��r1");
											}
											else{
												text = str.substring(imgend,str.length())+"....";
												System.out.println("����Ϥ��b��r2");
											}
											
										}
										
									}
								%>	
<!-- �쭹�O���̪��Ϥ�.�K�n��쭹�O�K�n -->
						
						<div class="might-grid">
							<div id="ranckedArticleImg" class="grid-might">
								<%=img%> 
							</div>
							<div class="might-top">
								<h4>
									<a href="single.html">${articleVO.article_title}</a>
								</h4>
								<p><%=text %></p>
							</div>
							<div class="clearfix"></div>
						</div>	

									<form id="rackedArticle" METHOD="post" ACTION="<%=request.getContextPath()%>/reply/reply.do">
											<input type="hidden" name="article_no" value="${articleVO.article_no}">
											<input type="hidden" name="article_click" value="${articleVO.article_click}">
											<input type="hidden" name="action" value="getSome_For_Display">
									</form>
									

									<label>�I����:${articleVO.article_click}</label>

							
						</c:forEach>
					</div>
					
<!-- ���é��a -->					
					<div class="abt-2">
						<h3>�̷s���é��a</h3>
						<div class="might-grid">
							<div class="grid-might">
								<a href="single.html"><img
									src="<%=request.getContextPath()%>/images2/ma.jpg"
									class="img-responsive" alt=""> </a>
							</div>
							<div class="might-top">
								<h4>
									<a href="">�s���Ʋz�T���Q���@</a>
								</h4>
								<p>�a�ϡG������</p>
								<p>�����G���A�Ʋz</p>
							</div>
							<div class="clearfix"></div>

						</div>
						<div class="might-grid">
							<div class="grid-might">
								<a href="single.html"><img
									src="<%=request.getContextPath()%>/images2/ma.jpg"
									class="img-responsive" alt=""> </a>
							</div>
							<div class="might-top">
								<h4>
									<a href="">�s���Ʋz�T���Q���T</a>
								</h4>
								<p>�a�ϡG�x�_��</p>
								<p>�����G���A�Ʋz</p>
							</div>
							<div class="clearfix"></div>

						</div>
						<div class="might-grid">
							<div class="grid-might">
								<a href=""><img
									src="<%=request.getContextPath()%>/images2/ma.jpg"
									class="img-responsive" alt=""> </a>
							</div>
							<div class="might-top">
								<h4>
									<a href="single.html">�s���Ʋz�T���Q���G</a>
								</h4>
								<p>�a�ϡG�x�n��</p>
								<p>�����G���A�Ʋz</p>
							</div>
							<div class="clearfix"></div>

						</div>
						
						
						<c:forEach var="storeVO" items="${keepStores}" >
						
						<div class="might-grid">
							<div class="grid-might">
								<a href=""><img width="95"src="<%=request.getContextPath()%>/DBGifReader13?store_no=${storeVO.store_no}"></a>
							</div>
							<div class="might-top">
								<h4>
									<a href="single.html">${storeVO.store_name}</a>
								</h4>
								<p>�a�ϡG${storeVO.store_city}</p>
								<p>�����G${storeVO.store_type}</p>
							</div>
							<div class="clearfix"></div>

						</div>
						</c:forEach>
					</div>
					
<!-- �o�����O -->					
					<div class="writeArticle">
						<h3>�o�����O</h3>
						<ul>
							<li><form METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do">
								<input type="submit" value="�o���O">
								<input type="hidden" name="action" value="myArticle">
								</form>
							</li>
						</ul>
					</div>
					

<!-- �b�B�ͭ�����ܬY�H���B�ͭ� -->
					<div class="myFriend">
						<h3>�ڪ��B��</h3>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/friend/friend.do">
							<input type="submit" value="�ڪ��B��">
							<input type="hidden" name="mem_no" value="${memberVO2.mem_no}">
							<input type="hidden" name="action" value="getSome_For_Display">
						</FORM>

<!-- �b�ӤH������ܬY�H���B�ͭ� -->		
						<table border='5' bordercolor='#CCCCFF'>

							<c:forEach var="memberVO" items="${memberSvc.all}">
								<c:if test="${param.mem_no==memberVO.mem_no}">
									<h1>${memberVO.mem_name}���B�ͭ�<3</h1>
								</c:if>
							</c:forEach>
							</br>
							<%@ include file="page1A.file"%>
							<c:forEach var="friendVO" items="${list}" begin="<%=pageIndex%>"
								end="<%=pageIndex+rowsPerPage-1%>">
								<c:if test="${friendVO.friend_status==1}">
									
									<tr align='center' valign='middle'>
										<!-- �B�ͥ��� -->
										<td><c:forEach var="memberVO" items="${memberSvc.all}">
												<c:if test="${friendVO.friend_no==memberVO.mem_no}">
													<img width="100" src="<%=request.getContextPath()%>/DBGifReader13?mem_no=${memberVO.mem_no}" >
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/personal/personal.do">
														<input type="submit" value="�ӤH����">
														 <input type="hidden" name="mem_no" value="${memberVO.mem_no}">
														 <input type="hidden" name="login_mem_no" value="${sessionScope.memberVO.mem_no}">
														<input type="hidden" name="action" value="getPersonal_Display">
													</FORM>
	                    								${memberVO.mem_name}
												</c:if>
											</c:forEach>
										</td>
									</tr>
								</c:if>
							</c:forEach>
						</table>

						<%@ include file="page2A.file"%>
					</div>
<!-- �ڪ��峹 -->
					<div class="myArticle">
						<h3>�ڪ��峹</h3>
						<ul>
							<li>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/reply/reply.do">
									<b><font color=orange>��ܭ��O:</font></b> 
									<select size="1" name="article_no">
										<c:forEach var="articleVO" items="${articleSvc.all}">
											<c:if test="${memberVO2.mem_no==articleVO.mem_no}">
											<option value="${articleVO.article_no}">${articleVO.article_title}
											</c:if>
										</c:forEach>
									</select> 
									<input type="submit" value="�e�X">
									<input type="hidden" name="action" value="getSome_For_Display">
								</FORM>
							</li>
						</ul>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	
<!-- �U�話�a�s�i���� -->
	<div class="slide">
		<div class="container">
			<div class="fle-xsel">
				<ul id="flexiselDemo3">
					<li><a href="#">
							<div class="banner-1">
								<img src="<%=request.getContextPath()%>/images2/01.jpg"
									class="img-responsive" alt="">
							</div>
						</a>
					</li>
					<li><a href="#">
							<div class="banner-1">
								<img src="<%=request.getContextPath()%>/images2/02.jpg"
									class="img-responsive" alt="">
							</div>
						</a>
					</li>
					<li><a href="#">
							<div class="banner-1">
								<img src="<%=request.getContextPath()%>/images2/ad1.jpg"
									class="img-responsive" alt="">
							</div>
						</a>
					</li>
					<li><a href="#">
							<div class="banner-1">
								<img src="<%=request.getContextPath()%>/images2/ad2.jpg"
									class="img-responsive" alt="">
							</div>
						</a>
					</li>
					<li><a href="#">
							<div class="banner-1">
								<img src="<%=request.getContextPath()%>/images2/ad3.jpg"
									class="img-responsive" alt="">
							</div>
						</a>
					</li>
					<li><a href="#">
							<div class="banner-1">
								<img src="<%=request.getContextPath()%>/images2/ad5.jpg"
									class="img-responsive" alt="">
							</div>
						</a>
					</li>
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
				<script type="text/javascript"
					src="<%=request.getContextPath()%>/js/jquery.flexisel.js"></script>
				<div class="clearfix"></div>
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
	
	<script>
$(function() {
var ms = $('#ms').magicSuggest({
allowFreeEntries: false,
selectFirst: true,
resultAsString: true,
toggleOnClick: true,
maxSelection:1,
data: ['${memberVO.mem_no}','Los Angeles','Chicago','Houston','Philadelphia','Phoenix','San Antonio','San Diego','Dallas','San Jose','Jacksonville']
});
ms.setName('ms1');//ms1[]
});
</script>
</body>
<script>

	var MyPoint = "/MyEchoServer";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
	var statusOutput = document.getElementById("statusOutput");
	var webSocket;

	function connect(mem_no) {
		// �إ� websocket ����
		webSocket = new WebSocket(endPointURL);
		
		
		
		

		webSocket.onopen = function(event) {
			updateStatus("WebSocket ���\�s�u");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
		};

		webSocket.onmessage = function(event) {
			var messagesArea = document.getElementById("popup-messages");
			var jsonObj = JSON.parse(event.data);
			var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
			messagesArea.value = messagesArea.value + message;
			messagesArea.scrollTop = messagesArea.scrollHeight;
		};

		webSocket.onclose = function(event) {
			updateStatus("WebSocket �w���u");
		};
	}

	var inputUserName = '${memberVO.mem_name}';
	
	inputUserName.focus();

	function sendMessage() {
		var userName = inputUserName.trim();
		var inputMessage = document.getElementById("message");
		var message = inputMessage.value.trim();

		if (message === "") {
			alert("�T���ФŪť�!");
			inputMessage.focus();
		} else {
			var jsonObj = {
				"userName" : userName,
				"message" : message
			};
			webSocket.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}

	function disconnect() {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
		alert("disconnect");
	}

	function updateStatus(newStatus) {
		statusOutput.innerHTML = newStatus;
	}

</script>

</html>