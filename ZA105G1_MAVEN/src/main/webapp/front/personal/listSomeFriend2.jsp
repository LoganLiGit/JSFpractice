<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.friend.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	FriendService friendSvc = new FriendService();

List<FriendVO> list = (List<FriendVO>) request.getAttribute("friends"); //EmpServlet.java(Concroller), 存入req的empVO物件
System.out.println("listSomeFriend查到的朋友筆數:"+list.size());
pageContext.setAttribute("list",list);
%>

<jsp:useBean id="memberSvc" scope="page"
	class="com.member.model.MemberService" />

<html>

<head>
<title>肯德基的朋友頁面</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<meta name="keywords"
	content="Coffee Break Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />

<script type="application/x-javascript">
	
	
	
	
		addEventListener("load", function () {
			setTimeout(hideURLbar, 0);
		}, false);

		function hideURLbar() {
			window.scrollTo(0, 1);
		}
	



</script>
<link href="<%=request.getContextPath()%>/front/personal/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="<%=request.getContextPath()%>/front/personal/css/personalStyle.css" rel='stylesheet' type='text/css' />
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/personal/css/style.css">
<script src="js/jquery.min.js"></script>
<!---- start-smoth-scrolling---->
<script type="text/javascript" src="<%=request.getContextPath()%>/front/personal/js/move-top.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/front/personal/js/easing.js"></script>
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
<!--start-smoth-scrolling-->
</head>

<body>
<div id="header" style="width: 100%; height: 225px;">
	<iFrame style="width:100%; height: 225px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/Header.jsp" scrolling="no"> </iFrame>
</div>
	

		
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


			</div>
		</div>
	<script>
		$("span.menu").click(function() {
			$(" ul.navig").slideToggle("slow", function() {
			});
		});
	</script>
	<!-- script-for-menu -->
	
	<!--about-starts-->
	<div class="about">
		<div class="container">
			<div class="about-main">
			
			<!-- 以下是某人的邀請名單 -->
<table border='1' bordercolor='#CCCCFF' width='800'>
    
    <span style="color:black; margin:5px; font-size:30px; font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;">${memberVO2.mem_name}的朋友邀請名單<span>
	<%@ include file="page1A.file" %>     
	<c:forEach var="friendVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<c:if test="${friendVO.friend_status==0}">
                    
		<tr align='center' valign='middle'${(friendVO.friend_no==param.friend_no) ? 'bgcolor=orange':''}>	
			<!-- 朋友左排 -->
			<td width="75">
			<c:forEach var="memberVO" items="${memberSvc.all}">
                    <c:if test="${friendVO.friend_no==memberVO.mem_no}">
	                    <img width="75" src="<%=request.getContextPath()%>/DBGifReader13?mem_no=${memberVO.mem_no}">
        			</c:if>
            </c:forEach>
			</td>
			
			<td>
			<c:forEach var="memberVO" items="${memberSvc.all}">
                <c:if test="${friendVO.friend_no==memberVO.mem_no}">
                <a href="../personal/personal.do?action=getPersonal_Display&mem_no=${memberVO.mem_no}&login_mem_no=${sessionScope.memberVO.mem_no}" style="color:black; margin:5px; font-size:30px; font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;">${memberVO.mem_name} - ${memberVO.mem_nickname}</a>
                 
                </c:if>
            </c:forEach>
			</td>
			
			<c:if test="${memberVO.mem_no==memberVO2.mem_no}">
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/friend/friend.do">
			     <input type="image" src="<%=request.getContextPath()%>/front/personal/images/accept.png" style="width:50px; height:50px" > 
			     <input type="hidden" name="mem_no" value="${friendVO.mem_no}">
			     <input type="hidden" name="friend_no" value="${friendVO.friend_no}">
			     <input type="hidden" name="friend_status" value="1">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"value="update2"><!-- 我接受朋友邀請 ,我這邊更新朋友狀態 -->
			     <input type="hidden" name="action2"value="insert"><!-- 我接受朋友邀請 ,朋友那邊新增我這個朋友 -->
			  </FORM>	
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/friend/friend.do">
			     <input type="image" src="<%=request.getContextPath()%>/front/personal/images/refuse.png" style="width:50px; height:50px"> 
			     <input type="hidden" name="mem_no" value="${friendVO.mem_no}">
			     <input type="hidden" name="friend_no" value="${friendVO.friend_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"value="delete"></FORM>
			</td>
			</c:if>
			
		</tr>
		</c:if>
	</c:forEach>
	
