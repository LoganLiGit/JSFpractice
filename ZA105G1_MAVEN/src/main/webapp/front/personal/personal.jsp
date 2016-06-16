<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.friend.model.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.store.model.*"%>
<%
	FriendService friendSvc = new FriendService();

	List<FriendVO> list = (List<FriendVO>) session.getAttribute("friends"); //EmpServlet.java(Concroller), 存入req的empVO物件
	System.out.println("非登入者朋友筆數:" + list.size());
	pageContext.setAttribute("list", list);
	
	List<FriendVO> list3 = (List<FriendVO>) session.getAttribute("userfriends"); //EmpServlet.java(Concroller), 存入req的empVO物件
	System.out.println("登入者的朋友筆數:" + list3.size());
	pageContext.setAttribute("list3", list3);
	
	//Integer todayFriend_no = (Integer)session.getAttribute("todayFriend_no");//抓到今日朋友的編號
	//System.out.println("PtodayFriend_no:"+todayFriend_no);
	//pageContext.setAttribute("todayFriend_no",todayFriend_no);
	
%>
	
<jsp:useBean id="articleSvc" scope="page" class="com.article.model.ArticleService" />
<%
	//ArticleService articleSvc = new ArticleService();
	List<ArticleVO> list2 = (List<ArticleVO>) session.getAttribute("articles"); //EmpServlet.java(Concroller), 存入req的empVO物件
	System.out.println("personal查到的食記筆數:" + list2.size());
	pageContext.setAttribute("list2", list2);
	
	List<ArticleVO> allarticles = (List<ArticleVO>) session.getAttribute("allarticles");
	pageContext.setAttribute("allarticles", allarticles);
	
	List<ArticleVO> rankedArticles = (List<ArticleVO>) session.getAttribute("rankedArticles");
	pageContext.setAttribute("rankedArticles", rankedArticles);
	
	List<Integer> articleRepliesNum = (List<Integer>) session.getAttribute("articleRepliesNum");
	pageContext.setAttribute("articleRepliesNum", articleRepliesNum);
	
	
	
	List<StoreVO> stores = (List<StoreVO>) session.getAttribute("stores");
	pageContext.setAttribute("stores", stores);
	
	List<StoreVO> keepStores = (List<StoreVO>) session.getAttribute("keepStores");
	pageContext.setAttribute("keepStores", keepStores);
	
	String inviteResult = (String)session.getAttribute("inviteResult");
	pageContext.setAttribute("inviteResult", inviteResult);

%>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />

<html>

<head>
<title>${memberVO.mem_name}的個人頁面</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


<meta name="keywords" content="Coffee Break Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />

<!-- 連結關於javascript的程式區 開始 -->
<script>

<!-- googla map-->
function initMap() {
	  var latlng = new google.maps.LatLng(25,121.5);
	  
	  var locations = [
						<c:forEach var="articleVO" items="${list2}" begin="0" end="3">
						
							<c:forEach var="storeVO" items="${stores}" >
								<c:if test="${articleVO.store_no==storeVO.store_no }">
									
									
									["${articleVO.store_name}",
									 "${storeVO.store_longitude}",
									 "${storeVO.store_latitude}",
									 "${storeVO.store_no}",
									 "${articleVO.article_title}",
									 "${articleVO.article_click}",
									 "${storeVO.store_city}",
									 "${storeVO.store_district}",
									 "${storeVO.store_address}",
									 "${articleVO.article_no}"
									 ],
									 
								</c:if>						
							</c:forEach>
						</c:forEach>	
						
	                   
	                 ];
	  
	  
	  var map = new google.maps.Map(document.getElementById('map'), {
	    center: latlng,
	    zoom: 14,
	    styles: [{
	      featureType: 'poi',
	      stylers: [{ visibility: 'off' }]  // Turn off points of interest.
	    }, {
	      featureType: 'transit.station',
	      stylers: [{ visibility: 'off' }]  // Turn off bus stations, train stations, etc.
	    }],
	    disableDoubleClickZoom: true
	  });
	  
	  var infowindow = new google.maps.InfoWindow();
	  
	  var image='../front/personal/images/burger.png';
	 
	  
	  var marker, i;
	  var bounds = new google.maps.LatLngBounds();
	  
	  for (i = 0; i < locations.length; i++) {  
	    marker = new google.maps.Marker({
	      position: new google.maps.LatLng(locations[i][2], locations[i][1]),
	      title: locations[i][0],
	      zIndex: locations[i][3],
	      map: map,
	      icon:image
	    });
	    google.maps.event.addListener(marker, 'click', (function(marker, i) {
	      return function() {
	        infowindow.setContent(
	        		'<a href="<%=request.getContextPath()%>/front/store/store_detail.jsp?store_no='+locations[i][3]+'">店家名稱:'+locations[i][0]+'</a>'+
	        	    '<br>Latitude: ' + locations[i][1]+
	        	    '<br>Longitude: ' + locations[i][2]+
	        	    '<br>店家編號: ' + locations[i][3]+
	        	    '<br><a href="../reply/reply.do?action=getSome_For_Display&article_no='+locations[i][9]+'&article_click='+locations[i][5]+'">食記標題:'+locations[i][4]+'</a>'+
	        	    '<br>店家地址:' + locations[i][6]+ locations[i][7]+ locations[i][8]+
	        	    '<br><img width=300 src="../DBGifReader13?store_no='+locations[i][3]+'">'
	        );
	        
	        infowindow.open(map, marker);
	      }
	    })(marker, i));
	    
	    bounds.extend(marker.position);
	  }
	  
	  map.fitBounds(bounds);
	}

