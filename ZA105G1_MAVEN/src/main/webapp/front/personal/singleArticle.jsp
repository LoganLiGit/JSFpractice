<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.reply.model.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.member.model.*"%>

<jsp:useBean id="articleSvc" scope="page" class="com.article.model.ArticleService" />
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="replySvc" scope="page" class="com.reply.model.ReplyService" />
<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />


<%

List<ReplyVO> list = (List<ReplyVO>) request.getAttribute("replies"); //EmpServlet.java(Concroller), 存入req的empVO物件
System.out.println("listSomeReply查到的回覆筆數:"+list.size());
pageContext.setAttribute("list",list);


MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
ReplyVO replyVO = (ReplyVO) request.getAttribute("replyVO");
List<MemberVO> allMembers = (List<MemberVO>) session.getAttribute("allMembers"); 


%>



<html>
<head>
<title>${articleVO.article_title}</title>
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

 <script>
 $(document).ready(//  $(function(){......})<!-- 檢舉食記 -->
 	 function test(){
 		
 	   $("#addArticleReport").click(function (){	   
 		  
 		  swal({   title: "你確定要檢舉這篇食記嗎?",   
				  text: "亂檢舉自己也會受到處罰喔!!",   
				  type: "warning",   
				  showCancelButton: true,   
				  confirmButtonColor: "#DD6B55",   
				  confirmButtonText: "檢舉!",   
				  cancelButtonText: "不檢舉了!",   
				  closeOnConfirm: false,   
				  closeOnCancel: true 
			  }, 
			  function(isConfirm){   
				if (isConfirm) {     
					$.ajax({
		 				 type:"post",
		 				 url:"<%=request.getContextPath()%>/article/article.do",
		 				 data:{action:"insertArticleReport", mem_name:$('#reportMem_name').val(), article_no:$('#reportArticle_no').val()
		 					 , report_content:$('#report_content').val(), report_status:"0"},
		 				 dataType:"json",
		 				 
		              })
		              
		              swal("你的檢舉已送出!","", "success")
				
			  } else {      
				return;
		 	  }
			});
 		  
 		   
 	   });
 	   
 	  $("#deleteArticle").click(function (){	   
 		  
 		 var form = $(this).parents('form');
 		  
 		 swal({   title: "你確定要刪除食記嗎?",   
 				  text: "刪除後無法回覆!!",   
 				  type: "warning",   
 				  showCancelButton: true,   
 				  confirmButtonColor: "#DD6B55",   
 				  confirmButtonText: "刪掉吧!",   
 				  cancelButtonText: "不刪了!",   
 				  closeOnConfirm: false,   
 				  closeOnCancel: true 
 			  }, 
 			  function(isConfirm){   
 				if (isConfirm) {     
 					swal("Deleted!", "Your imaginary file has been deleted.", "success");
 				form.submit();
 				
 			  } else {      
 				return;
 		 	  }
 			});
	   });
 	  
 	 $("#addReply").click(function (){	   
		  
 		 var form = $(this).parents('form');
 		  
 		 swal({   
 			 	title: "即將新增留言回復",     
 			 	type: "info",   
 			 	showCancelButton: true,   
 			 	closeOnConfirm: false,   
 			 	showLoaderOnConfirm: true,
 			  }, 
 			  function(isConfirm){ 
 				 if(isConfirm){
 					setTimeout(function(){
 	 					
 	 					form.submit();
 	 				}, 2000); 
 				 }
 				 else{
 					 return;
 				 }
 				 
 			});
 		
	   });
 	 
 	   
 	  
 


 

 	 
 	   
 	  $("#addReplyArea").hide();
 	  $("#showReply").click(function (){	   
 		 $("#showReply").hide();
 		 $("#addReplyArea").show();
	   });
 })
 


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

<style>
	#article_content p, #reply_msg p{
		color:black; 
		font-size:24px; 
		font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;
	}
	
	.modal.fade.in{
		padding-right: 300px !important;
	}
	.modal.fade{
		padding-right: 300px !important;
	}
</style>

