<%@ page contentType="text/html; charset=Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO_onemember"); //EmpServlet.java(Concroller), 存入req的memberVO物件
%>
<html>

<head>
<title>肯德基的個人頁面</title>
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


<script>
	var file = Array(5);
	var fileReader;
	function doFirst()
	{
  		 document.getElementById('myFile').onchange = fileChange;
	}
	
	
	function fileChange()
	{
		
	    file = document.getElementById('myFile').files[0];
	    if(file)
	    {
	        fileReader = new FileReader();
	        fileReader.readAsDataURL(file);
	        fileReader.onload = openFile;
	    }
	    
	    
	}
	
	function openFile(e)
	{
	    //document.getElementById('fileContent').value = fileReader.result;
	    document.getElementById('image').src = e.target.result;
	}
	
	window.addEventListener('load',doFirst,false);	
	
</script>


<link href="<%=request.getContextPath()%>/front/personal/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="<%=request.getContextPath()%>/front/personal/css/personalStyle.css" rel='stylesheet' type='text/css' />
<link href="<%=request.getContextPath()%>/front/personal/css/style.css" rel='stylesheet' type='text/css' />
<script src="<%=request.getContextPath()%>/front/personal/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/front/personal/js/bootstrap.js"></script>
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

<style>
	table, tr, td{
		border:solid black;
		color:black; 
		font-size:24px; 
		font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;
		
	}
	td{
		width:200px;
	}
	table{
		wifth:10px;
		margin-left:300px;
	}
	
	tr:nth-child(even) {background: #CCC}
	tr:nth-child(odd) {background: #FFF}
	
	
	.modal.fade.in{
		padding-right: 400px !important;
	}
	.modal.fade{
		padding-right: 400px !important;
	}
</style>

</head>

<body>
<!-- 右上方登入登出 -->
<!--============ header ============-->
<div id="header" style="width:100%; height: 225px;">
	<iFrame style="width:100%; height: 225px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/Header.jsp" scrolling="no"> </iFrame>
</div>
<!--============ header ============-->
	
		
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
	<!--banner-starts-->
	
	<!--banner-end-->
	<!--about-starts-->
	
	<div align='center' valign='middle' style="font-size:36px;">
		<img width="300" src="<%=request.getContextPath()%>/DBGifReader13?mem_no=<%=memberVO.getMem_no()%>"></p>
		<%=memberVO.getMem_nickname()%>-<%=memberVO.getMem_name()%>
		
			<div>
<!-- 按鈕觸發 modal -->	
				<c:if test="${memberVO2.mem_no==sessionScope.memberVO.mem_no}">						
				<input type="button" id="addPhoto" class=" btn btn-lg" data-toggle="modal" data-target="#myModal" value="換一張更漂亮的照片">	
				</c:if>
			</div>
			
			
			<div class="container">
					<!-- Modal -->
  					<div class="modal fade" id="myModal" role="dialog" >
   					<div class="modal-dialog">
    
     				 <!-- Modal content-->
      				<div align="center" class="modal-content" style="width:1000px;">
        				<div class="modal-header">
          					<button type="button" class="close" data-dismiss="modal">&times;</button>
         					 <h4 class="modal-title">換一張照片</h4>
        				</div>
        				<div class="modal-body" id="modal-body" style=" height:500px;">
        					<form METHOD="post" ACTION="<%=request.getContextPath()%>/personal/personal.do" enctype="multipart/form-data">
	        						   <input type="file" name="mem_photo" id="myFile"> 
	        						  	 
	        						   <input type="hidden" name="photo_mem_no" value="${memberVO.mem_no}">	 
	          						   <input type="hidden" name="action" value="change_photo">
	          						   <p>
       										<img id="image" width="200">
   									   </p>	
   									  
	          						   <input type="submit" class="btn btn-default" value="確定" >
	          						   
          					</form>	
          					
	
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
	
	<table border='1' bordercolor='#CCCCFF' width='1300'  align='center' valign='middle'>

	<tr align='center' valign='middle'>
		
		<td><p>生日</p><%=memberVO.getMem_birthday()%></td>
	</tr>
	<tr align='center' valign='middle'>
		<td><p>性別</p><%=memberVO.getMem_sex()%></td>
	</tr>
	<tr align='center' valign='middle'>
		<td>電子郵件</p><%=memberVO.getMem_email()%></td>
	</tr>
	<tr align='center' valign='middle'>
		<td>專長</p><%=memberVO.getMem_skill()%></td>
	</tr>
	<tr align='center' valign='middle'>
		<td>菜色喜好</p><%=memberVO.getMem_hobby()%></td>	
	</tr>
	<tr align='center' valign='middle'>
		<td>感情狀態</p>
		<c:if test="${memberVO.mem_relationship==0}">
		未婚
		</c:if>
		<c:if test="${memberVO.mem_relationship==1}">
		已婚
		</c:if>
		</td>	
	</tr>
	<tr align='center' valign='middle'>
		<td>關於我</p><%=memberVO.getMem_intro()%></td>	
	</tr>

	
	
	<tr align='center' valign='middle'>

</table>


	<!--about-end-->
	<!--slide-starts-->
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
	<!--slide-end-->
<!--============ footer ============-->
<div id="footer" style="width: 100%; height: 455px;">
	<iFrame style="width:100%; height: 455px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/footer.jsp" scrolling="no"> </iFrame>
</div>
<!--============ footer  ============-->
	
</body>

</html>