</script>


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
<script src="<%=request.getContextPath()%>/front/personal/js/sweetalert.min.js"></script>
<script src="<%=request.getContextPath()%>/front/personal/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/front/personal/js/jquery-ui.js"></script>
<script src="<%=request.getContextPath()%>/front/personal/js/sweetalert.min.js"></script> 

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
	
	$("#myranckedArticleImg").find("div").click(function(){
		if($(this).attr("thisname2")=='myranckedArticleImg'){
			var no1 = $(this).attr("no1");
			var no2 = $(this).attr("no2");
			
			$("#article_no2").val(no1);
			$("#article_click2").val(no2);
			$("#articles").submit();
		}
	})	
	

	$("#ranckedArticleImg").find("div").click(function(){
		if($(this).attr("thisname3")=='ranckedArticleImg'){
			var no1 = $(this).attr("no1");
			var no2 = $(this).attr("no2");
			
			$("#article_no3").val(no1);
			$("#article_click3").val(no2);
			$("#rackedArticle").submit();
		}
	})
	


	
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

 <script><!-- 今日朋友 -->
 
//  var maxNum = 10;  
//  var minNum = 1;  
//  var n = Math.floor(Math.random() * (maxNum - minNum + 1)) + minNum;  

 
 var lock=0;
 var timeTask=setInterval(function(){
     var date=new Date();
     var h=date.getHours();
     var m=date.getMinutes();
     var s=date.getSeconds();
     if(h==0&&m==0&&s==0){
    	 
    	 $('#rspTable tr').remove();
    	 $("#inviting").show();
    	 $("#randomfriend").attr("disabled", false);
    	 alert('解鎖!!');	 
    	 lock=0;    //解鎖   
     }
     
     $(document).ready(
   		 $.ajax({//取得狀態為已上線的朋友
   			 type:"post",
   			 url:"<%=request.getContextPath()%>/personal/personal.do",
   			 data:{action:"getOnlineFriend", mem_no:'${sessionScope.memberVO.mem_no}'},
   			 dataType:"json",
   			 success:function (data){
   				for(var i=0; i<Object.keys(data).length; i++){
   					data[i].mem_no;
   					data[i].mem_status;
   					
   					if(data[i].mem_status==0){
   						
   						document.getElementById('online'+data[i].mem_no).src='../front/personal/images/bullet_red.png';
   					}
   					else{
   						
   						document.getElementById('online'+data[i].mem_no).src='../front/personal/images/bullet_green.png';
   					}
   					
   				}	 
   			}
		}),

		$.ajax({
   			 type:"post",
   			 url:"<%=request.getContextPath()%>/personal/personal.do",
   			 data:{action:"getUnReadMessage", mem_no:'${sessionScope.memberVO.mem_no}'},//抓誰密我的未讀訊息數(顯示在我的頁面)
   			 dataType:"json",
   			 success:function (data){
   				for(var i=0; i<Object.keys(data).length; i++){
					data[i].mem_no;
					
					
					if(data[i].unreadMessageNum>0&&s%2==0){
						
						document.getElementById('unReadMessageNums'+data[i].mem_no).innerHTML='未讀訊息'+data[i].unreadMessageNum;
					}
					else if(data[i].unreadMessageNum>0&&s%2!=0){
						document.getElementById('unReadMessageNums'+data[i].mem_no).innerHTML="";
					}
					else if(data[i].unreadMessageNum==0){
						
						document.getElementById('unReadMessageNums'+data[i].mem_no).innerHTML="";
					}
					
				}	
   				
   			}
		})
 	)
 	
 	if(s%2==0){
		
 		document.getElementById('latestArticletag').innerHTML="&nbsp;";
	}
	else {
		document.getElementById('latestArticletag').innerHTML="最新食記";
	}

 },1000);

 

 
 $(document).ready(//  $(function(){......})
 	 function test(){
	
 	   $("#randomfriend").click(function (){//取得今日朋友

 		   if(lock==0){
 			  $.ajax({
 				 type:"post",
 				 url:"<%=request.getContextPath()%>/friend/friend.do",
 				 data:{action:"getTodayFriend", mem_no:$('#mem_no').val()},
 				 dataType:"json",
 				 success:function (data){
 					 drawTable(data);
 			     },
 	             error:function(){alert("error")}
              })
              
              lock++;
 		   }
 		   else{
 			   alert('今日朋友已找過了喔!!');
 		   }

 		  
 	   });
 	   
 	   
 	  $("#inviting").click(function (){//今日朋友的發送邀請

		   
 		  $.ajax({
 			 type:"post",
 			 url:"<%=request.getContextPath()%>/friend/friend.do",
 			 data:{action:"inviteToBeFriend1", mem_no:$('#invite_mem_no').val(), friend_no:$("tr:eq(1) td:eq(1)").text()},
 			 dataType:"json",
    
 	         })
 	         
 	        $("#inviting").hide();
 			$("#randomfriend").attr("disabled", true);
 	  
 		   });
 	  if('${inviteResult}'!=""){
 		 if('${inviteResult}'=='邀請者已經是朋友或已邀請過了'){
 			swal({   title: "邀請者已經是朋友或已邀請過了!",//秀出邀請訊息結果
 					 imageUrl: "../front/personal/images/surprise.png" });
 		 }
 		 else if('${inviteResult}'=='發送邀請成功'){
 			swal({   title: "發送邀請成功!",//秀出邀請訊息結果
 					 imageUrl: "../front/personal/images/good.png" });
 		 }
 		 <%session.setAttribute("inviteResult",null); 
 		 %>
 	  }
 	    
 	 
 	   
 })
 
 function changeColor1(){
	$(this).addClass("chgClass");
}

