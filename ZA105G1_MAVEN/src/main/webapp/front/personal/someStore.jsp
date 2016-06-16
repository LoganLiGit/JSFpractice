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
	
	
	
%>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />

<%
	StoreService storeSvc = new StoreService();

	List<StoreVO> allstores = (List<StoreVO>) session.getAttribute("allstores"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
	System.out.println("�Ҧ����a����:" + allstores.size());
	
	List<StoreVO> searchedStores = (List<StoreVO>) session.getAttribute("searchedStores"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
	System.out.println("���M���a����:" + searchedStores.size());
	pageContext.setAttribute("searchedStores", searchedStores);
%>
<html>

<head>
<title>��ܩ��a</title>
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

<!-- scroll function -->
<script type="text/javascript">
$(document).ready(function() {
   $('#navigations').localScroll({duration:800});
});
</script>

<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>

<!---- start-smoth-scrolling---->
<script type="text/javascript" src="<%=request.getContextPath()%>/front/personal/js/move-top.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/front/personal/js/easing.js"></script>
<script src="<%=request.getContextPath()%>/front/personal/js/jquery.localScroll.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/front/personal/js/jquery.scrollTo.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/front/personal/js/facebookchatboxUI.js"></script>
<script src="<%=request.getContextPath()%>/front/personal/js/bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/front/personal/js/magicsuggest-min.js"></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="<%=request.getContextPath()%>/front/personal/js/wow.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/front/personal/js/MyJQ.js"></script>
<script src="<%=request.getContextPath()%>/front/personal/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/front/personal/js/jquery.glide.js"></script>
<script>
new WOW().init();
</script>

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


<script type="text/javascript"><!-- �j�M���abar -->
$(function() {
    var availableTags = [
<c:forEach var="storeVO" items="${storeSvc.all}">
"${storeVO.store_name}",
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

<link href="<%=request.getContextPath()%>/front/personal/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="<%=request.getContextPath()%>/front/personal/css/personalStyle.css" rel='stylesheet' type='text/css' />
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/personal/css/style.css">
<link href="<%=request.getContextPath()%>/front/personal/css/magicsuggest-min.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/personal/css/animate.css">
<link rel="stylesheet"href="<%=request.getContextPath()%>/front/personal/css/facebookchatboxUI.css"/>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

<style>
	#tags {
		width:600px;
		position: absolute; left:23%;
	}
	
	#search{
		position: absolute; top:95%; left:55%;
	}
	
	#optradio{
		position: absolute; top:73%; left:60%;
	}

</style>

<!-- �s������css���{���� ���� -->



    
    

<!-- scroll function -->
<script type="text/javascript">
$(document).ready(function() {
   $('#navigations').localScroll({duration:800});
});
</script>


<script src="<%=request.getContextPath()%>/front/personal/js/wow.min.js"></script>
<script>
new WOW().init();
</script>

</head>

<body>
<div id="header" style="width: 100%; height: 225px;">
	<iFrame src="<%=request.getContextPath()%>/front/member/JuicyTalk/Header.jsp" scrolling="no"> </iFrame>
</div>
	

	<div class="header">
		
		<div class="container" style="padding:20px;">
			<div class="head">
<!-- �W��ƥ\������C -->			
				<div class="navigation">
					<span class="menu"></span>
					<ul class="navig">
						
						
						<li style="border-right:solid 5px;">
<%-- 						<li><form METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do"> --%>
<!-- 								<input type="submit" value="�����" >  -->
<%-- 								<input type="hidden" name="mem_no" value="${memberVO2.mem_no}">  --%>
<!-- 								<input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 							</form> -->
							<a href="../member/member.do?action=getOne_For_Display&mem_no=${memberVO2.mem_no}"
								style="font-size:36px; font-family: Meiryo, �L�n������, Microsoft JhengHei;">�����</a>
						</li>
						<li style="border-right:solid 5px;">
<%-- 							<form METHOD="post" ACTION="<%=request.getContextPath()%>/personal/personal.do"> --%>
<!-- 								<input type="submit" value="�Ҧ����O" class="btn-info btn-lg">  -->
<%-- 								<input type="hidden" name="mem_no" value="${memberVO2.mem_no}">  --%>
<!-- 								<input type="hidden" name="action" value="get_SomeOne_All_Article">	 -->
<!-- 							</form> -->
							<a href="../personal/personal.do?action=get_SomeOne_All_Article&mem_no=${memberVO2.mem_no}"
							style="font-size:36px;font-family: Meiryo, �L�n������, Microsoft JhengHei;">�Ҧ����O</a>
						</li>
						<li style="border-right:solid 5px;">
<%-- 							<form METHOD="post" ACTION="<%=request.getContextPath()%>/personal/personal.do"> --%>
<!-- 								<input type="submit" value="��ï" class="btn-info btn-lg">  -->
<%-- 								<input type="hidden" name="mem_no" value="${memberVO2.mem_no}">  --%>
<!-- 								<input type="hidden" name="action" value="get_Album"> -->
<!-- 							</form> -->
							<a href="../personal/personal.do?action=get_Album&mem_no=${memberVO2.mem_no}"
							style="font-size:36px;font-family: Meiryo, �L�n������, Microsoft JhengHei;">��ï</a>
						</li>
						<c:if test="${memberVO.mem_no==memberVO2.mem_no}">
						<li style="border-right:solid 5px;">
<%-- 							<form METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do"> --%>
<!-- 								<input type="submit" value="�o���O" class="btn-info btn-lg"> -->
<!-- 								<input type="hidden" name="action" value="myArticle"> -->
<!-- 							</form> -->
							<a href="../article/article.do?action=myArticle"
							style="font-size:36px;font-family: Meiryo, �L�n������, Microsoft JhengHei;">�o���O</a>
						</li>
						</c:if>
						<li style="border-right:solid 5px;">
<%-- 							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/friend/friend.do"> --%>
<!-- 								<input type="submit" value="�ڪ��B��" class="btn-info btn-lg"> -->
<%-- 								<input type="hidden" name="mem_no" value="${memberVO2.mem_no}" > --%>
<!-- 								<input type="hidden" name="action" value="getSome_For_Display"> -->
<!-- 							</FORM> -->
							<a href="../friend/friend.do?action=getSome_For_Display&mem_no=${memberVO2.mem_no}"
							style="font-size:36px;font-family: Meiryo, �L�n������, Microsoft JhengHei;">�ڪ��B��</a>
						</li>

					</ul>
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
			

		</div>
	</div>
	<!--banner-end-->
	
<!--============ Slider ============-->

<div class="sliderwrapper">
      <div id="slider" class="container">
           <div class="slider">
      			<ul class="slides">
    		 	 	  <li class="slide">
                      	<h5 class="wow fadeInDown" data-wow-delay="0.8s">Juicy Talk </h5>
                      	<p class="wow fadeInUp" data-wow-delay="0.8s">
							�@�Ӭ��F���j�a�W�i��y�P�ɥά���������<br>
                            �]�����ѰӮa���A�ȥ\��<br>
                            ���\�U�������W��
						</p>
                      <img src="<%=request.getContextPath()%>/front/personal/images2/logo1.png" width="317" height="256" class="wow fadeInRight" 
                      data-wow-delay="0.8s" alt="slide1img"> 
                      </li>
      			 	  <li class="slide">
                      	<h5 class="wow fadeInDown" data-wow-delay="0.8s">�W�r�Ѩ� </h5>
                      	<p class="wow fadeInUp" data-wow-delay="0.8s">
                        �������W�r���X�FJuicy�MTalk<br>
                        Juicy�O���۩󭹪����h�ġA�N���O�������̲�<br>
                        Talk�O���ܪ��f�y�A�ɥN��y���N�q
                        </p>
                      <img src="<%=request.getContextPath()%>/front/personal/images2/juta.png" width="317" height="256" class="wow fadeInRight" 
                      data-wow-delay="0.8s" alt="slideimg2"> 
                      </li>
     			 	  <li class="slide">
                      	<h5 class="wow fadeInDown" data-wow-delay="0.8s">�̷s����</h5>
                      	<p class="wow fadeInUp" data-wow-delay="0.8s">�q�L�U���̷s��APP�i�H�����|���IPHONE 6S</p>
                      <img src="<%=request.getContextPath()%>/front/personal/images2/hero.png" width="400" height="256" class="wow fadeInRight" 
                      data-wow-delay="0.8s" alt="slideimg2"> 
                      </li>
        		  </ul>
            </div>
      </div> <!-- End of Slider-->
</div> <!-- end of sliderwrapper-->


	<!--about-starts-->
	<div class="about">
		<div class="container">
			<div class="header-right">
<!-- �j�M���abar -->	<div class="search-bar">		
						 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do">
						 	<div style="background-color:orange;">
								<label class="radio-inline;">
     								 <input type="radio" name="optradio" value="storeName" checked="checked">���a�W��
   							 	</label>
    							<label class="radio-inline">
     								 <input type="radio" name="optradio" value="foodType">�Ʋz����
   								 </label>
   					 		
   					 		
							<input id="tags"  name="store_key" value="Search" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search';}">	
							<input id="search" type="submit" value="">					
							<input type="hidden" name="action" value="getSearchedStoreDisplay">
							</div>
						</FORM> 
					</div>
				</div>
			<div class="about-main" >
					<div class="about-tre">
						
				
						
<!-- show���a���ϰ� -->						
						<%@ include file="pageStore1.file" %> 
						<c:forEach var="StoreVO" items="${searchedStores}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"><!-- ���a -->
							<div class="a-1" >	
								<div class="col-md-6 abt-left" style="margin-bottom:5px; padding:30px; border:1px; ">	
									<img src="<%=request.getContextPath()%>/DBGifReader13?store_no=${StoreVO.store_no}">
										
										
									<h3 style="font-size: 26px; font-family: Microsoft YaHei; background-color:#CDBA96;">
										${StoreVO.store_name}
									</h3>
									<div style="background-color:#FFF; padding:10px; margin:5px; height:225px">
									<p style=" margin:5px; font-size:20px; font-family: Meiryo, �L�n������, Microsoft JhengHei;">�����`��:${StoreVO.store_type}</p>
									<p style=" margin:5px; font-size:20px; font-family: Meiryo, �L�n������, Microsoft JhengHei;">���a�a�}:${StoreVO.store_city}${StoreVO.store_district}${StoreVO.store_address}</p>
									<p style=" margin:5px; font-size:20px; font-family: Meiryo, �L�n������, Microsoft JhengHei;">�s���q��:${StoreVO.store_phone}</p>
									<p style=" margin:5px; font-size:20px; font-family: Meiryo, �L�n������, Microsoft JhengHei;">���a����:${StoreVO.store_score}</p>
									
									
									<form METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do">					
										<div align="center">
											<input type="submit" value="�o���O" style="background: #000; weight:bold; border-radius: 5px; color: #FFF; padding:10px;font-family: malgun gothic,simhei;">
										</div>
										<input type="hidden" name="store_no" value="${StoreVO.store_no}">				
										<input type="hidden" name="action" value="myArticle2">
									</form>
									</div>
								</div>
							</div>	
						</c:forEach>
						<%@ include file="pageStore2.file" %>
							<div class="clearfix"></div>
					</div>
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
								<img src="<%=request.getContextPath()%>/front/personal/images2/01.jpg"
									class="img-responsive" alt="">
							</div>
						</a>
					</li>
					<li><a href="#">
							<div class="banner-1">
								<img src="<%=request.getContextPath()%>/front/personal/images2/02.jpg"
									class="img-responsive" alt="">
							</div>
						</a>
					</li>
					<li><a href="#">
							<div class="banner-1">
								<img src="<%=request.getContextPath()%>/front/personal/images2/ad1.jpg"
									class="img-responsive" alt="">
							</div>
						</a>
					</li>
					<li><a href="#">
							<div class="banner-1">
								<img src="<%=request.getContextPath()%>/front/personal/images2/ad2.jpg"
									class="img-responsive" alt="">
							</div>
						</a>
					</li>
					<li><a href="#">
							<div class="banner-1">
								<img src="<%=request.getContextPath()%>/front/personal/images2/ad3.jpg"
									class="img-responsive" alt="">
							</div>
						</a>
					</li>
					<li><a href="#">
							<div class="banner-1">
								<img src="<%=request.getContextPath()%>/front/personal/images2/ad5.jpg"
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
					src="<%=request.getContextPath()%>/front/personal/js/jquery.flexisel.js"></script>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--slide-end-->
<!--============ footer ============-->
<div id="footer" style="width: 100%; height: 455px;">
	<iFrame style="width:100%; height: 455px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/footer.jsp" scrolling="no"> </iFrame>
</div>
<!--============ footer  ============-->
	
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
<script type="text/javascript">
    $('.sliderwrapper .slider').glide({
		autoplay: 7000,
		animationDuration: 3000,
		arrows: true,
		
		
	
		});
	
</script>

<script type="text/javascript">
    $('.bestdisheswrapper .slider').glide({
		autoplay: false,
		animationDuration: 700,
		arrows: true,
		navigation:false,
		
		
	
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

	function connect() {
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