<!-- 連結關於css的程式區 結束 -->
<!--start-smoth-scrolling-->
</head>
<body>
<div id="header" style="width: 100%; height: 225px;">
	<iFrame style="width: 100%; height: 225px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/Header.jsp" scrolling="no"> </iFrame>
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
	<script>
		$("span.menu").click(function() {
			$(" ul.navig").slideToggle("slow", function() {
			});
		});
	</script>
	<!-- script-for-menu -->

	<!--start-single-->
	<div class="single">
		<div class="container">
			<div class="single-top">

				<div class=" single-grid">
					<div style="background-color:#FFB90F;">
					<span style="color:black; margin:5px; font-size:36px; font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;">${articleVO.article_title}</span>
					<ul class="blog-ic">
						<li><span style="color:black; margin:5px; font-size:22px; font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;"><i class="glyphicon glyphicon-user">
						 <c:forEach var="member" items="${allMembers}">
						 
						 <c:if test="${member.mem_no==articleVO.mem_no}">
							</i><a href="../personal/personal.do?action=getPersonal_Display&mem_no=${articleVO.mem_no}&login_mem_no=${sessionScope.memberVO.mem_no}">${member.mem_name}</a></span></li>
						 </c:if>
						 </c:forEach>
						<li><span style="color:black; margin:5px; font-size:22px; font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;"><i class="glyphicon glyphicon-time" > 
							</i>${articleVO.article_create.toString().substring(0,articleVO.article_create.toString().lastIndexOf(":"))}</span></li>
						<li><span style="color:black; margin:5px; font-size:22px; font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;"><i class="glyphicon glyphicon-eye-open">
							</i>點擊數:${articleVO.article_click}</span></li>
						<li><span style="color:black; margin:5px; font-size:22px; font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;"><i class="glyphicon glyphicon-eye-open">
							</i>作者的食記評分:${articleVO.article_score}</span></li>	
						<li><span style="color:black; margin:5px; font-size:22px; font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;" ><i class="glyphicon glyphicon-eye-open">
							</i>食記狀態:${articleVO.article_status}</span></li>
						<li><span style="color:black; margin:5px; font-size:22px; font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;"><i class="glyphicon glyphicon-eye-open">
							</i>店家名稱:${articleVO.store_name}</span></li>
						<c:if test="${articleVO.mem_no==sessionScope.memberVO.mem_no}">
						
						<li style="width:50px; height:50px; display:inline-block;" >
							
							
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do">	
								<input type="hidden" name="article_no" value="${articleVO.article_no}">
								<input type="hidden" name="mem_no" value="${memberVO.mem_no}">
								<input type="hidden" name="action" value="delete">
								<input type="button" id="deleteArticle" style="background-image:url(<%=request.getContextPath()%>/front/personal/images/delete.png); width:50px;height:50px ; background-color:#FFB90F; background-size: cover;">
							</FORM>
						</li>
						<li style="display:inline-block;">
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do" name="fixArticleForm">
								<input type="button" style="background-image:url(<%=request.getContextPath()%>/front/personal/images/fix.png); width:50px; height:50px; background-size: cover; background-color:#FFB90F;" onClick="document.fixArticleForm.submit();"> 
								<input type="hidden" name="article_no" value="${articleVO.article_no}">
								<input type="hidden" name="action" value="updateMyArticle">
								
							</FORM>
						</li>
						
						</c:if>	
						<c:if test="${sessionScope.memberVO.mem_no!=articleVO.mem_no}">
						<li>
							<input type="button" id="reportArticle" data-toggle="modal" data-target="#myModal" style="background-image:url(<%=request.getContextPath()%>/front/personal/images/report.png); width:50px; height:50px; background-size: cover; background-color:#FFB90F;"> 
						</li>
						</c:if>
					</ul>
					</div>
					<div id="article_content" style="background-color:#F7F7F7">
						${articleVO.article_content}
					</div>
				<div class="container">
					<!-- Modal -->
  					<div class="modal fade" id="myModal" role="dialog" >
   					<div class="modal-dialog">
    
     				 <!-- Modal content-->
      				<div align="center" class="modal-content" style="width:800px;">
        				<div class="modal-header" style="background-color:#FFFACD;">
          					<button type="button" class="close" data-dismiss="modal">&times;</button>
         					 <h4 class="modal-title">檢舉食記</h4>
        				</div>
        				<div class="modal-body" id="modal-body" style=" height:400px;width:800px;">
		    			檢舉人:<input type="text" id="reportMem_name" value="${sessionScope.memberVO.mem_name}" readonly><br>
		    				   <input type="hidden" id="reportArticle_no" value="${articleVO.article_no}">
       					檢舉內容:
       						   <textarea  style="width:500px; height:300px;"id="report_content" value="請輸入食記檢舉內容!!!"></textarea>
       						   <input type="submit" id="addArticleReport" class="btn btn-default" value="送出檢舉">	
          					  		
        				</div>
        				<div>       		
								 
								   					     				        
        				</div>
        				<div class="modal-footer">
          					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        				</div>
     				</div>
      
    				</div>
  					</div>
				</div>
					
					
					
					
				</div>
				<div style="font-weight: bold; color:red; font-size:40px; font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;">
				目前有${articleVO.article_replies}篇回應
				</div>
				<c:forEach var="replyVO" items="${list}">
					
						<div style="border-bottom:solid 1px; background-color:#F7F7F7; margin:10px; width:1120px; height:150px;">
							<div class="media-body" style="width:1120px; height:100px !important; margin-top:0px;">
								<h4 class="media-heading"  style="background-color:#FF7F00">
									<c:forEach var="memberVO" items="${memberSvc.all}">
										<c:if test="${replyVO.mem_no==memberVO.mem_no}">
	                  					  <span style="color:black; margin:5px; font-size:22px; font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;">
	                  					  		<c:forEach var="member" items="${allMembers}">
						 
													 <c:if test="${member.mem_no==replyVO.mem_no}">
														<a href="../personal/personal.do?action=getPersonal_Display&mem_no=${replyVO.mem_no}&login_mem_no=${sessionScope.memberVO.mem_no}">${member.mem_name}</a></span><br>
													 </c:if>
												 </c:forEach>
	                  					  
	                  					  <span style="color:black; margin:5px; font-size:16px; font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;">留言時間:${replyVO.reply_time}</span>
                   						 </c:if>
									</c:forEach>
									
								
									<c:if test="${replyVO.mem_no==sessionScope.memberVO.mem_no}">
								    <form METHOD="post" ACTION="<%=request.getContextPath()%>/reply/reply.do" style="width:200px" name="replyForm">
										<input type="image" src="<%=request.getContextPath()%>/front/personal/images/delete.png" style="width:50px; height:50px" onClick="document.replyForm.submit();">
										<input type="hidden" name="reply_no" value="${replyVO.reply_no}">
										<input type="hidden" name="article_no" value="${replyVO.article_no}">
							   		    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
										<input type="hidden" name="action" value="delete2">
									</form>
									</c:if>
								
								
								</h4>
								
								<c:forEach var="articleVO" items="${articleSvc.all}">
									<c:if test="${replyVO.article_no==articleVO.article_no}">
                 					    <div id="reply_msg">
											 <p>${replyVO.reply_msg}</p>
										</div>
                   					</c:if>
								</c:forEach>
							</div>
							<div class="media-right" style="padding-left:0px !important">		
								<c:forEach var="memberVO" items="${memberSvc.all}">
									<c:if test="${replyVO.mem_no==memberVO.mem_no}">
										<img width="100" height="143" src="<%=request.getContextPath()%>/DBGifReader13?mem_no=${memberVO.mem_no}"></br>
										
									</c:if>
								</c:forEach>
								
								
							</div>
						</div>
						
					
				</c:forEach>


				<div class="comment-bottom heading">

				<div style="text-align:center;">
					<input type="button" id="showReply" style="background-image:url(<%=request.getContextPath()%>/front/personal/images/reply.png); width:50px; height:50px; background-size: cover;" > 
				</div>
				
				<div id="addReplyArea" style="text-align:center;">
					
					<form METHOD="post" ACTION="<%=request.getContextPath()%>/reply/reply.do" name="form1" style="margin: 0 auto;">
						<input type="hidden" name="mem_no" size="45" value="${sessionScope.memberVO.mem_no}" />
						<input type="hidden" name="article_no" size="45" value="<%=(articleVO == null) ? "1" : articleVO.getArticle_no()%>" />
	
						<textarea style="height:80px; background-color:#FFFACD" cols="77" rows="6" name="reply_msg" value=" "
							onfocus="this.value='';"
							onblur="if (this.value == '') {this.value = 'Message';}">Message</textarea>
						<input type="hidden" name="action" value="insert2"> 
						<input type="button" id="addReply" class=" btn btn-lg" value="送出新增">
					</form>
				</div>

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
	
</body>
</html>