function changeColor2(){
	$(this).removeClass("chgClass");
}
 

function drawTable(oJson){

	 
	 var htmlStr = "<tr>";
		
			htmlStr += "<td>"+'照片'+"</td>";
			htmlStr += "<td>"+'會員編號'+"</td>";
			htmlStr += "<td>"+'會員名字'+"</td>";
			htmlStr += "<td>"+'會員暱稱'+"</td>";
			htmlStr += "<td>"+'會員生日'+"</td>";
			htmlStr += "<td>"+'專長'+"</td>";
			htmlStr += "<td>"+'興趣'+"</td>";
		
	 htmlStr += "</tr>";	
	 
	 $("#rspTable").append(htmlStr);
	 var i=0;
	 $.each(oJson,function(){
		 addTableRow(oJson[i].mem_no,oJson[i].mem_name,oJson[i].mem_nickname,oJson[i].mem_birthday,oJson[i].mem_skill,oJson[i].mem_hobby);
		 i++;
	 });	
	 
	 $("tr:odd:not(tr:first)").addClass("oddClass");
	 $("tr:even").addClass("evenClass");
	 $("tr:not(tr:first)").hover(changeColor1,changeColor2);
		

}

	function addTableRow(mem_no,mem_name,mem_nickname,mem_birthday,mem_skill,mem_hobby){
		
	   var oTable=$("#rspTable").get(0);
	   var oTr=oTable.insertRow(oTable.rows.length);
	   var aText = new Array();

	   var image = document.createElement("img");
	   	   image.src = '<%=request.getContextPath()%>/DBGifReader13?mem_no='+mem_no;
	   	   
	   	
	   	   
	   
	   aText[1]=document.createTextNode(mem_no);	   
	   aText[2]=document.createTextNode(mem_name);
	   aText[3]=document.createTextNode(mem_nickname);
	   aText[4]=document.createTextNode(mem_birthday);
	   aText[5]=document.createTextNode(mem_skill);
	   aText[6]=document.createTextNode(mem_hobby);
	   
	   
	   
	   for(var i=0;i<aText.length;i++){
	       var oTd=oTr.insertCell(i);
	       if(i==0){
	    	   oTd.appendChild(image);
	       }
	       else{
	    	   oTd.appendChild(aText[i]);
	       }
	       
	   }
	   
	}
	
	
	
	
	
	

</script>

<!-- 連結關於javascript的程式區 結束 -->


<!-- 連結關於css的程式區 開始 -->

<link href="<%=request.getContextPath()%>/front/personal/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="<%=request.getContextPath()%>/front/personal/css/personalStyle.css" rel='stylesheet' type='text/css' />
<link href="<%=request.getContextPath()%>/front/personal/css/magicsuggest-min.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/personal/css/style.css">
<link rel="stylesheet"href="<%=request.getContextPath()%>/front/personal/css/facebookchatboxUI.css"/>
<link rel="stylesheet"href="<%=request.getContextPath()%>/front/personal/css/sweetalert.css"/>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front/personal/css/sweetalert.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/personal/css/star-rating.css" media="all" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/personal/css/theme-krajee-fa.css" media="all" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/personal/css/theme-krajee-svg.css" media="all" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/personal/css/theme-krajee-uni.css" media="all" type="text/css"/>