</table>
				
				<%@ include file="page2A.file"%>
				
				
			<!-- 以下是某人的朋友們 -->
<table border='1' bordercolor='#CCCCFF' width='800'>
	<c:forEach var="memberVO" items="${memberSvc.all}">
                <c:if test="${param.mem_no==memberVO.mem_no}">
	                 <span style="color:black; margin:5px; font-size:30px; font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;">${memberVO2.mem_name}的朋友們</span>
                </c:if>
    </c:forEach>
    </br>
	<c:forEach var="friendVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<c:if test="${friendVO.friend_status==1}">
	                   
                    
		<tr align='center' valign='middle'${(friendVO.friend_no==param.friend_no) ? 'bgcolor=orange':''}>	
			<!-- 朋友左排 -->
			<td width="75">
			<c:forEach var="memberVO" items="${memberSvc.all}">
                    <c:if test="${friendVO.friend_no==memberVO.mem_no}">
	                    <img width="75" src="<%=request.getContextPath()%>/DBGifReader13?mem_no=${memberVO.mem_no}">
                    </c:if>
            </c:forEach>
			</td>
			
			<td>
			<c:forEach var="memberVO" items="${memberSvc.all}">
                    <c:if test="${friendVO.friend_no==memberVO.mem_no}">
	                    <a href="../personal/personal.do?action=getPersonal_Display&mem_no=${memberVO.mem_no}&login_mem_no=${sessionScope.memberVO.mem_no}" style="color:black; margin:5px; font-size:30px; font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;">${memberVO.mem_name} - ${memberVO.mem_nickname}</a>
                    </c:if>
            </c:forEach>
			</td>
			
			<c:if test="${memberVO.mem_no==memberVO2.mem_no}">
			<td>
			  	 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/friend/friend.do">
			   	  	<input type="image" src="<%=request.getContextPath()%>/front/personal/images/delete.png" style="width:50px; height:50px" onClick="document.deleteArticleForm.submit();"> 
			     	<input type="hidden" name="mem_no" value="${friendVO.mem_no}">
			    	<input type="hidden" name="friend_no" value="${friendVO.friend_no}">
			    	<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			     	<input type="hidden" name="whichPage"	value="<%=whichPage%>">              
			     	<input type="hidden" name="action"value="delete">
			     </FORM>
			</td>
			</c:if>
			
		</tr>
		</c:if>
	</c:forEach>
	
</table>
				


			</div>

		</div>
	</div>
	<!--about-end-->
	<!--slide-starts-->
	<div class="slide">
		<div class="container">
			<div class="fle-xsel">
				<ul id="flexiselDemo3">
					<li><a href="#">
							<div class="banner-1">
								<img src="images2/01.jpg" class="img-responsive" alt="">
							</div>
					</a></li>
					<li><a href="#">
							<div class="banner-1">
								<img src="images2/02.jpg" class="img-responsive" alt="">
							</div>
					</a></li>
					<li><a href="#">
							<div class="banner-1">
								<img src="images2/ad1.jpg" class="img-responsive" alt="">
							</div>
					</a></li>
					<li><a href="#">
							<div class="banner-1">
								<img src="images2/ad2.jpg" class="img-responsive" alt="">
							</div>
					</a></li>
					<li><a href="#">
							<div class="banner-1">
								<img src="images2/ad3.jpg" class="img-responsive" alt="">
							</div>
					</a></li>
					<li><a href="#">
							<div class="banner-1">
								<img src="images2/ad5.jpg" class="img-responsive" alt="">
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
	<!--slide-end-->
<!--============ footer ============-->
<div id="footer" style="width: 100%; height: 455px;">
	<iFrame style="width:100%; height: 455px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/footer.jsp" scrolling="no"> </iFrame>
</div>
<!--============ footer  ============-->
</body>

</html>