<!-- 連結關於css的程式區 結束 -->
<style>
	.latestimgtest img {
		width:676px !important;
		height:337px !important;
		vertical-align : middle !important;
		
	}

	span img {
		width:334px !important;
		height:267px !important;
		
	}
	
	img{
    vertical-align:middle;
}
	
	
	.grid-might img {
		width:95px !important;
		height:71px !important;
		border:solid 0.1px gray"
		
	}
	
	.col-md{
		background-color:orange;
	}	
	
	#map{
		position:relative; top:20%; left:20%;
	}
	
	table img{
		width:200px;
		height:287px;
	}
	
	#latest_article_content p{
		color:black; 
		font-size:24px; 
		font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;
	}
	
	#not_latest_article_content p{
		color:black; 
		font-size:18px; 
		font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;
	}
	
	a{
    color: #06c;
	}
a:hover{
    color:#5AA0E6;
    -o-transition: color .20s linear;
    -webkit-transition: color .20s linear;
    -moz-transition: color .20s linear;
    transition:  color .20s linear;
	}
	
	.modal.fade.in{
		padding-right: 400px !important;
	}
	.modal.fade{
		padding-right: 400px !important;
	}
		
	tr:nth-child(even) {background: #FFFACD;}
	tr:nth-child(odd) {background: #FFF}
	
	table, tr, td{
		border:solid black;
		color:black; 
		font-size:24px; 
		font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;
		align:center;
		padding:10px;
	}
	
</style>
</head>

<body>
<div id="header" style="width:100%; height: 225px;">
	<iFrame src="<%=request.getContextPath()%>/front/member/JuicyTalk/Header.jsp" scrolling="no"> </iFrame>
</div>
<!--============ header  ============-->
	

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
<!-- 按鈕觸發 modal -->	<c:if test="${memberVO.mem_no==memberVO2.mem_no}">					
						<li>
							<form METHOD="post">
  								
  								<a href="" id="randomfriend" data-toggle="modal" data-target="#myModal" 
  								style="font-size:36px;font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;">今日朋友</a>
								<input type="hidden" id="mem_no" name="mem_no" value="${memberVO.mem_no}">
							</form>		
						</li>
						</c:if>
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
<!-- google map -->	
	<div id="map" style="width:1000px;height:600px;">
	</div>
	<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCLGOjC7cqIM6cJtejrXFJUQHdrTWUHzV0
        &libraries=visualization&callback=initMap">
    </script>

	<!-- chatbox-starts-->
<!-- 在個人頁面顯示某人的朋友們 (右邊聊天欄)-->	
	<div class="chat-sidebar" style="width:300px;background-color:#FFFACD;">
	
		<c:forEach var="friendVO" items="${list3}">
			<c:if test="${friendVO.friend_status==1}">
				<c:forEach var="memberVO" items="${memberSvc.all}">
					<c:if test="${friendVO.friend_no==memberVO.mem_no}">
						<div class="sidebar-name" id="sidebar-name${memberVO.mem_no}" style="width:300px;" onclick="connect('${memberVO.mem_no}','${memberVO.mem_name}','${sessionScope.memberVO.mem_no}')">
						
							
						
						<div style="display: inline-block;">
						<img id="online${memberVO.mem_no}" style="width:26px;height:26px;" src="<%=request.getContextPath()%>/front/personal/images/bullet_red.png">
						</div>
						<div style="display: inline-block;">
						<a href="javascript:register_popup('${memberVO.mem_no}', '${memberVO.mem_name}','${sessionScope.memberVO.mem_no}');">
							<img width="100" src="<%=request.getContextPath()%>/DBGifReader13?mem_no=${memberVO.mem_no}">
							<span style=" font-size:20px;">${memberVO.mem_name}</span>
						</a>
						</div>
						<div style="display: inline-block; font-size:14px; color:red; weight:bold;" id="unReadMessageNums${memberVO.mem_no}" >
						
						</div>	
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
					
						
						<c:forEach var="articleVO" items="${list2}" >
<!-- 最新食記開始 -->			<c:if test="${articleVO.article_create==latestArticle.article_create}">
							<div style="text-align:left; color:orange; font-size:36px; weight:bold;" id="latestArticletag" ></div>
							<div style="padding:20px;margin-bottom:5px; height:700px;">
							<div class="about-one">
								
								<span style="color:black; margin:5px; font-size:36px; font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;">${articleVO.article_title}</span>
							</div>
							
							<div class="about-two"><!-- 最新食記的內容 -->
								<c:set var="content" value="${articleVO.article_content}" scope="page"/>
<!-- 抓食記的裡的圖片放到食記摘要 -->		<%
									String str = (String) pageContext.getAttribute("content");						
									int imgfront = str.indexOf("<img");
									int imgback = str.indexOf("style");
									int textfront = 0;
									int textback = str.indexOf("<img");
									int imgend = imgback;
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
										
										if(str.length()>50){
											text = str.substring(0, 50);//直接抓文摘
										}
										else{
											text = str.substring(0, str.length());//直接抓文摘
										}
										
										img="<img src="+"\"/ZA105G10228/front/personal/images2/logo2.jpg\""+"/>";
									}
									else{//有抓到圖片
										if(imgfront!=3){//先放字在放圖片
											if(imgfront>50){
												text = str.substring(0, 50)+"....";
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
											
												if(str.length()-imgend>100)
												{
													text = str.substring(imgend+36,imgend+86)+"....";
													System.out.println("先放圖片在放字1");
												}
												else{
													text = str.substring(imgend+36,str.length());
													System.out.println("先放圖片在放字2");
												}
											
											
											
										}
										
									}
										
									%>
								
								<span class="latestimgtest" id="latestimgtest"><%=img %></span>
								<div style="padding:10px;margin-top:10px;height:230px; ">
								<div style="align:top;">
									<a href="../reply/reply.do?action=getSome_For_Display&article_no=${articleVO.article_no}&article_click=${articleVO.article_click}"><h6>Read More</h6></a>
								</div>
								<div id="latest_article_content" style="color:black; height:100px; margin:5px; font-size:22px; font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;"><%=text %></div>
								<div style="color:black; margin:5px; font-size:18px; font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;">點擊數:${articleVO.article_click}
									<a href="../reply/reply.do?action=getSome_For_Display&article_no=${articleVO.article_no}&article_click=${articleVO.article_click}"><h6>留言(${articleVO.article_replies})</h6></a>
								</div>
								
								</div>
								<div>
									<label style="color:black; margin:5px; font-size:22px; font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;">${articleVO.article_create.toString().substring(0,articleVO.article_create.toString().lastIndexOf(":"))}</label>
								</div>
								<div class="about-btn">
									<form id="latestArticle" METHOD="post" ACTION="<%=request.getContextPath()%>/reply/reply.do">
										<input type="hidden" name="article_no" value="${articleVO.article_no}">
										<input type="hidden" name="article_click" value="${articleVO.article_click}">
										<input type="hidden" name="action" value="getSome_For_Display">
									</form>
									
								</div>
								
							</div>		
							</div>				
<!-- 最新食記結束 -->				</c:if>
							</c:forEach>
							
							
							<form id="articles" METHOD="post" ACTION="<%=request.getContextPath()%>/reply/reply.do">
							<div id="myranckedArticleImg">
							<c:forEach var="articleVO" items="${list2}" >
<!-- 下方四篇食記開始 -->			<c:if test="${articleVO.article_create.toString().substring(0,articleVO.article_create.toString().lastIndexOf(":"))!=latestArticle.article_create.toString().substring(0,latestArticle.article_create.toString().lastIndexOf(":"))}">
							<div class="a-1">
								<div class="col-md-6 abt-left" style="padding:10px;margin-bottom:5px;margin:1px;height:530px;">	
									<c:set var="content" value="${articleVO.article_content}" scope="page"/>
<!-- 抓食記的裡的圖片放到食記摘要 -->		<%
									String str = (String) pageContext.getAttribute("content");						
									int imgfront = str.indexOf("<img");
									int imgback = str.indexOf("style");
									int textfront = 0;
									int textback = str.indexOf("<img");
									int imgend = imgback;
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
										
										if(str.length()>30){
											text = str.substring(0, 30);//直接抓文摘
										}
										else{
											text = str.substring(0, str.length());//直接抓文摘
										}
										
										img="<img src="+"\"/ZA105G10228/front/personal/images2/logo2.jpg\""+"/>";
									}
									else{//有抓到圖片
										if(imgfront!=3){//先放字在放圖片
											if(imgfront>30){
												text = str.substring(0, 30)+"....";
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
											
												if(str.length()-imgend>100)
												{
													text = str.substring(imgend+36,imgend+66);
													System.out.println("先放圖片在放字1");
												}
												else{
													text = str.substring(imgend+36,str.length());
													System.out.println("先放圖片在放字2");
												}
											
											//else{
												//	text="我沒有輸入內容";
											//	}
											
											
										}
										
									}
										
									%>
									<div thisname2="myranckedArticleImg" no1="${articleVO.article_no}" no2="${articleVO.article_click}">
									
									<span id="imgtest"><%=img %></span>
									</div>
									<div style="padding:10px;margin-top:10px;height:180px;">
										<div style="align:top;">
											<a href="../reply/reply.do?action=getSome_For_Display&article_no=${articleVO.article_no}&article_click=${articleVO.article_click}"><h6>Read More</h6></a>
											<div style="text-align:left;"><span style="font-size:36px;">${articleVO.article_title}</span></div>
										</div>
									<div id="not_latest_article_content" style="color:black; height:75px; margin:1px; font-size:18px; font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;"><%=text %></div>
									<div style="color:black; margin:5px; font-size:18px; font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;">點擊數:${articleVO.article_click}
										<a href="../reply/reply.do?action=getSome_For_Display&article_no=${articleVO.article_no}&article_click=${articleVO.article_click}"><h6>留言(${articleVO.article_replies})</h6></a>
									</div>
								
									</div>
									<div >
										<label style="color:black; margin:5px; font-size:18px; font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;">${articleVO.article_create.toString().substring(0,articleVO.article_create.toString().lastIndexOf(":"))}</label>
									</div>
									
								
								</div>
							</div>	
<!-- 下方四篇食記結束 -->		</c:if>
						</c:forEach>
						</div>
						<input type="hidden" name="article_no" id="article_no2">
						<input type="hidden" name="article_click" id="article_click2">
						<input type="hidden" name="action" value="getSome_For_Display">
						</form>
							<div class="clearfix"></div>
					</div>
				</div>
				
				 
<!-- 今日朋友 -->				
				<div class="col-md-4 about-right heading" >
					
				  <div class="container">
					<!-- Modal -->
  					<div class="modal fade" id="myModal" role="dialog" >
   					<div class="modal-dialog">
    
     				 <!-- Modal content-->
      				<div align="center" class="modal-content" style="width:1000px;">
        				<div class="modal-header">
          					<button type="button" class="close" data-dismiss="modal">&times;</button>
         					 <h4 class="modal-title">今日朋友</h4>
        				</div>
        				<div class="modal-body" id="modal-body" style=" height:500px;">
          					<table id="rspTable" border="1">
          					</table>

        				</div>
        				<div>
        					
								<input type="button" id="inviting" class="btn btn-default" value="交朋友">	
								<input type="hidden" id="invite_mem_no" value="${memberVO.mem_no}">
								<input type="hidden" id="invite_friend_no" value="${todayFriend_no}">		 
								<input type="hidden" id"="invite_friend_status" value="0">	   					     				        
								
 							
 							
        				</div>
        				<div class="modal-footer">
          					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        				</div>
     				</div>
      
    				</div>
  					</div>
				</div>
			
<!-- 本人資訊 -->			
					<div class="abt-1">
						
						
						<a href="../personal/personal.do?action=getPersonal_Display&mem_no=${memberVO2.mem_no}&login_mem_no=${memberVO.mem_no}"><h3 style="font-size:26px;">${memberVO2.mem_name}</h3></a>
						<div class="abt-one">
							<img width="200"src="<%=request.getContextPath()%>/DBGifReader13?mem_no=${memberVO2.mem_no}">
							           
							<c:if test="${sessionScope.memberVO.mem_no!=memberVO2.mem_no}">
				
							<div class="a-btn">
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/friend/friend.do">
									<input type="image" src="<%=request.getContextPath()%>/front/personal/images/addFriend.png">
									<input type="hidden" name="mem_no" value="${sessionScope.memberVO.mem_no}">
									<input type="hidden" name="friend_no" value="${memberVO2.mem_no}">
									<input type="hidden" name="action" value="inviteToBeFriend2">
									
								</FORM>
							</div>
							</c:if>
						</div>
					</div>
					
<!-- 食記排名 -->				
	
				   <div class="abt-2" >
						<h3 style="font-size:26px;">食記排行榜</h3>	
<!-- 推荐食記排名-->		<form id="rackedArticle" METHOD="post" ACTION="<%=request.getContextPath()%>/reply/reply.do">
							<div id="ranckedArticleImg">
							<c:forEach var="articleVO" items="${rankedArticles}" varStatus="s">
								<c:set var="content" value="${articleVO.article_content}" scope="page"/>
<!-- 抓食記的裡的圖片.摘要放到食記摘要 --><%
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
										
										if(str.length()>10){
											text = str.substring(0, 10);//直接抓文摘
										}
										else{
											text = str.substring(0, str.length());//直接抓文摘
										}
											System.out.println("沒圖片");
											//img="<img src="+"\"images2/logo2.jpg\""+"/>";
											img="<img src="+"\"/ZA105G10228/front/personal/images2/logo2.jpg\""+"/>";
									}
									else{//有抓到圖片
											if(imgfront!=3){//先放字在放圖片
												if(imgfront>50){
													text = str.substring(0, 50);
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
														text = str.substring(imgend,imgend+60)+"....";
													System.out.println("先放圖片在放字1");
														}
													else{
														text = str.substring(imgend,str.length())+"....";
														System.out.println("先放圖片在放字2");
													}							
												}				
										}
								%>	
<!-- 抓食記的裡的圖片.摘要放到食記摘要 -->
						
								<div class="might-grid" style="padding:10px;border-bottom:solid 0.1px gray; width:350px;">
									<div style="float:left;">
										<span style="font-size:22px;">${s.count }</span>
									</div>
									<div class="grid-might" thisname3="ranckedArticleImg" no1="${articleVO.article_no}" no2="${articleVO.article_click}">
										<%=img%> 
									</div>
									<div class="might-top" style=" width:221px; height:71px; vertical-align:middle;">
										<div style="width:221px !important; color:black; margin:5px; font-size:24px; font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;">
											
											<a style="width:221px" href="../reply/reply.do?action=getSome_For_Display&article_no=${articleVO.article_no}&article_click=${articleVO.article_click}">${articleVO.article_title}</a>
										</div>
									</div>
									<div class="clearfix"></div>
								
								</div>	
									
							</c:forEach>
						</div>
						<input type="hidden" name="article_no" id="article_no3">
						<input type="hidden" name="article_click" id="article_click3">
						<input type="hidden" name="action" value="getSome_For_Display">
						</form>
					</div>
					
<!-- 收藏店家 -->					
					<div class="abt-2">
						<h3 style="font-size:26px;">最新收藏店家</h3>
						
						
						
						<c:forEach var="storeVO" items="${keepStores}" >
						
						<div class="might-grid">
							<div class="grid-might">
								<a href="<%=request.getContextPath()%>/front/store/store_detail.jsp?store_no=${storeVO.store_no}"><img width="95"src="<%=request.getContextPath()%>/DBGifReader13?store_no=${storeVO.store_no}"></a>
							</div>
							<div class="might-top">
								<h4>
									<a href="<%=request.getContextPath()%>/front/store/store_detail.jsp?store_no=${storeVO.store_no}">${storeVO.store_name}</a>
								</h4>
								<p>地區：${storeVO.store_city}</p>
								<p>分類：${storeVO.store_type}</p>
							</div>
							<div class="clearfix"></div>

						</div>
						</c:forEach>
					</div>
					
					

<!-- 在朋友頁面顯示某人的朋友們 --> 
<!-- 					<div class="myFriend"> -->
<!-- 						<h3>我的朋友</h3> -->
<%-- 						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/friend/friend.do"> --%>
<!-- 							<input type="submit" value="我的朋友"> -->
<%-- 							<input type="hidden" name="mem_no" value="${memberVO2.mem_no}"> --%>
<!-- 							<input type="hidden" name="action" value="getSome_For_Display"> -->
<!-- 						</FORM> -->
<!-- 					</div>	 -->

<!-- 在個人頁面顯示某人的朋友們 -->		
<!-- 				 <table border='5' bordercolor='#CCCCFF'> -->

<%-- 							<c:forEach var="memberVO" items="${memberSvc.all}"> --%>
<%-- 								<c:if test="${param.mem_no==memberVO.mem_no}"> --%>
<%-- 									<h1>${memberVO.mem_name}的朋友們<3</h1> --%>
<%-- 								</c:if> --%>
<%-- 							</c:forEach> --%>
<!-- 							</br> -->
<%-- 							<%@ include file="page1A.file"--%> 
<%-- 							<c:forEach var="friendVO" items="${list}" begin="<%=pageIndex%>" --%>
<%-- 								end="<%=pageIndex+rowsPerPage-1%>"> --%>
<%-- 								<c:if test="${friendVO.friend_status==1}"> --%>
									
<!-- 									<tr align='center' valign='middle'> -->
<!-- 										朋友左排 -->
<%-- 										<td><c:forEach var="memberVO" items="${memberSvc.all}"> --%>
<%-- 												<c:if test="${friendVO.friend_no==memberVO.mem_no}"> --%>
<%-- 													<img width="100" src="<%=request.getContextPath()%>/DBGifReader3?mem_no=${memberVO.mem_no}" > --%>
<%-- 													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/personal/personal.do"> --%>
<!-- 														<input type="submit" value="個人頁面"> -->
<%-- 														 <input type="hidden" name="mem_no" value="${memberVO.mem_no}"> --%>
<%-- 														 <input type="hidden" name="login_mem_no" value="${sessionScope.memberVO.mem_no}"> --%>
<!-- 														<input type="hidden" name="action" value="getPersonal_Display"> -->
<!-- 													</FORM> -->
<%-- 	                    								${memberVO.mem_name} --%>
<%-- 												</c:if> --%>
<%-- 											</c:forEach> --%>
<!-- 										</td> -->
<!-- 									</tr> -->
<%-- 								</c:if> --%>
<%-- 							</c:forEach> --%>
<%-- 							<%@ include file="page2A.file"%> --%>
<!-- 						</table>	 -->

						
					
<!-- 我的文章 -->
<!-- 					<div class="myArticle"> -->
<!-- 						<h3>我的文章</h3> -->
<!-- 						<ul> -->
<!-- 							<li> -->
<%-- 								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/reply/reply.do"> --%>
<!-- 									<b><font color=orange>選擇食記:</font></b>  -->
<!-- 									<select size="1" name="article_no"> -->
<%-- 										<c:forEach var="articleVO" items="${articleSvc.all}"> --%>
<%-- 											<c:if test="${memberVO2.mem_no==articleVO.mem_no}"> --%>
<%-- 											<option value="${articleVO.article_no}">${articleVO.article_title} --%>
<%-- 											</c:if> --%>
<%-- 										</c:forEach> --%>
<!-- 									</select>  -->
<!-- 									<input type="submit" value="送出"> -->
<!-- 									<input type="hidden" name="action" value="getSome_For_Display"> -->
<!-- 								</FORM> -->
<!-- 							</li> -->
<!-- 						</ul> -->
<!-- 					</div> -->
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	
<!-- 下方店家廣告輪播 -->
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
<!--============ footer ============-->
<div id="footer" style="width: 100%; height: 455px;">
	<iFrame style="width:100%; height: 455px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/footer.jsp" scrolling="no"> </iFrame>
</div>
<!--============ footer  ============-->
</body>
<script>

	var MyPoint = "/MyEchoServer";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	
	var statusOutput = document.getElementById("statusOutput");
	var webSocket = new Array(50);
	var c=0;
	var myMap = new Map();
	

	function connect(reciever_no,reciever_name,sender_no) {
		param2='popup-messages'+reciever_no;

		 $.ajax({
				 type:"post",
				 url:"<%=request.getContextPath()%>/personal/personal.do",
				 data:{action:"get_message", mem_no:sender_no, friend_no:reciever_no},
				 dataType:"json",
				success:function (data){
					var messagesArea = document.getElementById(param2);
					for(var i=0; i<data.length; i++){
						message = data[i].sender + ": " + data[i].talk_note + "\r\n";
						messagesArea.value = messagesArea.value + message;
	 					messagesArea.scrollTop = messagesArea.scrollHeight;
					}
					//var messagesArea = document.getElementById(param2);
					//var jsonObj = JSON.parse(data);
					//var message = jsonObj.mem_no + ": " + jsonObj.talk_note + "\r\n";
					//messagesArea.value = messagesArea.value + message;
					//messagesArea.scrollTop = messagesArea.scrollHeight;
		     },
             error:function(){alert("error")}
             })	
    	
			 	   
			 
		
		
		
		if(myMap.get(reciever_name)!=1){
			
			
			// 建立 websocket 物件
			var endPointURL = "ws://" + window.location.host + webCtx + MyPoint + '/'+sender_no + '/'+reciever_no;
			webSocket[reciever_no] = new WebSocket(endPointURL);

			
			myMap.set(reciever_name, 1);
			
		}
		
		
		webSocket[reciever_no].onopen = function(event) {
			updateStatus("WebSocket 成功連線");
			
		};

		webSocket[reciever_no].onmessage = function(event) {//後
			
			
		    
			param2='popup-messages'+reciever_no;
			
			
			var messagesArea = document.getElementById(param2);
			var jsonObj = JSON.parse(event.data);
			var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
			messagesArea.value = messagesArea.value + message;
			messagesArea.scrollTop = messagesArea.scrollHeight;
			
			$.ajax({
				 type:"post",
				 url:"<%=request.getContextPath()%>/personal/personal.do",
				 data:{action:"update_message_status", mem_no:sender_no, friend_no:reciever_no},
				 dataType:"json",
				success:function (){
					
					}
					
		     
            
             })	
			
			
		};

		webSocket[reciever_no].onclose = function(event) {
			//alert('WebSocket 已離線:'+myMap.get(reciever_name));
			myMap.set(reciever_name, 0);
			updateStatus("WebSocket 已離線");
			
		};
	}

	var inputUserName = '${memberVO.mem_name}';
	
	inputUserName.focus();

	function sendMessage(reciever_no,sender_no) {
		param='message'+reciever_no;
		
		var userName = inputUserName.trim();
		var inputMessage = document.getElementById(param);
		var message = inputMessage.value.trim();

		if (message === "") {
			alert("訊息請勿空白!");
			inputMessage.focus();
		} else {
			var jsonObj = {
				"userName" : userName,
				"message" : message
			};
			webSocket[reciever_no].send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}

	function disconnect(reciever_no) {
		
		webSocket[reciever_no].close();
		//alert('XDD');
		
	}

	function updateStatus(newStatus) {
		statusOutput.innerHTML = newStatus;
	}

</script>

</html>